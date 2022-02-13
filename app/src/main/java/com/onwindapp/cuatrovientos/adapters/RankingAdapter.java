package com.onwindapp.cuatrovientos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.models.Ride;
import com.onwindapp.cuatrovientos.models.Users;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.RankingViewHolder> {
    private List<Users> users;
    private OnItemClickListener mListener;
    Context context;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class RankingViewHolder extends RecyclerView.ViewHolder {
        public TextView username, date, tripType;

        public RankingViewHolder(View itemView, final OnItemClickListener listener) {
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

    public RankingAdapter(Context context, List<Users> users) {
        this.context = context;
        this.users = users;
    }


    @Override
    public RankingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_ride_item, parent, false);
        RankingViewHolder evh = new RankingViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(RankingViewHolder holder, int position) {
        Users currentRide = users.get(position);

        holder.username.setText(currentRide.getName());
        holder.date.setText(currentRide.getMail());
        holder.tripType.setText(Float.toString(currentRide.getCO2points()));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
