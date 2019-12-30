package com.springboot.fun.controller.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.springboot.fun.entity.SysOssEntity;
import com.springboot.fun.service.AppletLoginService;
import com.springboot.fun.service.SysConfigService;
import com.springboot.fun.util.JsonResult;
import com.springboot.fun.util.UuidUtils;
import com.springboot.fun.util.oss.CloudStorageConfig;
import com.springboot.fun.util.oss.OSSFactory;
import com.springboot.fun.util.oss.RandomImgName;
import com.springboot.fun.util.oss.validator.ValidatorUtils;
import com.springboot.fun.util.oss.validator.group.AliyunGroup;
import com.springboot.fun.util.oss.validator.group.LocalGroup;
import com.springboot.fun.util.oss.validator.group.QcloudGroup;
import com.springboot.fun.util.oss.validator.group.QiniuGroup;
import com.springboot.fun.util.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.Console;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 文件上传
 *
 * @author tiankong
 * @email 2366207000@qq.com
 * @date 2017-03-25 12:13:26
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("img/oss")
public class SysOssController {
    @Autowired
    private SysConfigService sysConfigService;
    private AppletLoginService appletLoginService;

    private final static String KEY = ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;

    public SysOssController(AppletLoginService appletLoginService) {
        this.appletLoginService = appletLoginService;
    }


    /**
     * 云存储配置信息
     */
    @RequestMapping("/config")
    @RequiresPermissions("sys:oss:all")
    public R config() {
        CloudStorageConfig config = sysConfigService.getConfigObject(KEY, CloudStorageConfig.class);

        return R.ok().put("config", config);
    }


    /**
     * 保存云存储配置信息
     */
    @RequestMapping("/saveConfig")
    @RequiresPermissions("sys:oss:all")
    public R saveConfig(@RequestBody CloudStorageConfig config) {
        //校验类型
        ValidatorUtils.validateEntity(config);

        if (config.getType() == Constant.CloudService.QINIU.getValue()) {
            //校验七牛数据
            ValidatorUtils.validateEntity(config, QiniuGroup.class);
        } else if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
            //校验阿里云数据
            ValidatorUtils.validateEntity(config, AliyunGroup.class);
        } else if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
            //校验腾讯云数据
            ValidatorUtils.validateEntity(config, QcloudGroup.class);
        }else{
            ValidatorUtils.validateEntity(config, LocalGroup.class);
        }


        sysConfigService.updateValueByKey(KEY, JSON.toJSONString(config));

        return R.ok();
    }


    /**
     * 上传文件
     */
    @RequestMapping("/upload")
    @RequiresPermissions("sys:oss:all")
    public R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        System.out.println("1111111111111");
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        String modulePrefix=request.getParameter("prefix");
        System.out.println(modulePrefix+"222222");
        //上传文件
        String url = OSSFactory.build().upload(file,modulePrefix);
        System.out.println(url+"33333333");
        //保存文件信息
        SysOssEntity ossEntity = new SysOssEntity();
        ossEntity.setUrl(url);
        ossEntity.setCreateDate(new Date());
        /*sysOssService.save(ossEntity);*/
        String id = UuidUtils.creatUUID();
        /*存图片oss*/
        appletLoginService.insertImg(url);
        R r = new R();
        r.put("url", url);
        r.put("link", url);
        return r;
    }
    //上传图片到数据库
    @RequestMapping(value = "/fileUploadImg.fun", method = RequestMethod.POST)
    @RequiresPermissions("sys:oss:all")
    @ResponseBody
    public Object fileUploadUserImg(@RequestBody MultipartFile file) throws Exception {
        InputStream inputStream = null;
        String imgJson = "";
            @SuppressWarnings("static-access")
            String modulePrefix;
            modulePrefix = new RandomImgName().getOrderIdByUUId() + ".png";
           String  url = OSSFactory.build().upload(file,modulePrefix);
           /* pathname = "/www/wwwroot/api.a7c8.com/img/" + originalFilename;
            FileUtils.copyInputStreamToFile(inputStream, new File(pathname));
            System.out.println(pathname);
            String url = "https://api.a7c8.com/img/" + originalFilename;*/
            //会员头像保存到数据库
            appletLoginService.insertImg(url);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("imgUrl", url);
        imgJson = JSON.toJSONString(map);
        return new JsonResult(imgJson);
    }
    /*返回Oss到前段*/
    @RequestMapping(value = "/findOss.fun")
    @ResponseBody
    public Object findOss() {

        String accessId = "LTAInPVSejpRXy31"; // 请填写您的AccessKeyId。
        String accessKey = "8TZ5Z6DiUJCTkRFkxZeMeUQe8KvB4M"; // 请填写您的AccessKeySecret。
        String endpoint = "oss-cn-beijing.aliyuncs.com"; // 请填写您的 endpoint。
        String bucket = "youlequ"; // 请填写您的 bucketname 。
        String host = "https://" + bucket + "." + endpoint; // host的格式为 bucketname.endpoint
        // callbackUrl为 上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
        String callbackUrl = "https://youlequ.oss-cn-beijing.aliyuncs.com";
        String dir = "user-dir-imgs/"; // 用户上传文件时指定的前缀。
        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
        try {
            long expireTime = 30000;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            System.out.println("expireEndTime"+expireEndTime);
             Date expiration = new  Date(expireEndTime);
            System.out.println("expiration"+expiration);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);
            System.out.println("77777"+encodedPolicy);
            System.out.println("888888"+postPolicy);
            System.out.println("9999999"+postSignature);
            Map<String, String> respMap = new LinkedHashMap<String, String>();
            respMap.put("accessid", accessId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            /*respMap.put("expire", formatISO8601Date(expiration));*/
            System.out.println(String.valueOf(expireEndTime / 1000)+"LLLLLLLLLLLLLLLLLLLll");
            net.sf.json.JSONObject jasonCallback = new net.sf.json.JSONObject();
            jasonCallback.put("callbackUrl", callbackUrl);
            jasonCallback.put("callbackBody",
                    "filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
            jasonCallback.put("callbackBodyType", "application/x-www-form-urlencoded");
            String base64CallbackBody = BinaryUtil.toBase64String(jasonCallback.toString().getBytes());
            respMap.put("callback", base64CallbackBody);

            net.sf.json.JSONObject ja1 = net.sf.json.JSONObject.fromObject(respMap);
            System.out.println("HHHHHHHHHHHHHHHHHHHHH" + ja1);
            return new JsonResult(respMap);
        } catch (Exception e) {
            // Assert.fail(e.getMessage());
        }
        return null;
    }
    private static String formatISO8601Date(Date date) {
        final String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.CHINA);
        dateFormat.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return dateFormat.format(date);
    }





    static final String endpoint = "http://youlequ.oss-cn-beijing.aliyuncs.com";
    static final String accessId = "LTAInPVSejpRXy31";
    static final String accessKey = "8TZ5Z6DiUJCTkRFkxZeMeUQe8KvB4M";

    static final String bucketName = "post-test";
    @RequestMapping(value = "/findOss2.fun")
    @ResponseBody
    public static Map<String, String> getPostPolicy(String user_id){

        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
        try {
            Date expiration = new Date(new Date().getTime() + 2900 * 1000);
            System.out.println("expiration"+expiration);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, "user-dir");

            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);
            System.out.println("888888"+policyConds+postPolicy+postSignature);
            Map<String, String> respMap = new LinkedHashMap<String, String>();
            respMap.put("accessid", accessId);
            respMap.put("host", endpoint);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("expire", formatISO8601Date(expiration));
            respMap.put("dir", "user-dir");
            System.out.println(respMap.size());

            return respMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
