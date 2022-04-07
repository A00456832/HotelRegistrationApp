package com.example.hotelApp;

import static java.lang.Integer.parseInt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class HotelGuestListFragment extends Fragment {

    View view;
    ProgressBar progressBar;
    public static String GenderID="";
    Integer noOfGuests;
    String userName;
    Button bookingconfirmation_button;
    HotelGuestListAdapter hotelGuestListAdapter;
    String checkInDate_Date;
    String checkOutDate_Date;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.hotelguestlistfragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView hotelRecapTextView = view.findViewById(R.id.hotel_recap_text_view);

        progressBar = view.findViewById(R.id.Guest_List_progress_bar);
        bookingconfirmation_button = view.findViewById(R.id.bookingconfirmation_button);

        String hotelName = getArguments().getString("hotel name");
        Integer hotelPrice = getArguments().getInt("hotel price");
        Boolean hotelAvailability = getArguments().getBoolean("hotel availability");
        String hotelCity = getArguments().getString("hotel city");
        Integer hotelStarRating = getArguments().getInt("hotel Star Rating");
        String numberOfGuests = getArguments().getString("number of guests");
        Integer hotelId = getArguments().getInt("hotel Id");
        String checkOutDate = getArguments().getString("check out date");
        String checkInDate = getArguments().getString("check in date");

        noOfGuests = parseInt(numberOfGuests);

        hotelRecapTextView.setText("You have selected " +hotelName+ " for " +numberOfGuests +" guests. The cost will be $ "+hotelPrice+ " and availability is " +hotelAvailability);
        try {
//             checkInDate_Date=new SimpleDateFormat("dd-MM-yyyy").parse(checkInDate);
//             checkOutDate_Date=new SimpleDateFormat("dd-MM-yyyy").parse(checkOutDate);

            bookingconfirmation_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<GuestData> guestData = hotelGuestListAdapter.getGuestDataList();

                    ReservationData reservationData = new ReservationData();
                    reservationData.setHotelId(hotelId);
                    reservationData.setCheckinDate(checkInDate);
                    reservationData.setCheckoutDate(checkOutDate);

//                    for (int i = 0; i < parseInt(numberOfGuests); i++) {
//                        View tempView = recyclerView.getChildAt(i);
//                        EditText firstNameEditText = (EditText) tempView.findViewById(R.id.first_name_edittext);
//                        EditText lastNameEditText = (EditText) tempView.findViewById(R.id.last_name_edittext);
//                        EditText genderEditText = (EditText) tempView.findViewById(R.id.gender_edittext);
//
//                        String firstName = firstNameEditText.getText().toString();
//                        String lastName = lastNameEditText.getText().toString();
//                        String gender = genderEditText.getText().toString();
//
//                        GuestData gData = new GuestData(firstName,lastName,gender);
//                        guestData.add(gData);
//                    }

                    reservationData.setGuestList(guestData);

                     confirmReservation(hotelId, reservationData);
                }
            });

            setupRecyclerView();


        } catch (Exception e) {
            Toast.makeText(getContext(), "INVALID DATE FORMAT", Toast.LENGTH_LONG).show();
        }

    }

    public void confirmReservation(Integer hotelId, ReservationData reservationData) {
        progressBar.setVisibility(View.VISIBLE);
        Api.getClient().makeReservation(hotelId,
                reservationData,
                new Callback<Object>() {
                    @Override
                    public void success(Object res, Response response) {
                        LinkedTreeMap reservation = (LinkedTreeMap) ((LinkedTreeMap) res).get("reservation");
                        Double id = (Double) reservation.get("id");
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "Reservation Confirmed with id: "+ id, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        // if error occurs in network transaction then we can get the error in this method.
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    private void setupRecyclerView() {
       // progressBar.setVisibility(View.GONE);
        this.recyclerView = view.findViewById(R.id.hotel_guestlist_recyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        this.hotelGuestListAdapter = new HotelGuestListAdapter(getActivity(),noOfGuests);
        recyclerView.setAdapter(this.hotelGuestListAdapter);
    }

}
