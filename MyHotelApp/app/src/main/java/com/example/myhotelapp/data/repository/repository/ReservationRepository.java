package com.example.myhotelapp.data.repository.repository;

import com.example.myhotelapp.model.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository {
    List<Reservation> findOverlappingReservations(LocalDate checkInDate, LocalDate checkOutDate);

}
