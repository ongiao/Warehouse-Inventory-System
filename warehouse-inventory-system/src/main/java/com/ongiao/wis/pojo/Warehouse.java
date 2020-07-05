package com.ongiao.wis.pojo;

public class Warehouse {
    private String location;

    public Warehouse () {
    }

    public Warehouse (String name) {
        this.location = name;
    }

    public String getLocation () {
        return location;
    }

    public void setLocation (String location) {
        this.location = location;
    }

    @Override
    public String toString () {
        return "Warehouse{" +
                "location='" + location + '\'' +
                '}';
    }
}
