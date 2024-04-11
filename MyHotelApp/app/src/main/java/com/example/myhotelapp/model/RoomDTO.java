package com.example.myhotelapp.model;

import java.util.List;

public class RoomDTO {
    private Room room;
    private List<ImageData> images;

    public RoomDTO(Room room, List<ImageData> images) {
        this.room = room;
        this.images = images;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<ImageData> getImages() {
        return images;
    }

    public void setImages(List<ImageData> images) {
        this.images = images;
    }
}
