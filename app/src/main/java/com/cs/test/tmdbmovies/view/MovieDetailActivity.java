package com.cs.test.tmdbmovies.view;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.cs.test.tmdbmovies.R;
import com.cs.test.tmdbmovies.model.movies.moviedetail.MovieDetail;
import com.cs.test.tmdbmovies.utils.BaseUrls;
import com.cs.test.tmdbmovies.utils.controls.TextViewBold;
import com.cs.test.tmdbmovies.utils.controls.TextViewLight;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MovieDetailActivity extends AppCompatActivity {

    ImageView imageView;

    TextViewBold textViewName;
    TextViewLight textViewDescription;

    int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        String poster = getIntent().getStringExtra("poster");
        String name = getIntent().getStringExtra("name");
        movieId = getIntent().getIntExtra("id", 0);


        textViewName=(TextViewBold)findViewById(R.id.textViewName);
        textViewDescription=(TextViewLight) findViewById(R.id.textViewDesciption);


        getSupportActionBar().setTitle(name);

        imageView = (ImageView) findViewById(R.id.imageViewBackground);
        Picasso.with(this).load(BaseUrls.posterBaseUrl + poster).into(imageView);
        requestMovieDetail();
    }


    private void requestMovieDetail() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Movie Detail.....");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseUrls.webBaseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        Call<MovieDetail> data = retrofit.create(api.class).getMovieDetail(movieId + "");
        data.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                MovieDetail data = response.body();
                showData(data);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                progressDialog.dismiss();

            }
        });

    }


    private void showData(MovieDetail data) {

        textViewName.setText(data.getTitle());
        textViewDescription.setText(data.getOverview());

    }

    interface api {
        @GET("movie/{MOVIE_ID}?api_key=da46474f8dd8ea1caebd07b84cb98b86&language=en-US")
        Call<MovieDetail> getMovieDetail(@Path("MOVIE_ID") String movieId);
    }
}
