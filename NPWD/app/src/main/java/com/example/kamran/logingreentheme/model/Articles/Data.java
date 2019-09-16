package com.example.kamran.logingreentheme.model.Articles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("article")
    @Expose
    private Article_ article;

    public Article_ getArticle() {
        return article;
    }

    public void setArticle(Article_ article) {
        this.article = article;
    }
}

