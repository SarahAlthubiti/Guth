package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_paramedic_page extends AppCompatActivity {
    EditText editTextName;
    EditText editTextNationalID;
    EditText editTextID;
    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextRepeatedPassword;

    Spinner spinnerJobLevel;
    Spinner spinnerCenter;
    ProgressBar progressbar;

    ImageView imageView_home_ic,imageView_logout_ic;

    Button buttonAdd;

    private FirebaseAuth mAuth;
    DatabaseReference databaseparamedic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_paramedic_page);

        imageView_home_ic=(ImageView)findViewById(R.id.imageView_home_ic);
        imageView_home_ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(add_paramedic_page.this, selection_paramedic_hospital_page.class);
                startActivity(i);
            }
        });

        imageView_logout_ic=(ImageView)findViewById(R.id.imageView_logout_ic);
        imageView_logout_ic.setVisibility(View.INVISIBLE);

        databaseparamedic = FirebaseDatabase.getInstance().getReference("Paramedic");

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextNationalID = (EditText) findViewById(R.id.editTextNationalID);
        editTextID = (EditText) findViewById(R.id.editTextID);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextRepeatedPassword = (EditText) findViewById(R.id.editTextRepeatedPassword);

        spinnerJobLevel = (Spinner) findViewById(R.id.spinnerJobLevel);
        spinnerCenter = (Spinner) findViewById(R.id.spinnerCenter);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);

        databaseparamedic = FirebaseDatabase.getInstance().getReference("Paramedic");
        mAuth = FirebaseAuth.getInstance();

        buttonAdd = (Button) findViewById(R.id.buttonAdd);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            addParamedic();
            }
        });
    }


    public void addParamedic(){
        final String name = editTextName.getText().toString().trim();
        final String nationalID = editTextNationalID.getText().toString().trim();
        final String id = editTextID.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String repeatedPassword = editTextRepeatedPassword.getText().toString().trim();


        final String jobLevel = spinnerJobLevel.getSelectedItem().toString();
        final String center = spinnerCenter.getSelectedItem().toString();


        if (name.isEmpty()) {
            editTextName.setError("إملأ حقل الاسم");
            editTextName.requestFocus();
            return;
        }

        if (nationalID.isEmpty()) {
            editTextNationalID.setError("إملأ حقل رقم الهوية");
            editTextNationalID.requestFocus();
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

        if (!nationalID.matches("[0-9]+")) {
            editTextNationalID.setError("يجب أن يكون رقم الهوية مكون من أرقام فقط");
            editTextNationalID.requestFocus();
            return;
        }

        if (nationalID.length() != 10) {
            editTextNationalID.setError("يجب أن يكون رقم الهوية مكون من 10 أرقام");
            editTextNationalID.requestFocus();
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

//--------------------------------------------------------------------------------
        progressbar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            public String idChild;
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressbar.setVisibility(View.GONE);

                if (task.isSuccessful()) {
                    idChild = databaseparamedic.push().getKey();
                    Paramedic paramedic = new Paramedic(idChild,name, nationalID, email, id, password, jobLevel, center);
                    databaseparamedic.child(idChild).setValue(paramedic);
                    Toast.makeText(getApplicationContext(), "تم التسجيل بنجاح", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(add_paramedic_page.this,selection_paramedic_hospital_page.class);
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


