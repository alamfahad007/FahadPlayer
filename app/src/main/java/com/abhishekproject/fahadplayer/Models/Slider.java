package com.abhishekproject.fahadplayer.Models;

public class Slider {
    private int Image ;
    private String Title;
    // Add more field depand on whay you wa&nt ...


    public Slider(int image, String title) {
        Image = image;
        Title = title;
    }


    public int getImage() {
        return Image;
    }

    public String getTitle() {
        return Title;
    }

    public void setImage(int image) {
        Image = image;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
