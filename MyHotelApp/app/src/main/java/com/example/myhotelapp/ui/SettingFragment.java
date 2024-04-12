package com.example.myhotelapp.ui;

import static androidx.core.app.ActivityCompat.recreate;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.LocaleList;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myhotelapp.R;
import com.example.myhotelapp.model.Reservation;
import com.example.myhotelapp.model.Room;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class SettingFragment extends Fragment {
    private View view;
    private Room room;
    private Reservation reservation;
    private String checkInDate, checkOutDate, totalPrice;
    private int numberOfGuests;
    private TextView stayDateTextView, roomTypeTextView, chargeTotalTextView, statusTextView, resIdTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.setting_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize UI elements
        RadioButton englishRadioButton = view.findViewById(R.id.english_radio_button);
        RadioButton traditionalChineseRadioButton = view.findViewById(R.id.traditional_chinese_radio_button);

        // Set click listeners to handle language selection
        englishRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("en");
            }
        });
        traditionalChineseRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("zh");
            }
        });
    }

    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        LocaleList localeList = new LocaleList(locale);
        LocaleList.setDefault(localeList);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocales(localeList);

        Context updatedContext = getContext().createConfigurationContext(configuration);

        // Detach the fragment
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.detach(this).commitNow();

        // Reattach the fragment
        transaction = getParentFragmentManager().beginTransaction();
        transaction.attach(this).commitNow();

        // Refresh UI to apply the selected language
        getActivity().recreate(); // Restart activity to apply language changes
    }
}
