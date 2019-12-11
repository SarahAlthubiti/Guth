package com.example.sarah.paramedicsguide;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ParamedicList extends ArrayAdapter<Paramedic> {
    private Activity context;
    List<Paramedic> paramedics;

    public ParamedicList(Activity context, List<Paramedic> paramedics) {
        super(context, R.layout.list_layout, paramedics);
        this.context = context;
        this.paramedics = paramedics;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewID = (TextView) listViewItem.findViewById(R.id.textViewID);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.textViewEmail);

        Paramedic paramedic = paramedics.get(position);
        textViewName.setText(paramedic.getParamedicName());
        textViewID.setText(paramedic.getParamedicID());
        textViewEmail.setText(paramedic.getParamedicEmail());

        return listViewItem;
    }
}

