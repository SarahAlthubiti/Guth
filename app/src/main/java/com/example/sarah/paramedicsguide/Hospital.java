package com.example.sarah.paramedicsguide;
public class Hospital {
    String hospitalName;
    String hospitalID;
    String hospitalEmail;
    String hospitalPassword;
    String GPS_key;
    boolean brainAndNerves;
    boolean accidents;
    boolean bones;
    boolean birth;
    boolean other;

    String idChild;
    double locationX;
    double  locationY;


    public Hospital() {
    }


    public Hospital(String idChild, String hospitalName, String hospitalID, String hospitalEmail, String hospitalPassword, boolean brainAndNerves, boolean accidents, boolean bones, boolean birth, boolean other,double locationX, double locationY,String GPS_key) {
        this.idChild = idChild;
        this.hospitalName = hospitalName;
        this.hospitalID = hospitalID;
        this.hospitalEmail = hospitalEmail;
        this.hospitalPassword = hospitalPassword;
        this.brainAndNerves = brainAndNerves;
        this.accidents = accidents;
        this.bones = bones;
        this.birth = birth;
        this.other = other;
        this.locationX = locationX;
        this.locationY = locationY;
        this.GPS_key=GPS_key;
    }



    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(String hospitalID) {
        this.hospitalID = hospitalID;
    }

    public String getHospitalEmail() {
        return hospitalEmail;
    }

    public void setHospitalEmail(String hospitalEmail) {
        this.hospitalEmail = hospitalEmail;
    }

    public String getHospitalPassword() {
        return hospitalPassword;
    }

    public void setHospitalPassword(String hospitalPassword) {
        this.hospitalPassword = hospitalPassword;
    }
    public String getIdChild() {
        return idChild;
    }

    public void setIdChild(String idChild) {
        this.idChild = idChild;
    }

    public boolean isBrainAndNerves() {
        return brainAndNerves;
    }

    public void setBrainAndNerves(boolean brainAndNerves) {
        this.brainAndNerves = brainAndNerves;
    }

    public boolean isAccidents() {
        return accidents;
    }

    public void setAccidents(boolean accidents) {
        this.accidents = accidents;
    }

    public boolean isBones() {
        return bones;
    }

    public void setBones(boolean bones) {
        this.bones = bones;
    }

    public boolean isBirth() {
        return birth;
    }

    public void setBirth(boolean birth) {
        this.birth = birth;
    }

    public boolean isOther() {
        return other;
    }

    public void setOther(boolean other) {
        this.other = other;
    }


}

