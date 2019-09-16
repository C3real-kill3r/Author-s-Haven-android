package com.example.kamran.logingreentheme.model.Articles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Author {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("following")
    @Expose
    private Boolean following;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getFollowing() {
        return following;
    }

    public void setFollowing(Boolean following) {
        this.following = following;
    }
}
