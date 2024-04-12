package com.example.myhotelapp.data.api;

import com.example.myhotelapp.model.Hotel;
import com.example.myhotelapp.model.Reservation;
import com.example.myhotelapp.model.ReservationDTO;
import com.example.myhotelapp.model.Room;
import com.example.myhotelapp.model.RoomDTO;

import java.time.LocalDate;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    // API's endpoints
    @GET("api/rooms/getRooms")
    Call<List<Room>> getAllRooms();

    @GET("api/rooms/getAvailableRooms")
    Call<List<RoomDTO>> getAvailableRooms(@Query("checkInDate") String checkInDate, @Query("checkOutDate") String checkOutDate, @Query("numberOfGuests") int numberOfGuests);

    @POST("api/reservations")
    Call<Reservation> addReservation(@Body ReservationDTO reservationDTO);

    @GET("api/reservations/{id}")
    Call<ReservationDTO> getReservationById(@Path("id") Long reservationId);

    @POST("api/reservations/{id}/remark")
    Call<Reservation> updateReservationRemark(@Path("id") Long reservationId, @Body String remark);
}
