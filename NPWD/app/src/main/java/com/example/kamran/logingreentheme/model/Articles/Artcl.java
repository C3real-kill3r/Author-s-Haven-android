package com.example.kamran.logingreentheme.model.Articles;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Artcl {
    @SerializedName("articles")
    @Expose
    private List<Article> articles = null;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

}
