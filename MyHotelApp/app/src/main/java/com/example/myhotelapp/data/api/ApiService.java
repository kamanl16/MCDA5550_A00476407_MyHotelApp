package com.example.myhotelapp.data.api;

import com.example.myhotelapp.model.Hotel;
import com.example.myhotelapp.model.Room;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    // API's endpoints
    @GET("api/rooms/getRooms")
    Call<List<Room>> getAllRooms();
}
