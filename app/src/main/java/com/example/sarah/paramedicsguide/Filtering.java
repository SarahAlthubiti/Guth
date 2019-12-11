package com.example.sarah.paramedicsguide;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class Filtering extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance() ;
    DatabaseReference myRef = database.getReference("Medications");

    List<Hospital> hospitals ;
    Hospital hospital;
    private CheckBox ch1,ch2,ch3,ch4,ch5,ch6,ch7;
    private Button bt;
    boolean can_go_to_map;

    //fierbase***
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtering);

        hospitals= new ArrayList<Hospital>();
        //getMultyData();
        Button but = (Button) findViewById(R.id.next);
        ch1=(CheckBox)findViewById(R.id.brain_check_box);
        ch2=(CheckBox)findViewById(R.id.accidents_check_box);
        ch3=(CheckBox)findViewById(R.id.bones_check_box);
        ch4=(CheckBox)findViewById(R.id.birth_check_box);
        ch5=(CheckBox)findViewById(R.id.other_check_box);

        getHospitalsFromFirebase();

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                filter();
            }}); }//end onCreat


    //******
    public  void getHospitalsFromFirebase(){

        FirebaseDatabase.getInstance().getReference().child("Hospital").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //hospitals.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Hospital module = snapshot.getValue(Hospital.class);
                    hospitals.add(module) ; } }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                }}); }

    public void filter(){
        int size = hospitals.size();
//***************All******************************
       if(ch1.isChecked()&&ch2.isChecked()&&ch3.isChecked() &&ch4.isChecked()&&ch5.isChecked()){
            for(int i = 0;i<size;i++){
                hospital=hospitals.get(i);
         if(hospital.other==true
                        &&hospital.birth==true
                        &&hospital.bones==true
                        &&hospital.accidents==true
                        &&hospital.brainAndNerves==true)
                { can_go_to_map=true;
                    Ways_find_hospital.hospitalList.add(hospital);
                } } }
//*******************Quaternary****************************
        else if(ch1.isChecked()&&ch2.isChecked()&&ch3.isChecked() &&ch4.isChecked()){
           for(int i = 0;i<size;i++){
               hospital=hospitals.get(i);
               if(hospital.birth==true
                       &&hospital.bones==true
                       &&hospital.accidents==true
                       &&hospital.brainAndNerves==true)
               { can_go_to_map=true;
                   Ways_find_hospital.hospitalList.add(hospital);
               } }}
       else if(ch2.isChecked()&&ch3.isChecked() &&ch4.isChecked()&&ch5.isChecked()){
           for(int i = 0;i<size;i++){
               hospital=hospitals.get(i);
               if(hospital.other==true
                       &&hospital.birth==true
                       &&hospital.bones==true
                       &&hospital.accidents==true)
               { can_go_to_map=true;
                   Ways_find_hospital.hospitalList.add(hospital);
               } } }
       else if(ch1.isChecked()&&ch2.isChecked()&&ch3.isChecked() &&ch5.isChecked()){
           for(int i = 0;i<size;i++){
               hospital=hospitals.get(i);
               if(hospital.other==true
                       &&hospital.bones==true
                       &&hospital.accidents==true
                       &&hospital.brainAndNerves==true)
               { can_go_to_map=true;
                   Ways_find_hospital.hospitalList.add(hospital);
               } } }
       else if(ch1.isChecked()&&ch3.isChecked() &&ch4.isChecked()&&ch5.isChecked()){
           for(int i = 0;i<size;i++){
               hospital=hospitals.get(i);
               if(hospital.other==true
                       &&hospital.birth==true
                       &&hospital.bones==true
                       &&hospital.brainAndNerves==true)
               { can_go_to_map=true;
                   Ways_find_hospital.hospitalList.add(hospital);
               } } }
      else if(ch1.isChecked()&&ch2.isChecked()&&ch4.isChecked()&&ch5.isChecked()){
           for(int i = 0;i<size;i++){
               hospital=hospitals.get(i);
               if(hospital.other==true
                       &&hospital.birth==true
                       &&hospital.accidents==true
                       &&hospital.brainAndNerves==true)
               { can_go_to_map=true;
                   Ways_find_hospital.hospitalList.add(hospital);
               } } }
//*****************Tripartite*************************
       else if(ch3.isChecked() &&ch4.isChecked()&&ch5.isChecked()){
           for(int i = 0;i<size;i++){
               hospital=hospitals.get(i);
               if(hospital.bones==true
                       &&hospital.accidents==true
                       &&hospital.brainAndNerves==true)
               { can_go_to_map=true;
                   Ways_find_hospital.hospitalList.add(hospital);
               } } }
       else if(ch2.isChecked() &&ch4.isChecked()&&ch5.isChecked()){
           for(int i = 0;i<size;i++){
               hospital=hospitals.get(i);
               if(hospital.birth==true
                       &&hospital.accidents==true
                       &&hospital.brainAndNerves==true)
               { can_go_to_map=true;
                   Ways_find_hospital.hospitalList.add(hospital);
               } } }
       else if(ch2.isChecked()&&ch3.isChecked()&&ch5.isChecked()){
           for(int i = 0;i<size;i++){
               hospital=hospitals.get(i);
               if(hospital.birth==true
                       &&hospital.bones==true
                       &&hospital.brainAndNerves==true)
               {can_go_to_map=true;
                   Ways_find_hospital.hospitalList.add(hospital);
               } } }
        else if(ch2.isChecked()&&ch3.isChecked() &&ch4.isChecked()){
            for(int i = 0;i<size;i++){
                hospital=hospitals.get(i);
                if(hospital.birth==true
                        &&hospital.bones==true
                        &&hospital.accidents==true)
                { can_go_to_map=true;
                    Ways_find_hospital.hospitalList.add(hospital);
                } } }
        else if(ch1.isChecked()&&ch4.isChecked()&&ch5.isChecked()){
            for(int i = 0;i<size;i++){
                hospital=hospitals.get(i);
                if(hospital.other==true
                        &&hospital.accidents==true
                        &&hospital.brainAndNerves==true)
                { can_go_to_map=true;
                    Ways_find_hospital.hospitalList.add(hospital);
                } } }
        else if(ch1.isChecked()&&ch3.isChecked()&&ch5.isChecked()){
            for(int i = 0;i<size;i++){
                hospital=hospitals.get(i);
                if(hospital.other==true
                        &&hospital.bones==true
                        &&hospital.brainAndNerves==true)
                { can_go_to_map=true;
                    Ways_find_hospital.hospitalList.add(hospital);
                } } }
        else if(ch1.isChecked()&&ch3.isChecked()&&ch4.isChecked()){
            for(int i = 0;i<size;i++){
                hospital=hospitals.get(i);
                if(hospital.other==true
                        &&hospital.bones==true
                        &&hospital.accidents==true)
                { can_go_to_map=true;
                    Ways_find_hospital.hospitalList.add(hospital);
                } } }
        else if(ch1.isChecked() &&ch2.isChecked()&&ch5.isChecked()){
            for(int i = 0;i<size;i++){
                hospital=hospitals.get(i);
                if(hospital.other==true
                        &&hospital.birth==true
                        &&hospital.brainAndNerves==true)
                { can_go_to_map=true;
                    Ways_find_hospital.hospitalList.add(hospital);
                } } }
        else if(ch1.isChecked() &&ch2.isChecked()&&ch4.isChecked()){
            for(int i = 0;i<size;i++){
                hospital=hospitals.get(i);
                if(hospital.other==true
                        &&hospital.birth==true
                        &&hospital.accidents==true)
                { can_go_to_map=true;
                    Ways_find_hospital.hospitalList.add(hospital);
                } } }
        else if(ch1.isChecked()&&ch2.isChecked()&&ch3.isChecked()){
            for(int i = 0;i<size;i++){
                hospital=hospitals.get(i);
                if(hospital.other==true
                        &&hospital.birth==true
                        &&hospital.bones==true)
                { can_go_to_map=true;
                    Ways_find_hospital.hospitalList.add(hospital);
                } } }

//*******************Pair*************************
       else if(ch1.isChecked()&&ch2.isChecked()){
           for(int i = 0;i<size;i++){
               hospital=hospitals.get(i);
               if(hospital.accidents==true
                       &&hospital.brainAndNerves==true)
               { can_go_to_map=true;
                   Ways_find_hospital.hospitalList.add(hospital);
               } } }
       else if(ch1.isChecked()&&ch3.isChecked()){
           for(int i = 0;i<size;i++){
               hospital=hospitals.get(i);
               if(hospital.bones==true
                       &&hospital.brainAndNerves==true)
               { can_go_to_map=true;
                   Ways_find_hospital.hospitalList.add(hospital);
               } } }
       else if(ch1.isChecked()&&ch4.isChecked()){
           for(int i = 0;i<size;i++){
               hospital=hospitals.get(i);
               if(hospital.birth==true
                       &&hospital.brainAndNerves==true)
               {can_go_to_map=true;
                   Ways_find_hospital.hospitalList.add(hospital);
               } } }
       else if(ch1.isChecked()&&ch5.isChecked()){
           for(int i = 0;i<size;i++){
               hospital=hospitals.get(i);
               if(hospital.other==true
                       &&hospital.brainAndNerves==true)
               { can_go_to_map=true;
                   Ways_find_hospital.hospitalList.add(hospital);
               } } }
       else if(ch2.isChecked()&&ch3.isChecked()){
           for(int i = 0;i<size;i++){
               hospital=hospitals.get(i);
               if(hospital.bones==true
                       &&hospital.accidents==true)
               { can_go_to_map=true;
                   Ways_find_hospital.hospitalList.add(hospital);
               } } }
       else if(ch2.isChecked()&&ch4.isChecked()){
           for(int i = 0;i<size;i++){
               hospital=hospitals.get(i);
               if(hospital.birth==true
                       &&hospital.accidents==true)
               { can_go_to_map=true;
                   Ways_find_hospital.hospitalList.add(hospital);
               } } }
       else if(ch2.isChecked()&&ch5.isChecked()){
           for(int i = 0;i<size;i++){
               hospital=hospitals.get(i);
               if(hospital.other==true
                       &&hospital.accidents==true)
               { can_go_to_map=true;
                   Ways_find_hospital.hospitalList.add(hospital);
               } } }
       else if(ch3.isChecked() &&ch4.isChecked()){
           for(int i = 0;i<size;i++){
               hospital=hospitals.get(i);
               if(hospital.birth==true
                       &&hospital.accidents==true)
               { can_go_to_map=true;
                   Ways_find_hospital.hospitalList.add(hospital);
               } } }
       else if(ch3.isChecked() &&ch5.isChecked()){
           for(int i = 0;i<size;i++){
               hospital=hospitals.get(i);
               if(hospital.other==true
                       &&hospital.bones==true)
               { can_go_to_map=true;
                   Ways_find_hospital.hospitalList.add(hospital);
               } } }
       else if(ch4.isChecked()&&ch5.isChecked()){
           for(int i = 0;i<size;i++){
               hospital=hospitals.get(i);
               if(hospital.other==true
                       &&hospital.birth==true)
               { can_go_to_map=true;
                   Ways_find_hospital.hospitalList.add(hospital);
               } } }
 //***********Individually****************
        else if(ch1.isChecked()){
           for(int i = 0;i<size;i++){
               hospital=hospitals.get(i);
               if(hospital.brainAndNerves==true)
               { can_go_to_map=true;
                   Ways_find_hospital.hospitalList.add(hospital);
               } } }
       else if(ch2.isChecked()){
           for(int i = 0;i<size;i++){
               hospital=hospitals.get(i);
               if(hospital.accidents==true)
               { can_go_to_map=true;
                   Ways_find_hospital.hospitalList.add(hospital);
               } } }
       else if(ch3.isChecked()){
           for(int i = 0;i<size;i++){
               hospital=hospitals.get(i);
               if(hospital.bones==true)
               { can_go_to_map=true;
                   Ways_find_hospital.hospitalList.add(hospital);
               } } }
       else if(ch4.isChecked()){
           for(int i = 0;i<size;i++){
               hospital=hospitals.get(i);
               if(hospital.birth==true)
               { can_go_to_map=true;
                   Ways_find_hospital.hospitalList.add(hospital);
               } } }
       else if(ch5.isChecked()){
           for(int i = 0;i<size;i++){
               hospital=hospitals.get(i);
               if(hospital.other==true)
               { can_go_to_map=true;
                   Ways_find_hospital.hospitalList.add(hospital);
               } } }
               else{}

        if(can_go_to_map){
            Toast.makeText(Filtering.this," !تم العتور", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(Filtering.this,MapsActivity.class);
            startActivity(i); }
        else{ Toast.makeText(Filtering.this,"لاتوجد مستشفى تجمع كل التخصصات المختارة !", Toast.LENGTH_SHORT).show(); }
    }
}
