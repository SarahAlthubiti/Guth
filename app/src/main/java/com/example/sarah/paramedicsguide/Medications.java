package com.example.sarah.paramedicsguide;

public class Medications {
    String patient_key;
    boolean Atropine=false,Aspirin=false,Gelatin=false,Dextrose=false,Nitroglycerin=false,Adrenaline=false,Salbutamol=false;

    public Medications() {
    }

    public String getPatient_key() {
        return patient_key;
    }

    public void setPatient_key(String patient_key) {
        this.patient_key = patient_key;
    }
}
