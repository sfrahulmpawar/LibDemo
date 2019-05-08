package com.example.libdemoapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity implements View.OnClickListener, LocationListener {


    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    LocationManager locationManager;
    String provider;
    User user;
    UserRealmController userRealmController;
    EditText firstNameEdit,lastNameEdit,roleEdit;
    Button submitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        user = new User();
        userRealmController = new UserRealmController();
        firstNameEdit = findViewById(R.id.firstNameEdit);
        lastNameEdit = findViewById(R.id.lastNameEdit);
        roleEdit = findViewById(R.id.roleEdit);
        submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, true);
        if (provider != null && !provider.equals("")) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location location = locationManager.getLastKnownLocation(provider);
            locationManager.requestLocationUpdates(provider, 15000, 1, this);
            if (location != null) {
                onLocationChanged(location);
            } else {
                Log.d("Status " , "No location found");
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submitBtn:
                if (TextUtils.isEmpty(firstNameEdit.getText())){
                    firstNameEdit.setError("Please enter first name");
                }else if (TextUtils.isEmpty(lastNameEdit.getText())){
                    lastNameEdit.setError("Please enter last name");
                }else if (TextUtils.isEmpty(roleEdit.getText())){
                    roleEdit.setError("Please enter role");
                }else {
                    String latitude = PreferenceHelper.getValueString(InputActivity.this,LATITUDE);
                    String longitude = PreferenceHelper.getValueString(InputActivity.this,LONGITUDE);
                    String fName = firstNameEdit.getText().toString();
                    String lName = lastNameEdit.getText().toString();
                    String role = roleEdit.getText().toString();
                    user.setFirst_name(fName);
                    user.setLast_name(lName);
                    user.setRole(role);
                    user.setLat(latitude);
                    user.setLon(longitude);
                    userRealmController.addUserData(user);
                    Intent intent = new Intent(InputActivity.this,MainActivity.class);
                    startActivity(intent);
                }
        }
    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double lon = location.getLongitude();
        String latitude = String.valueOf(lat);
        String longitude =String.valueOf(lon);
        PreferenceHelper.setValueString(InputActivity.this,LATITUDE,latitude);
        PreferenceHelper.setValueString(InputActivity.this,LONGITUDE,longitude);
        Log.d("Location", "Latitude: " + lat + " Longitude: " + lon);
    }
}
