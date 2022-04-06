package com.example.hotelApp;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface ApiInterface {

    // API's endpoints
    @GET("/hotel")
    public void getHotelsLists(Callback<List<HotelListData>> callback);

//    @GET("/api/hotel")
//    public void getHotelsList(Callback<List<HotelListData>> callback);

    @POST("/reservation/{hotelId}")
    public void makeReservation(@Path("hotelId") Integer hotelId, @Body ReservationData reservationData, Callback<List<Object>> callback);
}
