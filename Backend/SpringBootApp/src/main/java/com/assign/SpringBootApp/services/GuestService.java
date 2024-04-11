package com.assign.SpringBootApp.services;

import com.assign.SpringBootApp.model.Guest;
import com.assign.SpringBootApp.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    public Guest addGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    public List<Guest> addGuest(List<Guest> guests) {
        return guestRepository.saveAll(guests);
    }
}
