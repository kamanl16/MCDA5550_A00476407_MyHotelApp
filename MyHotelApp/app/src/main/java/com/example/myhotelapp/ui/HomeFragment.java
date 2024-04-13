package com.example.myhotelapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhotelapp.R;
import com.example.myhotelapp.adapter.DineListAdapter;
import com.example.myhotelapp.adapter.GallaryAdapter;
import com.example.myhotelapp.model.DineImage;
import com.example.myhotelapp.model.ImageData;
import com.example.myhotelapp.viewmodel.ImageViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private List<DineImage> imageDataList;
    private List<ImageData> gallery;
    private View view;
    private ImageViewModel imageViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        imageViewModel = new ViewModelProvider(this).get(ImageViewModel.class);
        imageViewModel.init();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        imageViewModel.getAllImages().observe(getViewLifecycleOwner(), image -> {
            if (image != null) {
                gallery = image;
                setupGalleryRecyclerView(gallery);
            } else {
                Toast.makeText(getActivity(), "Failed to fetch room data", Toast.LENGTH_SHORT).show();
            }
        });
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        imageDataList = new ArrayList<>();

        // Populate the list with ImageData objects
        imageDataList.add(new DineImage(R.drawable.dine1, getString(R.string.name_restaurant1), getString(R.string.name_restaurant1_cuisine)));
        imageDataList.add(new DineImage(R.drawable.dine2, getString(R.string.name_restaurant2), getString(R.string.name_restaurant2_cuisine)));

        RecyclerView recyclerView = view.findViewById(R.id.dine_list_recycler_view);

        recyclerView.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
        DineListAdapter dineListAdapter = new DineListAdapter(getActivity(), imageDataList);
        recyclerView.setAdapter(dineListAdapter);
    }

    private void setupGalleryRecyclerView(List<ImageData> imageData) {
        List<String> imageList = new ArrayList<>();
        for (ImageData img : imageData) {
            imageList.add(img.getPath());
        }
        RecyclerView recyclerView = view.findViewById(R.id.gallery_list_recycler_view);
        recyclerView.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
        GallaryAdapter galleryAdapter = new GallaryAdapter(getActivity(), imageList);
        recyclerView.setAdapter(galleryAdapter);
    }
}
