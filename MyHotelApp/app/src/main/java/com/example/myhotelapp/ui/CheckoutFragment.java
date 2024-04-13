package com.example.myhotelapp.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.myhotelapp.viewmodel.ReservationViewModel;
import com.example.myhotelapp.viewmodel.RoomViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
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
            TextInputLayout nameInputLayout = guestView.findViewById(R.id.nameInputLayout);
            TextInputEditText nameEditText = guestView.findViewById(R.id.nameEditText);
            TextInputLayout emailInputLayout = guestView.findViewById(R.id.emailInputLayout);
            TextInputEditText emailEditText = guestView.findViewById(R.id.emailEditText);
            TextInputLayout phoneInputLayout = guestView.findViewById(R.id.phoneInputLayout);
            TextInputEditText phoneEditText = guestView.findViewById(R.id.phoneEditText);

            nameInputLayout.setHint(getString(R.string.title_name, (i + 1)));
            emailInputLayout.setHint(getString(R.string.title_email, (i + 1)));
            phoneInputLayout.setHint(getString(R.string.title_phone, (i + 1)));

            nameEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                    // Not used
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                    // Validate name input as user types
                    String name = charSequence.toString();
                    if (name.isEmpty()) {
                        nameInputLayout.setError("Name cannot be empty");
                    } else {
                        nameInputLayout.setError(null); // Clear error message
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    // Not used
                }
            });

            emailEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                    // Not used
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                    // Validate name input as user types
                    String email = emailEditText.getText().toString();
                    if (email.isEmpty()) {
                        emailInputLayout.setError("Email cannot be empty");
                    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        emailInputLayout.setError("Invalid email format");
                    } else {
                        emailInputLayout.setError(null); // Clear error message
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    // Not used
                }
            });
            phoneEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                    // Not used
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                    // Validate name input as user types
                    String phone = phoneEditText.getText().toString();
                    if (phone.isEmpty()) {
                        phoneInputLayout.setError("Phone cannot be empty");
                    } else if (!android.util.Patterns.PHONE.matcher(phone).matches()) {
                        phoneInputLayout.setError("Invalid phone number format");
                    } else {
                        phoneInputLayout.setError(null); // Clear error message
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    // Not used
                }
            });
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
        submitButton.setOnClickListener(v -> {
            try {
                reservationViewModel.addReservation(room.getRoomId(), checkInDate, checkOutDate, totalPrice, getGuestInfo()).observe(getViewLifecycleOwner(), reservation -> {
                    if (reservation != null) {
                        goToResultFragment(reservation);
                    } else {
                        Toast.makeText(getActivity(), "Failed to reserve. \nPlease try again later.", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (ParseException e) {
                throw new RuntimeException(e);
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
