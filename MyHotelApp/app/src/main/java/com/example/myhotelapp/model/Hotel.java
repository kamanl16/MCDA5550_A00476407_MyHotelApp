package com.example.myhotelapp.model;

public class Hotel {
    private String hotelName;
    private int ranking;
    private String location;
    private String availability;

    public Hotel(String hotelName, int price, String location, String availability) {
        this.hotelName = hotelName;
        this.ranking = price;
        this.location = location;
        this.availability = availability;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getPrice() {
        return ranking;
    }

    public void setPrice(int price) {
        this.ranking = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
