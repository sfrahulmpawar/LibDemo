package com.example.libdemoapp;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class UserRealmController {
    public void addUserData(User user){
        Realm realm = RealmController.getInstance().getRealm();

        if (!realm.isInTransaction())
            realm.beginTransaction();

        realm.copyToRealm(user);
        realm.commitTransaction();
    }

    public RealmResults<User> getUserData() {
        Realm realm = RealmController.getInstance().getRealm();
        RealmQuery<User> query = realm.where(User.class);
        RealmResults<User> user = query.findAll();
        return user;
    }

    public boolean isUserAvailable() {
        Realm realm = RealmController.getInstance().getRealm();
        RealmQuery<User> query = realm.where(User.class);
        RealmResults<User> Users = query.findAll();
        return Users.size() > 0;
    }
}
