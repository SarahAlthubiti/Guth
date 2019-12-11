package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RadioButton;

public class select_voice_language extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_voice_language);
        CardView cv_arabi = (CardView)findViewById(R.id.cv_arabi);
        CardView cv_english=(CardView)findViewById(R.id.cv_english);

        cv_arabi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in= new Intent(select_voice_language.this,by_voice_Arabic.class);
                startActivity(in);
            }
        });

        cv_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in= new Intent(select_voice_language.this,by_voice.class);
                startActivity(in);
            }
        });
    }
 }
