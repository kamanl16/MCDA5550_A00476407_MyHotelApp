package com.assign.SpringBootApp.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", referencedColumnName = "type_id")
    private RoomType type;

    @Column(name = "price_per_night", nullable = false)
    private BigDecimal pricePerNight;

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
