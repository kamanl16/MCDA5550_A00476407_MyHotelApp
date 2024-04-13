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
import androidx.fragment.app.FragmentTransaction;
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
    private String checkInDate, checkOutDate;
    private int numberOfGuests;
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
        numberOfGuests = getArguments().getInt("number of guests");

        roomViewModel.getAvailableRooms(checkInDate, checkOutDate, numberOfGuests).observe(getViewLifecycleOwner(), new Observer<List<RoomDTO>>() {
            @Override
            public void onChanged(List<RoomDTO> roomDTOs) {
                progressBar.setVisibility(View.INVISIBLE);
                if (roomDTOs != null) {
                    if (roomDTOs.size() > 0) {
                        userListResponseData = roomDTOs;
                        headingTextView.setText(getString(R.string.title_search_result, numberOfGuests, checkInDate, checkOutDate));
                        setupRecyclerView(roomDTOs);
                    } else {
                        headingTextView.setText(getString(R.string.title_search_result_nth, numberOfGuests, checkInDate, checkOutDate));
                    }
                } else {
                    Toast.makeText(getActivity(), "Failed to fetch room data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        progressBar.setVisibility(View.VISIBLE);
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

        Bundle bundle = new Bundle();
        bundle.putParcelable("room", roomData.getRoom());
        bundle.putString("checkInDate", checkInDate);
        bundle.putString("checkOutDate", checkOutDate);
        bundle.putInt("numberOfGuests", numberOfGuests);

        CheckoutFragment checkoutFragment = new CheckoutFragment();
        checkoutFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.remove(RoomsListFragment.this);
        fragmentTransaction.replace(R.id.frame_layout, checkoutFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
