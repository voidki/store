package com.voidki.store.service.impl;

import com.voidki.store.domain.ResponseResult;
import com.voidki.store.enums.AppHttpCodeEnum;
import com.voidki.store.service.ImageService;
import com.voidki.store.utils.PathUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @projectName: store
 * @package: com.voidki.store.service.impl
 * @className: ImageServiceImpl
 * @author: voidki
 * @description:
 * @date: 2023/2/4 12:54
 */
@Service
public class ImageServiceImpl implements ImageService {
    @Value("${file-save-path}")
    private String fileSavePath;
    @Override
    public ResponseResult load(MultipartFile multipartFile, HttpServletRequest request) {
        //获得文件名
        String originalFilename = multipartFile.getOriginalFilename();
        //判断类型
        if(!(originalFilename.endsWith(".jpg") || originalFilename.endsWith(".png"))){
            return ResponseResult.errorResult(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }
        //生成目录
        String filePath = PathUtils.generateFilePath();
        String realFilePath = fileSavePath+filePath;
        File file = new File(realFilePath);
        //创建目录
        if(!file.exists()){
            file.mkdirs();
        }
        //生成名字
        String fileName = PathUtils.generateFileName(originalFilename);
        //存入
        String url = null;
        try {
            multipartFile.transferTo(new File(file,fileName));
            //生成返回给前端的url
            url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +"/images/"+ filePath+fileName;
            //返回URL
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseResult.okResult(url);
    }
}
