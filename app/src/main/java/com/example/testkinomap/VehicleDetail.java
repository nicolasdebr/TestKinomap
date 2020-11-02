package com.example.testkinomap;

import androidx.annotation.NonNull;
import java.net.URL;

public class VehicleDetail {

    private Integer id;
    private String name;
    private String imageurl;


    public VehicleDetail(Integer id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageurl = imageUrl;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    @Override
    public String toString() {
        return "VehiculeDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageurl='" + imageurl + '\'' +
                '}';
    }
}