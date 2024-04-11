package com.assign.SpringBootApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "Name")
    private String name;
    @Column(name = "ranking")
    private int ranking;
    @Column(name = "location")
    private String location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name ='" + name + '\'' +
                ", ranking=" + ranking +
                ", location='" + location + '\'' +
                '}';
    }
}
