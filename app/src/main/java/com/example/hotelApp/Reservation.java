package com.example.hotelApp;

import java.util.List;

public class Reservation {
    Long id;
    float totalPrice;
    List<GuestData> guestList;

    public List<GuestData> getGuestList() {
        return guestList;
    }

    public void setGuestList(List<GuestData> guestList) {
        this.guestList = guestList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
