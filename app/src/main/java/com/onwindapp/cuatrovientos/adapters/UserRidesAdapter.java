package com.onwindapp.cuatrovientos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.models.Ride;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class UserRidesAdapter extends RecyclerView.Adapter<UserRidesAdapter.UserTripsViewHolder> {
    private List<Ride> trips;
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

    public UserRidesAdapter(Context context, List<Ride> trips) {
        this.context = context;
        this.trips = trips;
    }


    @Override
    public UserTripsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_ride_item, parent, false);
        UserTripsViewHolder evh = new UserTripsViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(UserTripsViewHolder holder, int position) {
        Ride currentRide = trips.get(position);

        holder.username.setText(currentRide.getDescription());
        holder.date.setText(currentRide.getMeetHour());
        holder.tripType.setText(currentRide.getRideType().toString());
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
