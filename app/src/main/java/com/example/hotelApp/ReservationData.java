package com.example.hotelApp;

import java.util.Date;
import java.util.List;

public class ReservationData {

    private String checkoutDate;
    private String checkinDate;
    private Date reservationDateTime;
    private Integer totalPrice;
    private Integer hotelId;
    private List<GuestData> guestList;
    private HotelListData hotel;

    public HotelListData getHotel() {
        return hotel;
    }

    public void setHotel(HotelListData hotel) {
        this.hotel = hotel;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Date getReservationDateTime() {
        return reservationDateTime;
    }

    public void setReservationDateTime(Date reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public List<GuestData> getGuestList() {
        return guestList;
    }

    public void setGuestList(List<GuestData> guestList) {
        this.guestList = guestList;
    }

}
