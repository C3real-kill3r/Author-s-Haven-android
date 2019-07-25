package com.example.kamran.logingreentheme;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {


    @FormUrlEncoded
    @POST("users/")
    Call<ResponseBody> createUser(
            @FieldMap HashMap<String, String> data
            );

    @FormUrlEncoded
    @POST("users/login/")
    Call<ResponseBody> loginUser(
            @FieldMap HashMap<String, String> data
            );
}
