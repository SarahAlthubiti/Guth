package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Dsplay_V4 extends AppCompatActivity {
    ImageView imageView11,imageView12,imageView13,imageView14,imageView_track;
    ImageView imageView_home_ic,imageView_logout_ic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsplay__v4);

        imageView_home_ic=(ImageView)findViewById(R.id.imageView_home_ic);
        imageView_home_ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Dsplay_V4.this, Hospital_home_page.class);
                startActivity(i);
            }
        });
        imageView_logout_ic=(ImageView)findViewById(R.id.imageView_logout_ic);
        imageView_logout_ic.setVisibility(View.INVISIBLE);


        Hospital_home_page.getNewCaseByQuery(Dsplay_V4.this);

        imageView11=(ImageView)findViewById(R.id.imageView11);
        imageView12=(ImageView)findViewById(R.id.imageView12);
        imageView13=(ImageView)findViewById(R.id.imageView13);
        imageView14=(ImageView)findViewById(R.id.imageView14);
        imageView_track=(ImageView)findViewById(R.id.imageView_track);

        imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Dsplay_V4.this,Dsplay_V1.class);
                startActivity(i);
            }
        });
        imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Dsplay_V4.this,Dsplay_V2.class);
                startActivity(i);
            }
        });
        imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Dsplay_V4.this,Dsplay_V3.class);
                startActivity(i);
            }
        });
        imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        imageView_track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Dsplay_V4.this,MapsActivity2.class);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed() {
       Intent i = new Intent(Dsplay_V4.this,MapsActivity2.class);
       startActivity(i);
    }
}
