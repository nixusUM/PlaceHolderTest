package com.example.nlbochas.placeholdertest.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nlbochas.placeholdertest.R;
import com.example.nlbochas.placeholdertest.data.DbManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostDetails extends Fragment {

    @BindView(R.id.txt_title)
    TextView postTitle;
    @BindView(R.id.txt_body)
    TextView postBody;
    @BindView(R.id.ic_favourite)
    ImageView favourite;

    private int postid;
    private DbManager dbManager;

    private static final String POST = "post";

    public static PostDetails newInstance(int postId)  {
        PostDetails fragment = new PostDetails();
        Bundle bundle = new Bundle();
        bundle.putInt(POST, postId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        dbManager = new DbManager();
        if (arguments != null) {
            postid = arguments.getInt(POST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.post_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPosts();
    }

    private void initPosts() {
        postTitle.setText(Html.fromHtml(dbManager.getPost(postid).getTitle()));
        postBody.setText(Html.fromHtml(dbManager.getPost(postid).getBody()));
        if (dbManager.getPost(postid).isFavourite()) {
            favourite.setImageDrawable(getResources().getDrawable(android.R.drawable.star_on));
        } else {
            favourite.setImageDrawable(getResources().getDrawable(android.R.drawable.star_off));
        }
    }

    @OnClick(R.id.ic_favourite)
    void favouriteClicked() {
        if (!dbManager.getPost(postid).isFavourite()) {
            dbManager.updateFavourite(dbManager.getPost(postid), true);
            favourite.setImageDrawable(getResources().getDrawable(android.R.drawable.star_on));
        } else {
            openDialog();
        }
    }

    public void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle(R.string.remove_favourite);
        builder.setPositiveButton(R.string.yes, (dialog, id) -> {
            dbManager.updateFavourite(dbManager.getPost(postid), false);
            favourite.setImageDrawable(getResources().getDrawable(android.R.drawable.star_off));
        }).setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }
}