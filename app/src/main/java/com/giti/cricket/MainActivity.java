package com.giti.cricket;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.Volley;


import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    Context context;
    private List<Model> modelList;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    String t11,t22;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("CricFan");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#FF3700B3"));

        getSupportActionBar().setBackgroundDrawable(colorDrawable);


        modelList=new ArrayList<>();

        init();
        context=MainActivity.this;
        loadData();
    }

    private void init() {

        recyclerView=findViewById(R.id.match_list);


    }



    private void loadData() {

        RequestQueue requestQueue =Volley.newRequestQueue(this);
        String url="https://cricapi.com/api/matches?apikey=qWzCW32WJEfbKy1qgdwWDY6pspj2";





        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {


            try {
                JSONArray jsonArray = response.getJSONArray("matches");
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        String id=jsonArray.getJSONObject(i).getString("unique_id");
                         t11 = jsonArray.getJSONObject(i).getString("team-1");
                        t22 = jsonArray.getJSONObject(i).getString("team-2");
                        String matchType = jsonArray.getJSONObject(i).getString("type");
                        String matchStatus = jsonArray.getJSONObject(i).getString("matchStarted");
                        if(matchStatus.equals("true")){
                            matchStatus="Match Started";
                        }else{
                            matchStatus="Match Not Started";
                        }

                        String dateTimeGMT = jsonArray.getJSONObject(i).getString("dateTimeGMT");

                        SimpleDateFormat format1= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                        format1.setTimeZone(TimeZone.getTimeZone(dateTimeGMT));
                        Date date=format1.parse(dateTimeGMT);

                        SimpleDateFormat format2= new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
                        format2.setTimeZone(TimeZone.getTimeZone("GMT"));

                        String dateTime=format2.format(date);


                        Model model=new Model(id,t11,t22,matchType,matchStatus,dateTime);
                        modelList.add(model);
                        adapter = new MyAdapter(this,modelList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(this));
                        recyclerView.setAdapter(adapter);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }catch(Exception e){e.printStackTrace();}
                    Log.e("Responsse is : " , response.toString());


        },error -> {
           Log.e("Error is : " , error.toString());
        });
        requestQueue.add(request);
    }
}