package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class new_case extends AppCompatActivity {
   static NewCase newCase;

    FirebaseDatabase database = FirebaseDatabase.getInstance() ;
    DatabaseReference myRef = database.getReference("Patient");

    EditText editText_name;
    EditText editText_id;
    RadioGroup rg_gender;
    RadioButton rb_gender;
    RadioGroup rg_danger_or_not;
    RadioButton rb_danger_or_not;
    Spinner spinner;
    String bed_type;
     static Patient patient;
     static boolean map1=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_case);



        spinner = (Spinner) findViewById(R.id.pg2_2_bed_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bed_type= adapterView.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        addnewcase();
    }


    public void addnewcase( ) {
         editText_name= (EditText) findViewById(R.id.pg2_2_name);

         editText_id= (EditText) findViewById(R.id.pg2_2_id);

        Button button = (Button) findViewById(R.id.pg2_2button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editText_name.getText().toString();
                if(!name.isEmpty()){
                    if (!name.matches("[ا-ي]+")&& name.matches(" ")) {
                        editText_name.setError("يجب أن يكون الإسم باللغة العربية بلا رموز او أرقام ");
                        editText_name.requestFocus();
                        return;
                    }
                }

                String id= editText_id.getText().toString();
                if(!id.isEmpty()){
                    if (!id.matches("[0-9]+")) {
                        editText_id.setError("يجب أن يكون رقم الهوية مكون من أرقام فقط");
                        editText_id.requestFocus();
                        return;
                    }

                    if (id.length() != 10) {
                        editText_id.setError("يجب أن يكون رقم الهوية مكون من 10 أرقام");
                        editText_id.requestFocus();
                        return;
                    }
                }

                rg_gender = (RadioGroup) findViewById(R.id.pg2_2_gender_rg);
                rb_gender = (RadioButton) findViewById (rg_gender.getCheckedRadioButtonId());
                String sex=rb_gender.getText().toString();

                rg_danger_or_not = (RadioGroup) findViewById(R.id.pg2_2_case_rg);
                rb_danger_or_not = (RadioButton) findViewById (rg_danger_or_not.getCheckedRadioButtonId());
                String medicalState = rb_danger_or_not.getText().toString();

                String key =myRef.push().getKey();
                patient = new Patient(id, name, bed_type,  sex,  medicalState,key);
                myRef.push().setValue(patient);

                //add patient to NewCase class
                //important to communcate
                newCase=new NewCase();
                newCase.setKey_patient(key);


                Toast.makeText(new_case.this,"تم اضافةالمريض بنجاح",Toast.LENGTH_SHORT).show();
                Intent i;
                if(medicalState.equals(" حرجة")){
                    i =new Intent(new_case.this,MapsActivity3.class);}
                else{ i =new Intent(new_case.this,Ways_find_hospital.class);}

                startActivity(i);


            }
        });

    }




}
