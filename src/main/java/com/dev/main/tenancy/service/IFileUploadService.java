package com.dev.main.tenancy.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

public interface IFileUploadService {

    String uploadCover(MultipartFile multipartFile);

    FileInputStream getImageFile(String imagePath) throws IOException;
}
