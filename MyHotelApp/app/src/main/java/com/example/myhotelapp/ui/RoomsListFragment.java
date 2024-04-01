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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhotelapp.adapter.RoomlListAdapter;
import com.example.myhotelapp.data.api.ApiClient;
import com.example.myhotelapp.data.api.ApiService;
import com.example.myhotelapp.adapter.ItemClickListener;
import com.example.myhotelapp.R;
import com.example.myhotelapp.model.Hotel;
import com.example.myhotelapp.model.Room;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomsListFragment extends Fragment implements ItemClickListener {
    private View view;
    TextView headingTextView;
    ProgressBar progressBar;
    List<Room> userListResponseData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.room_list_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //heading text view
        headingTextView = view.findViewById(R.id.heading_text_view);
        progressBar = view.findViewById(R.id.progress_bar);
        String checkInDate = getArguments().getString("check in date");
        String checkOutDate = getArguments().getString("check out date");
        String numberOfGuests = getArguments().getString("number of guests");

        //Set up the header
        headingTextView.setText(getString(R.string.title_search_result, numberOfGuests, checkInDate, checkOutDate));

        // Set up the RecyclerView
        List<Room> roomListData = initRoomListData();
        RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RoomlListAdapter hotelListAdapter = new RoomlListAdapter(getActivity(), roomListData);
        recyclerView.setAdapter(hotelListAdapter);
//        getRoomsListsData();
    }

    public List<Room> initRoomListData() {
        // JSON array as string
        String json = "[{\"roomId\":1,\"type\":{\"typeId\":1,\"typeName\":\"Standard Room\",\"occupancy\":2},\"pricePerNight\":100.00,\"occupancy\":2},{\"roomId\":2,\"type\":{\"typeId\":1,\"typeName\":\"Standard Room\",\"occupancy\":2},\"pricePerNight\":100.00,\"occupancy\":2},{\"roomId\":3,\"type\":{\"typeId\":2,\"typeName\":\"Deluxe Room\",\"occupancy\":2},\"pricePerNight\":150.00,\"occupancy\":2},{\"roomId\":4,\"type\":{\"typeId\":2,\"typeName\":\"Deluxe Room\",\"occupancy\":2},\"pricePerNight\":150.00,\"occupancy\":2},{\"roomId\":5,\"type\":{\"typeId\":3,\"typeName\":\"Suite\",\"occupancy\":4},\"pricePerNight\":200.00,\"occupancy\":4},{\"roomId\":6,\"type\":{\"typeId\":3,\"typeName\":\"Suite\",\"occupancy\":4},\"pricePerNight\":200.00,\"occupancy\":4}]";

        // Parse JSON array into array of Room objects
        Gson gson = new Gson();
        Room[] roomsArray = gson.fromJson(json, Room[].class);

        // Convert array to ArrayList
        List<Room> roomList = new ArrayList<>();
        for (Room room : roomsArray) {
            roomList.add(room);
        }

        return roomList;
    }

    private void getRoomsListsData() {
        progressBar.setVisibility(View.VISIBLE);
        ApiService apiService = ApiClient.getApiService();
        Call<List<Room>> call = apiService.getAllRooms();
        call.enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    userListResponseData = response.body();
                    // Process the data
                } else {
                    // Handle unsuccessful response
                }

                setupRecyclerView();
            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable throwable) {
                Toast.makeText(getActivity(), throwable.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setupRecyclerView() {
        progressBar.setVisibility(View.GONE);
        RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RoomlListAdapter roomlListAdapter = new RoomlListAdapter(getActivity(), userListResponseData);
        recyclerView.setAdapter(roomlListAdapter);

        //Bind the click listener
        roomlListAdapter.setClickListener(this);
    }


    @Override
    public void onClick(View view, int position) {
        Room roomData = userListResponseData.get(position);

        String roomType = roomData.getType().getTypeName();
        BigDecimal price = roomData.getPricePerNight();
        int occupancy = roomData.getOccupancy();

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
