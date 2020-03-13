package com.pinyougou.manager.controller;

import com.pinyougou.util.FastDFSClient;
import entity.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: UploadController
 * @description:
 * @author: XZQ
 * @create: 2020/2/9 13:03
 **/
@RestController
public class UploadController {

    @Value("${FILE_SERVER_URL}")
    private String FILE_SERVER_URL;

    @RequestMapping("/upload")
    public Result upload(MultipartFile file) {
        //1、取文件的扩展名
        String fileName = file.getName();
        String extensionName = fileName.substring(fileName.indexOf(".") + 1);//获取文件的扩展名
        //2、创建一个 FastDFS 的客户端
        try {
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fdfs_client.conf");
            //3、执行上传处理
            String path = fastDFSClient.uploadFile(file.getBytes(), extensionName);
            //4、拼接返回的 url 和 ip 地址，拼装成完整的 url
            String url = FILE_SERVER_URL + path;
            return new Result(true, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(true, "上传失败");
    }
}
