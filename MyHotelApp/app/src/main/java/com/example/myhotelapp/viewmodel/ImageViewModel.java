package com.example.myhotelapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myhotelapp.model.Reservation;
import com.example.myhotelapp.model.Room;
import com.example.myhotelapp.data.repository.repository.RoomRepository;
import com.example.myhotelapp.model.RoomDTO;

import java.util.List;

public class RoomViewModel extends ViewModel {
    private RoomRepository roomRepository;

    public void init() {
        roomRepository = RoomRepository.getInstance();
    }
    public LiveData<List<RoomDTO>> getAvailableRooms(String checkInDate, String checkOutDate, int numberOfGuests) {
        return roomRepository.getAvailableRooms(checkInDate, checkOutDate, numberOfGuests);
    }
}