package com.example.hotelApp;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//import retrofit.Callback;

public class HotelGuestListAdapter extends RecyclerView.Adapter<HotelGuestListAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private ItemClickListener clickListener;
    private Integer noOfGuests;
    private String userName;
    private ArrayList<GuestData> guestDataList;


    public HotelGuestListAdapter(Context context, Integer guests) {
        this.layoutInflater = LayoutInflater.from(context);
        this.noOfGuests = guests;

    }

    //Data gets passed in the constructor
    HotelGuestListAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
       // this.hotelListData = hotelListData;
    }

    @NonNull
    @Override
    public HotelGuestListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.hotelguestlistlayout, parent, false);
        if(guestDataList == null) {
            guestDataList = new ArrayList<>();
            for (int i = 0; i < this.noOfGuests; i++) {
                guestDataList.add(new GuestData("","","M"));
            }
        }
        return new ViewHolder(view);

    }

    @Override
    //public void onBindViewHolder(@NonNull ViewHolder holder, int position){
    public void onBindViewHolder(@NonNull HotelGuestListAdapter.ViewHolder holder, int position) {
//        if(position==0) {
//           // holder.guestName.setText(userName);
//           // guestDataList.get(0).setGuest_name(userName);
//        }
        //holder.guestNameTextView.setText("Guest "+(position+1));

        holder.guestFName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                guestDataList.get(position).setGuest_fname(editable.toString());
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
                guestDataList.get(position).setGuest_lname(editable.toString());
            }
        });

        holder.gender.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                guestDataList.get(position).setGender(editable.toString());
            }
        });

//        holder.gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                if(i==0) {
//                    guestDataList.get(position).setGender("M");
//                } else {
//                    guestDataList.get(position).setGender("F");
//                }
//            }
//        });
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



    public class ViewHolder extends RecyclerView.ViewHolder //implements View.OnClickListener
    {
        EditText guestFName;
        EditText guestLName;
        EditText gender;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            guestFName = itemView.findViewById(R.id.first_name_edittext);
            guestLName = itemView.findViewById(R.id.last_name_edittext);
            gender = itemView.findViewById(R.id.gender_edittext);

        }


    }
}
