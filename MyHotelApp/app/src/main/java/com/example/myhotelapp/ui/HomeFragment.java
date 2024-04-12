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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhotelapp.R;
import com.example.myhotelapp.adapter.DineListAdapter;
import com.example.myhotelapp.model.DineImage;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class HomeFragment extends Fragment {
    private List<DineImage> imageDataList;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        imageDataList = new ArrayList<>();

        // Populate the list with ImageData objects
        imageDataList.add(new DineImage(R.drawable.dine1, getString(R.string.name_restaurant1), getString(R.string.name_restaurant1_cusine)));
        imageDataList.add(new DineImage(R.drawable.dine2, getString(R.string.name_restaurant2), getString(R.string.name_restaurant2_cusine)));

        RecyclerView recyclerView = view.findViewById(R.id.dine_list_recycler_view);

        recyclerView.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
        DineListAdapter dineListAdapter = new DineListAdapter(getActivity(), imageDataList);
        recyclerView.setAdapter(dineListAdapter);
    }
}
