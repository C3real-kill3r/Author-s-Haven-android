package com.example.kamran.logingreentheme.model.Articles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("published")
    @Expose
    private Boolean published;
    @SerializedName("avg_rating")
    @Expose
    private AvgRating avgRating;
    @SerializedName("author")
    @Expose
    private Author author;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("tags")
    @Expose
    private List<Object> tags = null;
    @SerializedName("reactions")
    @Expose
    private Reactions reactions;
    @SerializedName("share_article")
    @Expose
    private ShareArticle shareArticle;
    @SerializedName("read_time")
    @Expose
    private Double readTime;
    @SerializedName("favourited")
    @Expose
    private Boolean favourited;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public AvgRating getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(AvgRating avgRating) {
        this.avgRating = avgRating;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public Reactions getReactions() {
        return reactions;
    }

    public void setReactions(Reactions reactions) {
        this.reactions = reactions;
    }

    public ShareArticle getShareArticle() {
        return shareArticle;
    }

    public void setShareArticle(ShareArticle shareArticle) {
        this.shareArticle = shareArticle;
    }

    public Double getReadTime() {
        return readTime;
    }

    public void setReadTime(Double readTime) {
        this.readTime = readTime;
    }

    public Boolean getFavourited() {
        return favourited;
    }

    public void setFavourited(Boolean favourited) {
        this.favourited = favourited;
    }
}

