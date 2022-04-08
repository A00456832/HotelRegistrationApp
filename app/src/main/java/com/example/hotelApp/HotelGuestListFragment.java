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
import androidx.fragment.app.FragmentTransaction;
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
    Integer noOfGuests;
    Button bookingconfirmationButton;
    HotelGuestListAdapter hotelGuestListAdapter;
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

        TextView hotelRecapTextView = view.findViewById(R.id.Hotel_Recap_TextView);

        progressBar = view.findViewById(R.id.Guest_List_progress_bar);
        bookingconfirmationButton = view.findViewById(R.id.BookingConfirmation_Button);

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

        hotelRecapTextView.setText("You have selected " +hotelName+ " for " +numberOfGuests +" guests. The per day cost will be $ "+hotelPrice+ " and availability is " +hotelAvailability);
        try {
                bookingconfirmationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<GuestData> guestData = hotelGuestListAdapter.getGuestDataList();

                    ReservationData reservationData = new ReservationData();
                    reservationData.setHotelId(hotelId);
                    reservationData.setCheckinDate(checkInDate);
                    reservationData.setCheckoutDate(checkOutDate);
                    reservationData.setGuestList(guestData);
                    confirmReservation(hotelId, reservationData);
                }
            });

            setupRecyclerView();
            //???
//            for (int i = 0; i < parseInt(numberOfGuests); i++) {
//                View tempView = recyclerView.getChildAt(i);
//
//            }

        } catch (Exception e) {
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
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
                        // Put the reservation id in bundle so that it can be passed as parameter to get call on ReservationFragment.
                        Bundle bundle = new Bundle();
                        bundle.putInt("currentReservationId", id.intValue());

                        ReservationFragment reservationFragment = new ReservationFragment();
                        reservationFragment.setArguments(bundle);
                        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                        fragmentTransaction.remove(HotelGuestListFragment.this);
                        fragmentTransaction.replace(R.id.main_layout, reservationFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commitAllowingStateLoss();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        // if error occurs in network transaction then we can get the error in this method.
                        String message;
                        if(error.getBody() != null) {
                            message = (String) ((LinkedTreeMap)error.getBody()).get("message");
                        } else {
                            message = error.toString();
                        }
                        Toast.makeText(getActivity(),message, Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    private void setupRecyclerView() {
        this.recyclerView = view.findViewById(R.id.Hotel_Guestlist_RecyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.hotelGuestListAdapter = new HotelGuestListAdapter(getActivity(),noOfGuests);
        recyclerView.setAdapter(this.hotelGuestListAdapter);
    }

}
