package com.abhishekproject.fahadplayer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import com.abhishekproject.fahadplayer.Adapters.SliderPagerAdapter;
import com.abhishekproject.fahadplayer.Adapters.SongsAdapter;
import com.abhishekproject.fahadplayer.Adapters.SongsItemClickListener;
import com.abhishekproject.fahadplayer.Models.Slider;
import com.abhishekproject.fahadplayer.Models.Songs;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Homepage extends AppCompatActivity implements SongsItemClickListener {

    private List<Slider> lstSlides ;
    private ViewPager sliderpager;
    private TabLayout indicator;
    private RecyclerView MoviesRV;
    private RecyclerView MoviesRV2;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        sliderpager = findViewById(R.id.slider_pager) ;
        indicator = findViewById(R.id.indicator);
        MoviesRV = findViewById(R.id.Rv_movies);
        MoviesRV2 = findViewById(R.id.Rv_movies2);

        // prepare a list of slides ..
        lstSlides = new ArrayList<>() ;
        lstSlides.add(new Slider(R.drawable.slide11,"Bollywood Party 2020 \nSuper-Hit Songs"));
        lstSlides.add(new Slider(R.drawable.slide12,"New Bollywood Hindi Songs \nBest of 2019-20"));
        lstSlides.add(new Slider(R.drawable.slide13,"Top Romantic Songs \nFall in Love"));
        lstSlides.add(new Slider(R.drawable.slide14,"Mashup \nMoments Of love"));
        SliderPagerAdapter adapter = new SliderPagerAdapter(this,lstSlides);
        sliderpager.setAdapter(adapter);

        // setup timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new Homepage.SliderTimer(),4000,6000);
        indicator.setupWithViewPager(sliderpager,true);

        // Recyclerview Setup
        // ini data

        List<Songs> lstSongs = new ArrayList<>();
        lstSongs.add(new Songs("Dus Bahane 2.0",R.drawable.song1,R.drawable.songcover));
        lstSongs.add(new Songs("Haan Mai Galat",R.drawable.song2,R.drawable.songcover));
        lstSongs.add(new Songs("Garmi",R.drawable.song3));
        lstSongs.add(new Songs("Shayad",R.drawable.song4));
        lstSongs.add(new Songs("Teri Khaamiyan",R.drawable.song5));
        lstSongs.add(new Songs("Tere Laare",R.drawable.song6));

        SongsAdapter songsAdapter= new SongsAdapter(this,lstSongs,this);
        MoviesRV.setAdapter(songsAdapter);
        MoviesRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        List<Songs> lstSongs2 = new ArrayList<>();
        lstSongs2.add(new Songs("Tere Laare",R.drawable.song6,R.drawable.songcover));
        lstSongs2.add(new Songs("Teri Khaamiyan",R.drawable.song5,R.drawable.songcover));
        lstSongs2.add(new Songs("Garmi",R.drawable.song3));
        lstSongs2.add(new Songs("Shayad",R.drawable.song4));
        lstSongs2.add(new Songs("Haan Mai Galat",R.drawable.song2));
        lstSongs2.add(new Songs("Dus Bahane 2.0",R.drawable.song1));

        SongsAdapter songsAdapter2= new SongsAdapter(this,lstSongs2,this);
        MoviesRV2.setAdapter(songsAdapter2);
        MoviesRV2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onSongsClick(Songs songs, ImageView songsImageView) {
        // here we send movie information to detail activity
        // also we ll create the transition animation between the two activity

        Intent intent = new Intent(this,playSongsActivity.class);
        // send movie information to deatilActivity
        intent.putExtra("title",songs.getTitle());
        intent.putExtra("imgURL",songs.getThumbnail());
      //  intent.putExtra("imgCover",songs.getCoverPhoto());
        // lets crezte the animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Homepage.this,
                songsImageView,"sharedName");

        startActivity(intent,options.toBundle());



        // i l make a simple test to see if the click works

     //   Toast.makeText(this,"item clicked : " + songs.getTitle(),Toast.LENGTH_LONG).show();
        // it works great


    }


    class SliderTimer extends TimerTask {


        @Override
        public void run() {

            Homepage.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderpager.getCurrentItem()<lstSlides.size()-1) {
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem()+1);
                    }
                    else
                        sliderpager.setCurrentItem(0);
                }
            });


        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}