package com.example.myhotelapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.util.List;

public class Room implements Parcelable {
    private Long roomId;

    private RoomType type;

    private BigDecimal pricePerNight;

    public Room(Long roomId, RoomType type, BigDecimal pricePerNight) {
        this.roomId = roomId;
        this.type = type;
        this.pricePerNight = pricePerNight;
    }

    protected Room(Parcel in) {
        if (in.readByte() == 0) {
            roomId = null;
        } else {
            roomId = in.readLong();
        }
        type = in.readParcelable(RoomType.class.getClassLoader());
        String priceString = in.readString();
        if (priceString != null) {
            pricePerNight = new BigDecimal(priceString);
        }
    }

    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel in) {
            return new Room(in);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getOccupancy() {
        return type != null ? type.getOccupancy() : 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (roomId != null) {
            dest.writeByte((byte) 1);
            dest.writeLong(roomId);
        } else {
            dest.writeByte((byte) 0);
        }
        dest.writeParcelable((Parcelable) type, flags);
        if (pricePerNight != null) {
            dest.writeString(pricePerNight.toString());
        } else {
            dest.writeString(null);
        }
    }
}
