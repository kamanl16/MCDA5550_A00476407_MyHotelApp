package com.example.myhotelapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhotelapp.R;
import com.example.myhotelapp.model.Room;

import java.util.List;

public class RoomlListAdapter extends RecyclerView.Adapter<RoomlListAdapter.ViewHolder> {

    private List<Room> roomData;
    private LayoutInflater layoutInflater;
    private ItemClickListener clickListener;

    public RoomlListAdapter(Context context, List<Room> roomData) {
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
        String roomType = roomData.get(position).getType().getTypeName();
        String roomPrice = roomData.get(position).getPricePerNight().toString();
        int roomOccupancy = roomData.get(position).getOccupancy();

        holder.roomName.setText(roomType);
        holder.roomPrice.setText("$" + roomPrice);
        holder.roomOccupancy.setText("Occupancy " + String.valueOf(roomOccupancy));
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roomName = itemView.findViewById(R.id.room_type_text_view);
            roomPrice = itemView.findViewById(R.id.price_text_view);
            roomOccupancy = itemView.findViewById(R.id.occupancy_text_view);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onClick(view, getAbsoluteAdapterPosition());
        }
    }
}
