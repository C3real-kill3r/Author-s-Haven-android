package com.example.kamran.logingreentheme;

import com.example.kamran.logingreentheme.model.Profile;
import com.example.kamran.logingreentheme.model.Topic;
import com.example.kamran.logingreentheme.model.User;

import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {


    @FormUrlEncoded
    @POST("users/")
    Call<ResponseBody> createUser(
            @FieldMap HashMap<String, String> data
            );

    @FormUrlEncoded
    @POST("users/login/")
    Call<User> loginUser(
            @FieldMap HashMap<String, String> data
            );

    @GET("profiles/")
    Call<List<Profile>> listProfiles();

    @GET("topics/")
    Call<List<Topic>> getTopics();

}
