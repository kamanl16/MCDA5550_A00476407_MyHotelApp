package com.example.myhotelapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhotelapp.R;
import com.example.myhotelapp.model.DineImage;
import com.example.myhotelapp.model.ImageData;
import com.example.myhotelapp.model.Room;

import java.util.List;

public class DineListAdapter extends RecyclerView.Adapter<DineListAdapter.ViewHolder> {
    private List<DineImage> imageData;
    private LayoutInflater layoutInflater;
    private ItemClickListener clickListener;

    public DineListAdapter(Context context, List<DineImage> imageData) {
        this.imageData = imageData;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public DineListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.dine_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DineListAdapter.ViewHolder holder, int position) {
        Integer imageRes = imageData.get(position).getImageResource();
        String imageTitle = imageData.get(position).getTitle();
        String imageDesc = imageData.get(position).getDescription();

        holder.image.setImageResource(imageRes);
        holder.title.setText(imageTitle);
        holder.description.setText(imageDesc);
    }

    @Override
    public int getItemCount() {
        if (imageData != null) {
            return imageData.size();
        } else {
            return 0;
        }
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        TextView title, description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.recycle_image);
            title = itemView.findViewById(R.id.recycle_title);
            description = itemView.findViewById(R.id.recycle_description);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAbsoluteAdapterPosition());
        }
    }
}
