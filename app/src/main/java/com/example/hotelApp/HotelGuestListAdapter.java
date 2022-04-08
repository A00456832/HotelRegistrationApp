package com.example.hotelApp;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HotelGuestListAdapter extends RecyclerView.Adapter<HotelGuestListAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private ItemClickListener clickListener;
    private Integer noOfGuests;
    private String userName;
    private ArrayList<GuestData> guestDataList;
    public static Context context;

    public HotelGuestListAdapter(Context context, Integer guests) {
        this.layoutInflater = LayoutInflater.from(context);
        this.noOfGuests = guests;
        this.context = context;
    }

    //Data gets passed in the constructor
    HotelGuestListAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public HotelGuestListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.hotelguestlistlayout, parent, false);
        if(guestDataList == null) {
            guestDataList = new ArrayList<>();
            for (int i = 0; i < this.noOfGuests; i++) {
                guestDataList.add(new GuestData("","",""));
            }
        }
        return new ViewHolder(view);
    }


    @Override

    public void onBindViewHolder(@NonNull HotelGuestListAdapter.ViewHolder holder, int position) {

        holder.guestFName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                guestDataList.get(position).setFirstName(editable.toString());
            }
        });

        holder.guestLName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                guestDataList.get(position).setLastName(editable.toString());
            }
        });

        holder.genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] genders = HotelGuestListAdapter.context.getResources().getStringArray(R.array.gender);
                guestDataList.get(position).setGender(genders[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    @Override
    public int getItemCount() {
        return noOfGuests;
    }

    public ArrayList<GuestData> getGuestDataList() {
        return guestDataList;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        EditText guestFName;
        EditText guestLName;
        Spinner genderSpinner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            guestFName = itemView.findViewById(R.id.first_name_edittext);
            guestLName = itemView.findViewById(R.id.last_name_edittext);
            genderSpinner =itemView.findViewById(R.id.gender_spinner);

            ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(HotelGuestListAdapter.context, R.array.gender, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            genderSpinner.setAdapter(adapter);
        }
    }
}
