package com.hth.service;

import com.hth.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService  {
    ResponseResult uploadImg(MultipartFile img) throws IOException;
}
