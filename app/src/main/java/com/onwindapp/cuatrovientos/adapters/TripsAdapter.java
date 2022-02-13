package com.onwindapp.cuatrovientos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.models.Ride;
import com.onwindapp.cuatrovientos.models.TripsTesting;

import java.util.ArrayList;
import java.util.List;

public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.TripsViewHolder> {
    private List<Ride> trips;
    private OnItemClickListener mListener;
    Context context;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class TripsViewHolder extends RecyclerView.ViewHolder {
        public TextView username, spacesAvailable, tripType;

        public TripsViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.driverName);
            spacesAvailable = (TextView) itemView.findViewById(R.id.availableSpaces);
            tripType = (TextView) itemView.findViewById(R.id.tripType);
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

    public TripsAdapter(Context context, List<Ride> trips) {
        this.context = context;
        this.trips = trips;
    }


    @Override
    public TripsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item, parent, false);
        TripsViewHolder evh = new TripsViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(TripsViewHolder holder, int position) {
        Ride currentRide = trips.get(position);

        holder.username.setText(currentRide.getDescription());
        holder.spacesAvailable.setText(currentRide.getAvailablePlaces()+" Plazas");
        holder.tripType.setText(currentRide.getRideType().toString());
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }
}
