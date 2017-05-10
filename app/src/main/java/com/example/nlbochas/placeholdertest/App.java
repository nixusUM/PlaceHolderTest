package com.example.nlbochas.placeholdertest;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

import com.example.nlbochas.placeholdertest.api.PostApi;

import io.realm.Realm;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static App app;
    private static PostApi postApi;
    private static final String JSON_URL = "http://jsonplaceholder.typicode.com/";

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Realm.init(this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JSON_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        postApi = retrofit.create(PostApi.class);
    }

    public static App getInstance() {
        return app;
    }

    public static PostApi getPostApi() {
        return postApi;
    }

    public boolean isNetworkAvailable() {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
