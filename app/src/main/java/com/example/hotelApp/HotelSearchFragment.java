package com.example.hotelApp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HotelSearchFragment extends Fragment {
    View view;
    ConstraintLayout mainLayout;
    TextView hotelTitleTextView;
    DatePicker CheckInDatePicker, CheckOutDatePicker;
    EditText numberOfGuestEditText, guestNameEditText;
    Button searchButton;
    String checkInDate, checkOutDate, numberOfGuests, guestName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout
        view = inflater.inflate(R.layout.hotelsearchlayout, container,false);
        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainLayout = view.findViewById(R.id.main_layout);
        hotelTitleTextView = view.findViewById(R.id.hotel_Title_TextView);
        numberOfGuestEditText = view.findViewById(R.id.editText_NumberOfGuest);
        guestNameEditText = view.findViewById(R.id.editText_guestName);
        searchButton = view.findViewById(R.id.Search_button);
        CheckInDatePicker = view.findViewById(R.id.CheckInDate_Calendar);
        CheckOutDatePicker = view.findViewById(R.id.CheckOutDate_Calendar);

        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkInDate = getDateFromCalendar(CheckInDatePicker);
                checkOutDate = getDateFromCalendar(CheckOutDatePicker);

                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date checkIn = formatter.parse(checkInDate);
                    Date checkOut = formatter.parse(checkOutDate);

                    if(checkOut.before(checkIn)) {
                        Toast.makeText(getActivity(), "Checkout date should be greater than the checkin date.", Toast.LENGTH_LONG).show();
                        return;
                    }

                    //Get input from guests count
                    numberOfGuests = numberOfGuestEditText.getText().toString();
                    guestName = guestNameEditText.getText().toString();

                    if ( numberOfGuests == null || numberOfGuests.length() == 0){
                        Toast.makeText(getActivity(), "Please provide at least 1 guest.", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if ( guestName == null || guestName.length() == 0){
                        Toast.makeText(getActivity(), "Please provide the your name.", Toast.LENGTH_LONG).show();
                        return;
                    }

                    Bundle bundle = new Bundle();
                    bundle.putString("check in date", checkInDate);
                    bundle.putString("check out date", checkOutDate);
                    bundle.putString("number of guests", numberOfGuests);
                    bundle.putString("Guest Name", guestName);

                    HotelListFragment hotelListFragment = new HotelListFragment();
                    hotelListFragment.setArguments(bundle);

                    FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_layout, hotelListFragment);
                    fragmentTransaction.remove(HotelSearchFragment.this);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String getDateFromCalendar(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = simpleDateFormat.format(calendar.getTime());
        return formattedDate;
    }
}
