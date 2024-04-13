package com.example.myhotelapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.myhotelapp.R;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class RoomSearchFragment extends Fragment {
    private View view;
    private ConstraintLayout mainLayout;
    private TextView dayOfWeekCheckinTextView, monthYearCheckinTextView, dayOfWeekCheckoutTextView, monthYearCheckoutTextView, errorTextView, errorGuestTextView;
    private TextInputEditText guestsCountEditText;
    private TextInputEditText textInputCheckin, textInputCheckout;
    private TextInputLayout textInputLayoutCheckin, textInputLayoutCheckout, textInputLayoutGuest;
    private String checkInDate, checkOutDate;
    private int numberOfGuests;
    private Button searchButton;
    private SimpleDateFormat dateFormat;
    private boolean isGuestNoOK = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.room_search_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainLayout = view.findViewById(R.id.main_layout);
        textInputLayoutCheckin = view.findViewById(R.id.text_input_layout_checkin);
        textInputLayoutCheckout = view.findViewById(R.id.text_input_layout_checkout);
        textInputLayoutGuest = view.findViewById(R.id.text_input_layout_guest);

        errorTextView = view.findViewById(R.id.error_text_view);
        errorGuestTextView = view.findViewById(R.id.error_guest_text_view);

        dayOfWeekCheckinTextView = view.findViewById(R.id.text_day_of_the_week_checkin);
        monthYearCheckinTextView = view.findViewById(R.id.text_month_year_checkin);
        dayOfWeekCheckoutTextView = view.findViewById(R.id.text_day_of_the_week_checkout);
        monthYearCheckoutTextView = view.findViewById(R.id.text_month_year_checkout);

        textInputCheckin = view.findViewById(R.id.text_input_checkin);
        textInputCheckin.setOnTouchListener((v, event) -> {
            v.onTouchEvent(event);
            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {   // another option would be ACTION_DOWN for example
                DatePickerdialog();
            }
            return true;
        });

        textInputCheckout = view.findViewById(R.id.text_input_checkout);
        textInputCheckout.setOnTouchListener((v, event) -> {
            v.onTouchEvent(event);
            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {   // another option would be ACTION_DOWN for example
                DatePickerdialog();
            }
            return true;
        });

        guestsCountEditText = view.findViewById(R.id.text_input_guest);
        guestsCountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Not needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Check if the input is a valid number
                if (!s.toString().isEmpty()) {
                    try {
                        int numGuests = Integer.parseInt(s.toString());
                        if (numGuests <= 0) {
                            errorGuestTextView.setText("Number of guests must be greater than 0");
                            isGuestNoOK = false;
                        } else {
                            errorGuestTextView.setText(null);
                            numberOfGuests = numGuests;
                            isGuestNoOK = true;
                        }
                    } catch (NumberFormatException e) {
                        errorGuestTextView.setText("Please enter a valid number");
                        isGuestNoOK = false;
                    }
                }
            }
        });

        searchButton = view.findViewById(R.id.search_button);
        searchButton.setOnClickListener(v -> {
            Log.i("log", "This is the on click listener");
            if (checkInDate == null || checkOutDate == null) {
                errorTextView.setVisibility(View.VISIBLE);
            } else if (!isGuestNoOK) {
                errorGuestTextView.setText("Number of guests must be greater than 0");
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("check in date", checkInDate);
                bundle.putString("check out date", checkOutDate);
                bundle.putInt("number of guests", numberOfGuests);

                RoomsListFragment roomsListFragment = new RoomsListFragment();
                roomsListFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();//getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, roomsListFragment);
                fragmentTransaction.remove(RoomSearchFragment.this);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    private void DatePickerdialog() {
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        constraintsBuilder.setValidator(DateValidatorPointForward.now());

        // Creating a MaterialDatePicker builder for selecting a date range
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("Select a date range");

        // Building the date picker dialog
        MaterialDatePicker<Pair<Long, Long>> datePicker = builder.setCalendarConstraints(constraintsBuilder.build()).build();
        datePicker.addOnPositiveButtonClickListener(selection -> {
            TimeZone UTCTimeZone = TimeZone.getTimeZone("UTC");

            // Retrieving the selected start and end dates
            Date startDate = new Date(selection.first);
            Date endDate = new Date(selection.second);

            // Formatting the selected dates as strings
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setTimeZone(UTCTimeZone);
            checkInDate = sdf.format(startDate);
            checkOutDate = sdf.format(endDate);

            SimpleDateFormat sdf_month = new SimpleDateFormat("MMM yyyy");
            sdf_month.setTimeZone(UTCTimeZone);

            SimpleDateFormat sdf_day = new SimpleDateFormat("EEE", Locale.ENGLISH);
            sdf_day.setTimeZone(UTCTimeZone);

            SimpleDateFormat sdf_date = new SimpleDateFormat("dd", Locale.ENGLISH);
            sdf_date.setTimeZone(UTCTimeZone);

            // Displaying the selected date range in the TextView
            dayOfWeekCheckinTextView.setText((sdf_day.format(startDate)).toUpperCase());
            textInputCheckin.setText(sdf_date.format(startDate));
            monthYearCheckinTextView.setText(sdf_month.format(startDate));
            dayOfWeekCheckoutTextView.setText((sdf_day.format(endDate)).toUpperCase());
            textInputCheckout.setText(sdf_date.format(endDate));
            monthYearCheckoutTextView.setText(sdf_month.format(endDate));

            errorTextView.setVisibility(View.INVISIBLE);
        });

        // Showing the date picker dialog
        datePicker.show(getChildFragmentManager(), "DATE_PICKER");
    }
}
