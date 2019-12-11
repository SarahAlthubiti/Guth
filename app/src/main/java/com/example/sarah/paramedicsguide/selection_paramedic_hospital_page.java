package com.example.sarah.paramedicsguide;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class selection_paramedic_hospital_page extends AppCompatActivity {
    ImageView imageView_logout_ic,imageView_home_ic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_paramedic_hospital_page);

        imageView_logout_ic=(ImageView)findViewById(R.id.imageView_logout_ic);
        imageView_logout_ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
            }
        });
        imageView_home_ic=(ImageView)findViewById(R.id.imageView_home_ic);
        imageView_home_ic.setVisibility(View.INVISIBLE);

    }

    public void toHospitalsPage(View view) {
        Intent intent = new Intent(selection_paramedic_hospital_page.this,hospitals_management_page.class);
        startActivity(intent);
    }

    public void toParamedicsPage(View view) {
        Intent intent = new Intent(selection_paramedic_hospital_page.this,paramedics_management_page.class);
        startActivity(intent);
    }
    private void createDialog() {
        AlertDialog.Builder alertDlg =new   AlertDialog.Builder(this);
        alertDlg.setMessage("لتأكيد تسجيل الخروج اختر تأكيد ");
        alertDlg.setCancelable(false);
        alertDlg.setPositiveButton("تأكيد", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(selection_paramedic_hospital_page.this,"تم تسجيل الخروج بنجاح ", Toast.LENGTH_SHORT).show();
                Intent i= new Intent(selection_paramedic_hospital_page.this,home_page.class);
                startActivity(i);
            }
        });

        alertDlg.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDlg.create().show();   }
    @Override
    public void onBackPressed() {
        createDialog();
    }

}
