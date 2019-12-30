package com.springboot.fun.util.oss;

import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.springboot.fun.util.utils.RRException;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 本地存储
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-25 15:41
 */
public class LocalCloudStorageService extends CloudStorageService {
    private UploadManager uploadManager;
    private String token;

    public LocalCloudStorageService(CloudStorageConfig config) {
        this.setConfig(config);

        //初始化
        //init();
    }

    private void init() {
        uploadManager = new UploadManager(new Configuration(Zone.autoZone()));
        token = Auth.create(this.getConfig().getQiniuAccessKey(), this.getConfig().getQiniuSecretKey()).
                uploadToken(this.getConfig().getQiniuBucketName());
    }

    @Override
    public String upload(MultipartFile file, String modulePrefix) throws Exception {
        //String fileName = file.getOriginalFilename();
        //String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
        //return upload(file.getBytes(), getPath(config.getAliyunPrefix()) + "." + prefix);



        if (file!=null) {// 判断上传的文件是否为空
            String path=null;// 文件路径
            String type=null;// 文件类型
            String fileName=file.getOriginalFilename();// 文件原名称
            System.out.println("上传的文件原名称:"+fileName);
            // 判断文件类型
            type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
            if (type!=null) {// 判断文件类型是否为空
                if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())||"APK".equals(type.toUpperCase())||"JPEG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath="D:\\webpicture\\"+this.getConfig().getLocalPrefix()+"\\"+modulePrefix+"\\";
                    File f=new File(realPath);
                    if(!f.exists()){
                        f.mkdirs();
                    }
                    // 自定义的文件名称
                    String trueFileName=String.valueOf(System.currentTimeMillis())+"."+type;
                    // 设置存放图片文件的路径
                    path=realPath+trueFileName;
                    System.out.println("存放图片文件的路径:"+path);
                    // 转存文件到指定的路径
                    file.transferTo(new File(path));
                    System.out.println("文件成功上传到指定目录下");
                    return this.getConfig().getLocalHost()+"/"+this.getConfig().getLocalPrefix()+"/"+modulePrefix+"/"+trueFileName;
                }else {
                    System.out.println("不是我们想要的文件类型,请按要求重新上传");
                    return null;
                }
            }else {
                System.out.println("文件类型为空");
                return null;
            }
        }else {
            System.out.println("没有找到相对应的文件");
            return null;
        }
    }

    @Override
    public String upload(byte[] data, String path) {
        try {
            Response res = uploadManager.put(data, path, token);
            if (!res.isOK()) {
                throw new RuntimeException("上传七牛出错：" + res.toString());
            }
        } catch (Exception e) {
            throw new RRException("上传文件失败，请核对七牛配置信息", e);
        }

        return this.getConfig().getQiniuDomain() + "/" + path;
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            byte[] data = IOUtils.toByteArray(inputStream);
            return this.upload(data, path);
        } catch (IOException e) {
            throw new RRException("上传文件失败", e);
        }
    }
}
