package com.example.libdemoapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.mylibrary.GetData;
import com.example.mylibrary.Users;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int REQUEST_PERMISSIONS = 101;
    Button submit;
    Users user;
    UserRealmController userRealmController;
    GetData getData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submit = findViewById(R.id.submit);
        user = new Users();
        getData = new GetData(this);
        userRealmController = new UserRealmController();
        boolean location = checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
        boolean loc = checkPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        if (!loc && !location) {
            if (Build.VERSION.SDK_INT >= 23) {
                requestPermissions();
            }
        }
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submit){
            String fName = "";
            String lName = "";
            String role = "";
            String latitude = "";
            String longitude = "";
            boolean userAvailable = userRealmController.isUserAvailable();
            if (userAvailable){
                RealmResults<User> realmResults = userRealmController.getUserData();
                if (realmResults.get(2) != null) {
                    fName = realmResults.get(2).getFirst_name();
                }else {
                    fName = "NULL";
                }
                if (realmResults.get(2) != null) {
                    lName = realmResults.get(2).getLast_name();
                }else {
                    lName = "NULL";
                }
                if (realmResults.get(2) != null) {
                    role = realmResults.get(2).getRole();
                }else {
                    role = "NULL";
                }
                if (realmResults.get(2) != null) {
                    latitude = realmResults.get(2).getLat();
                }else {
                    latitude = "NULL";
                }
                if (realmResults.get(2) != null) {
                    longitude = realmResults.get(2).getLon();
                }else {
                    longitude = "NULL";
                }
                user.setFirst_name(fName);
                user.setLast_name(lName);
                user.setRole(role);
                user.setLat(latitude);
                user.setLon(longitude);
                getData.insertUser(user);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.input_menu) {
            Intent i = new Intent(MainActivity.this, InputActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSIONS){
            for (int i=0;i<grantResults.length;i++){
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED){
                    if (Build.VERSION.SDK_INT >= 23) {
                        shouldShowRequestPermissionRationale(permissions[i]);
                    }
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestPermissions(){
        ArrayList<String> permissionsArrayList = new ArrayList<>();
        permissionsArrayList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        permissionsArrayList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        List<String> remainingPermissions = new ArrayList<>();
        for (String permission : permissionsArrayList){
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                remainingPermissions.add(permission);
            }
        }
        requestPermissions(remainingPermissions.toArray(new String[remainingPermissions.size()]),REQUEST_PERMISSIONS);
    }

    public boolean checkPermission(String permission){
        int res = checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }
}
