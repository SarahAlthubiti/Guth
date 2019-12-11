package com.example.sarah.paramedicsguide;

public class NewCase {

/////////////////////////////////////////////////////////

   private String name_paramedic, id_paramedic,paramedic_center, name_hospital  , key_patient ;
   boolean send=false, recive=false, done=false;

//////////////////////////////////////////////////////
    public NewCase() {
    }

    public NewCase( String key_patient, String name_paramedic, String id_paramedic,String paramedic_center, String name_hospital, boolean send, boolean recive, boolean done) {
        this.name_paramedic = name_paramedic;
        this.id_paramedic = id_paramedic;
        this.paramedic_center=paramedic_center;
        this.name_hospital = name_hospital;
        this.send = send;
        this.recive = recive;
        this.done = done;
        this.key_patient=key_patient;
    }

    //////////////////////////////////////////////////////



    public String getParamedic_center() {
        return paramedic_center;
    }

    public String getKey_patient() {
        return key_patient;
    }

    public String getName_paramedic() {
        return name_paramedic;
    }

    public String getId_paramedic() {
        return id_paramedic;
    }

    public String getName_hospital() {
        return name_hospital;
    }
 //////////////////////////////////////////////////////

    public void setParamedic_center(String paramedic_center) {
        this.paramedic_center = paramedic_center;
    }

    public void setName_paramedic(String name_paramedic) {
        this.name_paramedic = name_paramedic;
    }

    public void setId_paramedic(String id_paramedic) {
        this.id_paramedic = id_paramedic;
    }

    public void setName_hospital(String name_hospital) {
        this.name_hospital = name_hospital;
    }

    public void setKey_patient(String key_patient) {
        this.key_patient = key_patient;
    }



    ///////////////////////////////////////////////////
}
