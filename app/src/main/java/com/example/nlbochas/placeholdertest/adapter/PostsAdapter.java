package com.example.nlbochas.placeholdertest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nlbochas.placeholdertest.MainActivity;
import com.example.nlbochas.placeholdertest.R;
import com.example.nlbochas.placeholdertest.data.LocalPosts;
import com.example.nlbochas.placeholdertest.views.PostDetails;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private List<LocalPosts> postsList = new ArrayList<>();
    private Context context;

    public PostsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_posts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LocalPosts post = postsList.get(position);
        holder.postTitle.setText(Html.fromHtml(post.getTitle()));
        holder.postBody.setText(Html.fromHtml(post.getBody()));
        initFavourite(holder, post);
        holder.lytPosts.setOnClickListener(v -> ((MainActivity) context).showFragment(PostDetails.newInstance(post.getId())));
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public void setPosts(List<LocalPosts> postsFromApiList) {
        this.postsList = postsFromApiList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.lyt_posts)
        LinearLayout lytPosts;
        @BindView(R.id.txt_title)
        TextView postTitle;
        @BindView(R.id.txt_body)
        TextView postBody;
        @BindView(R.id.ic_favourite)
        ImageView favourite;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private void initFavourite(ViewHolder holder, LocalPosts post) {
        if (post.isFavourite()) {
            holder.favourite.setImageDrawable(context.getResources().getDrawable(android.R.drawable.star_on));
        } else {
            holder.favourite.setImageDrawable(context.getResources().getDrawable(android.R.drawable.star_off));
        }
    }
}