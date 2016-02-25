package com.neerajweb.expandablelistviewtest.Model;

/**
 * Created by Admin on 25/02/2016.
 */

public class modelMember {

    private String Owner_id = "";
    private String usr = "";
    private String email = "";
    private String regIP = "";
    private String dt = "";
    private String gcmregid = "";
    private String approve_status = "";
    private String flt_id = "";
    private String flt_type = "";
    private String flt_no = "";
    private String Owner_name = "";
    private String Owner_contact = "";
    private String Age = "";
    private String Renter_name = "";
    private String Renter_contact = "";
    private String customFont = "";
    private String Owner_LastName = "";
    private String Renter_LastName = "";
    private String Owner_Address = "";
    private String Renter_Address = "";
    private String Renter_Location = "";
    private String Owner_Location = "";
    private String Renter_age = "";
    private String Renter_email = "";
    private String Owner_email = "";


    public modelMember() {
    }

    public modelMember(String mOwner_id, String musr, String memail, String mregIP, String mdt, String mgcmregid, String mapprove_status, String mflt_id, String mflt_type, String mflt_no, String mOwner_name, String mOwner_contact, String mAge, String mRenter_name,
                       String mRenter_contact, String mcustomFont, String mOwner_LastName, String mRenter_LastName, String mOwner_Address, String mRenter_Address,
                       String mRenter_Location, String mOwner_Location, String mRenter_age, String mRenter_email, String mOwner_email) {
        this.Owner_id = mOwner_id;
        this.usr = musr;
        this.email = memail;
        this.regIP = mregIP;
        this.dt = mdt;
        this.gcmregid = mgcmregid;
        this.approve_status = mapprove_status;
        this.flt_id = mflt_id;
        this.flt_type = mflt_type;
        this.flt_no = mflt_no;
        this.Owner_name = mOwner_name;
        this.Owner_contact = mOwner_contact;
        this.Age = mAge;
        this.Renter_name = mRenter_name;
        this.Renter_contact = mRenter_contact;
        this.customFont = mRenter_Address;
        this.Owner_LastName = mOwner_LastName;
        this.Renter_LastName = mRenter_LastName;
        this.Owner_Address = mOwner_Address;
        this.Renter_Address = mRenter_Address;
        this.Renter_Location = mRenter_Location;
        this.Owner_Location = mOwner_Location;
        this.Renter_age = mRenter_age;
        this.Renter_email = mRenter_email;
        this.Owner_email = mOwner_email;
    }

    public void setOwner_id(String _Owner_id) {
        this.Owner_id = _Owner_id;
    }

    public String getOwner_id() {
        return this.Owner_id;
    }

    public void setusr(String _usr) {
        this.usr = _usr;
    }

    public String getusr() {
        return this.usr;
    }

    public void setemail(String _email) {
        this.email = _email;
    }

    public String getemail() {
        return this.email;
    }

    public void setregIP(String _regIP) {
        this.regIP = _regIP;
    }

    public String getregIP() {
        return this.regIP;
    }

    public void setdt(String _dt) {
        this.dt = _dt;
    }

    public String getdt() {
        return this.dt;
    }

    public void setgcmregid(String _gcmregid) {
        this.gcmregid = _gcmregid;
    }

    public String getgcmregid() {
        return this.gcmregid;
    }

    public void setapprove_status(String _approve_status) {
        this.approve_status = _approve_status;
    }

    public String getapprove_status() {
        return this.approve_status;
    }

    public void setflt_id(String _flt_id) {
        this.flt_id = _flt_id;
    }

    public String getflt_id() {
        return this.flt_id;
    }

    public void setflt_type(String _flt_type) {
        this.flt_type = _flt_type;
    }

    public String getflt_type() {
        return this.flt_type;
    }

    public void setflt_no(String _flt_no) {
        this.flt_no = _flt_no;
    }

    public String getflt_no() {
        return this.flt_no;
    }

    public void setOwner_name(String _Owner_name) {
        this.Owner_name = _Owner_name;
    }

    public String getOwner_name() {
        return this.Owner_name;
    }

    public void setOwner_contact(String _Owner_contact) {
        this.Owner_contact = _Owner_contact;
    }

    public String getOwner_contact() {
        return this.Owner_contact;
    }

    public void setAge(String _Age) {
        this.Age = _Age;
    }

    public String getAge() {
        return this.Age;
    }

    public void setRenter_name(String _Renter_name) {
        this.Renter_name = _Renter_name;
    }

    public String getRenter_name() {
        return this.Renter_name;
    }

    public void setRenter_contact(String _Renter_contact) {
        this.Renter_contact = _Renter_contact;
    }

    public String getRenter_contact() {
        return this.Renter_contact;
    }

    public void setcustomFont(String _customFont) {
        this.customFont = _customFont;
    }

    public String getcustomFont() {
        return this.customFont;
    }

    public void setOwner_LastName(String _Owner_LastName) {
        this.Owner_LastName = _Owner_LastName;
    }

    public String getOwner_LastName() {
        return this.Owner_LastName;
    }

    public void setRenter_LastName(String _Renter_LastName) {
        this.Renter_LastName = _Renter_LastName;
    }

    public String getRenter_LastName() {
        return this.Renter_LastName;
    }

    public void setOwner_Address(String _Owner_Address) {
        this.Owner_Address = _Owner_Address;
    }

    public String getOwner_Address() {
        return this.Owner_Address;
    }

    public void setRenter_Address(String _Renter_Address) {
        this.Renter_Address = _Renter_Address;
    }

    public String getRenter_Address() {
        return this.Renter_Address;
    }

    public void setRenter_Location(String _Renter_Location) {
        this.Renter_Location = _Renter_Location;
    }

    public String getRenter_Location() {
        return this.Renter_Location;
    }

    public void setOwner_Location(String _Owner_Location) {
        this.Owner_Location = _Owner_Location;
    }

    public String getOwner_Location() {
        return this.Owner_Location;
    }

    public void setRenter_age(String _Renter_age) {
        this.Renter_age = _Renter_age;
    }

    public String getRenter_age() {
        return this.Renter_age;
    }

    public void setRenter_email(String _Renter_email) {
        this.Renter_email = _Renter_email;
    }

    public String getRenter_email() {
        return this.Renter_email;
    }

    public void setOwner_email(String _Owner_email) {
        this.Owner_email = _Owner_email;
    }

    public String getOwner_email() {
        return this.Owner_email;
    }
}
