package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Dsplay_V3 extends AppCompatActivity {
    ImageView imageView11,imageView12,imageView13,imageView14,imageView_track;
    ImageView imageView_home_ic,imageView_logout_ic;
    TextView textView_sened_vital_voice1,textView_sened_vital_voice2,textView_sened_vital_voice3;
    private DatabaseReference refGetVital;
    private ValueEventListener refGetVitalLisener;
    boolean findVitalSign=false;
    String string="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsplay__v3);
        imageView_home_ic=(ImageView)findViewById(R.id.imageView_home_ic);
        imageView_home_ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Dsplay_V3.this, Hospital_home_page.class);
                startActivity(i);
            }
        });

        imageView_logout_ic=(ImageView)findViewById(R.id.imageView_logout_ic);
        imageView_logout_ic.setVisibility(View.INVISIBLE);

        Hospital_home_page.getNewCaseByQuery(Dsplay_V3.this);
        imageView11=(ImageView)findViewById(R.id.imageView11);
        imageView12=(ImageView)findViewById(R.id.imageView12);
        imageView13=(ImageView)findViewById(R.id.imageView13);
        imageView14=(ImageView)findViewById(R.id.imageView14);
        imageView_track=(ImageView)findViewById(R.id.imageView_track);
        textView_sened_vital_voice1=(TextView)findViewById(R.id.textView_sened_vital_voice1);
        textView_sened_vital_voice2=(TextView)findViewById(R.id.textView_sened_vital_voice2);
        textView_sened_vital_voice3=(TextView)findViewById(R.id.textView_sened_vital_voice3);

        imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Dsplay_V3.this,Dsplay_V1.class);
                startActivity(i);
            }
        });
        imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Dsplay_V3.this,Dsplay_V2.class);
                startActivity(i);
            }
        });
        imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Dsplay_V3.this,Dsplay_V4.class);
                startActivity(i);
            }
        });
        imageView_track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Dsplay_V3.this,MapsActivity2.class);
                startActivity(i);
            }
        });


        getVitalSignFromFirebase();
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(Dsplay_V3.this,MapsActivity2.class);
        startActivity(i);


    }

    public void getVitalSignFromFirebase(){
        //refGetVital = FirebaseDatabase.getInstance().getReference().child("VoiceToText");
        Query query2 = FirebaseDatabase.getInstance().getReference().child("VoiceToText")
                .orderByChild("key")
                .equalTo(Hospital_the_cases.selected_patient.key);
        query2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                VoiceToText v=snapshot.getValue(VoiceToText.class);
                string+=v.text+"\n";
                textView_sened_vital_voice1.setText(string);
                findVitalSign = true;
              }
                if(findVitalSign==false)
                    Toast.makeText(Dsplay_V3.this,"لم يتم ارسال العلامات الحيوية حتى الآن ", Toast.LENGTH_SHORT).show();
            }

            @Override

            public void onCancelled(DatabaseError databaseError) {

            }
        });
            }



        }



