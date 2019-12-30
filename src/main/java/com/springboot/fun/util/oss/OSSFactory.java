package com.springboot.fun.util.oss;

import com.springboot.fun.service.SysConfigService;
import com.springboot.fun.util.utils.ConfigConstant;
import com.springboot.fun.util.utils.Constant;
import com.springboot.fun.util.utils.SpringContextUtils;
import com.springboot.fun.util.utils.SpringContextUtils2;
import org.springframework.stereotype.Component;

/**
 * 文件上传Factory
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-26 10:18
 */
public final class OSSFactory {
    private static SysConfigService sysConfigService=(SysConfigService)SpringContextUtils.getBean(SysConfigService.class);


 /* static {
        System.out.println("99999999999999999999999");
        OSSFactory.sysConfigService = (SysConfigService) SpringContextUtils2.getBean("sysConfigService");
        System.out.println("0000000000000000000000000");
    } */
    public static CloudStorageService build() {
        //获取云存储配置信息
        CloudStorageConfig config = sysConfigService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);
        if (config.getType() == Constant.CloudService.QINIU.getValue()) {
            /*return new QiniuCloudStorageService(config);*/
        } else if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
            return new AliyunCloudStorageService(config);

        } else if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
           /* return new QcloudCloudStorageService(config);*/
        }else if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
            /*return new QcloudCloudStorageService(config);*/
        }else if (config.getType() == Constant.CloudService.LOCAL.getValue()) {
            return new LocalCloudStorageService(config);
        }

        return null;
    }

}
