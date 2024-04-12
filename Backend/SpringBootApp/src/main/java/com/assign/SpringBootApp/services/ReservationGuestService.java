package com.assign.SpringBootApp.services;

import com.assign.SpringBootApp.model.*;
import com.assign.SpringBootApp.repository.GuestRepository;
import com.assign.SpringBootApp.repository.ReservationGuestRepository;
import com.assign.SpringBootApp.repository.ReservationRepository;
import com.assign.SpringBootApp.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationGuestService {

    @Autowired
    private ReservationGuestRepository reservationGuestRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Transactional
    public Reservation addGuestsAndLinkToReservation(List<Guest> guests, Reservation reservation) {
        // Save reservation
        Reservation savedReservation = reservationRepository.save(reservation);

        // Iterate over the list of guests
        for (Guest guest : guests) {
            // Save guest
            guestRepository.save(guest);

            // Create ReservationGuest
            ReservationGuest reservationGuest = new ReservationGuest();
            reservationGuest.setReservation(reservation);
            reservationGuest.setGuest(guest);

            // Save ReservationGuest
            reservationGuestRepository.save(reservationGuest);
        }

        return savedReservation;
    }

    public ReservationDTO getReservationById(Long reservationId) {
        // Retrieve reservation details
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setReservation(reservation);

        // Retrieve guests associated with the reservation
        List<Guest> guests = guestRepository.findByReservationsReservationId(reservationId);
        reservationDTO.setGuests(guests);

        Room room = roomRepository.findById(reservation.getRoomId()).orElse(null);
        reservationDTO.setRoom(room);

        return reservationDTO;
    }

    public List<ReservationGuest> getAllReservationGuest() {
        return reservationGuestRepository.findAll();
    }

    public List<ReservationDTO> getAllReservationsWithGuests() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationDTO> reservationDTOs = new ArrayList<>();

        for (Reservation reservation : reservations) {
            ReservationDTO reservationDTO = new ReservationDTO();
            reservationDTO.setReservation(reservation);

            List<ReservationGuest> reservationGuests = reservationGuestRepository.findByReservationReservationId(reservation.getReservationId());
            List<Guest> guests = new ArrayList<>();

            for (ReservationGuest reservationGuest : reservationGuests) {
                Guest guest = guestRepository.findById(reservationGuest.getGuest().getGuestId()).orElse(null);
                if (guest != null) {
                    guests.add(guest);
                }
            }

            reservationDTO.setGuests(guests);
            reservationDTOs.add(reservationDTO);
        }

        return reservationDTOs;
    }
}