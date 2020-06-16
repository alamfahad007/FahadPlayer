package com.abhishekproject.fahadplayer.Adapters;

import android.widget.ImageView;

import com.abhishekproject.fahadplayer.Models.Songs;

public interface SongsItemClickListener {

    void onSongsClick(Songs songs, ImageView songsImageView); // we will need the imageview to make the shared animation between the two activity
}
