package com.neerajweb.expandablelistviewtest.Model;

/**
 * Created by Admin on 13/01/2016.
 */


public class modelMaintainancedetail {

    private int Dtl_id;
    private int Srno;
    private int Flt_id;
    private String Flatnumber="";
    private String Flattype="";
    private int Owner_id;
    private String Ownername ="";
    private String Owneremail ="";
    private String Ownercontact ="";
    private String Rentername="";
    private String Rentercontact="";
    private String PaidMonth="";
    private String PaidYear="";
    private String PaidAmount="";
    private String PaidEntrydt="";
    private String Paidby="";

    public modelMaintainancedetail() {}

    public modelMaintainancedetail(int mDtl_id, int mSrno, int mFlt_id, String mFlatnumber, String mFlattype, int mOwner_id, String mOwnername, String mOwneremail, String mOwnercontact, String mRentername, String mRentercontact, String mPaidMonth, String mPaidYear, String mPaidAmount, String mPaidEntrydt, String mPaidby)
    {
        this.Dtl_id=mDtl_id;
        this.Srno=mSrno;
        this.Flt_id=mFlt_id;
        this.Flatnumber=mFlatnumber;
        this.Flattype=mFlattype;
        this.Owner_id=mOwner_id;
        this.Ownername =mOwnername;
        this.Owneremail =mOwneremail;
        this.Ownercontact =mOwnercontact;
        this.Rentername=mRentername;
        this.Rentercontact = mRentercontact;
        this.PaidMonth= mPaidMonth;
        this.PaidYear =mPaidYear;
        this.PaidAmount=mPaidAmount;
        this.PaidEntrydt=mPaidEntrydt;
        this.Paidby=mPaidby;
    }

    public void setSrno(int _srno)    {        this.Srno = _srno;    }
    public int getSrno()    {        return this.Srno;    }

    public void setownerid(int _ownerid)    {        this.Owner_id = _ownerid;    }
    public int getownerid()    {        return this.Owner_id;    }

    public void setdtlid(int _dtl_id)    {        this.Dtl_id = _dtl_id;    }
    public int getdtlid()    {        return this.Dtl_id;    }
    public void setfltid(int _flt_id)    {        this.Flt_id = _flt_id;    }
    public int getfltid()    {        return this.Flt_id;    }
    public void setownername(String _ownername)    {        this.Ownername = _ownername;    }
    public String getownername()    {        return this.Ownername;    }
    public void setOwneremail(String _owneremail)    {        this.Owneremail = _owneremail;    }
    public String getOwneremail()    {        return this.Owneremail;    }
    public void setOwnercontact(String _ownercontact)    {        this.Ownercontact = _ownercontact;    }
    public String getOwnercontact()    {        return this.Ownercontact;    }
    public void setRentercontact(String _rentercontact)    {        this.Rentercontact = _rentercontact;    }
    public String getRentercontact()    {        return this.Rentercontact;    }
    public void setRentername(String _rentername)    {        this.Rentername = _rentername;    }
    public String getrentername()    {        return this.Rentername;    }
    public void setflatnumber(String _flatnumber)    {        this.Flatnumber = _flatnumber;    }
    public String getflatnumber()    {        return this.Flatnumber;    }
    public void setflattype(String _flattype)    {        this.Flattype = _flattype;    }
    public String getflattype()    {        return this.Flattype;    }

    public void setPaidMonth(String _lastPaidMonth)    {        this.PaidMonth = _lastPaidMonth;    }
    public void setPaidYear(String _lastPaidYear)   { this.PaidYear = _lastPaidYear;  }
    public void setPaidAmount(String _lastPaidAmount) { this.PaidAmount = _lastPaidAmount;}

    public void setPaidEntrydt(String _lastPaidEntrydt)   {      this.PaidEntrydt= _lastPaidEntrydt;    }
    public void setPaidby(String _lastPaidby)    {        this.Paidby = _lastPaidby;    }

    public String getPaidMonth()    {        return this.PaidMonth;    }
    public String getPaidYear()    {        return this.PaidYear;    }
    public String getPaidAmount()    {        return this.PaidAmount;    }
    public String getPaidEntrydt()    {        return this.PaidEntrydt;    }
    public String getPaidby()    {        return this.Paidby;    }
}