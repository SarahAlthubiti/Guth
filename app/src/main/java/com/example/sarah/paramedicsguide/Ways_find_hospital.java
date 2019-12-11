package com.example.sarah.paramedicsguide;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Ways_find_hospital extends AppCompatActivity {
    static List<Hospital> hospitalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ways_find_hospital);
        hospitalList= new ArrayList<Hospital>();
//CAER 1 DISPLAY
        RelativeLayout l1 = (RelativeLayout )findViewById(R.id.layout_desplay);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Ways_find_hospital.this,Filtering.class);
                startActivity(i);
            }
        });


//CARD 2 SEARCH
        RelativeLayout l2 = (RelativeLayout)findViewById(R.id.layout_search);
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Ways_find_hospital.this,Search.class);
                startActivity(i);
            }
        });


    }
    //***********
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"لا يمكن العوده ، يجب انهاء الحالة",Toast.LENGTH_SHORT).show();
    }
}
