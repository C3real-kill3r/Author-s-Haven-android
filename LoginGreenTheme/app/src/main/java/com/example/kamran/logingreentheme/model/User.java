package com.example.kamran.logingreentheme.model;

public class User {
    private int id;
    private String email;
    private String token;

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }
}
