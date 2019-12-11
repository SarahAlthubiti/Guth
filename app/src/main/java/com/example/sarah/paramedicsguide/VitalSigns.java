package com.example.sarah.paramedicsguide;

import java.util.Date;

public class VitalSigns {
    String patient_key;
    String vitalByVoice;
    double pluseRate;
    double bloodPressure;
    double respRate;
    double GCS;
    double bloodGlucose;
    double BloodOxygen;
    double tempreture;
    String timeOfMeasurement;
    double num;



    public VitalSigns(String vitalByVoice, String patient_key)
    {
       this.vitalByVoice=vitalByVoice;
       this.patient_key=patient_key;


    }

    public VitalSigns() {
    }

    public VitalSigns(double pluseRate, double bloodPressure, double respRate, double GCS, double bloodGlucose, double BloodOxygen, double tempreture, String patient_key,double num,String time){
        this.pluseRate=pluseRate;
        this.bloodPressure=bloodPressure;
        this.respRate=respRate;
        this.GCS=GCS;
        this.bloodGlucose=bloodGlucose;
        this.BloodOxygen=BloodOxygen;
        this.tempreture=tempreture;
        this.patient_key=patient_key;
        this.num=num;
        this.timeOfMeasurement=time;



    }
    public double pluseRate() {
        return pluseRate;
    }
    public double bloodPressure() {
        return bloodPressure;
    }
    public double respRate() {
        return respRate;
    }
    public double GCS() {
        return GCS;
    }
    public double bloodGlucose() {
        return bloodGlucose;
    }
    public double BloodOxygen() {
        return BloodOxygen;
    }
    public double tempreture() {
        return tempreture;
    }
    public  String timeOfMeasurement() {
        return timeOfMeasurement;
    }

}
