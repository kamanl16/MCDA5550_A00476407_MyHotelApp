package com.example.myhotelapp.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class RoomType implements Parcelable {

    private Long typeId;

    private String typeName;

    private String typeName_TC;
    private int occupancy;
    private String bedType;
    private String bedType_TC;
    private String view;
    private String view_TC;
    private String size;
    private String description;
    private String description_TC;

    public RoomType(Long typeId, String typeName, int occupancy) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.occupancy = occupancy;
    }

    protected RoomType(Parcel in) {
        if (in.readByte() == 0) {
            typeId = null;
        } else {
            typeId = in.readLong();
        }
        typeName = in.readString();
        typeName_TC = in.readString();
        occupancy = in.readInt();
        bedType = in.readString();
        bedType_TC = in.readString();
        view = in.readString();
        view_TC = in.readString();
        size = in.readString();
        description = in.readString();
        description_TC = in.readString();
    }

    public static final Creator<RoomType> CREATOR = new Creator<RoomType>() {
        @Override
        public RoomType createFromParcel(Parcel in) {
            return new RoomType(in);
        }

        @Override
        public RoomType[] newArray(int size) {
            return new RoomType[size];
        }
    };

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public String getTypeName_TC() {
        return typeName_TC;
    }

    public void setTypeName_TC(String typeName_TC) {
        this.typeName_TC = typeName_TC;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getBedType_TC() {
        return bedType_TC;
    }

    public void setBedType_TC(String bedType_TC) {
        this.bedType_TC = bedType_TC;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getView_TC() {
        return view_TC;
    }

    public void setView_TC(String view_TC) {
        this.view_TC = view_TC;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription_TC() {
        return description_TC;
    }

    public void setDescription_TC(String description_TC) {
        this.description_TC = description_TC;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (typeId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(typeId);
        }
        dest.writeString(typeName);
        dest.writeString(typeName_TC);
        dest.writeInt(occupancy);
        dest.writeString(bedType);
        dest.writeString(bedType_TC);
        dest.writeString(view);
        dest.writeString(view_TC);
        dest.writeString(size);
        dest.writeString(description);
        dest.writeString(description_TC);
    }
}
