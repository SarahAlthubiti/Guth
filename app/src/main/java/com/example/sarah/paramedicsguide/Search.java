package com.example.sarah.paramedicsguide;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapor_hospital adapor_hospital;
    static EditText search_bar;
    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search_bar = (EditText) findViewById(R.id.editText_search);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_id);

        FirebaseDatabase.getInstance().getReference().child("Hospital").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Hospital module = snapshot.getValue(Hospital.class);
                    Ways_find_hospital.hospitalList.add(module) ; } }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }});

        adapor_hospital= new Adapor_hospital(this,Ways_find_hospital.hospitalList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapor_hospital);

        ImageButton icon = (ImageButton) findViewById(R.id.imageButton_search);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size=Ways_find_hospital.hospitalList.size();
                if(search_bar.getText().toString().isEmpty()){
                    Toast.makeText(Search.this,"لم تدخل اسم المستشفى بعد !", Toast.LENGTH_SHORT).show();}
                else {
                    int count=0;
                    for(int i =0;i<size;i++){
                        if(search_bar.getText().toString().equals(Ways_find_hospital.hospitalList.get(i).hospitalName)){
                            Hospital module=new Hospital();
                            module=Ways_find_hospital.hospitalList.get(i);
                            Ways_find_hospital.hospitalList.clear();
                            Ways_find_hospital.hospitalList.add(module);
                            Intent intent = new Intent(Search.this,MapsActivity.class);
                            startActivity(intent);
                            break; }
                        else
                            count++;
                        }
                    if(count==size){
                        String massage ="\""+ search_bar.getText().toString()+"\"";
                        Toast.makeText(Search.this,"لم يتم العثور على المستشفى "+massage, Toast.LENGTH_SHORT).show();
                    } } }});//end setOnClickListener
    }//end onCreate
    @Override
    public void onBackPressed() {
        Ways_find_hospital.hospitalList.clear();
        Intent i = new Intent(Search.this,Ways_find_hospital.class);
        startActivity(i);
    }
}
