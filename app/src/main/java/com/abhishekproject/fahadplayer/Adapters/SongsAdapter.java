package com.abhishekproject.fahadplayer.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abhishekproject.fahadplayer.Models.Songs;
import com.abhishekproject.fahadplayer.R;

import java.util.List;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.MyViewHolder> {

    Context context ;
    List<Songs> mData;
    SongsItemClickListener songsItemClickListener;


    public SongsAdapter(Context context, List<Songs> mData,SongsItemClickListener listener) {
        this.context = context;
        this.mData = mData;
        songsItemClickListener = listener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(context).inflate(R.layout.item_songs,viewGroup,false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {


        myViewHolder.TvTitle.setText(mData.get(i).getTitle());
        myViewHolder.ImgMovie.setImageResource(mData.get(i).getThumbnail());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView TvTitle;
        private ImageView ImgMovie;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            TvTitle = itemView.findViewById(R.id.item_movie_title);
            ImgMovie = itemView.findViewById(R.id.item_movie_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    songsItemClickListener.onSongsClick(mData.get(getAdapterPosition()),ImgMovie);


                }
            });

        }
    }

}
