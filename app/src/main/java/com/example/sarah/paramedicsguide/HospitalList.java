package com.example.sarah.paramedicsguide;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class HospitalList extends ArrayAdapter<Hospital> {
    private Activity context;
    List<Hospital> hospitals;

    public HospitalList(Activity context, List<Hospital> hospitals) {
        super(context, R.layout.list_layout, hospitals);
        this.context = context;
        this.hospitals = hospitals;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewID = (TextView) listViewItem.findViewById(R.id.textViewID);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.textViewEmail);

        Hospital hospital = hospitals.get(position);
        textViewName.setText(hospital.getHospitalName());
        textViewID.setText(hospital.getHospitalID());
        textViewEmail.setText(hospital.getHospitalEmail());

        return listViewItem;
    }
}

