package com.example.sarah.paramedicsguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class activity_display_modify_delete_hospital2 extends AppCompatActivity {

    ListView listViewHospital;
    DatabaseReference databaseHospital;
    List<Hospital> hospitalsList;

    public CheckBox checkboxBrainAndNerves;
    public CheckBox checkboxAccidents;
    public CheckBox checkboxBones;
    public CheckBox checkboxBirth;
    public CheckBox checkboxOther;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_modify_delete_hospital2);

        databaseHospital = FirebaseDatabase.getInstance().getReference("Hospital");
        listViewHospital = (ListView) findViewById(R.id.listViewHospital);
        hospitalsList = new ArrayList<>();
        listViewHospital.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Hospital h = hospitalsList.get(i);
                showUpdateDialog(h.getHospitalID(),h.getIdChild());
                return false;
            }
        });
    }
    protected void onStart() {
        super.onStart();
        databaseHospital.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                hospitalsList.clear();

                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {
                    Hospital h = artistSnapshot.getValue(Hospital.class);

                    hospitalsList.add(h);
                }

                HospitalList adapter = new HospitalList(activity_display_modify_delete_hospital2.this, hospitalsList);
                listViewHospital.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void showUpdateDialog(final String id,final String idChild) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.hospital_update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editTextName);
        final EditText editTextEmail = (EditText) dialogView.findViewById(R.id.editTextEmail);
        final EditText editTextPassword = (EditText) dialogView.findViewById(R.id.editTextPassword);
        final EditText editTextRepeatedPassword = (EditText) dialogView.findViewById(R.id.editTextRepeatedPassword);
        final EditText editTextLocationX = (EditText) dialogView.findViewById(R.id.editTextLocationX);
        final EditText editTextLocationY = (EditText) dialogView.findViewById(R.id.editTextLocationY);

        checkboxBrainAndNerves=(CheckBox)dialogView.findViewById(R.id.checkboxBrainAndNerves);
        checkboxAccidents =(CheckBox)dialogView.findViewById(R.id.checkboxAccidents);
        checkboxBones =(CheckBox)dialogView.findViewById(R.id.checkboxBones);
        checkboxBirth =(CheckBox)dialogView.findViewById(R.id.checkboxBirth);
        checkboxOther =(CheckBox)dialogView.findViewById(R.id.checkboxOther);

        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdate);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDelete);

        dialogBuilder.setTitle("تعديل معلومات المستشفى");
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            public boolean brainAndNerves = false;
            public boolean accidents = false;
            public boolean bones = false;
            public boolean birth = false;
            public boolean other = false;
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String repeatedPassword = editTextRepeatedPassword.getText().toString().trim();
                String locationX = editTextLocationX.getText().toString().trim();
                String locationY = editTextLocationY.getText().toString().trim();


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

                if (locationX.isEmpty()) {
                    editTextLocationX.setError("إملأ حقل الموقع X");
                    editTextLocationX.requestFocus();
                    return;
                }

                if (locationY.isEmpty()) {
                    editTextLocationY.setError("إملأ حقل الموقع Y");
                    editTextLocationY.requestFocus();
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

                Double d_locationX=new Double(locationX);
                Double d_locationY=new Double(locationY);


                updateHospital( idChild, name, id, email, password,brainAndNerves,accidents,bones,birth,other,d_locationX,d_locationY);
                alertDialog.dismiss();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteHospital(idChild);
                alertDialog.dismiss();
            }
        });

    }

    private void deleteHospital(String idChild){
        DatabaseReference dr = FirebaseDatabase.getInstance().getReference("Hospital").child(idChild);
        dr.removeValue();
        Toast.makeText(this, "تم حذف المستشفى بنجاح", Toast.LENGTH_LONG).show();

    }

    private boolean updateHospital(String idChild,String name,String id,String email,String password,boolean brainAndNerves,boolean accidents,boolean bones,boolean birth,boolean other,double locationX,double locationY) {
        ////
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Hospital").child(idChild);
        LatLng location = new LatLng(locationX,locationY) ;
        DatabaseReference ref =FirebaseDatabase.getInstance().getReference("GPS_Hospitals");
        GeoFire geoFire = new GeoFire(ref);
        String key =ref.push().getKey();
        geoFire.setLocation(key,new GeoLocation(location.latitude,location.longitude));
        /////
        Hospital hospital = new Hospital(idChild, name, id, email, password, brainAndNerves, accidents, bones, birth, other, locationX,locationY,key);
        dR.setValue(hospital);
        Toast.makeText(this, "تم حفظ التغييرات بنجاح", Toast.LENGTH_LONG).show();
        return true;
    }

}
