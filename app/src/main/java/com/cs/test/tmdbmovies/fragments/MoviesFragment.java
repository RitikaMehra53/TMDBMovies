package com.cs.test.tmdbmovies.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.cs.test.tmdbmovies.R;
import com.cs.test.tmdbmovies.model.movies.MoviesList;
import com.cs.test.tmdbmovies.utils.BaseUrls;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Amit on 6/29/2017.
 */

public class MoviesFragment extends Fragment {


    int pageNumber = 1;

    RecyclerView recyclerView;
    LinearLayout linearLayoutProgress;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = layoutInflater.inflate(R.layout.view_movies_fragment, container, false);

        recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView);
        linearLayoutProgress=(LinearLayout)itemView.findViewById(R.id.linearLayoutProgress);

        getMoviesList(pageNumber);
        return itemView;
    }


    private void getMoviesList(int pageNumber) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrls.webBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<MoviesList> data = retrofit.create(api.class).getMoviesList(pageNumber + "");
        data.enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                MoviesList data = response.body();
                setAdapter(data);

                linearLayoutProgress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
                Log.e("failure", t.getMessage());
                linearLayoutProgress.setVisibility(View.GONE);
                Toast.makeText(getActivity(),"Unable to get movies",Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void setAdapter(MoviesList moviesList) {
        MoviesBaseAdapter adapter = new MoviesBaseAdapter(moviesList,getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);
    }


    public interface api {
        @GET("discover/movie?api_key=da46474f8dd8ea1caebd07b84cb98b86&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false")
        Call<MoviesList> getMoviesList(@Query("page") String pageNumber);
    }


}
