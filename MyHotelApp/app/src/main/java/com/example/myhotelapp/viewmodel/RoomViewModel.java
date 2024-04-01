package com.example.myhotelapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.myhotelapp.model.Room;
import com.example.myhotelapp.data.repository.repository.RoomRepository;

import java.util.List;

public class RoomViewModel extends ViewModel {
    private RoomRepository roomRepository;
    private List<Room> allRooms;

    public RoomViewModel(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
        allRooms = roomRepository.getAllRooms();
    }

    public List<Room> getAllRooms() {
        return allRooms;
    }
}