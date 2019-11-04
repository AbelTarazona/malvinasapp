package com.example.malvinasapp.utils;

import com.example.malvinasapp.model.User;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IMyAPI {


    @GET("login/login/{username}/{password}?apiKey=0effd594-43b8-4274-a07e-2f931e65de8b")
    Call<List<User>> auth_login(
            @Path("username") String username,
            @Path("password") String password
    );


}
