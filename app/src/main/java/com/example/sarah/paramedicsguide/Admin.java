package com.example.sarah.paramedicsguide;

public class Admin {
    String adminName;
    String adminID;
    String adminEmail;
    String adminPassword;

    public Admin() {
    }

    public Admin(String adminName, String adminID, String adminEmail, String adminPassword) {
        this.adminName = adminName;
        this.adminID = adminID;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;

    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

}
