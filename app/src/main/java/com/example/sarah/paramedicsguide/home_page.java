package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class home_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        TextView t = (TextView)findViewById(R.id.textView999);
        t.setVisibility(View.INVISIBLE);
    }

    public void toAdminLoginPage(View view) {
        Intent intent = new Intent(home_page.this,admin_login_page.class);
        startActivity(intent);
    }

    public void toHospitalLoginPage(View view) {
        Intent intent = new Intent(home_page.this,hospital_login_page.class);
        startActivity(intent);
    }

    public void toParamedicLoginPage(View view) {
        Intent intent = new Intent(home_page.this,paramedic_login_page.class);
        startActivity(intent);
    }
}
