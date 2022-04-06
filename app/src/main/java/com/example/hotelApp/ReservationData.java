package com.example.hotelApp;

import java.util.Date;
import java.util.List;

public class ReservationData {

    private Date checkout_date;
    private Date checkin_date;
    private Date reservation_datetime;
    private Integer total_price;
    private Integer hotel_id;
    private List<GuestData> guests_list;

    public Date getCheckin_date() {
        return checkin_date;
    }

    public void setCheckin_date(Date checkin_date) {
        this.checkin_date = checkin_date;
    }

    public Date getCheckout_date() {
        return checkout_date;
    }

    public void setCheckout_date(Date checkout_date) {
        this.checkout_date = checkout_date;
    }

    public Date getReservation_datetime() {
        return reservation_datetime;
    }

    public void setReservation_datetime(Date reservation_datetime) {
        this.reservation_datetime = reservation_datetime;
    }

    public Integer getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Integer total_price) {
        this.total_price = total_price;
    }

    public Integer getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(Integer hotel_id) {
        this.hotel_id = hotel_id;
    }

    public List<GuestData> getGuests_list() {
        return guests_list;
    }

    public void setGuests_list(List<GuestData> guests_list) {
        this.guests_list = guests_list;
    }

}
