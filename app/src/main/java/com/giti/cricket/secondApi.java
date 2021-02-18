package com.giti.cricket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;



import org.json.JSONObject;

import java.util.List;

public class secondApi extends AppCompatActivity {

    TextView t21,t22,score,ms,date;
    String ddate;
    String url="https://cricapi.com/api/cricketScore?apikey=qWzCW32WJEfbKy1qgdwWDY6pspj2&unique_id=";

    //String url="https://cricapi.com/api/cricketScore?apikey=qWzCW32WJEfbKy1qgdwWDY6pspj2&unique_id=" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_api);

        getSupportActionBar().setTitle("Match Details");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#FF3700B3"));

        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        Intent i=getIntent();
        String id=i.getStringExtra("idid");
         ddate=i.getStringExtra("date");
      url=url+id;

        t21=findViewById(R.id.t21);
        t22=findViewById(R.id.t22);
        date=findViewById(R.id.date);
        score=findViewById(R.id.score);
        ms=findViewById(R.id.ms);

        loaddData();
    }



    public void loaddData() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);




       StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {

               try {
                   JSONObject jsonObject = new JSONObject(response);

             //      String tscore=jsonObject.getString("score");
                   String tt21 = jsonObject.getString("team-1");
                   String tt22 = jsonObject.getString("team-2");
                // String tdesc = jsonObject.getString("description");
                   String tmatchStatus = jsonObject.getString("matchStarted");
                   if(tmatchStatus.equals("true")){
                       tmatchStatus="Match Started";
                   }else {
                       tmatchStatus = "Match Not Started";
                   }
                   t21.setText(tt21);
                   t22.setText(tt22);
                   date.setText(ddate);
                  // score.setText(tscore);
                   ms.setText(tmatchStatus);

                   try{
                       String tscore=jsonObject.getString("score");
                       score.setText(tscore);
                   }catch(Exception e){
                       Toast.makeText(secondApi.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                   }



               }catch (Exception e){e.printStackTrace();}


               Log.e("Response is : " , response);

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Log.e("Error is : " , error.toString());
           }
       });


        requestQueue.add(stringRequest);
    }




}