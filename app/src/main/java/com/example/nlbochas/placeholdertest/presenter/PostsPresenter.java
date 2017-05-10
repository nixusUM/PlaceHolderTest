package com.example.nlbochas.placeholdertest.presenter;

import com.example.nlbochas.placeholdertest.App;
import com.example.nlbochas.placeholdertest.R;
import com.example.nlbochas.placeholdertest.data.DbManager;
import com.example.nlbochas.placeholdertest.data.LocalPosts;
import com.example.nlbochas.placeholdertest.data.PostsFromApi;
import com.example.nlbochas.placeholdertest.views.PostView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsPresenter implements Presenter {

    private PostView postView;
    private DbManager dbManager;
    private List<LocalPosts> postsList;

    public PostsPresenter(PostView postView) {
        this.postView = postView;
    }

    @Override
    public void onResume() {
        if (!App.getInstance().isNetworkAvailable()) {
            postView.showErrorToast();
            postView.showPosts(dbManager.getLocalcPosts());
        } else {
            try {
                getPostFromApi();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onCreate() {
        dbManager = new DbManager();
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onPause() {

    }

    private void getPostFromApi() throws IOException {
        postView.showLoading();
        App.getPostApi().getPosts().enqueue(new Callback<List<PostsFromApi>>() {
            @Override
            public void onResponse(Call<List<PostsFromApi>> call, Response<List<PostsFromApi>> response) {
                postView.hideLoading();
                postsList = new ArrayList<>();
                for (PostsFromApi postsFromApi : response.body()) {
                    postsList.add(new LocalPosts(postsFromApi.getId(), postsFromApi.getTitle(), postsFromApi.getBody()));
                }
                if (dbManager.getLocalcPosts().size() != 0) {
                    dbManager.saveLocalPosts(initFavourites());
                } else {
                    dbManager.saveLocalPosts(postsList);
                }
                postView.showPosts(dbManager.getLocalcPosts());
            }

            @Override
            public void onFailure(Call<List<PostsFromApi>> call, Throwable t) {
                postView.hideLoading();
                postView.showPosts(dbManager.getLocalcPosts());
                postView.showErrorToast();
            }
        });
    }

    private List<LocalPosts> initFavourites() {
        for (LocalPosts localPostses : dbManager.getLocalcPosts()) {
            for (LocalPosts posts : postsList) {
                if (localPostses.getId() == (posts.getId()) && localPostses.isFavourite()) {
                    posts.setFavourite(true);
                }
            }
        }
        return postsList;
    }
}

