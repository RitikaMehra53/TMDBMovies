package com.cs.test.tmdbmovies.view;

import android.app.FragmentContainer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.cs.test.tmdbmovies.R;
import com.cs.test.tmdbmovies.fragments.MoviesFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        getSupportActionBar().setTitle("Movies");





        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                if (item.getItemId() == R.id.navigation_Movies) {
                    getSupportActionBar().setTitle("Movies");
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content, new MoviesFragment());
                    fragmentTransaction.commit();
                } else if (item.getItemId() == R.id.navigation_upcoming) {

                    getSupportActionBar().setTitle("Upcoming");

                } else {
                    getSupportActionBar().setTitle("Categories");
                }
                return true;
            }
        });
    }


}
