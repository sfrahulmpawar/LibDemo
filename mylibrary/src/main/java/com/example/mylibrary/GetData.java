package com.example.mylibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class GetData {
    @SuppressLint("StaticFieldLeak")
    private Context context;

    public GetData(Context context) {
        this.context = context;
    }

    public void insertUser(final Users user){
        final String url = "http://10.10.11.105/library_data/insert_user.php";
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("success")){
                    Toast.makeText(context,"Inserted successfully",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Error inserting data",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("first_name",user.getFirst_name());
                param.put("last_name",user.getLast_name());
                param.put("role",user.getRole());
                param.put("latitude",user.getLat());
                param.put("longitude",user.getLon());
                return param;
            }
        };
        queue.add(stringRequest);
    }
}
