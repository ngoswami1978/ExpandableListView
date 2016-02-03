package com.neerajweb.expandablelistviewtest.Model;

/**
 * Created by Admin on 13/01/2016.
 */

public class modelLoadrptPeriodsMaintainance {

    private int Month;
    private int Year;
    private String MonthName;
    private String MonthNameYear;
    private int Amount;


    public modelLoadrptPeriodsMaintainance() {}

    public modelLoadrptPeriodsMaintainance(int mMonth, int mmYear, String mMonthName, String mmMonthNameYear,int mTotalAmount)
    {
        this.Month=mMonth;
        this.Year=mmYear;
        this.MonthName=mMonthName;
        this.MonthNameYear=mmMonthNameYear;
        this.Amount=mTotalAmount;
    }

    public void setMonth(int _sMonth)    {        this.Month = _sMonth;    }
    public int getMonth()    {        return this.Month;    }

    public void setYear(int _Year)    {        this.Year= _Year;    }
    public int getYear()    {        return this.Year;    }

    public void setMonthName(String _MonthName)    {        this.MonthName = _MonthName;    }
    public String getMonthName()    {        return this.MonthName;    }

    public void setMonthNameYear(String _MonthNameYear)    {        this.MonthNameYear = _MonthNameYear;    }
    public String getMonthNameYear()    {        return this.MonthNameYear;    }

    public void setTotalAmount(int _Amount)    {        this.Amount= _Amount;    }
    public int getTotalAmount()    {        return this.Amount;    }



}