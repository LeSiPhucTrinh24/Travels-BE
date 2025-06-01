package com.lspt.Travels_BE.service;

import com.lspt.Travels_BE.dto.response.TourImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UploadImageFile {
    String uploadImage(MultipartFile file) throws IOException;
}
