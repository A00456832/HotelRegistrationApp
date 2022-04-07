package com.example.hotelApp;


//??? Reservation id is not considered here, it is present in the db.
public class GuestData {
    String firstName;
    String lastName;
    String gender;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public GuestData(String guest_fname, String guest_lname, String gender) {
        this.firstName = guest_fname;
        this.lastName = guest_lname;
        this.gender = gender;
    }

}

