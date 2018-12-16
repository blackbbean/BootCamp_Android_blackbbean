package com.example.android.boostcamp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;





/**
 * Created by Minjeong Kim on 2018-12-06.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Activity activity;
    private List<Movie> movie;

    public RecyclerViewAdapter(Activity activity){
        this.activity = activity;
        movie = new ArrayList<>();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        RatingBar rate;
        TextView movieTitle;
        TextView movieDate;
        TextView movieDirector;
        TextView movieActor;
        public final View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            poster = (ImageView)itemView.findViewById(R.id.poster);
            rate = (RatingBar)itemView.findViewById(R.id.movieRate);
            movieTitle = (TextView)itemView.findViewById(R.id.movieTitle);
            movieDate = (TextView)itemView.findViewById(R.id.movieDate);
            movieDirector = (TextView)itemView.findViewById(R.id.movieDirector);
            movieActor = (TextView)itemView.findViewById(R.id.movieActor);
        }
    }

    public void swap(List <Movie> movie){
        this.movie = movie;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Movie data = movie.get(position);


        if(data.getImage().length()!=0){
            Picasso.with(activity).load(data.getImage()).into(holder.poster);
        }
        holder.rate.setRating((float)data.getUserRating()*5/10);
        holder.movieActor.setText(data.getActor());
        holder.movieDirector.setText(data.getDirector());
        holder.movieTitle.setText(data.getTitle());
        holder.movieDate.setText(data.getPubDate());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setToolbarColor(activity.getColor(R.color.colorPrimary));
                CustomTabsIntent intent = builder.build();
                intent.launchUrl(activity, Uri.parse(data.getLink()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return movie.size();
    }
}
