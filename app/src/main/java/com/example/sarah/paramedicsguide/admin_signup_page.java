package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class admin_signup_page extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private DatabaseReference databaseadmin;

    EditText editTextName;
    EditText editTextID;
    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextRepeatedPassword;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup_page);

        TextView t = (TextView)findViewById(R.id.textView999);
        t.setVisibility(View.INVISIBLE);

        editTextName = (EditText)findViewById(R.id.editTextName);
        editTextID = (EditText)findViewById(R.id.editTextID);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        editTextRepeatedPassword = (EditText)findViewById(R.id.editTextRepeatedPassword);
        progressbar = (ProgressBar)findViewById(R.id.progressbar);
        databaseadmin = FirebaseDatabase.getInstance().getReference("Admin");
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.buttonSignUp).setOnClickListener(this);
    }

    private void registerAdmin(){
        final String name = editTextName.getText().toString().trim();
        final String id = editTextID.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String repeatedPassword = editTextRepeatedPassword.getText().toString().trim();


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






        if (!id.matches("[0-9]+") && id.length() != 7) {
            editTextID.setError("يجب أن يكون الرقم التعريفي مكون من أرقام فقط و مكون من 7 خانات");
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

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressbar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Admin admin = new Admin(name, id, email, password);
                    databaseadmin.push().setValue(admin);
                    Toast.makeText(getApplicationContext(),"تم التسجيل بنجاح",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(admin_signup_page.this,selection_paramedic_hospital_page.class);
                    startActivity(intent);
                }else{
                    if(task.getException()instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"هذا الحساب مسجل من قبل",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSignUp:
                registerAdmin();
                break;



        }
    }
}

