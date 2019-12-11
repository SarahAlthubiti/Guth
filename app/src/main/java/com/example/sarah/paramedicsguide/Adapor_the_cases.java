package com.example.sarah.paramedicsguide;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.List;


public class Adapor_the_cases  extends RecyclerView.Adapter<Adapor_the_cases.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_name,text_case, text_details ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text_name=(TextView) itemView.findViewById(R.id.textView_name_get);
            text_case=(TextView) itemView.findViewById(R.id.textView_case_get);
            text_details=(TextView) itemView.findViewById(R.id.textView_details);
        }

    }

    private Context context;
    private List<Patient> patients;
    private Patient patient = null;

    public Adapor_the_cases(Context context, List<Patient> patients) {
        this.context = context;
        this.patients = patients;
    }

    @NonNull
    @Override
    public Adapor_the_cases.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_new_case, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder,  int i) {
        final Patient p = patients.get(i);
        viewHolder.text_name.setText(p.name);
        viewHolder.text_case.setText(p.medicalState);
        viewHolder.text_details.setText("التفاصيل..");
        viewHolder.text_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hospital_the_cases.selected_patient=p;
                Intent intent = new Intent(context,MapsActivity2.class);
                context.startActivity(intent);
            }
        });
    }

    public Patient getPatient (){

        return patient;
    }
    private void setPatient(Patient p){
        this.patient =p;
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }



}