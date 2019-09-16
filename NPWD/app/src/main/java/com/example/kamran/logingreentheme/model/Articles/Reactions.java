package com.example.kamran.logingreentheme.model.Articles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reactions {
    @SerializedName("likes")
    @Expose
    private Likes likes;
    @SerializedName("dislikes")
    @Expose
    private Dislikes dislikes;

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public Dislikes getDislikes() {
        return dislikes;
    }

    public void setDislikes(Dislikes dislikes) {
        this.dislikes = dislikes;
    }
}

