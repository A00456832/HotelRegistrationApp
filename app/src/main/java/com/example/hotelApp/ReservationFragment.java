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
    TextView Reservation_TextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout
        view = inflater.inflate(R.layout.reservationfragment, container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getActivity(), "You are on reservation page.", Toast.LENGTH_LONG).show();
        mainLayout = view.findViewById(R.id.main_layout);
        Reservation_TextView = view.findViewById(R.id.Reservation_TextView);

        getReservationData();
    }
    private void getReservationData() {

        Integer currentReservationId = getArguments().getInt("currentReservationId");
        // Get the reservation recent details by passing ReservationId created in the HotelGuestListFragment
        Api.getClient().getReservation(currentReservationId, new Callback<Reservation>() {
            @Override
            public void success(Reservation reservationData, Response response) {
                // Fetch the TotalPrice which is calculated from the API.
                Float totalPrice =  reservationData.getTotalPrice();
                List<GuestData> guestData = reservationData.getGuestList();
                // Set up the RecyclerView
                Reservation_TextView.setText("Reservation id is "+currentReservationId +" for "+guestData.size() + " guest(s) can stay and total price would be $ "+totalPrice);
            }

            @Override
            public void failure(RetrofitError error) {
                // if error occurs in network transaction then we can get the error in this method.
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
