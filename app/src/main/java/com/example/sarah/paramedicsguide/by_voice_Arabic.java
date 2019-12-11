package com.example.sarah.paramedicsguide;

import android.content.DialogInterface;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class by_voice_Arabic extends AppCompatActivity {
    private TextView txvResult;
    String text="";
    VitalSigns VitalSigns;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("VoiceToText");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_voice);
        txvResult = (TextView) findViewById(R.id.txvResult);
        Bundle b = getIntent().getExtras();
    }

    public void getSpeechInput(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ar-SA");



        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "هذا الجهاز لا يدعم التسجيل الصوتي", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    text+=" "+result.get(0);

                    txvResult.setText(text);
                }
                break;
        }
    }


    private void createDialog() {
        AlertDialog.Builder alertDlg =new   AlertDialog.Builder(this);
        alertDlg.setMessage("لإرسال النص اختر تأكيد");
        alertDlg.setCancelable(false);
        alertDlg.setPositiveButton("تأكيد", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(by_voice_Arabic.this,"تم الارسال ", Toast.LENGTH_SHORT).show();

                DatabaseReference database = FirebaseDatabase.getInstance().getReference("VoiceToText");
                vitalAndDrugs.count_v++;
                VoiceToText voiceToText=new VoiceToText(text,new_case.newCase.getKey_patient(),vitalAndDrugs.count_v);
                database.push().setValue(voiceToText);
                Intent i = new Intent(by_voice_Arabic.this,vitalAndDrugs.class);
                startActivity(i);
            }
        });

        alertDlg.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDlg.create().show();   }

    public void buttonSend(View view) {
        if(!text.isEmpty()){ createDialog();}

    }
    public void buttonDeleat(View view) {
        if(!text.isEmpty()){ createDialogDeleat();}
    }
    private void createDialogDeleat() {

        if (txvResult != null || txvResult.equals("")) {
            AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
            alertDlg.setMessage("لحذف النص اختر تأكيد ");
            alertDlg.setCancelable(false);
            alertDlg.setPositiveButton("تأكيد", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    txvResult.setText("");
                    text="";
                }
            });

            alertDlg.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDlg.create().show();
        }
        else{}
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(by_voice_Arabic.this,vitalAndDrugs.class);
        startActivity(i);
    }
}
