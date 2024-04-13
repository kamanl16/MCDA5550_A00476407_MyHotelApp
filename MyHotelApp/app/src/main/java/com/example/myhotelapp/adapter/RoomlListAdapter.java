package com.example.myhotelapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myhotelapp.R;
import com.example.myhotelapp.model.ImageData;
import com.example.myhotelapp.model.RoomDTO;

import java.util.ArrayList;
import java.util.List;

public class RoomlListAdapter extends RecyclerView.Adapter<RoomlListAdapter.ViewHolder> {

    private List<RoomDTO> roomData;
    private LayoutInflater layoutInflater;
    private ItemClickListener clickListener;

    public RoomlListAdapter(Context context, List<RoomDTO> roomData) {
        this.roomData = roomData;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RoomlListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.room_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomlListAdapter.ViewHolder holder, int position) {
        String roomType = roomData.get(position).getRoom().getType().getTypeName();
        String roomPrice = roomData.get(position).getRoom().getPricePerNight().toString();
        int roomOccupancy = roomData.get(position).getRoom().getOccupancy();

        holder.roomName.setText(roomType);
        holder.roomPrice.setText("$" + roomPrice + " CAD / Night");
        holder.roomOccupancy.setText("Maximum Occupancy: " + String.valueOf(roomOccupancy));

        List<ImageData> imageData = roomData.get(position).getImages();
        List<String> imageList = new ArrayList<>();
        for (ImageData img : imageData) {
            imageList.add(img.getPath());
        }
        ImageAdapter adapter = new ImageAdapter(imageList);
        holder.viewPager.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        if (roomData != null) {
            return roomData.size();
        } else {
            return 0;
        }
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView roomName, roomPrice, roomOccupancy;
        ViewPager2 viewPager;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roomName = itemView.findViewById(R.id.recycle_room_title);
            roomPrice = itemView.findViewById(R.id.recycle_room_price);
            roomOccupancy = itemView.findViewById(R.id.recycle_room_occupancy);
            viewPager = itemView.findViewById(R.id.recycle_viewPager);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onClick(view, getAbsoluteAdapterPosition());
        }
    }
}
