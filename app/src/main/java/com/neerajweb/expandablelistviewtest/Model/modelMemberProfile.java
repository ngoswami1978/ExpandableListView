package com.neerajweb.expandablelistviewtest.Model;

/**
 * Created by Admin on 07/03/2016.
 */
import java.util.ArrayList;

public class modelMemberProfile {
    private String thumbnailUrl;
    private String flt_id = "";
    private String flt_no = "";
    private String flt_type = "";
    private String Owner_id = "";
    private String Owner_name = "";
    private String approve_status="";
    private String usr = "";
    private String email = "";
    private String Owner_contact = "";
    private String Renter_name = "";
    private String Renter_contact = "";
    private String LastPaidMonth = "";
    private String LastPaidYear = "";
    private String LastPaidAmount = "";
    private String LastPaidEntrydt = "";
    private String LastPaidAdminApproval = "";
    private String LastPaidby = "";
    private String CustomFontName = "";
    private String Owner_email = "";
    private String Owner_Address = "";
    private String Owner_Location = "";
    private String Renter_Address = "";
    private String Renter_Location = "";
    private String Renter_age = "";
    private String Renter_email = "";
    private String photoname = "";
    private String photopath = "";


    public modelMemberProfile() {
    }

    public modelMemberProfile(String mflt_id, String mflt_no, String mflt_type, String mOwner_id, String mOwner_name,String mapprove_status, String musr,String memail,
                              String mOwner_contact, String mRenter_name, String mRenter_contact, String mLastPaidMonth, String mLastPaidYear, String mLastPaidAmount,
                              String mLastPaidEntrydt, String mLastPaidAdminApproval, String mLastPaidby, String mCustomFontName, String mOwner_email, String mOwner_Address,
                              String mOwner_Location, String mRenter_Address, String mRenter_Location, String mRenter_age, String mRenter_email, String mphotoname, String mphotopath,String mthumbnailUrl)
    {
        this.flt_id =mflt_id;
        this.flt_no = mflt_no;
        this.flt_type = mflt_type;
        this.Owner_id = mOwner_id;
        this.Owner_name = mOwner_name;
        this.approve_status=mapprove_status;
        this.usr= musr;
        this.email = memail;
        this.Owner_contact = mOwner_contact;
        this.Renter_name = mRenter_name;
        this.Renter_contact = mRenter_contact;
        this.LastPaidMonth = mLastPaidMonth;
        this.LastPaidYear = mLastPaidYear;
        this.LastPaidAmount = mLastPaidAmount;
        this.LastPaidEntrydt = mLastPaidEntrydt;
        this.LastPaidAdminApproval = mLastPaidAdminApproval;
        this.LastPaidby = mLastPaidby;
        this.CustomFontName = mCustomFontName;
        this.Owner_email = mOwner_email;
        this.Owner_Address = mOwner_Address;
        this.Owner_Location = mOwner_Location;
        this.Renter_Address = mRenter_Address;
        this.Renter_Location = mRenter_Location;
        this.Renter_age = mRenter_age;
        this.Renter_email = mRenter_email;
        this.photoname = mphotoname;
        this.photopath = mphotopath;
        this.thumbnailUrl=mthumbnailUrl;
    }

    public String getflt_id() {        return flt_id;    }
    public void setflt_id(String  _flt_id) {        this.flt_id = _flt_id;    }

    public String getflt_no() {        return flt_no;    }
    public void setflt_no(String  _flt_no) {        this.flt_no = _flt_no;    }

    public String getflt_type() {        return flt_type;    }
    public void setflt_type(String  _flt_type) {        this.flt_type = _flt_type;    }

    public String getOwner_id() {        return Owner_id;    }
    public void setOwner_id(String  _Owner_id) {        this.Owner_id = _Owner_id;    }

    public String getOwner_name() {        return Owner_name;    }
    public void setOwner_name(String  _Owner_name) {        this.Owner_name = _Owner_name;    }

    public String getemail() {        return email;    }
    public void setemail(String  _email) {        this.email = _email;    }

    public String getusr() {        return usr;    }
    public void setusr(String  _usr) {        this.usr = _usr;    }

    public String getapprove_status() {        return approve_status;    }
    public void setapprove_status(String  _approve_status) {        this.approve_status = _approve_status;    }

    public String getOwner_contact() {        return Owner_contact;    }
    public void setOwner_contact(String  _Owner_contact) {        this.Owner_contact = _Owner_contact;    }

    public String getRenter_name() {        return Renter_name;    }
    public void setRenter_name(String  _Renter_name) {        this.Renter_name = _Renter_name;    }

    public String getRenter_contact() {        return Renter_contact;    }
    public void setRenter_contact(String  _Renter_contact) {        this.Renter_contact = _Renter_contact;    }

    public String getLastPaidMonth() {        return LastPaidMonth;    }
    public void setLastPaidMonth(String  _LastPaidMonth) {        this.LastPaidMonth = _LastPaidMonth;    }

    public String getLastPaidYear() {        return LastPaidYear;    }
    public void setLastPaidYear(String  _LastPaidYear) {        this.LastPaidYear = _LastPaidYear;    }

    public String getLastPaidAmount() {        return LastPaidAmount;    }
    public void setLastPaidAmount(String  _LastPaidAmount) {        this.LastPaidAmount = _LastPaidAmount;    }

    public String getLastPaidEntrydt() {        return LastPaidEntrydt;    }
    public void setLastPaidEntrydt(String  _LastPaidEntrydt) {        this.LastPaidEntrydt = _LastPaidEntrydt;    }

    public String getLastPaidAdminApproval() {        return LastPaidAdminApproval;    }
    public void setLastPaidAdminApproval(String  _LastPaidAdminApproval) {        this.LastPaidAdminApproval = _LastPaidAdminApproval;    }

    public String getLastPaidby() {        return LastPaidby;    }
    public void setLastPaidby(String  _LastPaidby) {        this.LastPaidby = _LastPaidby;    }

    public String getCustomFontName() {        return CustomFontName;    }
    public void setCustomFontName(String  _CustomFontName) {        this.CustomFontName = _CustomFontName;    }

    public String getOwner_email() {        return Owner_email;    }
    public void setOwner_email(String  _Owner_email) {        this.Owner_email = _Owner_email;    }

    public String getOwner_Address() {        return Owner_Address;    }
    public void setOwner_Address(String  _Owner_Address) {        this.Owner_Address = _Owner_Address;    }

    public String getOwner_Location() {        return Owner_Location;    }
    public void setOwner_Location(String  _Owner_Location) {        this.Owner_Location = _Owner_Location;    }

    public String getRenter_Address() {        return Renter_Address;    }
    public void setRenter_Address(String  _Renter_Address) {        this.Renter_Address = _Renter_Address;    }

    public String getRenter_Location() {        return Renter_Location;    }
    public void setRenter_Location(String  _Renter_Location) {        this.Renter_Location = _Renter_Location;    }

    public String getRenter_age() {        return Renter_age;    }
    public void setRenter_age(String  _Renter_age) {        this.Renter_age = _Renter_age;    }

    public String getRenter_email() {        return Renter_email;    }
    public void setRenter_email(String  _Renter_email) {        this.Renter_email = _Renter_email;    }

    public String getphotoname() {        return photoname;    }
    public void setphotoname(String  _photoname) {        this.photoname = _photoname;    }

    public String getphotopath() {        return photopath;    }
    public void setphotopath(String  _photopath) {        this.photopath = _photopath;    }

    public String getThumbnailUrl() {        return thumbnailUrl;    }

    public void setThumbnailUrl(String _thumbnailUrl) {        this.thumbnailUrl = _thumbnailUrl;    }

}