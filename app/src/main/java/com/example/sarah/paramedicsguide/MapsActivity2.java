package com.example.sarah.paramedicsguide;

import android.content.Context;
import android.location.Location;
import android.os.ParcelUuid;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {

    TextView textView_hospital_id_patient,textView_hospital_bed,textView_name_dsplay2,textView_medical_case_dsplay2;
    Button button_vitalsigns_dsplay;
    Button imageView_done;
    private GoogleMap mMap;
    boolean contnueLookForLocation=true;
    private LatLng destinationLatLng, pickupLatLng;
    Marker pickupMarker;
    private DatabaseReference assignedCustomerPickupLocationRef;
    private ValueEventListener assignedCustomerPickupLocationRefListener;
    Location mLastLocation;
    NewCase getNewCase;
    String newCase_key;
    DatabaseReference refRemoveNewCase;
    NewCase remove_case;
    Medications m;


    DatabaseReference reference_check_befor_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        textView_name_dsplay2=(TextView)findViewById(R.id.textView_name_dsplay2);
        textView_hospital_id_patient=(TextView)findViewById(R.id.textView_hospital_id_patient);
        textView_hospital_bed=(TextView)findViewById(R.id.textView_hospital_bed);
        textView_medical_case_dsplay2=(TextView)findViewById(R.id.textView_medical_case_dsplay2);


        button_vitalsigns_dsplay=(Button)findViewById(R.id.button_vitalsigns_dsplay);
        imageView_done=(Button)findViewById(R.id.imageView_done);

        //BOTTON
        button_vitalsigns_dsplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { vitalSignClicked();}});



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng jeddah=new LatLng(21.482911, 39.222083);
        mMap.addMarker(new MarkerOptions().position(jeddah).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_jeddah_star)));
        mMap.moveCamera( CameraUpdateFactory.newLatLng(jeddah));

        Hospital_home_page.list_newCase.clear();
        Hospital_home_page.getNewCaseByQuery(MapsActivity2.this);
        //TEXT VIEW
        String name =Hospital_the_cases.selected_patient.name;
        textView_name_dsplay2.setText(name);
        textView_hospital_id_patient.setText(Hospital_the_cases.selected_patient.getNationalId());
        textView_hospital_bed.setText(Hospital_the_cases.selected_patient.bedType);
        textView_medical_case_dsplay2.setText(Hospital_the_cases.selected_patient.medicalState);

        //IMAGE VIEW
        imageView_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  inClickDoneMark();}});

        //LOCATION
        double lx=hospital_login_page.user.locationX;
        double ly=hospital_login_page.user.locationY;
        LatLng posetion = new LatLng(lx, ly);
        mMap.addMarker(new MarkerOptions().position(posetion).title("المشفى"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(posetion));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(13));

        getAssignedCustomerPickupLocation();

        //CREAT REPORT
        reportList_fillup_patient_info();
}

////////////////////////////////////////////////////////////////////////////////////////////
    public void vitalSignClicked(){
        contnueLookForLocation=false;
        Intent i = new Intent(MapsActivity2.this,Dsplay_V1.class);
        startActivity(i);
    }
    private void getAssignedCustomerPickupLocation(){
        assignedCustomerPickupLocationRef = FirebaseDatabase.getInstance().getReference().child("GPS")
                .child(Hospital_the_cases.selected_patient.key).child("l");
        assignedCustomerPickupLocationRefListener = assignedCustomerPickupLocationRef
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists() && !Hospital_the_cases.selected_patient.key.equals("")){
                    List<Object> map = (List<Object>) dataSnapshot.getValue();
                    double locationLat = 0;
                    double locationLng = 0;
                    if(map.get(0) != null){
                        locationLat = Double.parseDouble(map.get(0).toString());
                    }
                    if(map.get(1) != null){
                        locationLng = Double.parseDouble(map.get(1).toString());
                    }
                    addMarkerLocationOfParamedic(locationLat,locationLng);

                    }
            }

            public void addMarkerLocationOfParamedic(double locationLat, double locationLng){
                mMap.clear();
                // Add a marker in Sydney and move the camera
                double lx=hospital_login_page.user.locationX;
                double ly=hospital_login_page.user.locationY;

                LatLng posetion = new LatLng(lx, ly);
                mMap.addMarker(new MarkerOptions().position(posetion).title("المشفى"));
               // mMap.moveCamera(CameraUpdateFactory.newLatLng(posetion));
               // mMap.animateCamera(CameraUpdateFactory.zoomTo(10));

                //
                pickupLatLng = new LatLng(locationLat,locationLng);
                pickupMarker = mMap.addMarker(new MarkerOptions().position(pickupLatLng)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pickup)));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    private  void reportList_fillup_patient_info() {
            for(int i=0;i<Hospital_home_page.reportList.size();i++){
                if(Hospital_the_cases.selected_patient.key.equals(Hospital_home_page.reportList.get(i).key_patient)){
                    Report r = new Report();
                    r.paramedic_center=Hospital_home_page.reportList.get(i).paramedic_center;
                    r.paramedic_name=Hospital_home_page.reportList.get(i).paramedic_name;
                    r.key_patient=Hospital_home_page.reportList.get(i).key_patient;

                    r.patient_name=Hospital_the_cases.selected_patient.name;
                    r.patient_medical_case=Hospital_the_cases.selected_patient.medicalState;
                    r.hospital_name=hospital_login_page.user.hospitalName;

                    Calendar calendar= Calendar.getInstance();
                    String date="";
                    date+=calendar.get(Calendar.YEAR);
                    date+=" / "+(calendar.get(Calendar.MONTH)+1);
                    date+=" / "+calendar.get(Calendar.DAY_OF_MONTH);
                    r.date=date;

                    String day="";
                    switch(calendar.get(Calendar.DAY_OF_WEEK)){
                        case 1:day="الاحد";break;
                        case 2:day="الاتنين";break;
                        case 3:day="الثلاثاء";break;
                        case 4:day="الاربعاء";break;
                        case 5:day="الخميس";break;
                        case 6:day="الجمعة";break;
                        case 7:day="السبت";break;
                    }
                    r.day=day;

                    Hospital_home_page.reportList.set(i,r);
                    break;
                    }
                    }

    }
    public void inClickDoneMark(){
        Query query2 = FirebaseDatabase.getInstance().getReference().child("NewCase")
                .orderByChild("key_patient")
                .equalTo(Hospital_the_cases.selected_patient.key);
        query2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    NewCase model = new NewCase();
                    model = snapshot.getValue(NewCase.class);
                    if(model.done==true){
                        Intent intent=new Intent(MapsActivity2.this,Hospital_home_page.class);
                        startActivity(intent);
                        case_done_send_repor_firebase();
                        delete_new_case();

                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }});
    }
    private void case_done_send_repor_firebase() {
        for(int i=0;i<Hospital_home_page.reportList.size();i++){

            if(Hospital_the_cases.selected_patient.key.equals(Hospital_home_page.reportList.get(i).key_patient)){

                Report r =Hospital_home_page.reportList.get(i);
                FirebaseDatabase database = FirebaseDatabase.getInstance() ;
                DatabaseReference myRef = database.getReference("Report");
                myRef.push().setValue(r);

                break;
            }

        }

    }
    private void delete_new_case() {
        final DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("NewCase");
        Query query_delete_newCase = ref
                .orderByChild("key_patient")
                .equalTo(Hospital_the_cases.selected_patient.key);
        query_delete_newCase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ref.child(snapshot.getKey()).removeValue();

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }});

    }
    @Override
    public void onBackPressed() {
        Dsplay_V1.vitalSigns=null;
        Intent i= new Intent(MapsActivity2.this,Hospital_home_page.class);
        startActivity(i);
    }



}
