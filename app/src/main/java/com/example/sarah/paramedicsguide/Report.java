package com.example.sarah.paramedicsguide;

import android.provider.ContactsContract;

import java.util.Date;

public class Report {

    String hospital_name;

    String patient_name;
    String patient_medical_case;
    String arival_time;

    String key_patient;

    String paramedic_name;
    String paramedic_center;

    String medicine;

    String day;
    String date;

    public Report() {
    }

    public Report(String patient_name, String patient_medical_case, String arival_time, String paramedic_name, String paramedic_center, String medicine) {
        this.patient_name = patient_name;
        this.patient_medical_case = patient_medical_case;
        this.arival_time = arival_time;
        this.paramedic_name = paramedic_name;
        this.paramedic_center = paramedic_center;
        this.medicine = medicine;
    }
}
