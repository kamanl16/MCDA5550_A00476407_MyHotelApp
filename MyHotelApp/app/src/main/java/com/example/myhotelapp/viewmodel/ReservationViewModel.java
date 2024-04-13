package com.example.myhotelapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myhotelapp.data.repository.repository.ReservationRepository;
import com.example.myhotelapp.model.Guest;
import com.example.myhotelapp.model.Reservation;
import com.example.myhotelapp.model.ReservationDTO;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ReservationViewModel extends ViewModel {
    private ReservationRepository reservationRepository;

    public void init() {
        reservationRepository = ReservationRepository.getInstance();
    }

    public LiveData<Reservation> addReservation(Long roomId, String checkInDate, String checkOutDate, BigDecimal totalPrice, List<Guest> guestList) throws ParseException {
        Reservation res = new Reservation(roomId, convertToDate(checkInDate), convertToDate(checkOutDate), totalPrice, "CONFIRM");
        ReservationDTO resDTO = new ReservationDTO(guestList, res);
        return reservationRepository.addReservation(resDTO);
    }

    public LiveData<ReservationDTO> getReservationById(String reservationId) throws ParseException {
        Long id = Long.parseLong(reservationId);
        return reservationRepository.getReservationById(id);
    }

    private Date convertToDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse(dateString);
        return new Date(date.getTime());
    }
}