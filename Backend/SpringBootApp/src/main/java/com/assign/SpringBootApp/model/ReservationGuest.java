package com.assign.SpringBootApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ReservationGuests")
public class ReservationGuest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "guest_id")
    private Guest guest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
