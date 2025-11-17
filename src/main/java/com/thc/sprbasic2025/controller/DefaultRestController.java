package com.thc.sprbasic2025.controller;

import com.thc.sprbasic2025.util.FileUpload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/api/default")
@RestController
public class DefaultRestController {

    @PostMapping("/uploadFile")
    public String uploadFile(MultipartFile file) throws IOException {
        return FileUpload.upload(file);
    }

}
