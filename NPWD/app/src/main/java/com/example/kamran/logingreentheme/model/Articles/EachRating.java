package com.example.kamran.logingreentheme.model.Articles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EachRating {
    @SerializedName("5")
    @Expose
    private Integer _5;

    public Integer get5() {
        return _5;
    }

    public void set5(Integer _5) {
        this._5 = _5;
    }
}

