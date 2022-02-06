package com.onwindapp.cuatrovientos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.models.TripsTesting;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.TripsViewHolder> {
    private ArrayList<TripsTesting> trips;
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
        public CircleImageView userAvatar;

        public TripsViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.nameTextView);
            spacesAvailable = (TextView) itemView.findViewById(R.id.availableSpaces);
            tripType = (TextView) itemView.findViewById(R.id.tripType);
            userAvatar = (CircleImageView) itemView.findViewById(R.id.iconImageView);
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

    public TripsAdapter(Context context, ArrayList<TripsTesting> trips) {
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
        TripsTesting currentTrip = trips.get(position);

        holder.username.setText(currentTrip.getUsername());
        holder.spacesAvailable.setText(currentTrip.getSlots()+" Plazas");
        holder.tripType.setText(currentTrip.getTripType());
        Glide.with(context).load(currentTrip.getProfile()).into(holder.userAvatar);
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }
}
