package com.assign.SpringBootApp.repository;

import com.assign.SpringBootApp.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r " +
            "WHERE (r.checkInDate < :checkOutDate AND r.checkOutDate > :checkInDate) " +
            "OR (r.checkInDate >= :checkInDate AND r.checkInDate < :checkOutDate)")
    List<Reservation> findOverlappingReservations(@Param("checkInDate") LocalDate checkInDate, @Param("checkOutDate") LocalDate checkOutDate);

}
