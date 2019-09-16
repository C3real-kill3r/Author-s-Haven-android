package com.example.kamran.logingreentheme.model.Articles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShareArticle {
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Twitter")
    @Expose
    private String twitter;
    @SerializedName("Facebook")
    @Expose
    private String facebook;
    @SerializedName("LinkedIn")
    @Expose
    private String linkedIn;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }
}

