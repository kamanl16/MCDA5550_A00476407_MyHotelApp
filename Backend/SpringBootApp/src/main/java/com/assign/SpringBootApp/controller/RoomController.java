package com.assign.SpringBootApp.controller;

import com.assign.SpringBootApp.model.Room;
import com.assign.SpringBootApp.model.RoomDTO;
import com.assign.SpringBootApp.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/checkTimezone")
    public String checkTimezone() {
        TimeZone defaultTimeZone = TimeZone.getDefault();
        return "Default Timezone: " + defaultTimeZone.getID();
    }

    @GetMapping("/getRooms")
    public List<Room> getAllHotel() {
        return roomService.getAllRooms();
    }

    @GetMapping("/getAvailableRooms")
    public ResponseEntity<List<RoomDTO>> getAvailableRooms(
            @RequestParam("checkInDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
            @RequestParam("checkOutDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
            @RequestParam("numberOfGuests") int numberOfGuests) {
        List<RoomDTO> availableRooms = roomService.findAvailableRooms(checkInDate, checkOutDate, numberOfGuests);
        return new ResponseEntity<>(availableRooms, HttpStatus.OK);
    }
}
