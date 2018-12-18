package com.example.hrida.onlinedb;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class University {
    private ArrayList<Student> AllStudents;
    private Context context;
    private String key;

    public University(Context context) {
        this.context = context;
        AllStudents = new ArrayList<Student>();
    }


    public void setKey(String key) {

        this.key = key;
    }

    public void updateImage(final ImageView iv) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);

        String url = "https://basseldhaini.000webhostapp.com/getImage.php";

        ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                iv.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.FIT_CENTER, Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "That didn't work!", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(request);

    }

    public void addStudent(Student s) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        final int id = s.getId();
        final String name = s.getName();

        String url = "https://basseldhaini.000webhostapp.com/save.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "That didn't work!", Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id",String.valueOf(id) );
                params.put("name", name);
                params.put("key", key);

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }

        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void updateAllStudents(final ListView lsAllStudents) {
        String url = "https://basseldhaini.000webhostapp.com/getAllStudents.php";
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);

        // Request a json response from the provided URL.
        JsonArrayRequest jsonRequest = new JsonArrayRequest( url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray ja) {
                        try {
                            for (int i = 0;i < ja.length();i++) {
                                JSONObject st = ja.getJSONObject(i);
                                int id = Integer.parseInt(st.getString("sid"));
                                String name = st.getString("name");
                                AllStudents.add(new Student(id, name));

                            }
                            lsAllStudents.setAdapter(new ArrayAdapter<Student>(context,
                                    android.R.layout.simple_list_item_1, AllStudents));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("error", error.toString());
            }
        });
        // Add the request to the RequestQueue.
        queue.add(jsonRequest);
    }

    public void getStudentName(int id) {
        String url = "https://basseldhaini.000webhostapp.com/getStudentName.php?id="+id;

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        // Request a json response from the provided URL.
        StringRequest jsonRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String name) {

                        Toast.makeText(context, "Student name: " + name, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("error", error.toString());
            }
        });
        // Add the request to the RequestQueue.
        queue.add(jsonRequest);
    }

    public ArrayList<Student> getAllStudents() {
        return AllStudents;
    }


}

