package com.assign.SpringBootApp.services;

import com.assign.SpringBootApp.model.*;
import com.assign.SpringBootApp.repository.GuestRepository;
import com.assign.SpringBootApp.repository.ReservationGuestRepository;
import com.assign.SpringBootApp.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation updateReservationRemark(Long id, String remark) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + id));

        reservation.setRemark(remark);
        return reservationRepository.save(reservation);
    }
}