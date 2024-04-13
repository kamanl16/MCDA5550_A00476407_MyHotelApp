package com.example.myhotelapp.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myhotelapp.R;
import com.example.myhotelapp.model.ReservationDTO;
import com.example.myhotelapp.viewmodel.ReservationViewModel;

import java.text.ParseException;

public class ReservationCheckFragment extends Fragment {
    private View view;
    private TextView reservationIdTextView, errorTextView;
    private ReservationViewModel reservationViewModel;
    private Button searchButton;
    private boolean isIDChecked;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.reservation_search_fragment, container, false);
        reservationViewModel = new ViewModelProvider(this).get(ReservationViewModel.class);
        reservationViewModel.init();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        errorTextView = view.findViewById(R.id.error_text_view);
        reservationIdTextView = view.findViewById(R.id.text_input_resrvation_id);
        reservationIdTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // Check if the input is a valid number
                if (!s.toString().isEmpty()) {
                    try {
                        int numGuests = Integer.parseInt(s.toString());
                        if (numGuests <= 0) {
                            errorTextView.setText("Please enter a valid reservation ID");
                            isIDChecked = false;
                        } else {
                            errorTextView.setText(null);
                            isIDChecked = true;
                        }
                    } catch (NumberFormatException e) {
                        errorTextView.setText("Please enter a valid reservation ID");
                        isIDChecked = false;
                    }
                }
            }
        });
        searchButton = view.findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (isIDChecked) {
                        reservationViewModel.getReservationById(reservationIdTextView.getText().toString()).observe(getViewLifecycleOwner(), new Observer<ReservationDTO>() {
                            @Override
                            public void onChanged(ReservationDTO reservation) {
                                if (reservation != null) {
                                    goToResultFragment(reservation);
                                } else {
                                    Toast.makeText(getActivity(), "No records are found.\nPlease try another ID.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        if (errorTextView.getText().toString() == null || errorTextView.getText().toString().trim().isEmpty())
                            errorTextView.setText("Please enter reservation id");
                    }
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }

    private void goToResultFragment(ReservationDTO reservation) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("reservationDTO", reservation);

        ReservationRecordFragment reservationRecordFragment = new ReservationRecordFragment();
        reservationRecordFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.remove(ReservationCheckFragment.this);
        fragmentTransaction.replace(R.id.frame_layout, reservationRecordFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
