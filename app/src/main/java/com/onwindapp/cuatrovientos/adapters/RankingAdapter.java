package com.onwindapp.cuatrovientos.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.fragments.RidesAvailableFragment;
import com.onwindapp.cuatrovientos.fragments.UserRidesFragment;
import com.onwindapp.cuatrovientos.models.Ride;
import com.onwindapp.cuatrovientos.models.Users;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

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
        public TextView rankingDPosition, username, co2points;
        public ImageView position;

        public RankingViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.rankingDriverName);
            rankingDPosition = (TextView) itemView.findViewById(R.id.rankingDPosition);
            co2points = (TextView) itemView.findViewById(R.id.co2points);
            position = (ImageView) itemView.findViewById(R.id.rankingPosition);
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public RankingAdapter(Context context, List<Users> users) {
        this.context = context;
        this.users = users.stream().sorted(Comparator.comparing(Users::getCO2points).reversed()).collect(Collectors.toList());;
    }


    @Override
    public RankingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_item, parent, false);
        RankingViewHolder evh = new RankingViewHolder(v, mListener);
        return evh;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(RankingViewHolder holder, int position) {
        Users currentUser = users.get(position);
        switch (position){
            case 0:
                holder.position.setImageResource(R.drawable.first);
                break;
            case 1:
                holder.position.setImageResource(R.drawable.second);
                break;
            case 2:
                holder.position.setImageResource(R.drawable.third);
                break;
            default:
                holder.rankingDPosition.setBackgroundResource(R.drawable.round);
                holder.rankingDPosition.setText(Integer.toString(position + 1));
        }
        holder.username.setText(currentUser.getName()+" "+currentUser.getSurname());
        holder.co2points.setText(currentUser.getCO2points()+" co2 Points");
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
