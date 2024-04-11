package com.assign.SpringBootApp.model;

import java.util.List;

public class ReservationDTO {
    private List<Guest> guests;
    private Reservation reservation;

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
