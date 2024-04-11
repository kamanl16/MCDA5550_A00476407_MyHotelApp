package com.assign.SpringBootApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ImageData")
public class ImageData {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;
    @Column(name = "path", nullable = false)
    private String path;
    @Column(name = "title")
    private String title;
    @Column(name = "cat_type", nullable = false)
    private int catType;
    @Column(name = "cat_id", nullable = false)
    private Long catId;

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
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
}
