package com.example.hollywoodcelebritiesdatabase;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hollywoodcelebritiesdatabase.model.Celebrity.Celebrity;

import java.util.ArrayList;

public class CelebrityListAdapter extends RecyclerView.Adapter<CelebrityListAdapter.ViewHolder> {
    ArrayList<Celebrity> celebrities;

    public CelebrityListAdapter(ArrayList<Celebrity> celebrities) {
        this.celebrities = celebrities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.hollywood_celebrity_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Celebrity celebrity = celebrities.get(position);
        holder.tvFirstName.setText(celebrity.getFirstName());
        holder.tvLastName.setText(celebrity.getLastName());
        holder.tvMostPopularMovie.setText(celebrity.getMostPopularMovie());
        holder.tvRecentScandal.setText(celebrity.getRecentScandal());
        String aliveString = "";
        if(celebrity.isAlive()){
            aliveString = "True";
        } else {
            aliveString = "False";
        }
        holder.tvIsAlive.setText(aliveString);
        holder.tvPicture.setText(celebrity.getPicture());
        //holder.tvPicture.setText(celebrity.getPicture().toString());

        String favoriteString = "";
        if(celebrity.isFavorite()){
            favoriteString = "True";
        } else {
            favoriteString = "False";
        }
        holder.tvIsFavorite.setText(favoriteString);

        holder.setCelebrity(celebrity);
    }

    @Override
    public int getItemCount() {
        return celebrities.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvFirstName;
        TextView tvLastName;
        TextView tvMostPopularMovie;
        TextView tvRecentScandal;
        TextView tvIsAlive;
        TextView tvPicture;
        TextView tvIsFavorite;
        Celebrity celebrity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFirstName = itemView.findViewById(R.id.tvFirstName);
            tvLastName = itemView.findViewById(R.id.tvLastName);
            tvMostPopularMovie = itemView.findViewById(R.id.tvMostPopularMovie);
            tvRecentScandal = itemView.findViewById(R.id.tvRecentScandal);
            tvIsAlive = itemView.findViewById(R.id.tvIsAlive);
            tvPicture = itemView.findViewById(R.id.tvPicture);
            tvIsFavorite = itemView.findViewById(R.id.tvIsFavorite);
            itemView.setOnClickListener(this);

        }

        public Celebrity getCelebrity() {
            return celebrity;
        }

        public void setCelebrity(Celebrity celebrity) {
            this.celebrity = celebrity;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ViewCelebrity.class);
            intent.putExtra("celebrity", getCelebrity());
            view.getContext().startActivity(intent);
        }
    }
}
