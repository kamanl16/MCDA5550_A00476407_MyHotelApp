package com.example.myhotelapp.data.repository.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myhotelapp.data.api.ApiClient;
import com.example.myhotelapp.model.Reservation;
import com.example.myhotelapp.model.ReservationDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationRepository {
    private static ReservationRepository instance;
    private MutableLiveData<Reservation> reservationLiveData = new MutableLiveData<>();

    public static ReservationRepository getInstance() {
        if (instance == null) {
            instance = new ReservationRepository();
        }
        return instance;
    }

    public LiveData<Reservation> addReservation(ReservationDTO reservationDTO) {
        ApiClient.getApiService().addReservation(reservationDTO).enqueue(new Callback<Reservation>() {
            @Override
            public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                try {
                    if (response.isSuccessful() || response.code() == 201) {
                        reservationLiveData.setValue(response.body());
                    } else {
                        reservationLiveData.setValue(null);
                    }
                } catch (Exception e) {
                    Log.e("onResponse", "Exception: " + e.getMessage());
                    reservationLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Reservation> call, Throwable throwable) {
                    try {
                    Log.e("onFailure", "Throwable: " + throwable.getMessage());
                    reservationLiveData.setValue(null);
                } catch (Exception e) {
                    Log.e("onFailure", "Exception: " + e.getMessage());
                    reservationLiveData.setValue(null);
                }
            }
        });
        return reservationLiveData;
    }
}