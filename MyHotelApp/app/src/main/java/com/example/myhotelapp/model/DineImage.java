package com.example.myhotelapp.model;

public class DineImage {
    private Integer ImageResource;
    private String title;
    private String description;

    public DineImage(Integer imageResource, String title, String description) {
        ImageResource = imageResource;
        this.title = title;
        this.description = description;
    }

    public Integer getImageResource() {
        return ImageResource;
    }

    public void setImageResource(Integer imageResource) {
        ImageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
