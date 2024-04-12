package com.assign.SpringBootApp.services;

import com.assign.SpringBootApp.model.Guest;
import com.assign.SpringBootApp.model.ImageData;
import com.assign.SpringBootApp.model.Room;
import com.assign.SpringBootApp.repository.GuestRepository;
import com.assign.SpringBootApp.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public List<ImageData> findAllImages() {
        return imageRepository.findAll();
    }
    public List<ImageData> findByCatTypeAndCatId(int catType, Long catId) {
        return imageRepository.findByCatTypeAndCatId(catType, catId);
    }
}
