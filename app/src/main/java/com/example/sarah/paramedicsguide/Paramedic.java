package com.example.sarah.paramedicsguide;

public class Paramedic {
    String paramedicName;
    String paramedicNationalID;
    String paramedicEmail;
    String paramedicID;
    String paramedicPassword;
    String paramedicJobLevel;
    String paramedicCenter;
    String idChild;

    public Paramedic() {
    }


    public Paramedic(String idChild, String paramedicName, String paramedicNationalID, String paramedicEmail, String paramedicID, String paramedicPassword, String paramedicJobLevel, String paramedicCenter) {
        this.idChild = idChild;
        this.paramedicName = paramedicName;
        this.paramedicNationalID = paramedicNationalID;
        this.paramedicEmail = paramedicEmail;
        this.paramedicID = paramedicID;
        this.paramedicPassword = paramedicPassword;
        this.paramedicJobLevel = paramedicJobLevel;
        this.paramedicCenter = paramedicCenter;
    }

    public String getidChild() {
        return idChild;
    }

    public void setidChild(String idChild) {
        this.idChild = idChild;
    }

    public String getParamedicName() {
        return paramedicName;
    }

    public void setParamedicName(String paramedicName) {
        this.paramedicName = paramedicName;
    }

    public String getParamedicNationalID() {
        return paramedicNationalID;
    }

    public void setParamedicNationalID(String paramedicNationalID) {
        this.paramedicNationalID = paramedicNationalID;
    }

    public String getParamedicEmail() {
        return paramedicEmail;
    }

    public void setParamedicEmail(String paramedicEmail) {
        this.paramedicEmail = paramedicEmail;
    }

    public String getParamedicID() {
        return paramedicID;
    }

    public void setParamedicID(String paramedicID) {
        this.paramedicID = paramedicID;
    }

    public String getParamedicPassword() {
        return paramedicPassword;
    }

    public void setParamedicPassword(String paramedicPassword) {
        this.paramedicPassword = paramedicPassword;
    }

    public String getParamedicJobLevel() {
        return paramedicJobLevel;
    }

    public void setParamedicJobLevel(String paramedicJobLevel) {
        this.paramedicJobLevel = paramedicJobLevel;
    }

    public String getParamedicCenter() {
        return paramedicCenter;
    }

    public void setParamedicCenter(String paramedicCenter) {
        this.paramedicCenter = paramedicCenter;
    }

}


