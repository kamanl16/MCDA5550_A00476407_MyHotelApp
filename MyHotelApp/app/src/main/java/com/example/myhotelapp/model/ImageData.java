package com.example.myhotelapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ImageData implements Parcelable {
    private Long imageId;
    private int catType;
    private Long catId;
    private String path;
    private String title;

    public ImageData(Long imageId, int catType, Long catId, String path, String title) {
        this.imageId = imageId;
        this.catType = catType;
        this.catId = catId;
        this.path = path;
        this.title = title;
    }

    protected ImageData(Parcel in) {
        if (in.readByte() == 0) {
            imageId = null;
        } else {
            imageId = in.readLong();
        }
        catType = in.readInt();
        if (in.readByte() == 0) {
            catId = null;
        } else {
            catId = in.readLong();
        }
        path = in.readString();
        title = in.readString();
    }

    public static final Creator<ImageData> CREATOR = new Creator<ImageData>() {
        @Override
        public ImageData createFromParcel(Parcel in) {
            return new ImageData(in);
        }

        @Override
        public ImageData[] newArray(int size) {
            return new ImageData[size];
        }
    };

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public int getCatType() {
        return catType;
    }

    public void setCatType(int catType) {
        this.catType = catType;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (imageId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(imageId);
        }
        dest.writeInt(catType);
        if (catId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(catId);
        }
        dest.writeString(path);
        dest.writeString(title);
    }
}