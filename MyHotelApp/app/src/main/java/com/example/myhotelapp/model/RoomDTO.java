package com.example.myhotelapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class RoomDTO implements Parcelable {
    private Room room;
    private List<ImageData> images;

    public RoomDTO(Room room, List<ImageData> images) {
        this.room = room;
        this.images = images;
    }

    protected RoomDTO(Parcel in) {
        room = in.readParcelable(Room.class.getClassLoader());
        images = new ArrayList<>();
        in.readList(images, ImageData.class.getClassLoader());
    }

    public static final Creator<RoomDTO> CREATOR = new Creator<RoomDTO>() {
        @Override
        public RoomDTO createFromParcel(Parcel in) {
            return new RoomDTO(in);
        }

        @Override
        public RoomDTO[] newArray(int size) {
            return new RoomDTO[size];
        }
    };

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<ImageData> getImages() {
        return images;
    }

    public void setImages(List<ImageData> images) {
        this.images = images;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeParcelable(room, flags);
        dest.writeList(images);
    }
}
