package com.ihai.accounting.tax.common.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;

import java.io.*;

public class OssUtil {

    private static final String accessKeyId = "LTAI4Fech2c6rGydQztTyhDZ";
    private static final String accessKeySecret = "inCATiUY3SdAPb3TZpSqR0kS5XotwT";
    private static final String bucketName = "pany2020";


    public static void upload(String fileName, InputStream inputStream) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 上传文件流。
        PutObjectResult result = ossClient.putObject(bucketName, fileName, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
