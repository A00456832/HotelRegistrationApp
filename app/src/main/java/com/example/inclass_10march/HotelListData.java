package com.example.inclass_10march;

public class HotelListData {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    String name;
    int price;
    String city;
    int starRating;
    boolean available;

    public HotelListData(String name, int price, String city, int starRating, boolean available) {
        this.name = name;
        this.price = price;
        this.city = city;
        this.starRating = starRating;
        this.available = available;
    }


}
