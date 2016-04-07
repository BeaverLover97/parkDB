package com.example.bal_mdscherrer.parkdb;

/**
 * Created by bal_mdscherrer on 4/5/2016.
 */
public class Park {
    private String name;
    private int lat;
    private int lon;

    public void setName(String name) {
        this.name = name;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public void setLong(int lon) {
        this.lon = lon;
    }


    public String getName(){
        return name;
    }

    public int getLat(){
        return lat;
    }

    public int getLong(){
        return lon;
    }
}
