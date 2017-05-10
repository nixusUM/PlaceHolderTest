package com.example.nlbochas.placeholdertest.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nlbochas.placeholdertest.MainActivity;
import com.example.nlbochas.placeholdertest.R;
import com.example.nlbochas.placeholdertest.adapter.PostsAdapter;
import com.example.nlbochas.placeholdertest.data.LocalPosts;
import com.example.nlbochas.placeholdertest.presenter.PostsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostsFragment extends Fragment implements PostView {

    @BindView(R.id.posts_list)
    RecyclerView postsList;

    private PostsPresenter presenter;
    private PostsAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new PostsPresenter(this);
        presenter.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new PostsAdapter(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        postsList.setLayoutManager(layoutManager);
        postsList.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void showPosts(List<LocalPosts> postsFromApiList) {
        adapter.setPosts(postsFromApiList);
    }

    @Override
    public void showLoading() {((MainActivity) getActivity()).showLoading();
    }

    @Override
    public void hideLoading() {
        ((MainActivity) getActivity()).hideLoading();
    }

    @Override
    public void showErrorToast() {
        ((MainActivity) getActivity()).showErrorToast();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
