package com.example.kamran.logingreentheme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Disability {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("profile")
    @Expose
    private Integer profile;
    @SerializedName("disability")
    @Expose
    private Integer disability;
    @SerializedName("cause")
    @Expose
    private String cause;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProfile() {
        return profile;
    }

    public void setProfile(Integer profile) {
        this.profile = profile;
    }

    public Integer getDisability() {
        return disability;
    }

    public void setDisability(Integer disability) {
        this.disability = disability;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
