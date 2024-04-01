package com.example.myhotelapp.model;


public class RoomType {

    private Long typeId;


    private String typeName;

    private int occupancy;

    public RoomType(Long typeId, String typeName, int occupancy) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.occupancy = occupancy;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }
}
