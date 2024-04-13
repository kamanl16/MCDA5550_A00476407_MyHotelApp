package com.example.myhotelapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myhotelapp.data.repository.repository.ImageRepository;
import com.example.myhotelapp.model.ImageData;

import java.util.List;

public class ImageViewModel extends ViewModel {
    private ImageRepository imageRepository;

    public void init() {
        imageRepository = ImageRepository.getInstance();
    }

    public LiveData<List<ImageData>> getAllImages() {
        return imageRepository.getAllImages();
    }
}