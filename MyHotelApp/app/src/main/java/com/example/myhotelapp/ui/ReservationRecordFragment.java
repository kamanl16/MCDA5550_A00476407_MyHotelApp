package com.example.myhotelapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myhotelapp.R;
import com.example.myhotelapp.model.Guest;
import com.example.myhotelapp.model.Reservation;
import com.example.myhotelapp.model.Room;
import com.example.myhotelapp.model.RoomDTO;
import com.example.myhotelapp.viewmodel.ReservationViewModel;
import com.example.myhotelapp.viewmodel.RoomViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ReservationRecordFragment extends Fragment {
    private View view;
    private Room room;
    private Reservation reservation;
    private String checkInDate, checkOutDate, totalPrice;
    private int numberOfGuests;
    private TextView stayDateTextView, roomTypeTextView, chargeTotalTextView, statusTextView, resIdTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.reserve_result_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            // Retrieve the RoomType object from the arguments Bundle
            room = arguments.getParcelable("room");
            reservation = arguments.getParcelable("reservation");
            if (room != null) {
                // Do something with the RoomType object
            }
            checkInDate = arguments.getString("checkInDate");
            checkOutDate = arguments.getString("checkOutDate");
            numberOfGuests = arguments.getInt("numberOfGuests");
            totalPrice = arguments.getString("totalPrice");
        }
        stayDateTextView = view.findViewById(R.id.stay_date_text_view);
        roomTypeTextView = view.findViewById(R.id.room_type_text_view);
        chargeTotalTextView = view.findViewById(R.id.charge_total_text_view);
        statusTextView = view.findViewById(R.id.status_text_view);
        resIdTextView = view.findViewById(R.id.res_id_text_view);

        statusTextView.setText(reservation.getStatus());
        resIdTextView.setText(reservation.getReservationId().toString());

        long daysDifference = ChronoUnit.DAYS.between(LocalDate.parse(checkInDate), LocalDate.parse(checkOutDate));

        stayDateTextView.setText(checkInDate + " to " + checkOutDate + " (" + daysDifference + " Night(s))");
        roomTypeTextView.setText(room.getType().getTypeName() + ", " + room.getType().getBedType() + ", " + room.getType().getView());
    }
}
