package com.assign.SpringBootApp.repository;

import com.assign.SpringBootApp.model.ReservationGuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationGuestRepository extends JpaRepository<ReservationGuest, Long> {
    List<ReservationGuest> findByReservationReservationId(Long reservationId);
}