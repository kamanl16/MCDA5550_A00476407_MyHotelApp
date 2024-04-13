package com.example.myhotelapp.data.repository.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myhotelapp.data.api.ApiClient;
import com.example.myhotelapp.model.ImageData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageRepository {
    private static ImageRepository instance;

    public static ImageRepository getInstance(){
        if (instance == null) {
            instance = new ImageRepository();
        }
        return instance;
    }

    public LiveData<List<ImageData>> getAllImages() {
        MutableLiveData<List<ImageData>> imageData = new MutableLiveData<>();
        ApiClient.getApiService().getAllImages().enqueue(new Callback<List<ImageData>>() {
            @Override
            public void onResponse(Call<List<ImageData>> call, Response<List<ImageData>> response) {
                if (response.isSuccessful()) {
                    imageData.setValue(response.body());
                } else {
                    imageData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<ImageData>> call, Throwable t) {
                imageData.setValue(null);
            }
        });
        return imageData;
    }
}