package com.ongiao.wis.pojo;

public class Product {
    private String name;
    private String code;
    private double weight;
    private int status;

    public Product () {
    }

    public Product (String name, String code, double weight) {
        this.name = name;
        this.code = code;
        this.weight = weight;
        this.status = 1;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getCode () {
        return code;
    }

    public void setCode (String code) {
        this.code = code;
    }

    public double getWeight () {
        return weight;
    }

    public void setWeight (double weight) {
        this.weight = weight;
    }

    public int getStatus () {
        return status;
    }

    public void setStatus (int status) {
        this.status = status;
    }

    @Override
    public String toString () {
        return "Product{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", weight=" + weight +
                ", status='" + status + '\'' +
                '}';
    }
}
