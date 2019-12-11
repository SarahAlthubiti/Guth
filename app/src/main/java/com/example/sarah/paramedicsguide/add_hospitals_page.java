package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_hospitals_page extends AppCompatActivity {
    EditText editTextName;
    EditText editTextID;
    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextRepeatedPassword;
    EditText editTextLocationX;
    EditText editTextLocationY;

    ImageView imageView_home_ic,imageView_logout_ic;

    ProgressBar progressbar;

    Button buttonAdd;
    Button buttonDisplay;

    private FirebaseAuth mAuth;
    DatabaseReference databasehospital;
    public CheckBox checkboxBrainAndNerves;
    public CheckBox checkboxAccidents;
    public CheckBox checkboxBones;
    public CheckBox checkboxBirth;
    public CheckBox checkboxOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hospitals_page);

        imageView_home_ic=(ImageView)findViewById(R.id.imageView_home_ic);
        imageView_home_ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(add_hospitals_page.this, selection_paramedic_hospital_page.class);
                startActivity(i);
            }
        });
        imageView_logout_ic=(ImageView)findViewById(R.id.imageView_logout_ic);
        imageView_logout_ic.setVisibility(View.INVISIBLE);





        databasehospital = FirebaseDatabase.getInstance().getReference("Hospital");

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextID = (EditText) findViewById(R.id.editTextID);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextRepeatedPassword = (EditText) findViewById(R.id.editTextRepeatedPassword);
        editTextLocationX = (EditText)findViewById(R.id.editTextLocationX);
        editTextLocationY = (EditText)findViewById(R.id.editTextLocationY);
        checkboxBrainAndNerves=(CheckBox)findViewById(R.id.checkboxBrainAndNerves);
        checkboxAccidents =(CheckBox)findViewById(R.id.checkboxAccidents);
        checkboxBones =(CheckBox)findViewById(R.id.checkboxBones);
        checkboxBirth =(CheckBox)findViewById(R.id.checkboxBirth);
        checkboxOther =(CheckBox)findViewById(R.id.checkboxOther);
        progressbar = (ProgressBar)findViewById(R.id.progressbar);

        databasehospital = FirebaseDatabase.getInstance().getReference("Hospital");
        mAuth = FirebaseAuth.getInstance();

        buttonAdd = (Button) findViewById(R.id.button_add_hospital_admin);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addHospital();
            }
        });


    }

    public boolean brainAndNerves;
    public boolean accidents;
    public boolean bones;
    public boolean birth;
    public boolean other;


    public void addHospital() {

        final String name = editTextName.getText().toString().trim();
        final String id = editTextID.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String repeatedPassword = editTextRepeatedPassword.getText().toString().trim();
        final String locationX = editTextLocationX.getText().toString();
        final String locationY = editTextLocationY.getText().toString();


        if (name.isEmpty()) {
            editTextName.setError("إملأ حقل الاسم");
            editTextName.requestFocus();
            return;
        }

        if (id.isEmpty()) {
            editTextID.setError("إملأ حقل الرقم التعريفي");
            editTextID.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("إملأ حقل البريد الإلكتروني");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("إملأ حقل البريد الإلكتروني");
            editTextPassword.requestFocus();
            return;
        }

        if (repeatedPassword.isEmpty()) {
            editTextRepeatedPassword.setError("إملأ حقل البريد الإلكتروني");
            editTextRepeatedPassword.requestFocus();
            return;
        }



        if (!id.matches("[0-9]+")) {
            editTextID.setError("يجب أن يكون الرقم التعريفي مكون من أرقام فقط");
            editTextID.requestFocus();
            return;
        }

        if (id.length() != 7) {
            editTextID.setError("يجب أن يكون الرقم التعريفي مكون من 7 خانات");
            editTextID.requestFocus();
            return;
        }

        if (!password.equals(repeatedPassword)) {
            editTextPassword.setError("كلمة المرور غير متطابقة");
            editTextPassword.requestFocus();
            return;
        }

//------------------------------------------------------------------------------

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("أدخل بريد إلكتروني صالح");
            editTextEmail.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("يجب أن تكون كلمة المرور مكونة من 6 خانات فأكثر");
            editTextPassword.requestFocus();
            return;
        }

//***************************************************************************************************************
        if(checkboxBrainAndNerves.isChecked()){
            brainAndNerves = true;
        }if(checkboxAccidents.isChecked()){
            accidents = true;
        }if(checkboxBones.isChecked()){
            bones = true;
        }if(checkboxBirth.isChecked()){
            birth = true;
        }if(checkboxOther.isChecked()){
            other = true;
        }
//***************************************************************************************************************


//--------------------------------------------------------------------------------
        progressbar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            public String idChild;
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressbar.setVisibility(View.GONE);

                if (task.isSuccessful()) {
                    idChild = databasehospital.push().getKey();
                    Double d_locationX=new Double(locationX);
                    Double d_locationY=new Double(locationY);
                    //GPS
                    LatLng location = new LatLng(d_locationX,d_locationY) ;
                    DatabaseReference ref =FirebaseDatabase.getInstance().getReference("GPS_Hospitals");
                    GeoFire geoFire = new GeoFire(ref);
                    geoFire.setLocation(idChild,new GeoLocation(location.latitude,location.longitude));
                    //
                    Hospital hospital = new Hospital(idChild, name, id, email,  password,  brainAndNerves, accidents, bones, birth, other,d_locationX,d_locationY,idChild);
                    databasehospital.child(idChild).setValue(hospital);
                    Toast.makeText(getApplicationContext(), "تم التسجيل بنجاح", Toast.LENGTH_SHORT).show();

                    //
                    Intent i = new Intent(add_hospitals_page.this,selection_paramedic_hospital_page.class);
                    startActivity(i);

                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "هذا الحساب مسجل من قبل", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}




