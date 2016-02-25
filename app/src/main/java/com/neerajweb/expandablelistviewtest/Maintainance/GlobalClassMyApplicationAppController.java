package com.neerajweb.expandablelistviewtest.Maintainance;

/**
 * Created by Admin on 02/01/2016.
 */
import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.neerajweb.expandablelistviewtest.utils.LruBitmapCache;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GlobalClassMyApplicationAppController extends Application {

    public static final String TAG = GlobalClassMyApplicationAppController.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static GlobalClassMyApplicationAppController mInstance;

    private String name;
    private String email;
    private String Owner_id;
    private String usr;
    private String regIP;
    private String dt ;
    private String gcmregid ;
    private String approve_status ;
    private String flt_id ;
    private String flt_type ;
    private String flt_no ;
    private String Owner_name ;
    private String Owner_contact ;
    private String Age ;
    private String Renter_name ;
    private String Renter_contact ;
    private String customFont ;
    private String Owner_LastName ;
    private String Renter_LastName ;
    private String Owner_Address ;
    private String Renter_Address ;
    private String Renter_Location ;
    private String Owner_Location ;
    private String Renter_age ;
    private String Renter_email ;
    private String Owner_email ;

    public String getName() {        return name;    }
    public void setName(String aName) {        name = aName;    }
    public String getEmail() {        return email;    }
    public void setEmail(String aEmail) {        email = aEmail;    }

    public void setOwner_id(String _Owner_id) {        this.Owner_id = _Owner_id;    }
    public String getOwner_id() {        return this.Owner_id;    }
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

    public void setRenter_LastName(String _Renter_LastName) {     this.Renter_LastName = _Renter_LastName;    }

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

    public void setRenter_Location(String _Renter_Location) {        this.Renter_Location = _Renter_Location;    }

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




    /**
     * @param monthNumber Month Number starts with 0. For January it is 0 and for December it is 11.
     * @return
     */
    public static String getMonthShortName(int monthNumber) {
        String monthName = "";

        if (monthNumber >= 0 && monthNumber < 12)
            try {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, monthNumber);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM");
                simpleDateFormat.setCalendar(calendar);
                monthName = simpleDateFormat.format(calendar.getTime());
            } catch (Exception e) {
                if (e != null)
                    e.printStackTrace();
            }
        return monthName.toUpperCase();
    }

    // Calling Application class (see application tag in AndroidManifest.xml)
    //--final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

    //Set name and email in global/application context
    //--globalVariable.setName("Android Example context variable");
    //--globalVariable.setEmail("xxxxxx@aaaa.com");


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized GlobalClassMyApplicationAppController getInstance() {
        return mInstance;
    }
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }
    public RequestQueue getReqQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }
    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToReqQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getReqQueue().add(req);
    }

    public <T> void addToReqQueue(Request<T> req) {
        req.setTag(TAG);
        getReqQueue().add(req);
    }

    public void cancelPendingReq(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
