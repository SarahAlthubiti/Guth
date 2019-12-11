package com.example.sarah.paramedicsguide;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class vitalByManuall extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("VitalSigns");


    EditText pluseRate;
    EditText bloodPressure;
    EditText respRate;
    EditText GCS;
    EditText bloodGlucose;
    EditText  BloodOxygen;
    EditText tempreture;
    double numpluseRate;
    double numbloodPressure;
    double numrespRate;
    double numGCS;
    double numbloodGlucose;
    double  numBloodOxygen;
    double numtempreture;

    String pluseRate2;
    String bloodPressure2;
    String respRate2;
    String GCS2;
    String bloodGlucose2;
    String  BloodOxygen2;
    String tempreture2;
    Date date ;
    VitalSigns signs;
    Calendar calendar;
    SimpleDateFormat format ;
    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_by_manuall);
         calendar= Calendar.getInstance();
         format =new SimpleDateFormat("HH.mm");
         time=format.format(calendar.getTime());

    }

    public void buttonSend(View view) {

        pluseRate=(EditText)findViewById(R.id.idpluseRate);
        pluseRate2=pluseRate.getText().toString();
        if(pluseRate2.isEmpty()){
            pluseRate.setError("إملأ الخانة");
            pluseRate.requestFocus();
            return;
        }
        numpluseRate=Double.parseDouble(pluseRate2);


        bloodPressure=(EditText)findViewById(R.id.idbloodPressure);
        bloodPressure2=bloodPressure.getText().toString();
        if(bloodPressure2.isEmpty()){
            bloodPressure.setError("إملأ الخانة");
            bloodPressure .requestFocus();
            return;
        }
        numbloodPressure=Double.parseDouble(bloodPressure2);

        respRate=(EditText)findViewById(R.id.idrespRate);
        respRate2=respRate.getText().toString();
        if(respRate2.isEmpty()){
            respRate.setError("إملأ الخانة");
            respRate.requestFocus();
            return;
        }
        numrespRate=Double.parseDouble(respRate2);

        GCS=(EditText) findViewById(R.id.idGCS);
        GCS2=GCS.getText().toString();
        if(GCS2.isEmpty()){
            GCS .setError("إملأ الخانة");
            GCS .requestFocus();
            return;
        }
        numGCS=Double.parseDouble(GCS2);

        bloodGlucose=(EditText)findViewById(R.id.idbloodGlucose);
        bloodGlucose2=bloodGlucose.getText().toString();
        if(bloodGlucose2.isEmpty()){
            bloodGlucose.setError("إملأ الخانة");
            bloodGlucose .requestFocus();
            return;
        }
        numbloodGlucose=Double.parseDouble(bloodGlucose2);

        BloodOxygen=(EditText)findViewById(R.id.idBloodOxygen);
        BloodOxygen2=BloodOxygen.getText().toString();
        if(BloodOxygen2.isEmpty()){
            BloodOxygen.setError("إملأ الخانة");
            BloodOxygen.requestFocus();
            return;
        }
        numBloodOxygen=Double.parseDouble(BloodOxygen2);

        tempreture=(EditText)findViewById(R.id.idtempreture);
        tempreture2=tempreture.getText().toString();
        if(tempreture2.isEmpty()){
            tempreture.setError("إملأ الخانة");
            tempreture.requestFocus();
            return;
        }
        numtempreture=Double.parseDouble(tempreture2);


        createDialog();

    }
    private void createDialog() {
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat format =new SimpleDateFormat("HH.mm");
        final String time=format.format(calendar.getTime());

        AlertDialog.Builder alertDlg =new   AlertDialog.Builder(this);
        alertDlg.setMessage("لإرسال العلامات الحيوية اختر تأكيد ");
        alertDlg.setCancelable(false);
        alertDlg.setPositiveButton("تأكيد", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                vitalAndDrugs.count_v+=1;
                signs = new VitalSigns(numpluseRate, numbloodPressure, numrespRate, numGCS, numbloodGlucose, numBloodOxygen, numtempreture,new_case.patient.key,vitalAndDrugs.count_v,time);
                myRef.push().setValue(signs);
                Intent in = new Intent(vitalByManuall.this, vitalAndDrugs.class);
                startActivity(in);
            }
        });

        alertDlg.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDlg.create().show();   }
}
