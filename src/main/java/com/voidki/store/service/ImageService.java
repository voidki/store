package com.voidki.store.service;

import com.voidki.store.domain.ResponseResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface ImageService {
    ResponseResult load(MultipartFile multipartFile, HttpServletRequest request);
}
