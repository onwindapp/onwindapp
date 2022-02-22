package com.onwindapp.cuatrovientos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.models.Ride;

import java.util.List;

public class RidesAdapter extends RecyclerView.Adapter<RidesAdapter.TripsViewHolder> {
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
        public TextView username, spacesAvailable, tripType, dateTime;

        public TripsViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.driverName);
            spacesAvailable = (TextView) itemView.findViewById(R.id.availableSpaces);
            tripType = (TextView) itemView.findViewById(R.id.tripType);
            dateTime = (TextView) itemView.findViewById(R.id.txtDateTime);
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

    public RidesAdapter(Context context, List<Ride> trips) {
        this.context = context;
        this.trips = trips;
    }


    @Override
    public TripsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ride_item, parent, false);
        TripsViewHolder evh = new TripsViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(TripsViewHolder holder, int position) {
        Ride currentRide = trips.get(position);
//        if (currentRide.getAvailablePlaces() < 0) {
//            holder.set
//        }
        holder.username.setText(currentRide.getDescription());
        holder.spacesAvailable.setText(currentRide.getAvailablePlaces()+" Plazas");
        holder.tripType.setText(currentRide.getRideType().toString());

//        String[] deteFormat = currentRide.getDateTime().split("")
        holder.dateTime.setText(currentRide.getDateTime());
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }
}
