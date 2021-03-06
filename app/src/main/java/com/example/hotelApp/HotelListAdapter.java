package com.example.hotelApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HotelListAdapter extends RecyclerView.Adapter<HotelListAdapter.ViewHolder> {

    private List<HotelListData> hotelListData;
    private LayoutInflater layoutInflater;
    private ItemClickListener clickListener;

    //Data gets passed in the constructor
    HotelListAdapter(Context context, List<HotelListData> hotelListData) {
        this.layoutInflater = LayoutInflater.from(context);
        this.hotelListData = hotelListData;
    }

    @NonNull
    @Override
    public HotelListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.hotellistlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelListAdapter.ViewHolder holder, int position) {
        String hotelName = hotelListData.get(position).getName();
        int hotelPrice = hotelListData.get(position).getPrice();
        String hotelCity = hotelListData.get(position).getCity();
        int hotelStarRating = hotelListData.get(position).getStarRating();
        String hotelAvailability = (hotelListData.get(position).getIsAvailable()) ? "Yes":"No";

        // set up the text
        holder.hotelName.setText(hotelName);
        holder.hotelPrice.setText(Integer.toString(hotelPrice));
        holder.hotelCity.setText(hotelCity);
        holder.hotelStarRating.setText(Integer.toString(hotelStarRating));
        holder.hotelAvailability.setText(hotelAvailability);
        // high the available hotel in green.
        //holder.hotelAvailability.setTextColor(hotelAvailability.equals("Yes") ? Color.GREEN:Color.RED);
    }

    @Override
    public int getItemCount() {
        if (hotelListData != null) {
            return hotelListData.size();
        } else {
            return 0;
        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView hotelName, hotelPrice, hotelAvailability, hotelCity, hotelStarRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelName = itemView.findViewById(R.id.Hotel_name_TextView);
            hotelPrice = itemView.findViewById(R.id.Price_title_TextView);
            hotelAvailability = itemView.findViewById(R.id.Availability_TextView);
            hotelCity = itemView.findViewById(R.id.City_TextView);
            hotelStarRating = itemView.findViewById(R.id.starRating_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onClick(view, getAbsoluteAdapterPosition());
        }
    }

}