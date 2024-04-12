package com.example.myhotelapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhotelapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GallaryAdapter extends RecyclerView.Adapter<GallaryAdapter.ViewHolder> {
    private final List<String> imageList;
    private final LayoutInflater layoutInflater;
    private ItemClickListener clickListener;

    public GallaryAdapter(Context context, List<String> imageList) {
        this.imageList = imageList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public GallaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.image_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GallaryAdapter.ViewHolder holder, int position) {
        String path = imageList.get(position);
        Picasso.get().load(path).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (imageList != null) {
            return imageList.size();
        } else {
            return 0;
        }
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recycle_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAbsoluteAdapterPosition());
        }
    }
}
