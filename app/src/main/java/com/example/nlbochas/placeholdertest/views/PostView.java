package com.example.nlbochas.placeholdertest.views;

import com.example.nlbochas.placeholdertest.data.LocalPosts;

import java.util.List;

public interface PostView {
    void showPosts(List<LocalPosts> postsFromApiList);
    void showLoading();
    void hideLoading();
    void showErrorToast();
}
