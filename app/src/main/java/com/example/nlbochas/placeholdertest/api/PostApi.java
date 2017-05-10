package com.example.nlbochas.placeholdertest.api;

import com.example.nlbochas.placeholdertest.data.PostsFromApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostApi  {

    @GET("/posts")
    Call<List<PostsFromApi>> getPosts();
}