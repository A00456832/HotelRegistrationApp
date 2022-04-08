package com.example.hotelApp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ReservationFragment extends Fragment {
    View view;
    ConstraintLayout mainLayout;
    TextView reservation_text_view; //hotel_Title_TextView


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
        reservation_text_view = view.findViewById(R.id.hotel_Title_TextView);

        getReservationData();
    }
    private void getReservationData() {

        Integer currentReservationId = getArguments().getInt("currentReservationId");
        // Get the reservation recent details by passing ReservationId created in the HotelGuestListFragment
        Api.getClient().getReservation(currentReservationId, new Callback<Reservation>() {
            @Override
            public void success(Reservation reservationData, Response response) {

                Float totalPrice =  reservationData.getTotalPrice();
                List<GuestData> guestData = reservationData.getGuestList();

                // Set up the RecyclerView
                reservation_text_view.setText(guestData.size() + "can stay and total price would be $ "+totalPrice);
            }

            @Override
            public void failure(RetrofitError error) {
                // if error occurs in network transaction then we can get the error in this method.
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
