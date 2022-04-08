package com.example.hotelApp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class HotelListFragment extends Fragment implements ItemClickListener {

    View view;
    TextView headingTextView;
    ProgressBar progressBar;
    List<HotelListData> userListResponseData;
    String numberOfGuests;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.hotellistfragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //heading text view
        headingTextView = view.findViewById(R.id.heading_text_view);
        progressBar = view.findViewById(R.id.progress_bar);

        String checkInDate = getArguments().getString("check in date");
        String checkOutDate = getArguments().getString("check out date");
        numberOfGuests = getArguments().getString("number of guests");
        String guestName = getArguments().getString("Guest Name");

        //Set up the header
        headingTextView.setText("For "+guestName + ", displaying hotel for " + numberOfGuests + " guests staying from " + checkInDate +
                " to " + checkOutDate);
        // Invoked the get API call to populate the available hotels
        getHotelsListsData();
    }

    private void getHotelsListsData() {
        progressBar.setVisibility(View.VISIBLE);
        Api.getClient().getHotelsLists(new Callback<List<HotelListData>>() {
            @Override
            public void success(List<HotelListData> userListResponses, Response response) {

                // If get hotel API returns no hotels then show message about unavailability of the hotels.
                if (userListResponses.size()== 0)
                {
                    Toast.makeText(getActivity(), "Sorry, no hotel is available.", Toast.LENGTH_LONG).show();

                    HotelSearchFragment hotelSearchFragment = new HotelSearchFragment();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.remove(HotelListFragment.this);
                    fragmentTransaction.replace(R.id.main_layout, hotelSearchFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commitAllowingStateLoss();
                }
                // in this method we will get the response from API
                userListResponseData =  userListResponses;
                // Set up the RecyclerView
                setupRecyclerView();

                progressBar.setVisibility(View.GONE);
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
        progressBar.setVisibility(View.GONE);
        RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(), userListResponseData);
        recyclerView.setAdapter(hotelListAdapter);

        //Bind the click listener
        hotelListAdapter.setClickListener(this);
    }


    @Override
    public void onClick(View view, int position) {
        HotelListData hotelListData = userListResponseData.get(position);

        String hotelName = hotelListData.getName();
        Integer hotelPrice = hotelListData.getPrice();
        Boolean hotelAvailability = hotelListData.isAvailable();
        String hotelCity = hotelListData.getCity();
        Integer hotelStarRating = hotelListData.getStarRating();
        Integer hotelId = hotelListData.getId();

        Bundle bundle = new Bundle();
        bundle.putString("hotel name", hotelName);
        bundle.putInt("hotel price", hotelPrice);
        bundle.putBoolean("hotel availability", hotelAvailability);
        bundle.putString("hotel city", hotelCity);
        // used the shortcut version of the code to put the dates in the bundle
        bundle.putString("check in date", getArguments().getString("check in date"));
        bundle.putString("check out date", getArguments().getString("check out date"));
        bundle.putInt("hotel Star Rating", hotelStarRating);
        bundle.putString("number of guests", numberOfGuests);
        bundle.putInt("hotel Id", hotelId);

        HotelGuestListFragment hotelGuestListFragment = new HotelGuestListFragment();
        hotelGuestListFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.remove(HotelListFragment.this);
        fragmentTransaction.replace(R.id.main_layout, hotelGuestListFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }
}


