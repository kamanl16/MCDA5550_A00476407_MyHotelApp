package com.example.myhotelapp.data.repository.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myhotelapp.data.api.ApiClient;
import com.example.myhotelapp.model.RoomDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomRepository {
    private static RoomRepository instance;

    public static RoomRepository getInstance(){
        if (instance == null) {
            instance = new RoomRepository();
        }
        return instance;
    }

    public LiveData<List<RoomDTO>> getAvailableRooms(String checkInDate, String checkOutDate, int numberOfGuests) {
        MutableLiveData<List<RoomDTO>> roomsLiveData = new MutableLiveData<>();
        ApiClient.getApiService().getAvailableRooms(checkInDate, checkOutDate, numberOfGuests).enqueue(new Callback<List<RoomDTO>>() {
            @Override
            public void onResponse(@NonNull Call<List<RoomDTO>> call, @NonNull Response<List<RoomDTO>> response) {
                if (response.isSuccessful()) {
                    roomsLiveData.setValue(response.body());
                } else {
                    roomsLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<RoomDTO>> call, @NonNull Throwable t) {
                roomsLiveData.setValue(null);
            }
        });
        return roomsLiveData;
    }
}