package com.example.sarah.paramedicsguide;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_password_page extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText editTextEmail;
    Button buttonForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_page);

        TextView t = (TextView)findViewById(R.id.textView999);
        t.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        buttonForgotPassword = (Button)findViewById(R.id.buttonForgotPassword);

        buttonForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.sendPasswordResetEmail(editTextEmail.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(forgot_password_page.this,"تم إرسال إستعادة كلمة المرور لبريدك الإلكتروني",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(forgot_password_page.this,"هذا البريد الإلكتروني لايملك حساب",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
