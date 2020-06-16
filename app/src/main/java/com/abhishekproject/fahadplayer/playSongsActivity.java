package com.abhishekproject.fahadplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class playSongsActivity extends SwipeDismissBaseActivity{
    private MediaPlayer mediaPlayer;
    ImageView btnPause;
    ImageView btnPlay;
    TextView songTitle;
    TextView playingSongTitle;
  //  ImageView songThumbnail;
    RoundedImageView songThumbnail;
    private SlidrInterface slidr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_songs);
        slidr= Slidr.attach(this);
        mediaPlayer = MediaPlayer.create(this, R.raw.sample);
        btnPause =  (ImageView) findViewById(R.id.btnPause);
        btnPlay =(ImageView) findViewById(R.id.btnPlay);
        songTitle = (TextView) findViewById(R.id.songTitle);
        songThumbnail= (RoundedImageView) findViewById(R.id.songThumbnail);
        playingSongTitle= (TextView) findViewById(R.id.playingSongTitle);


        String songTitleurl = getIntent().getExtras().getString("title");
        int songThumbnailUrl = getIntent().getExtras().getInt("imgURL");
        Glide.with(this).load(songThumbnailUrl).into(songThumbnail);
        songTitle.setText(songTitleurl);
        playingSongTitle.setText(songTitleurl);

        mediaPlayer.start();

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                btnPause.setVisibility(View.GONE);
                btnPlay.setVisibility(View.VISIBLE);
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Toast.makeText(playSongsActivity.this, "The Song is Over", Toast.LENGTH_SHORT).show();
                    }
                });
                btnPause.setVisibility(View.VISIBLE);
                btnPlay.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
       playSongsActivity.this.finish();

    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
        playSongsActivity.this.finish();
    }
}