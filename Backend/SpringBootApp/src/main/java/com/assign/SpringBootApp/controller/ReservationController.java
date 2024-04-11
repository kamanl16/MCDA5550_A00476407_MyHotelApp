package com.assign.SpringBootApp.controller;

import com.assign.SpringBootApp.model.Guest;
import com.assign.SpringBootApp.model.Reservation;
import com.assign.SpringBootApp.model.ReservationDTO;
import com.assign.SpringBootApp.model.ReservationGuest;
import com.assign.SpringBootApp.services.GuestService;
import com.assign.SpringBootApp.services.ReservationGuestService;
import com.assign.SpringBootApp.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private GuestService guestService;

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReservationGuestService reservationGuestService;

    @PostMapping()
    public ResponseEntity<Reservation> addReservation(@RequestBody ReservationDTO reservationDTO) {
        try {
            Reservation reservation = reservationGuestService.addGuestsAndLinkToReservation(reservationDTO.getGuests(), reservationDTO.getReservation());

            return new ResponseEntity<>(reservation, HttpStatus.CREATED);
        } catch (Exception ex) {

            // Handle exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/remark")
    public ResponseEntity<Reservation> updateReservationRemark(@PathVariable("id") Long id, @RequestBody String remark) {
        Reservation updatedReservation = reservationService.updateReservationRemark(id, remark);
        return ResponseEntity.ok(updatedReservation);
    }

    // Search reservation by ID
    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long reservationId) {
        try {
            ReservationDTO reservationDTO = reservationGuestService.getReservationById(reservationId);
            if (reservationDTO != null) {
                return new ResponseEntity<>(reservationDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            // Handle exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllReservationDTO")
    public ResponseEntity<List<ReservationDTO>> getAllReservationsWithGuests() {
        try {
            List<ReservationDTO> reservationDTOs = reservationGuestService.getAllReservationsWithGuests();
            return new ResponseEntity<>(reservationDTOs, HttpStatus.OK);
        } catch (Exception ex) {
            // Handle exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllReservation")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        try {
            List<Reservation> reservation = reservationService.getAllReservations();
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } catch (Exception ex) {
            // Handle exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllReservationGuest")
    public List<ReservationGuest> getAllReservationGuest() {
        return reservationGuestService.getAllReservationGuest();
    }
}
