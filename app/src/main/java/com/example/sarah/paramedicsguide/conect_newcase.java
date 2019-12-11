package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class conect_newcase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conect_newcase);
    }

    public void conect(View view) {
        Intent in =new Intent(this,vitalAndDrugs.class);
        startActivity(in);
    }
}
