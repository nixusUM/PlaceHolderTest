package com.example.nlbochas.placeholdertest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.nlbochas.placeholdertest.views.PostsFragment;
import com.victor.loading.rotate.RotateLoading;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rotate_loading)
    RotateLoading progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showPostsFragment();
    }

    public void showPostsFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_content, new PostsFragment(), PostsFragment.class.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    public void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_content, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    public void hideLoading() {
        runOnUiThread(() -> {
            if (progress != null) {
                progress.setVisibility(View.INVISIBLE);
                progress.stop();
            }
        });
    }

    public void showLoading() {
        runOnUiThread(() -> {
            if (progress != null) {
                progress.setVisibility(View.VISIBLE);
                progress.bringToFront();
                progress.start();
            }
        });
    }

    public void showErrorToast() {
        runOnUiThread(() -> Toast.makeText(getApplicationContext(), R.string.no_internet, Toast.LENGTH_SHORT).show());
    }
}
