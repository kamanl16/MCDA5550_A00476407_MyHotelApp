package com.example.myhotelapp.model;

import java.util.List;

public class ReservationDTO {
    private List<Guest> guests;
    private Reservation reservation;

    public ReservationDTO(List<Guest> guests, Reservation reservation) {
        this.guests = guests;
        this.reservation = reservation;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
