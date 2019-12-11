package com.example.sarah.paramedicsguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.RadioButton;
import android.content.Intent;
import android.view.View;


public class sendVs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_vs);

        CardView cv_vice = (CardView)findViewById(R.id.cv_voice);
        CardView cv_manual=(CardView)findViewById(R.id.cv_manual);

        cv_vice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vitalAndDrugs.valueOfInterfaceToShow=2;
                Intent in= new Intent(sendVs.this,select_voice_language.class);
                startActivity(in);
            }
        });

        cv_manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vitalAndDrugs.valueOfInterfaceToShow=1;
                Intent in= new Intent(sendVs.this,vitalByManuall.class);
                startActivity(in);
            }
        });
    }

}
