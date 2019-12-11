package com.example.sarah.paramedicsguide;


import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class activity_dispaly_modify_delete_paramedic2 extends AppCompatActivity {

    ListView listViewParamedic;
    DatabaseReference databaseParamedic;
    List<Paramedic> paramedicsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispaly_modify_delete_paramedic2);

        databaseParamedic = FirebaseDatabase.getInstance().getReference("Paramedic");
        listViewParamedic = (ListView) findViewById(R.id.listViewParamedic);
        paramedicsList = new ArrayList<>();
        listViewParamedic.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Paramedic paramedic = paramedicsList.get(i);
                showUpdateDialog(paramedic.getParamedicID(),paramedic.getParamedicNationalID(),paramedic.getidChild());
                return false;
            }
        });
    }
    protected void onStart() {
        super.onStart();
        databaseParamedic.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                paramedicsList.clear();

                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {
                    Paramedic paramedic = artistSnapshot.getValue(Paramedic.class);

                    paramedicsList.add(paramedic);
                }

                ParamedicList adapter = new ParamedicList(activity_dispaly_modify_delete_paramedic2.this, paramedicsList);
                listViewParamedic.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showUpdateDialog(final String artistId,final String nid,final String idChild) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.paramedic_update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editTextName);
        final EditText editTextEmail = (EditText) dialogView.findViewById(R.id.editTextEmail);
        final EditText editTextPassword = (EditText) dialogView.findViewById(R.id.editTextPassword);
        final EditText editTextRepeatedPassword = (EditText) dialogView.findViewById(R.id.editTextRepeatedPassword);
        //final EditText  editTextJobLevel = (EditText) dialogView.findViewById(R.id.editTextJobLevel);
        //final EditText  editTextCenter = (EditText) dialogView.findViewById(R.id.editTextCenter);
        final Spinner spinnerJobLevel = (Spinner)dialogView.findViewById(R.id.spinnerJobLevel);
        final Spinner spinnerCenter = (Spinner)dialogView.findViewById(R.id.spinnerCenter);

        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdate);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDelete);

        dialogBuilder.setTitle("تعديل معلومات المسعف");
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String repeatedPassword = editTextRepeatedPassword.getText().toString().trim();

                String jobLevel = spinnerJobLevel.getSelectedItem().toString();
                String center = spinnerCenter.getSelectedItem().toString();

                if (name.isEmpty()) {
                    editTextName.setError("إملأ حقل الاسم");
                    editTextName.requestFocus();
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



                if (!password.equals(repeatedPassword)) {
                    editTextPassword.setError("كلمة المرور غير متطابقة");
                    editTextPassword.requestFocus();
                    return;
                }

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


                updateParamedic(idChild,artistId,nid,name,email,password,jobLevel,center);
                alertDialog.dismiss();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteParamedic(idChild);
                alertDialog.dismiss();
            }
        });

    }
    private void deleteParamedic(String idChild){
        DatabaseReference dr = FirebaseDatabase.getInstance().getReference("Paramedic").child(idChild);
        dr.removeValue();
        Toast.makeText(this, "تم حذف المسعف بنجاح", Toast.LENGTH_LONG).show();

    }

    private boolean updateParamedic(String idChild, String id,String nid, String name,String email,String password,String jobLevel,String center) {

        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Paramedic").child(idChild);

        Paramedic paramedic = new Paramedic(idChild,name,nid,email,id,password,jobLevel,center);
        dR.setValue(paramedic);
        Toast.makeText(this, "تم حفظ التغييرات بنجاح", Toast.LENGTH_LONG).show();
        return true;
    }

}

