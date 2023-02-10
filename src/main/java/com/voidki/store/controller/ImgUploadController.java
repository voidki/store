package com.voidki.store.controller;

import com.voidki.store.domain.ResponseResult;
import com.voidki.store.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @projectName: store
 * @package: com.voidki.store.controller
 * @className: ImgUploadController
 * @author: voidki
 * @description:
 * @date: 2023/2/4 12:51
 */
@RestController
@RequestMapping("/image")
public class ImgUploadController {
    @Autowired()
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseResult fileLoad(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        return imageService.load(file,request);
    }
}
