package com.example.kamran.logingreentheme;

import com.example.kamran.logingreentheme.model.Articles.Article;
import com.example.kamran.logingreentheme.model.Person;
import com.example.kamran.logingreentheme.model.Profiles.Profile;

import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Api {


    @POST("users/")
    Call<ResponseBody> createUser(
            @Body HashMap User
            );

    @POST("users/login/")
    Call<Person> loginUser(
            @Body HashMap User
            );

    @FormUrlEncoded
    @POST("account/forgot_password/")
    Call<ResponseBody> forgotPassword(
            @FieldMap HashMap<String, String> data);

    @GET("profiles/")
    Call<List<Profile>> myProfile(@Header("Authorization") String token);

    @FormUrlEncoded
    @PUT("profiles/mine/")
    Call<Profile> editmyProfile(
            @Header("Authorization") String token,
            @FieldMap HashMap<String, String> data);

    @GET("articles")
    Call<List<Article>> getArticles(@Header("Authorization") String token);

}
