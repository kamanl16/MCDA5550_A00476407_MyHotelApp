package com.example.myhotelapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class ReservationDTO implements Parcelable {
    private List<Guest> guests;
    private Reservation reservation;
    private Room room;

    public ReservationDTO(List<Guest> guests, Reservation reservation) {
        this.guests = guests;
        this.reservation = reservation;
    }

    public ReservationDTO(List<Guest> guests, Reservation reservation, Room room) {
        this.guests = guests;
        this.reservation = reservation;
        this.room = room;
    }

    protected ReservationDTO(Parcel in) {
        guests = in.createTypedArrayList(Guest.CREATOR);
        reservation = in.readParcelable(Reservation.class.getClassLoader());
        room = in.readParcelable(Room.class.getClassLoader());
    }

    public static final Creator<ReservationDTO> CREATOR = new Creator<ReservationDTO>() {
        @Override
        public ReservationDTO createFromParcel(Parcel in) {
            return new ReservationDTO(in);
        }

        @Override
        public ReservationDTO[] newArray(int size) {
            return new ReservationDTO[size];
        }
    };

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeTypedList(guests);
        dest.writeParcelable(reservation, flags);
        dest.writeParcelable(room, flags);
    }
}
