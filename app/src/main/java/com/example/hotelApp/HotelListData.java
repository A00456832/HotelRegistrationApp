package com.example.hotelApp;

public class HotelListData {

    String name;
    int price;
    String city;
    int starRating;
    boolean isAvailable;
    int id;

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
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public int getId() {
        return id;
    }

    public HotelListData(String name, int price, String city, int starRating, boolean available) {
        this.name = name;
        this.price = price;
        this.city = city;
        this.starRating = starRating;
        this.isAvailable = available;
    }
}
