package com.example.hotelApp;


//??? Reservation id is not considered here, it is present in the db.
public class GuestData {
    String guest_fname;
    String guest_lname;
    String gender;

    public String getGuest_fname() {
        return guest_fname;
    }

    public void setGuest_fname(String guest_fname) {
        this.guest_fname = guest_fname;
    }

    public String getGuest_lname() {
        return guest_lname;
    }

    public void setGuest_lname(String guest_lname) {
        this.guest_lname = guest_lname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public GuestData(String guest_fname, String guest_lname, String gender) {
        this.guest_fname = guest_fname;
        this.guest_lname = guest_lname;
        this.gender = gender;
    }

}

