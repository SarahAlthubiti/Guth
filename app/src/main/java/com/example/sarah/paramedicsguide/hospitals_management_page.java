package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class hospitals_management_page extends AppCompatActivity {

    ImageView imageView_home_ic,imageView_logout_ic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals_management_page);
        imageView_home_ic=(ImageView)findViewById(R.id.imageView_home_ic);
        imageView_home_ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(hospitals_management_page.this, selection_paramedic_hospital_page.class);
                startActivity(i);
            }
        });
        imageView_logout_ic=(ImageView)findViewById(R.id.imageView_logout_ic);
        imageView_logout_ic.setVisibility(View.INVISIBLE);

    }

    public void toAddHospital(View view) {
        Intent intent = new Intent(hospitals_management_page.this,add_hospitals_page.class);
        startActivity(intent);
    }

    public void toDisplayHospitals(View view) {
      //  Intent intent = new Intent(hospitals_management_page.this,display_modify_delete_hospital.class);
       // startActivity(intent);
        Intent i =new Intent(hospitals_management_page.this, activity_display_modify_delete_hospital2.class);
        startActivity(i);
    }
}
