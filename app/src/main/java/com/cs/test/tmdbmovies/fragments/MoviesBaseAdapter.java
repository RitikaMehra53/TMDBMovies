package com.cs.test.tmdbmovies.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.cs.test.tmdbmovies.R;
import com.cs.test.tmdbmovies.model.movies.MoviesList;
import com.cs.test.tmdbmovies.model.movies.Result;
import com.cs.test.tmdbmovies.utils.BaseUrls;
import com.cs.test.tmdbmovies.utils.controls.TextViewLight;
import com.cs.test.tmdbmovies.view.MovieDetailActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by Amit on 6/30/2017.
 */

public class MoviesBaseAdapter extends RecyclerView.Adapter<MoviesBaseAdapter.MyViewHolder> {

    private MoviesList moviesList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextViewLight textViewTitle;
        public ImageView imageViewPoster;

        public MyViewHolder(View view) {
            super(view);
            textViewTitle = (TextViewLight) view.findViewById(R.id.textViewName);
            imageViewPoster = (ImageView) view.findViewById(R.id.imageViewPoster);
        }
    }


    public MoviesBaseAdapter(MoviesList moviesList, Context context) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_movie_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Result movie = moviesList.getResults().get(position);
        holder.textViewTitle.setText(movie.getTitle());
        Picasso.with(context).load(BaseUrls.posterBaseUrl+movie.getBackdropPath()).into(holder.imageViewPoster);

        holder.imageViewPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MovieDetailActivity.class);
                intent.putExtra("poster",movie.getPosterPath());
                intent.putExtra("name",movie.getTitle());
                intent.putExtra("id",movie.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.getResults().size();
    }
}