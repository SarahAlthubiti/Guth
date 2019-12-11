package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Report_list_pg extends AppCompatActivity {

    Adapor_report adapor_report;
    DatabaseReference database;
    static Report selected_report;
    RecyclerView recyclerView;
    static List<Report> reportList_from_firebase = new ArrayList<Report>();
    ImageView imageView_home_ic,imageView_logout_ic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list_pg);
        Hospital_home_page.getNewCaseByQuery(Report_list_pg.this);
        recyclerView = (RecyclerView) findViewById(R.id.rv_reports);

        imageView_home_ic=(ImageView)findViewById(R.id.imageView_home_ic);
        imageView_home_ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Report_list_pg.this, Hospital_home_page.class);
                startActivity(i);
            }
        });
        imageView_logout_ic=(ImageView)findViewById(R.id.imageView_logout_ic);
        imageView_logout_ic.setVisibility(View.INVISIBLE);



        Query query2 = FirebaseDatabase.getInstance().getReference().child("Report")
                    .orderByChild("hospital_name")
                    .equalTo(hospital_login_page.user.hospitalName);
        query2.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    reportList_from_firebase.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            Report r = new Report();
                            r = snapshot.getValue(Report.class);
                            reportList_from_firebase.add(r);

                    }
                    display();
                    if(reportList_from_firebase.size()==0){
                        Toast.makeText(Report_list_pg.this,"لا توجد تقارير  ", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Report_list_pg.this,Hospital_home_page.class);
                        startActivity(i);}
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }





    public void display(){
        adapor_report= new Adapor_report(this,reportList_from_firebase);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapor_report);


    }

  //  @Override
  //  public void onBackPressed() {
       // Dsplay_V1.vitalSigns=null;
       // Intent i= new Intent(Hospital_the_cases.this,Hospital_home_page.class);
      //  startActivity(i);


   // }
}

