package com.example.nlbochas.placeholdertest.data;

import java.util.List;

import io.realm.Realm;

public class DbManager {

    private Realm realm;

    public DbManager() {
        realm = Realm.getDefaultInstance();
    }

    public List<LocalPosts> getLocalcPosts() {
        return realm.where(LocalPosts.class).findAll();
    }

    public void saveLocalPosts(List<LocalPosts> localPostses) {
        realm.executeTransaction(realm -> {
            realm.copyToRealmOrUpdate(localPostses);
        });
    }

    public LocalPosts getPost(int postId) {
        return realm.where(LocalPosts.class).equalTo("id", postId).findFirst();
    }

    public void updateFavourite(LocalPosts localPosts, boolean isFavourite) {
        realm.executeTransaction(realm -> {
            localPosts.setFavourite(isFavourite);
            realm.copyToRealmOrUpdate(localPosts);
        });
    }
}
