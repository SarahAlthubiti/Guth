package com.example.sarah.paramedicsguide;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class vitalAndDrugs extends AppCompatActivity {
static int count_v=0,count_m=0;
TextView textView_count_v;
TextView textView_count_m;
static int valueOfInterfaceToShow=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_and_drugs);



    }


    public void tovitalPage(View view) {
        if(3>count_v){
            switch(valueOfInterfaceToShow){
                case 0:
                    Intent intent0=new Intent(this,sendVs.class);
                    startActivity(intent0);
                    break;
                case 1:
                    Intent intent1=new Intent(this,vitalByManuall.class);
                    startActivity(intent1);
                    break;
                case 2:
                    Intent intent2=new Intent(this,select_voice_language.class);
                    startActivity(intent2);
                    break;
            }

        }
        else {
            Toast.makeText(getApplicationContext(),"لا يمكن ارسال علامات حيويه ",Toast.LENGTH_SHORT).show();
        }
    }

    public void buttonDone(View view) {
        createDialog();
         }

    public void cameClicked(View view) {
        Intent i = new Intent(vitalAndDrugs.this,take_photo.class);
        startActivity(i);
    }


    public void todrugPage(View view) {
        if(1>count_m){
        Intent in=new Intent(this,drugs_selection.class);
        startActivity(in);}
        else {
            Toast.makeText(getApplicationContext(),"لقد قمت بارسلت العلامات الحيويه فعلا",Toast.LENGTH_SHORT).show();

        }

    }

    public void finish(){
        count_v=0;
        count_m=0;
        valueOfInterfaceToShow=0;

//        Ways_find_hospital.hospitalList.clear();

        setDoneTrueInFirebase();

        if(new_case.map1){

     //   MapsActivity.stopGPS=true;
     //   MapsActivity.geoFire.removeLocation( new_case.newCase.getKey_patient());
            MapsActivity.stopTrack();
        }
        else{
         //   MapsActivity3.stopGPS=true;
         //   MapsActivity3.geoFire.removeLocation(new_case.newCase.getKey_patient());
         //   MapsActivity3.isCanAccess=true;
          //  MapsActivity3.firstTime=true;
            MapsActivity3.stopTrack();

   }


        new_case.newCase=null;
        new_case.patient=null;

        Toast.makeText(getApplicationContext(),"يتم انهاء الحالة",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(vitalAndDrugs.this,paramedic_home_page.class);
        startActivity(i);
    }

    private void setDoneTrueInFirebase() {
        final DatabaseReference refFindHospital = FirebaseDatabase.getInstance().getReference();
        Query query = refFindHospital.child("NewCase").orderByChild(new_case.newCase.getKey_patient());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    NewCase n = new NewCase();
                    n=snapshot.getValue(NewCase.class);
                    n.done=true;
                    refFindHospital.child("NewCase").child(snapshot.getKey()).setValue(n);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void createDialog() {
        AlertDialog.Builder alertDlg =new   AlertDialog.Builder(this);
        alertDlg.setMessage("يتم انهاء الحاله عند الوصول الى المشفى ! لتأكيد انهاء الحالة اختر تأكيد");
        alertDlg.setCancelable(false);
        alertDlg.setPositiveButton("تأكيد", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(vitalAndDrugs.this,"تم انهاء الحالة ", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        alertDlg.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDlg.create().show();   }

    //***********
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"لا يمكن العوده ، يجب انهاء الحالة",Toast.LENGTH_SHORT).show();
    }

}
