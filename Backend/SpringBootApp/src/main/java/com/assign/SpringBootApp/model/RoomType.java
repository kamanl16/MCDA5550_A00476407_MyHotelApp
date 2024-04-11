package com.assign.SpringBootApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "RoomTypes")
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long typeId;

    @Column(name = "type_name", nullable = false)
    private String typeName;

    @Column(name = "type_name_tc", nullable = false)
    private String typeName_TC;

    @Column(name = "occupancy", nullable = false)
    private int occupancy;

    @Column(name = "bed_type")
    private String bedType;

    @Column(name = "bed_type_tc")
    private String bedType_TC;

    @Column(name = "view")
    private String view;

    @Column(name = "view_tc")
    private String view_TC;

    @Column(name = "size")
    private String size;

    @Column(name = "description")
    private String description;

    @Column(name = "description_tc")
    private String description_TC;

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

    public String getTypeName_TC() {
        return typeName_TC;
    }

    public void setTypeName_TC(String typeName_TC) {
        this.typeName_TC = typeName_TC;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
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
}
