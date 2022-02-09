package com.onwindapp.cuatrovientos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.models.TripsTesting;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class UserTripsAdapter extends RecyclerView.Adapter<UserTripsAdapter.UserTripsViewHolder> {
    private ArrayList<TripsTesting> trips;
    private OnItemClickListener mListener;
    Context context;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class UserTripsViewHolder extends RecyclerView.ViewHolder {
        public TextView username, date, tripType;

        public UserTripsViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.driverNameUT);
            date = (TextView) itemView.findViewById(R.id.date);
            tripType = (TextView) itemView.findViewById(R.id.tripTypeUT);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public UserTripsAdapter(Context context, ArrayList<TripsTesting> trips) {
        this.context = context;
        this.trips = trips;
    }


    @Override
    public UserTripsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_trip_item, parent, false);
        UserTripsViewHolder evh = new UserTripsViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(UserTripsViewHolder holder, int position) {
        TripsTesting currentTrip = trips.get(position);

        holder.username.setText(currentTrip.getUsername());
        holder.date.setText(getRandomDate());
        holder.tripType.setText(currentTrip.getTripType());
    }

    private String getRandomDate(){
        long time = System.currentTimeMillis();
        Date dat = new Date(time);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dat);
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("dd-MM-yyyy hh:mm");
        return format.format(gc.getTime());
    }
    @Override
    public int getItemCount() {
        return trips.size();
    }
}
