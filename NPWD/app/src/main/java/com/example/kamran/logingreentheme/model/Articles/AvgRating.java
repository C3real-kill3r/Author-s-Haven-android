package com.example.kamran.logingreentheme.model.Articles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AvgRating {@SerializedName("avg_rating")
@Expose
private Integer avgRating;
    @SerializedName("total_user")
    @Expose
    private Integer totalUser;
    @SerializedName("each_rating")
    @Expose
    private EachRating eachRating;

    public Integer getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Integer avgRating) {
        this.avgRating = avgRating;
    }

    public Integer getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(Integer totalUser) {
        this.totalUser = totalUser;
    }

    public EachRating getEachRating() {
        return eachRating;
    }

    public void setEachRating(EachRating eachRating) {
        this.eachRating = eachRating;
    }
}

