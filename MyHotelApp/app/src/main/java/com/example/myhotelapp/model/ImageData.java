package com.example.myhotelapp.model;

public class ImageData {
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
}