package com.dev.main.tenancy.controller;

import com.dev.main.common.util.ResultMap;
import com.dev.main.tenancy.controller.exception.SystemErrorException;
import com.dev.main.tenancy.service.IFileUploadService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@Scope(value="prototype")
@RequestMapping("/api/pic")
public class PictureController {

    @Autowired
    private IFileUploadService fileUploadService;

    @RequestMapping("/upload")
    public ResultMap coverUpload(@RequestParam("file") MultipartFile multipartFile) {
        ResultMap map =new ResultMap();
        try {
            String fileName = fileUploadService.uploadCover(multipartFile);
             map.put("path",fileName);
        } catch (SystemErrorException e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail("由于未知错误，操作失败");
        }
        return map;
    }


    /**
     * 读取服务器中的封面图片
     * @param imagePath
     * @param response
     * @throws IOException
     */
    @RequestMapping("/item")
    public void loadImage(String imagePath,  HttpServletResponse response) throws IOException {

        File file = fileUploadService.getImageFile(imagePath);

        FileInputStream fis = null;

        if (!file.exists()) { // 如果文件不存在,读取备选封面
            String imgPath = "/static/statics/img/tenancy_phone.png";
            String defaultImg = ResourceUtils.getURL("classpath:").getPath() +imgPath;
            file = new File(defaultImg);
        }
        fis = new FileInputStream(file);
        response.setContentType("image/jpg"); // 设置返回的文件类型
        response.setHeader("Access-Control-Allow-Origin", "*");// 设置该图片允许跨域访问
        IOUtils.copy(fis, response.getOutputStream());
    }
}
