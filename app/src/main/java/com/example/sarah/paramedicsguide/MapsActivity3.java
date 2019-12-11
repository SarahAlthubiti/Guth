package com.example.sarah.paramedicsguide;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MapsActivity3 extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, com.google.android.gms.location.LocationListener, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {


    private GoogleMap mMap;

    //LocationManager locationManager;
    Location mLastlocation;
    ImageView imageView_send;
    ImageView imageView_arrow;
    ImageView imageView_refrish;
    TextView textView_name_box;
    TextView textView_email_box;
    TextView textView_time_box;
    LinearLayout layout_box_name_email_time;
    static GoogleApiClient googleApiClient;
    static LocationRequest locationRequest;
    static Location location;
    static boolean isCanAccess = true;
    static boolean stopGPS=false;
    static GeoFire geoFire;
    LatLng latLng;
    Hospital targetHospital;

    //********************************************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps3);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        imageView_send = (ImageView) findViewById(R.id.imageView_send);
        imageView_arrow = (ImageView) findViewById(R.id.imageView_arrow);
        textView_name_box = (TextView) findViewById(R.id.textView_name_dsplay2);
        textView_email_box = (TextView) findViewById(R.id.textView_email_box);
        textView_time_box = (TextView) findViewById(R.id.textView_tima_dsplay2);
        layout_box_name_email_time = (LinearLayout) findViewById(R.id.layout_box_name_email_time);
        imageView_refrish = (ImageView)findViewById(R.id.imageView_refrish);
        imageView_send.setVisibility(View.INVISIBLE);
        imageView_arrow.setVisibility(View.INVISIBLE);
        layout_box_name_email_time.setVisibility(View.INVISIBLE);
        imageView_refrish.setVisibility(View.VISIBLE);

        imageView_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
            }
        });
        imageView_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next_int();
            }
        });
        imageView_refrish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MapsActivity3.this,MapsActivity3.class);
                startActivity(i);
            }
        });
        stopGPS=false;
        MapsActivity3.isCanAccess=true;
        MapsActivity3.firstTime=true;
        hospitalsFound=false;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();

        LatLng jeddah=new LatLng(21.482911, 39.222083);
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(jeddah,9));
        mMap.addMarker(new MarkerOptions().position(jeddah).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_jeddah_star)));

        new_case.map1=false;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        buldGooglApiClient();
        mMap.setMyLocationEnabled(true);


    }
    //************************************************************************************
    private void createDialog() {
        if(mLastlocation==null){
            openLocationGpsDaialog();
        }
        else {
            android.support.v7.app.AlertDialog.Builder alertDlg = new android.support.v7.app.AlertDialog.Builder(this);
            alertDlg.setMessage("لإرسال الطلب اختر تاكيد ");
            alertDlg.setCancelable(false);
            alertDlg.setPositiveButton("تأكيد", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    send();

                }
            });

            alertDlg.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDlg.create().show();
        }
    }
    public void openLocationGpsDaialog(){
        android.support.v7.app.AlertDialog.Builder alertDlg = new android.support.v7.app.AlertDialog.Builder(this);
        alertDlg.setMessage("قم بتشغيل نظام تحديد المواقع من الاعدادات ");
        alertDlg.setPositiveButton("حسنا", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }});
    }
//*********************************************************************************** TO VITAL SIGN
    public void next_int() {

        Hospital targetHospital=null;

        Intent i = new Intent(MapsActivity3.this, vitalAndDrugs.class);
        startActivity(i);
    }

//******************************************************************************** CURRENT LOCATION
private synchronized void buldGooglApiClient(){
    googleApiClient = new GoogleApiClient.Builder(this)
            .addConnectionCallbacks(MapsActivity3.this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build();
    googleApiClient.connect();
}
static boolean firstTime=true;
    @Override
    public void onLocationChanged(Location location) {
        mLastlocation=location;
        latLng = new LatLng(location.getLatitude(),location.getLongitude());
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
       // mMap.animateCamera(CameraUpdateFactory.zoomTo(9));

        if(stopGPS==false){
            DatabaseReference ref =FirebaseDatabase.getInstance().getReference("GPS");
            geoFire = new GeoFire(ref);
            geoFire.setLocation(new_case.newCase.getKey_patient(),new GeoLocation(location.getLatitude(),location.getLongitude()));
            if(firstTime){
                firstTime=false;
                getClosestHospitals();
            }
        }

    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }
    @Override
    public void onConnectionSuspended(int i) { }
    String hospitalKeyLocation;
    //LatLng pickupLatLng;
    Marker pickupMarker;
    private int radius = 1;
    static Boolean hospitalsFound = false;
    LatLng pickupLatLng;;
    //private String hospitalFoundID;
    private void getClosestHospitals(){
        DatabaseReference ref_GpsHospital = FirebaseDatabase.getInstance().getReference().child("GPS_Hospitals");
        GeoFire geoFire = new GeoFire(ref_GpsHospital);
        GeoQuery geoQuery = geoFire.queryAtLocation(new GeoLocation(latLng.latitude, latLng.longitude), radius);
        mMap.setOnMarkerClickListener(MapsActivity3.this);
        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {


                if (!hospitalsFound)
                {
                    Toast.makeText(MapsActivity3.this,"يتم عملية ايجاد المشفى ",Toast.LENGTH_SHORT).show();

                    hospitalKeyLocation=key;
                   // hospitalFoundID=key;
                    pickupLatLng = new LatLng(location.latitude,location.longitude);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(pickupLatLng));
                    pickupMarker= mMap.addMarker(new MarkerOptions().position(pickupLatLng).visible(true));
                    hospitalsFound=true;

                } }
            @Override
            public void onKeyExited(String key) {

            }
            @Override
            public void onKeyMoved(String key, GeoLocation location) {

            }
            @Override
            public void onGeoQueryReady() {
                if (!hospitalsFound)
                {
                    radius++;
                    getClosestHospitals();
                }
            }
            @Override
            public void onGeoQueryError(DatabaseError error) {

            }
        });
    }
    //******************************************************************************* BACK (No return)
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "لا يمكن العوده ، يجب انهاء الحالة", Toast.LENGTH_SHORT).show();
    }
    //*******************************************************************************MARKER - FIND NEAREST HOSPITAL - DSPLAY BOX
    @Override
    public boolean onMarkerClick(Marker marker) {
        imageView_refrish.setVisibility(View.INVISIBLE);
        if (isCanAccess) {
            searchHospital(marker);
            }
            return true;}
    private void searchHospital(final Marker marker) {

        DatabaseReference refFindHospital = FirebaseDatabase.getInstance().getReference("Hospital");
        Query query = refFindHospital.orderByChild("GPS_key").equalTo(hospitalKeyLocation);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Hospital hospital = null;
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                     hospital=new Hospital();
                    hospital=snapshot.getValue(Hospital.class);
                }
                dsplayBoxInformation(marker,hospital);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void dsplayBoxInformation(Marker marker,Hospital hospital) {
        imageView_send.setVisibility(View.VISIBLE);
        layout_box_name_email_time.setVisibility(View.VISIBLE);
        textView_name_box.setText(hospital.hospitalName);
        textView_email_box.setText(hospital.hospitalEmail);
        Date date = new Date();
        String convert = "" + date.getTime();
        textView_time_box.setText(convert);
        targetHospital=hospital;

    }
    public void send() {

        //name and id of paramedic
        new_case.newCase.setName_paramedic(paramedic_login_page.user.paramedicName);
        new_case.newCase.setId_paramedic(paramedic_login_page.user.paramedicID);
        new_case.newCase.setParamedic_center(paramedic_login_page.user.paramedicCenter);
        //name of hospital
        new_case.newCase.setName_hospital(targetHospital.hospitalName);
        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference myRef = fb.getReference("NewCase");
        myRef.push().setValue(new_case.newCase);

        Toast.makeText(getApplicationContext(), "تم ارسال الطلب", Toast.LENGTH_SHORT).show();

        imageView_send.setVisibility(View.INVISIBLE);
        layout_box_name_email_time.setVisibility(View.INVISIBLE);
        imageView_arrow.setVisibility(View.VISIBLE);
        isCanAccess = false;


    }
    /////////////////////////////////////////////////////////////////
    static public void stopTrack(){
       stopGPS=true;
       geoFire.removeLocation(new_case.newCase.getKey_patient());
    }

}
