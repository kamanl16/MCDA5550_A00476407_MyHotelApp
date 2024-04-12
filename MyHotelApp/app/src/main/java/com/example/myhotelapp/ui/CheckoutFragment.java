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
import com.example.myhotelapp.model.ReservationDTO;
import com.example.myhotelapp.model.Room;
import com.example.myhotelapp.model.RoomDTO;
import com.example.myhotelapp.viewmodel.ReservationViewModel;
import com.example.myhotelapp.viewmodel.RoomViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class CheckoutFragment extends Fragment {
    private View view;
    private Room room;
    private String checkInDate, checkOutDate;
    private int numberOfGuests;
    private TextView stayDateTextView, roomTypeTextView, chargeRoomTitleTextView, chargeRoomTextView, chargeTaxTextView, chargeTotalTextView;
    private RoomViewModel roomViewModel;
    private LinearLayout guestInfoLayout;
    private Button submitButton;
    private BigDecimal totalPrice;
    private ReservationViewModel reservationViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.checkout_fragment, container, false);
        reservationViewModel = new ViewModelProvider(this).get(ReservationViewModel.class);
        reservationViewModel.init();

        Bundle arguments = getArguments();
        if (arguments != null) {
            // Retrieve the RoomType object from the arguments Bundle
            room = arguments.getParcelable("room");
            if (room != null) {
                // Do something with the RoomType object
            }
            checkInDate = arguments.getString("checkInDate");
            checkOutDate = arguments.getString("checkOutDate");
            numberOfGuests = arguments.getInt("numberOfGuests");
        }

        guestInfoLayout = view.findViewById(R.id.guest_info_layout);

        // Add input fields dynamically
        for (int i = 0; i < numberOfGuests; i++) {
            View guestView = inflater.inflate(R.layout.guest_info_layout, guestInfoLayout, false);
            guestInfoLayout.addView(guestView);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        stayDateTextView = view.findViewById(R.id.stay_date_text_view);
        roomTypeTextView = view.findViewById(R.id.room_type_text_view);
        chargeRoomTitleTextView = view.findViewById(R.id.charge_room_title_text_view);
        chargeRoomTextView = view.findViewById(R.id.charge_room_text_view);
        chargeTaxTextView = view.findViewById(R.id.charge_tax_text_view);
        chargeTotalTextView = view.findViewById(R.id.charge_total_text_view);

        // Calculate the difference in days
        long daysDifference = ChronoUnit.DAYS.between(LocalDate.parse(checkInDate), LocalDate.parse(checkOutDate));

        stayDateTextView.setText(checkInDate + " to " + checkOutDate + " (" + daysDifference + " Night(s))");
        roomTypeTextView.setText(room.getType().getTypeName() + ", " + room.getType().getBedType() + ", " + room.getType().getView());
        chargeRoomTitleTextView.setText(daysDifference + " Night(s)");
        BigDecimal roomAmount = room.getPricePerNight().multiply(BigDecimal.valueOf(daysDifference));
        chargeRoomTextView.setText("CAD " + roomAmount);
        BigDecimal tax = roomAmount.multiply(new BigDecimal("0.15"));
        chargeTaxTextView.setText("CAD " + tax.setScale(2, RoundingMode.HALF_UP));
        totalPrice = roomAmount.add(tax).setScale(2, RoundingMode.HALF_UP);
        chargeTotalTextView.setText("CAD " + totalPrice);

        submitButton = view.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    reservationViewModel.addReservation(room.getRoomId(), checkInDate, checkOutDate, totalPrice, getGuestInfo()).observe(getViewLifecycleOwner(), new Observer<Reservation>() {
                        @Override
                        public void onChanged(Reservation reservation) {
                            if (reservation != null) {
                                goToResultFragment(reservation);
                            } else {
                                Toast.makeText(getActivity(), "Failed to fetch room data", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }

    private List<Guest> getGuestInfo() {
        List<Guest> guests = new ArrayList<>();
        for (int i = 0; i < guestInfoLayout.getChildCount(); i++) {
            View guestView = guestInfoLayout.getChildAt(i);
            TextInputEditText nameEditText = guestView.findViewById(R.id.nameEditText);
            TextInputEditText emailEditText = guestView.findViewById(R.id.emailEditText);
            TextInputEditText phoneEditText = guestView.findViewById(R.id.phoneEditText);

            String name = nameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String phone = phoneEditText.getText().toString();

            // Create a Guest object with the input values
            Guest guest = new Guest(name, email, phone);
            guests.add(guest);
        }
        return guests;
    }

    private void goToResultFragment(Reservation reservation) {
        Bundle bundle = new Bundle();
        ReservationDTO resDTO = new ReservationDTO(getGuestInfo(), reservation, room);
        bundle.putParcelable("reservationDTO", resDTO);

        ReservationRecordFragment reservationRecordFragment = new ReservationRecordFragment();
        reservationRecordFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.remove(CheckoutFragment.this);
        fragmentTransaction.replace(R.id.frame_layout, reservationRecordFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
