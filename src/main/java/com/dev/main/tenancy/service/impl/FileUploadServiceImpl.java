package com.dev.main.tenancy.service.impl;

import com.dev.main.common.util.CommonUtil;
import com.dev.main.tenancy.controller.exception.SystemErrorException;
import com.dev.main.tenancy.service.IFileUploadService;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class FileUploadServiceImpl implements IFileUploadService {

    private String sys = System.getProperty("os.name");
    private String dir = ""; // 存储路径
    private String LINUX_PATH = "/opt/zuche/images";
    private String WIN_PATH = "D:/zuche/images";
    private String DefaultImgPath = "/d/b/db0506094e3e4260b75cd967d72b4609.jpg";

    @Override
    public String uploadCover(MultipartFile multipartFile) {
        String[] allowTypes = {".jpg", ".jpeg",".gif",".bmp", ".png"};
        String originalFilename = multipartFile.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")); // 获取文件后缀名
        Long size = multipartFile.getSize();
        if (!CommonUtil.targetInStringArray(true, suffix, allowTypes)) { // 检查是否为允许上传的文件格式
            throw new SystemErrorException("上传失败，不支持该类型文件");
        }
        if (1024*1024*3 < size) { //文件大于3M
            throw new SystemErrorException("上传失败，文件大小超过3M");
        }
        String newFileName = CommonUtil.createUUID().toString() + suffix;
        String relativePath = this.getRelativePathByFileName(newFileName); //生成二级目录
        try {
            //获取磁盘路径
            String diskDir = getDiskPath();
            String absPath = diskDir+relativePath; // 绝对路径
            InputStream inputStream = multipartFile.getInputStream();
            File abs = new File(absPath);
            if (!abs.exists()) {
                abs.mkdirs();// 创建目录
            }
            FileOutputStream outputStream = new FileOutputStream(
                    new File(absPath, newFileName));
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new SystemErrorException("服务器异常，上传失败");
        } catch (SystemErrorException e) {
            throw new SystemErrorException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemErrorException("服务器异常，上传失败");
        }
        return relativePath+"/"+newFileName;
    }

    /**
     * 获取服务器系统文件路径，Linux系统'/opt/zuche/images'，Windows系统 'D:/zuche/images'
     * */
    private String getDiskPath() {
        if (StringUtils.containsIgnoreCase(sys, "linux")) { // Linux系统
            return LINUX_PATH;
        } else if (StringUtils.containsIgnoreCase(sys, "windows")) { // Windows系统
            return WIN_PATH;
        } else {
            throw new SystemErrorException("未知服务器类型，操作失败;");
        }
    }

    /**
     * 根据文件名生成二级目录 ，如文件名 hg4s45gh1xc4sd.jpg，返回“/h/g”
     */
    private String getRelativePathByFileName(String fileName) {
        char[] arr = fileName.toCharArray();
        char firstDir = arr[0];
        char secondfDir = arr[1];
        String relativePath = "/";
        relativePath+=firstDir;
        relativePath+="/";
        relativePath+=secondfDir;
        return relativePath;
    }


    /**
     * 获取图片文件
     * @param defaultImg
     * @param imagePath 图片相对路径 */
    @Override
    public FileInputStream getImageFile(String imagePath) throws IOException {
        // 如果文件不存在,读取备选封面
        if(StringUtils.isBlank(imagePath)){
            imagePath = this.DefaultImgPath;
        }


        // 获取磁盘路径
        String diskDir = getDiskPath();
        //文件绝对路径
        String filePath = diskDir + imagePath;

        File file  = new File(filePath);

        if (file == null ||!file.exists()) { // 如果文件不存在,读取备选封面

            String imgPath = this.DefaultImgPath;
            filePath = diskDir + imgPath;
//            String defaultImg = ResourceUtils.getURL("classpath:").getPath() +imgPath;
            file = new File(filePath);
        }
        FileInputStream fis = new FileInputStream(file);
        return fis;
    }
}
