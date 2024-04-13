package com.example.myhotelapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myhotelapp.R;
import com.example.myhotelapp.model.ReservationDTO;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class ReservationRecordFragment extends Fragment {
    private View view;
    private ReservationDTO reservation;
    private String checkInDate, checkOutDate, totalPrice;
    private int numberOfGuests;
    private TextView stayDateTextView, roomTypeTextView, chargeTotalTextView, statusTextView, resIdTextView;
    private LinearLayout guestInfoLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.reservation_result_fragment, container, false);

        Bundle arguments = getArguments();
        if (arguments != null) {
            // Retrieve the RoomType object from the arguments Bundle
            reservation = arguments.getParcelable("reservationDTO");
            if (reservation != null) {
                // Do something with the RoomType object
            }
            checkInDate = convertDateToString(reservation.getReservation().getCheckInDate(), "yyyy-MM-dd");
            checkOutDate = convertDateToString(reservation.getReservation().getCheckOutDate(), "yyyy-MM-dd");
            numberOfGuests = reservation.getGuests().size();
            totalPrice = reservation.getReservation().getTotalPrice().toString();
            DecimalFormat df = new DecimalFormat("#,##0.##");
            String formattedString = df.format(reservation.getReservation().getTotalPrice());
        }
        guestInfoLayout = view.findViewById(R.id.guest_info_layout);

        // Add input fields dynamically
        for (int i = 0; i < numberOfGuests; i++) {
            View guestView = inflater.inflate(R.layout.guest_record_layout, guestInfoLayout, false);
            guestInfoLayout.addView(guestView);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        stayDateTextView = view.findViewById(R.id.stay_date_text_view);
        roomTypeTextView = view.findViewById(R.id.room_type_text_view);
        chargeTotalTextView = view.findViewById(R.id.charge_total_text_view);
        statusTextView = view.findViewById(R.id.status_text_view);
        resIdTextView = view.findViewById(R.id.res_id_text_view);

        statusTextView.setText(reservation.getReservation().getStatus());
        resIdTextView.setText(reservation.getReservation().getReservationId().toString());

        long daysDifference = ChronoUnit.DAYS.between(LocalDate.parse(checkInDate), LocalDate.parse(checkOutDate));


        stayDateTextView.setText(getString(R.string.desc_stay_date, checkInDate, checkOutDate, daysDifference));

        Locale currentLocale = getResources().getConfiguration().locale;
        String localeLanguage = currentLocale.getLanguage();

        if ("zh".equals(localeLanguage)) {
            roomTypeTextView.setText(reservation.getRoom().getType().getTypeName_TC() + ", " + reservation.getRoom().getType().getBedType_TC() + ", " + reservation.getRoom().getType().getView_TC());

        } else {
            roomTypeTextView.setText(reservation.getRoom().getType().getTypeName() + ", " + reservation.getRoom().getType().getBedType() + ", " + reservation.getRoom().getType().getView());
        }
        setGuestInfo();
    }

    private void setGuestInfo() {
        for (int i = 0; i < guestInfoLayout.getChildCount(); i++) {
            View guestView = guestInfoLayout.getChildAt(i);

            TextView nameInputLayout = guestView.findViewById(R.id.name_title_text_view);
            TextView emailInputLayout = guestView.findViewById(R.id.email_title_text_view);
            TextView phoneInputLayout = guestView.findViewById(R.id.phone_title_text_view);

            nameInputLayout.setText(getString(R.string.title_name, (i + 1)));
            emailInputLayout.setText(getString(R.string.title_email, (i + 1)));
            phoneInputLayout.setText(getString(R.string.title_phone, (i + 1)));

            TextView nameTextView = guestView.findViewById(R.id.name_text_view);
            TextView emailTextView = guestView.findViewById(R.id.email_text_view);
            TextView phoneTextView = guestView.findViewById(R.id.phone_text_view);

            nameTextView.setText(reservation.getGuests().get(i).getName());
            emailTextView.setText(reservation.getGuests().get(i).getEmail());
            phoneTextView.setText(reservation.getGuests().get(i).getPhone());
        }
    }

    public String convertDateToString(Date dateToConvert, String formatPattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatPattern, Locale.getDefault());
        return dateFormat.format(dateToConvert);
    }

}
