package com.ds.antddun.controller;

import com.ds.antddun.entity.UploadImage;
import com.ds.antddun.service.ImageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;


@Controller
@Log4j2
public class ImageController {

    @Autowired
    ImageService imageService;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    private ServletContext context;

    @PostMapping("/image")
    public ResponseEntity<?> imageUpload(@RequestParam("file") MultipartFile file) {
        try {
            UploadImage uploadImage = imageService.store(file);
            String path = context.getContextPath();
            return ResponseEntity.ok().body(path + "/image/" + uploadImage.getImgNo());
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/image/{imgNo}")
    public ResponseEntity<?> serveFile(@PathVariable Long imgNo){
        try {
            UploadImage uploadImage = imageService.load(imgNo);
            Resource resource = resourceLoader.getResource("file:" + uploadImage.getFilePath());
            return ResponseEntity.ok().body(resource);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }
}