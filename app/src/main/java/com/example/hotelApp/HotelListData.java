package com.example.hotelApp;

public class HotelListData {

    String name;
    int price;
    String city;
    int starRating;
    boolean available;
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

    public boolean getIsAvailable() {
        return available;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.available = isAvailable;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
