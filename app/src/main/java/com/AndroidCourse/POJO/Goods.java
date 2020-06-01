package com.AndroidCourse.POJO;

public class Goods {
    private String GID;
    private String name;
    private double price;
    private String imgUrl;

    public Goods() {
    }

    public Goods(String GID, String name, double price, String imgUrl) {
        this.GID = GID;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public String getGID() {
        return GID;
    }

    public void setGID(String GID) {
        this.GID = GID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "GID='" + GID + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
