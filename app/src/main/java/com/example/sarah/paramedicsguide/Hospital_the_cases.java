package com.example.sarah.paramedicsguide;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Hospital_the_cases extends AppCompatActivity {
    ImageView imageView_home_ic,imageView_logout_ic;
    RecyclerView recyclerView;
    Adapor_the_cases adapor_the_cases;
    DatabaseReference database;
    static Patient selected_patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_the_cases);

        imageView_logout_ic=(ImageView)findViewById(R.id.imageView_logout_ic);
        imageView_logout_ic.setVisibility(View.INVISIBLE);

        imageView_home_ic=(ImageView)findViewById(R.id.imageView_home_ic);
        imageView_home_ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Hospital_the_cases.this, Hospital_home_page.class);
                startActivity(i);
            }
        });
        Hospital_home_page.getNewCaseByQuery(Hospital_the_cases.this);
        recyclerView = (RecyclerView) findViewById(R.id.rv_the_cases);


        if( Hospital_home_page.list_newCase.size()==0){
            Toast.makeText(Hospital_the_cases.this,"لا توجد حالة  ", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(Hospital_the_cases.this,Hospital_home_page.class);
            startActivity(i);
        }
       int size = Hospital_home_page.list_newCase.size();
        for(int i  =0 ;i<size;i++) {
            NewCase newCase= Hospital_home_page.list_newCase.get(i);

            Query query2 = FirebaseDatabase.getInstance().getReference().child("Patient")
                    .orderByChild("key")
                    .equalTo(newCase.getKey_patient());
            Hospital_home_page.list_patient.clear();
            query2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Patient p = new Patient();
                        p = snapshot.getValue(Patient.class);
                        Hospital_home_page.list_patient.add(p);

                    }
                    display();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }

    }

public void display(){
    adapor_the_cases= new Adapor_the_cases(this,Hospital_home_page.list_patient);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(adapor_the_cases);


}

    @Override
    public void onBackPressed() {
        Dsplay_V1.vitalSigns=null;
        Intent i= new Intent(Hospital_the_cases.this,Hospital_home_page.class);
        startActivity(i);


    }
}

