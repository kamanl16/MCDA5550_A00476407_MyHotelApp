package com.example.myhotelapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhotelapp.R;
import com.example.myhotelapp.adapter.ItemClickListener;
import com.example.myhotelapp.adapter.RoomlListAdapter;
import com.example.myhotelapp.model.RoomDTO;
import com.example.myhotelapp.viewmodel.RoomViewModel;

import java.math.BigDecimal;
import java.util.List;

public class RoomsListFragment extends Fragment implements ItemClickListener {
    TextView headingTextView;
    ProgressBar progressBar;
    List<RoomDTO> userListResponseData;
    private View view;
    private String checkInDate, checkOutDate, numberOfGuests;

    private RoomViewModel roomViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        roomViewModel = new ViewModelProvider(this).get(RoomViewModel.class);
        roomViewModel.init();
        view = inflater.inflate(R.layout.room_list_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //heading text view
        headingTextView = view.findViewById(R.id.heading_text_view);
        progressBar = view.findViewById(R.id.progress_bar);
        checkInDate = getArguments().getString("check in date");
        checkOutDate = getArguments().getString("check out date");
        numberOfGuests = getArguments().getString("number of guests");

        //Set up the header
        headingTextView.setText(getString(R.string.title_search_result, numberOfGuests, checkInDate, checkOutDate));

        progressBar.setVisibility(View.VISIBLE);
        roomViewModel.getAvailableRooms(checkInDate, checkOutDate, Integer.parseInt(numberOfGuests)).observe(getViewLifecycleOwner(), new Observer<List<RoomDTO>>() {
            @Override
            public void onChanged(List<RoomDTO> roomDTOs) {
                progressBar.setVisibility(View.INVISIBLE);
                if (roomDTOs != null) {
                    setupRecyclerView(roomDTOs);
                } else {
                    Toast.makeText(getActivity(), "Failed to fetch room data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupRecyclerView(List<RoomDTO> data) {
        progressBar.setVisibility(View.GONE);
        RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RoomlListAdapter roomlListAdapter = new RoomlListAdapter(getActivity(), data);
        recyclerView.setAdapter(roomlListAdapter);

        //Bind the click listener
        roomlListAdapter.setClickListener(this);
    }


    @Override
    public void onClick(View view, int position) {
        RoomDTO roomData = userListResponseData.get(position);

        String roomType = roomData.getRoom().getType().getTypeName();
        BigDecimal price = roomData.getRoom().getPricePerNight();
        int occupancy = roomData.getRoom().getOccupancy();

        Bundle bundle = new Bundle();
        bundle.putString("room name", roomType);
        bundle.putString("room price", price.toString());
        bundle.putInt("room occupancy", occupancy);

//        HotelGuestDetailsFragment hotelGuestDetailsFragment = new HotelGuestDetailsFragment();
//        hotelGuestDetailsFragment.setArguments(bundle);
//
//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//        fragmentTransaction.remove(HotelsListFragment.this);
//        fragmentTransaction.replace(R.id.main_layout, hotelGuestDetailsFragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commitAllowingStateLoss();
    }
}
