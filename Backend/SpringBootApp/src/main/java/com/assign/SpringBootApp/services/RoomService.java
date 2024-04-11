package com.assign.SpringBootApp.services;

import com.assign.SpringBootApp.model.*;
import com.assign.SpringBootApp.repository.ImageRepository;
import com.assign.SpringBootApp.repository.ReservationRepository;
import com.assign.SpringBootApp.repository.RoomRepository;
import com.assign.SpringBootApp.util.Constants;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ImageRepository imageRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Transactional
    public List<RoomDTO> findAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate, int numberOfGuests) {
        List<Reservation> overlappingReservations = reservationRepository.findOverlappingReservations(checkInDate, checkOutDate);

        List<Room> allRooms = roomRepository.findAll(Sort.by(Sort.Direction.ASC, "pricePerNight"));

        List<RoomDTO> roomsDTO = new ArrayList<>();
        HashSet<String> roomtypePrice = new HashSet<String>();
        for (Room room : allRooms) {
            boolean isAvailable = true;
            for (Reservation reservation : overlappingReservations) {
                if (room.getRoomId().equals(reservation.getRoomId())) {
                    isAvailable = false;
                    break;
                }
            }
            if (isAvailable && room.getType().getOccupancy() >= numberOfGuests && !roomtypePrice.contains(room.getType().getTypeId() + "-" + room.getPricePerNight())) {
                roomtypePrice.add(room.getType().getTypeId() + "-" + room.getPricePerNight());
                List<ImageData> imageList = imageRepository.findByCatTypeAndCatId(1, room.getType().getTypeId());
                roomsDTO.add(new RoomDTO(room, imageList));
            }
        }

        return roomsDTO;
    }
}
