package com.assign.SpringBootApp.controller;

import com.assign.SpringBootApp.model.ImageData;
import com.assign.SpringBootApp.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/getAllImages")
    public List<ImageData> getAllImages() {
        return imageService.findAllImages();
    }
    @GetMapping("/getImagesByCat")
    public List<ImageData> findByCatTypeAndCatId(int catType, Long catId) {
        return imageService.findByCatTypeAndCatId(catType, catId);
    }

}
