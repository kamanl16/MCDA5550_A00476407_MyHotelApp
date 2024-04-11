package com.example.myhotelapp.model;

import java.math.BigDecimal;
import java.util.List;

public class Room {
    private Long roomId;

    private RoomType type;

    private BigDecimal pricePerNight;

    public Room(Long roomId, RoomType type, BigDecimal pricePerNight) {
        this.roomId = roomId;
        this.type = type;
        this.pricePerNight = pricePerNight;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getOccupancy() {
        return type != null ? type.getOccupancy() : 0;
    }
}
