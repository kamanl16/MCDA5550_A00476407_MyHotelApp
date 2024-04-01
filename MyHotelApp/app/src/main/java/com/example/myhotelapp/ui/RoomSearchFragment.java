package com.example.myhotelapp.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.myhotelapp.R;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RoomSearchFragment extends Fragment {
    private View view;
    private ConstraintLayout mainLayout;
    private TextView titleTextView;
    private TextInputEditText guestsCountEditText;
    private TextInputEditText textInputCheckin, textInputCheckout;
    private TextInputLayout textInputLayoutCheckin, textInputLayoutCheckout;
    private String checkInDate, checkOutDate, numberOfGuests;
    private Button searchButton;
    private SimpleDateFormat dateFormat;

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

        titleTextView = view.findViewById(R.id.title_text_view);
        titleTextView.setText(R.string.title_welcome);

        textInputCheckin = view.findViewById(R.id.text_input_checkin);
        textInputCheckin.setShowSoftInputOnFocus(false);
        textInputCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerdialog();
            }
        });

        textInputCheckout = view.findViewById(R.id.text_input_checkout);
        textInputCheckout.setShowSoftInputOnFocus(false);
        textInputCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerdialog();
            }
        });

        guestsCountEditText = view.findViewById(R.id.text_input_guest);

        searchButton = view.findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("log", "This is the on click listener");
                checkInDate = textInputCheckin.getText().toString();
                checkOutDate = textInputCheckout.getText().toString();
                numberOfGuests = guestsCountEditText.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("check in date", checkInDate);
                bundle.putString("check out date", checkOutDate);
                bundle.putString("number of guests", numberOfGuests);

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
        // Creating a MaterialDatePicker builder for selecting a date range
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("Select a date range");

        // Building the date picker dialog
        MaterialDatePicker<Pair<Long, Long>> datePicker = builder.build();
        datePicker.addOnPositiveButtonClickListener(selection -> {

            // Retrieving the selected start and end dates
            Long startDate = selection.first;
            Long endDate = selection.second;

            // Formatting the selected dates as strings
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String startDateString = sdf.format(new Date(startDate));
            String endDateString = sdf.format(new Date(endDate));

            // Creating the date range string
            String selectedDateRange = startDateString + " - " + endDateString;

            // Displaying the selected date range in the TextView
            textInputCheckin.setText(startDateString);
            textInputCheckout.setText(endDateString);
        });

        // Showing the date picker dialog
        datePicker.show(getChildFragmentManager(), "DATE_PICKER");
    }

}
