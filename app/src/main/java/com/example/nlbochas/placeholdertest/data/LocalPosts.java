package com.example.nlbochas.placeholdertest.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LocalPosts extends RealmObject {

    @PrimaryKey
    private int id;
    private String title;
    private String body;
    private boolean favourite;

    public LocalPosts() {

    }

    public LocalPosts(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}

