package com.dev.main.tenancy.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface IFileUploadService {

    String uploadCover(MultipartFile multipartFile);

    File getImageFile(String imagePath) throws IOException;
}
