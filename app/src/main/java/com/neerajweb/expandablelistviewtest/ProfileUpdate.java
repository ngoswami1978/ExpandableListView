package com.neerajweb.expandablelistviewtest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.neerajweb.expandablelistviewtest.JSONfunctions.PlaceJSONParser;
import com.neerajweb.expandablelistviewtest.JSONfunctions.resultJSON;
import com.neerajweb.expandablelistviewtest.JSONfunctions.result_MemberJSON;
import com.neerajweb.expandablelistviewtest.Maintainance.GlobalClassMyApplicationAppController;
import com.neerajweb.expandablelistviewtest.Model.modelMember;
import com.neerajweb.expandablelistviewtest.Model.modelPostComment;
import com.neerajweb.expandablelistviewtest.utils.Const;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;


/**
 * Created by Admin on 23/02/2016.
 */

//https://maps.googleapis.com/maps/api/place/autocomplete/json?input=gur&types=geocode&sensor=false&key=AIzaSyDif740rhfcbVIUrxEhMrd8gfDMIp46928

public class ProfileUpdate extends Activity implements View.OnClickListener {

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    GoogleCloudMessaging gcmObj;

    public static final String REG_ID = "regId";
    public static final String EMAIL_ID = "eMailId";
    public String mYregistrationId;

    ToggleButton tButton;
    TextView tvStateofToggleButton;
    private Button btnUpdate;
    private EditText etOwnerPhoneno,etRenterPhoneno;
    private EditText etOwnerFirstname,etOwnerLastname,etOwnerAge,etOwnerAddress,etOwnerEmail,etOwnerUser;
    private EditText etRenterFirstname,etRenterLastname,etRenterAge,etRenterAddress,etRenterEmail;

    ArrayList<modelMember> model_Member;
    private ArrayList<result_MemberJSON> resultMember;

    AutoCompleteTextView atvOwner_places,atvRenter_places;

    ProgressDialog PDLoadMember;
    public Context mContext;
    Context applicationContext;

    PlacesTask placesTask;
    ParserTask parserTask;

    PlacesTaskRenter placesTaskRenter;
    ParserTaskRenter parserTaskRenter;
    ProgressBar prBarOwnerLocation;
    ProgressBar prBarRenterLocation;

    ProgressBar prBarGCMid;



    private int keyDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_update);
        mContext = this;
        applicationContext = getApplicationContext();

        prBarOwnerLocation=(ProgressBar) findViewById(R.id.prBarOwnerLocation);
        prBarRenterLocation=(ProgressBar) findViewById(R.id.prBarRenterLocation);
        prBarGCMid=(ProgressBar) findViewById(R.id.prBarGCMid);

        etOwnerFirstname=(EditText) findViewById(R.id.etOwnerFirstname);
        etOwnerLastname=(EditText) findViewById(R.id.etOwnerLastname);
        etOwnerAge=(EditText) findViewById(R.id.etOwnerAge);
        etOwnerAddress=(EditText) findViewById(R.id.etOwnerAddress);
        etOwnerEmail=(EditText) findViewById(R.id.etOwnerEmail);
        etOwnerUser=(EditText) findViewById(R.id.etOwnerUser);
        etOwnerUser.setKeyListener(null);

        etRenterFirstname=(EditText) findViewById(R.id.etRenterFirstname);
        etRenterLastname=(EditText) findViewById(R.id.etRenterLastname);
        etRenterAge=(EditText) findViewById(R.id.etRenterAge);
        etRenterAddress=(EditText) findViewById(R.id.etRenterAddress);
        etRenterEmail=(EditText) findViewById(R.id.etRenterEmail);


        tButton = (ToggleButton) findViewById(R.id.toggleButton1);
        tvStateofToggleButton=(TextView)findViewById(R.id.tvstate);
        tvStateofToggleButton.setText("OFF");
        tButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                if(isChecked){
//                    tvStateofToggleButton.setText("ON");
                    if (checkPlayServices()) {
                        // Register Device in GCM Server
                        registerInBackground(etOwnerUser.getText().toString());
                    }
                }else{
                    tvStateofToggleButton.setText("OFF");
                    mYregistrationId="";
                }
            }
        });

        //validation on edit text all
        this.etOwnerFirstname.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(etOwnerFirstname);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        this.etOwnerLastname.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(etOwnerLastname);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        this.etOwnerAge.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(etOwnerAge);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        this.etOwnerAddress.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(etOwnerAddress);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });


        //validation on edit text all
        this.etRenterFirstname.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(etRenterFirstname);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        this.etRenterLastname.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(etRenterLastname);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        this.etRenterAge.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(etRenterAge);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        this.etRenterAddress.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(etRenterAddress);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        this.etRenterEmail.addTextChangedListener(new TextWatcher() {
            // after every change has been made to this editText, we would like to check validity
            public void afterTextChanged(Editable s) {
                Validation.isEmailAddress(etRenterEmail, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        // button click listeners
        btnUpdate.setOnClickListener(this);

        atvOwner_places = (AutoCompleteTextView) findViewById(R.id.atvOwner_places);
        atvOwner_places.setThreshold(1);
        atvOwner_places.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                placesTask = new PlacesTask();
                placesTask.execute(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                Validation.hasText(atvOwner_places);
            }
        });

        atvRenter_places = (AutoCompleteTextView) findViewById(R.id.atvRenter_places);
        atvRenter_places.setThreshold(1);
        atvRenter_places.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                placesTaskRenter = new PlacesTaskRenter();
                placesTaskRenter.execute(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                Validation.hasText(atvRenter_places);
            }
        });

        etOwnerPhoneno= (EditText) findViewById(R.id.etOwnerPhoneno);
        etOwnerPhoneno.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                etOwnerPhoneno.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {

                        if (keyCode == KeyEvent.KEYCODE_DEL)
                            keyDel = 1;
                        return false;
                    }
                });

                if (keyDel == 0) {
                    int len = etOwnerPhoneno.getText().length();
                    if(len == 3) {
                        etOwnerPhoneno.setText(etOwnerPhoneno.getText() + "-");
                        etOwnerPhoneno.setSelection(1, etOwnerPhoneno.getText().length());
                    }
                } else {
                    keyDel = 0;
                }
            }
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                Validation.isPhoneNumber(etOwnerPhoneno, true);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
                Validation.isPhoneNumber(etOwnerPhoneno, true);
            }
        });

        etRenterPhoneno= (EditText) findViewById(R.id.etRenterPhoneno);
        etRenterPhoneno.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                etRenterPhoneno.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {

                        if (keyCode == KeyEvent.KEYCODE_DEL)
                            keyDel = 1;
                        return false;
                    }
                });

                if (keyDel == 0) {
                    int len = etRenterPhoneno.getText().length();
                    if(len == 3) {
                        etRenterPhoneno.setText(etRenterPhoneno.getText() + "-");
                        Editable etext = etRenterPhoneno.getText();
                        etRenterPhoneno.setSelection(1,etRenterPhoneno.getText().length());
                    }
                } else {
                    keyDel = 0;
                }
            }
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                Validation.isPhoneNumber(etRenterPhoneno, true);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }
        });

        SharedPreferences prefs = getSharedPreferences(Const.APARTMENTGCMREGISTRATIONID,
                Context.MODE_PRIVATE);
        String registrationId = prefs.getString(REG_ID, "");

        if (!TextUtils.isEmpty(registrationId)) {
            mYregistrationId=registrationId;
        }
        Loaddata();
    }

    // AsyncTask to register Device in GCM Server
    private void registerInBackground(final String emailID) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcmObj == null) {
                        gcmObj = GoogleCloudMessaging
                                .getInstance(applicationContext);
                    }
                    mYregistrationId = gcmObj
                            .register(ApplicationConstants .GOOGLE_PROJ_ID);
                    msg = "Registration ID :" + mYregistrationId;

                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                }
                return msg;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                tvStateofToggleButton.setText("Connecting your device...");
                prBarGCMid.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String msg) {
                prBarGCMid.setVisibility(View.INVISIBLE);
                if (!TextUtils.isEmpty(mYregistrationId)) {
                    storeRegIdinSharedPref(applicationContext, mYregistrationId, emailID);
                    Toast.makeText(
                            applicationContext,
                            "Registered with GCM Server successfully.\n\n"
                                    + msg, Toast.LENGTH_SHORT).show();
                } else {
                    tButton.setChecked(false);
                    tvStateofToggleButton.setText("OFF");
                    Toast.makeText(
                            applicationContext,
                            "Reg ID Creation Failed.\n\nEither you haven't enabled Internet or GCM server is busy right now. Make sure you enabled Internet and try registering again after some time."
                                    + msg, Toast.LENGTH_LONG).show();
                }
            }
        }.execute(null, null, null);
    }
    // Store RegId and Email entered by User in SharedPref
    private void storeRegIdinSharedPref(Context context, String regId,String emailID) {
        SharedPreferences prefs = getSharedPreferences(Const.APARTMENTGCMREGISTRATIONID,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(REG_ID, regId);
        editor.putString(EMAIL_ID, emailID);
        editor.commit();

        tButton.setChecked(true);
        tvStateofToggleButton.setText("ON");
    }


    private void Loaddata()
    {
        final String ITEM_OWNER_ID="Owner_id";
        final String ITEM_SUBMIT_TYPE="submit_type";
        final String ITEM_USR="usr";
        final String ITEM_EMAIL="email";
        final String ITEM_REGIP="regIP";
        final String ITEM_DT="dt";
        final String ITEM_GCMREGID="gcmregid";
        final String ITEM_APPROVE_STATUS="approve_status";
        final String ITEM_FLT_ID="flt_id";
        final String ITEM_FLT_TYPE="flt_type";
        final String ITEM_FLT_NO="flt_no";
        final String ITEM_OWNER_NAME="Owner_name";
        final String ITEM_OWNER_CONTACT="Owner_contact";
        final String ITEM_AGE="Age";
        final String ITEM_RENTER_NAME="Renter_name";
        final String ITEM_RENTER_CONTACT="Renter_contact";
        final String ITEM_CUSTOMFONT="customFont";
        final String ITEM_OWNER_LASTNAME="Owner_LastName";
        final String ITEM_RENTER_LASTNAME="Renter_LastName";
        final String ITEM_OWNER_ADDRESS="Owner_Address";
        final String ITEM_RENTER_ADDRESS="Renter_Address";
        final String ITEM_RENTER_LOCATION="Renter_Location";
        final String ITEM_OWNER_LOCATION="Owner_Location";
        final String ITEM_RENTER_AGE="Renter_age";
        final String ITEM_RENTER_EMAIL="Renter_email";
        final String ITEM_OWNER_EMAIL="Owner_email";

        try
        {
            //create a new child and add that to the group
            final modelMember _modelMember = new modelMember();
            PDLoadMember = new ProgressDialog(this);
            PDLoadMember.setTitle("Getting information");
            PDLoadMember.setMessage("Please wait...");
            PDLoadMember.setCancelable(false);

            try {
                PDLoadMember.show();
                StringRequest postRequest = new StringRequest(Request.Method.POST, Const.URL_WS_CRUD_LOGIN,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    // prepare the list of all records--------
                                    model_Member  = new ArrayList<modelMember>();
                                    //----------------------------------------
                                    JSONObject jsonObject = new JSONObject(response);
                                    ArrayList<result_MemberJSON> resultjsonobj = new ArrayList<result_MemberJSON>();
                                    resultjsonobj = parseJsonResult(jsonObject);
                                    int success =resultjsonobj.get(0).getSuccess();
                                    int retid;
                                    if (success == 1) {
                                        PDLoadMember.dismiss();
                                        retid = Integer.parseInt(resultjsonobj.get(0).getOwner_id());
                                        refreshmemberInfoToClient(resultjsonobj.get(0).getMemberList());
                                    } // if ends
                                } catch (JSONException e) {
                                    PDLoadMember.dismiss();
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        PDLoadMember.dismiss();
                        Toast.makeText(mContext,
                                "failed to retrive infomations please check your network connection...", Toast.LENGTH_SHORT).show();
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put(ITEM_OWNER_ID,"37");
                        params.put(ITEM_SUBMIT_TYPE,"LoadProfileUpdate");
                        return params;
                    }
                };
                // Adding request to request queue
                GlobalClassMyApplicationAppController.getInstance().addToReqQueue(postRequest);
            }
            catch (Exception Ex) {
                Toast.makeText(this, Ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception Ex)
        {
            Toast.makeText(this, Ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void refreshmemberInfoToClient(ArrayList<modelMember> memberList)
    {
        try
        {
            if (!TextUtils.isEmpty(memberList.get(0).getgcmregid())){
                tButton.setChecked(true);
                tvStateofToggleButton.setText("ON");
            }
            else{
                tButton.setChecked(false);
                tvStateofToggleButton.setText("OFF");
            }

            etOwnerPhoneno.setText(memberList.get(0).getOwner_contact());
            etRenterPhoneno.setText(memberList.get(0).getRenter_contact());
            etOwnerFirstname.setText(memberList.get(0).getOwner_name());
            etOwnerLastname.setText(memberList.get(0).getOwner_LastName());
            etOwnerAge.setText(memberList.get(0).getAge());
            etOwnerAddress.setText(memberList.get(0).getOwner_Address());
            etOwnerEmail.setText(memberList.get(0).getOwner_email());
            etOwnerUser.setText(memberList.get(0).getemail()); //For android application the user should be login registered emailid and for web site user should be userName field
            etRenterFirstname.setText(memberList.get(0).getRenter_name());
            etRenterLastname.setText(memberList.get(0).getRenter_LastName());
            etRenterAge.setText(memberList.get(0).getRenter_age());
            etRenterAddress.setText(memberList.get(0).getRenter_Address());
            etRenterEmail.setText(memberList.get(0).getRenter_email());
            atvOwner_places.setText(memberList.get(0).getOwner_Location());
            atvRenter_places.setText(memberList.get(0).getRenter_Location());
        }
        catch(Exception Ex)
        {
            Toast.makeText(this, Ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private ArrayList<result_MemberJSON> parseJsonResult(JSONObject response) {
        result_MemberJSON item= new result_MemberJSON();
        resultMember= new ArrayList<result_MemberJSON>();
        //get the children for the group
        ArrayList<modelMember> memberList = item.getMemberList();

        String ITEM_OWNER_ID="Owner_id";
        String ITEM_USR="usr";
        String ITEM_EMAIL="email";
        String ITEM_REGIP="regIP";
        String ITEM_DT="dt";
        String ITEM_GCMREGID="gcmregid";
        String ITEM_APPROVE_STATUS="approve_status";
        String ITEM_FLT_ID="flt_id";
        String ITEM_FLT_TYPE="flt_type";
        String ITEM_FLT_NO="flt_no";
        String ITEM_OWNER_NAME="Owner_name";
        String ITEM_OWNER_CONTACT="Owner_contact";
        String ITEM_AGE="Age";
        String ITEM_RENTER_NAME="Renter_name";
        String ITEM_RENTER_CONTACT="Renter_contact";
        String ITEM_CUSTOMFONT="customFont";
        String ITEM_OWNER_LASTNAME="Owner_LastName";
        String ITEM_RENTER_LASTNAME="Renter_LastName";
        String ITEM_OWNER_ADDRESS="Owner_Address";
        String ITEM_RENTER_ADDRESS="Renter_Address";
        String ITEM_RENTER_LOCATION="Renter_Location";
        String ITEM_OWNER_LOCATION="Owner_Location";
        String ITEM_RENTER_AGE="Renter_age";
        String ITEM_RENTER_EMAIL="Renter_email";
        String ITEM_OWNER_EMAIL="Owner_email";
        String ITEM_SUCCESS="success";
        String ITEM_MESSAGE="message";

        //response.getJSONObject("result").get("flt_id")
            modelMember itemrpop = new modelMember();
            Iterator<String> iter = response.keys();
            try {
                while (iter.hasNext())
                {
                    String key = iter.next();
                    JSONObject jobj = (JSONObject) response.get(key);

                    item.setOwner_id(jobj.isNull(ITEM_OWNER_ID) ? "" : jobj.getString(ITEM_OWNER_ID));
                    item.setSuccess(jobj.isNull(ITEM_SUCCESS) ? 0 : Integer.parseInt(jobj.getString(ITEM_SUCCESS)));
                    item.setMessage(jobj.isNull(ITEM_MESSAGE) ? "" : jobj.getString(ITEM_MESSAGE));
                    itemrpop.setOwner_id(jobj.isNull(ITEM_OWNER_ID) ? "" : jobj.getString(ITEM_OWNER_ID));
                    itemrpop.setusr(jobj.isNull(ITEM_USR) ? "" : jobj.getString(ITEM_USR));
                    itemrpop.setemail(jobj.isNull(ITEM_EMAIL) ? "" : jobj.getString(ITEM_EMAIL));
                    itemrpop.setregIP(jobj.isNull(ITEM_REGIP) ? "" : jobj.getString(ITEM_REGIP));
                    itemrpop.setdt(jobj.isNull(ITEM_DT) ? "" : jobj.getString(ITEM_DT));
                    itemrpop.setgcmregid(jobj.isNull(ITEM_GCMREGID) ? "" : jobj.getString(ITEM_GCMREGID));
                    itemrpop.setapprove_status(jobj.isNull(ITEM_APPROVE_STATUS) ? "" : jobj.getString(ITEM_APPROVE_STATUS));
                    itemrpop.setflt_id(jobj.isNull(ITEM_FLT_ID) ? "" : jobj.getString(ITEM_FLT_ID));
                    itemrpop.setflt_type(jobj.isNull(ITEM_FLT_TYPE) ? "" : jobj.getString(ITEM_FLT_TYPE));
                    itemrpop.setflt_no(jobj.isNull(ITEM_FLT_NO) ? "" : jobj.getString(ITEM_FLT_NO));
                    itemrpop.setOwner_name(jobj.isNull(ITEM_OWNER_NAME) ? "" : jobj.getString(ITEM_OWNER_NAME));
                    itemrpop.setOwner_contact(jobj.isNull(ITEM_OWNER_CONTACT) ? "" : jobj.getString(ITEM_OWNER_CONTACT));
                    itemrpop.setAge(jobj.isNull(ITEM_AGE) ? "" : jobj.getString(ITEM_AGE));
                    itemrpop.setRenter_name(jobj.isNull(ITEM_RENTER_NAME) ? "" : jobj.getString(ITEM_RENTER_NAME));
                    itemrpop.setRenter_contact(jobj.isNull(ITEM_RENTER_CONTACT) ? "" : jobj.getString(ITEM_RENTER_CONTACT));
                    itemrpop.setcustomFont(jobj.isNull(ITEM_CUSTOMFONT) ? "" : jobj.getString(ITEM_CUSTOMFONT));
                    itemrpop.setOwner_LastName(jobj.isNull(ITEM_OWNER_LASTNAME) ? "" : jobj.getString(ITEM_OWNER_LASTNAME));
                    itemrpop.setRenter_LastName(jobj.isNull(ITEM_RENTER_LASTNAME) ? "" : jobj.getString(ITEM_RENTER_LASTNAME));
                    itemrpop.setOwner_Address(jobj.isNull(ITEM_OWNER_ADDRESS) ? "" : jobj.getString(ITEM_OWNER_ADDRESS));
                    itemrpop.setRenter_Address(jobj.isNull(ITEM_RENTER_ADDRESS) ? "" : jobj.getString(ITEM_RENTER_ADDRESS));
                    itemrpop.setRenter_Location(jobj.isNull(ITEM_RENTER_LOCATION) ? "" : jobj.getString(ITEM_RENTER_LOCATION));
                    itemrpop.setOwner_Location(jobj.isNull(ITEM_OWNER_LOCATION) ? "" : jobj.getString(ITEM_OWNER_LOCATION));
                    itemrpop.setRenter_age(jobj.isNull(ITEM_RENTER_AGE) ? "" : jobj.getString(ITEM_RENTER_AGE));
                    itemrpop.setRenter_email(jobj.isNull(ITEM_RENTER_EMAIL) ? "" : jobj.getString(ITEM_RENTER_EMAIL));
                    itemrpop.setOwner_email(jobj.isNull(ITEM_OWNER_EMAIL) ? "" : jobj.getString(ITEM_OWNER_EMAIL));

                    memberList.add(0, itemrpop);
                    item.setMemberList(memberList);
                }
                } catch (JSONException e) {
                    // Something went wrong!
                }
        resultMember.add(item);
        return resultMember;
    }

    /** A method to download json data from url for Owner Location*/
    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while( ( line = br.readLine()) != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){
            Log.d("Exception downloading.", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
    // Fetches all places from GooglePlaces AutoComplete Web Service
    private class PlacesTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... place) {
            // For storing data from web service
            String data = "";

            // Obtain browser key from https://code.google.com/apis/console
            String key = "key=AIzaSyDif740rhfcbVIUrxEhMrd8gfDMIp46928";

            String input="";

            try {
                input = "input=" + URLEncoder.encode(place[0], "utf-8");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }

            // place type to be searched
            String types = "types=geocode";

            // Sensor enabled
            String sensor = "sensor=false";

            // Building the parameters to the web service
            String parameters = input+"&"+types+"&"+sensor+"&"+key;

            // Output format
            String output = "json";

            // Building the url to the web service
            String url = "https://maps.googleapis.com/maps/api/place/autocomplete/"+output+"?"+parameters;

            try{
                // Fetching the data from we service
                data = downloadUrl(url);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // Creating ParserTask
            parserTask = new ParserTask();

            // Starting Parsing the JSON string returned by Web Service
            parserTask.execute(result);
        }
    }
    /** A class to parse the Google Places in JSON format */
    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String,String>>>{

        JSONObject jObject;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            prBarOwnerLocation.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<HashMap<String, String>> doInBackground(String... jsonData) {

            List<HashMap<String, String>> places = null;

            PlaceJSONParser placeJsonParser = new PlaceJSONParser();

            try{
                jObject = new JSONObject(jsonData[0]);

                // Getting the parsed data as a List construct
                places = placeJsonParser.parse(jObject);

            }catch(Exception e){
                Log.d("Exception",e.toString());
            }
            return places;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> result) {
            prBarOwnerLocation.setVisibility(View.INVISIBLE);
            String[] from = new String[] { "description"};
            int[] to = new int[] { android.R.id.text1 };

            // Creating a SimpleAdapter for the AutoCompleteTextView
            SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), result, android.R.layout.simple_list_item_1, from, to);

            // Setting the adapter
            atvOwner_places.setAdapter(adapter);
        }
    }


    /** A method to download json data from url for Renter Location*/
    private String downloadUrlRenter(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while( ( line = br.readLine()) != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){
            Log.d("Exception downloading.", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
    // Fetches all places from GooglePlaces AutoComplete Web Service
    private class PlacesTaskRenter extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... place) {
            // For storing data from web service
            String data = "";

            // Obtain browser key from https://code.google.com/apis/console
            String key = "key=AIzaSyDif740rhfcbVIUrxEhMrd8gfDMIp46928";

            String input="";

            try {
                input = "input=" + URLEncoder.encode(place[0], "utf-8");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }

            // place type to be searched
            String types = "types=geocode";

            // Sensor enabled
            String sensor = "sensor=false";

            // Building the parameters to the web service
            String parameters = input+"&"+types+"&"+sensor+"&"+key;

            // Output format
            String output = "json";

            // Building the url to the web service
            String url = "https://maps.googleapis.com/maps/api/place/autocomplete/"+output+"?"+parameters;

            try{
                // Fetching the data from we service
                data = downloadUrl(url);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            prBarRenterLocation.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // Creating ParserTask
            parserTaskRenter = new ParserTaskRenter();

            // Starting Parsing the JSON string returned by Web Service
            parserTaskRenter.execute(result);
        }
    }
    /** A class to parse the Google Places in JSON format */
    private class ParserTaskRenter extends AsyncTask<String, Integer, List<HashMap<String,String>>>{

        JSONObject jObject;

        @Override
        protected List<HashMap<String, String>> doInBackground(String... jsonData) {

            List<HashMap<String, String>> places = null;

            PlaceJSONParser placeJsonParser = new PlaceJSONParser();

            try{
                jObject = new JSONObject(jsonData[0]);

                // Getting the parsed data as a List construct
                places = placeJsonParser.parse(jObject);

            }catch(Exception e){
                Log.d("Exception",e.toString());
            }
            return places;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> result) {

            prBarRenterLocation.setVisibility(View.INVISIBLE);
            String[] from = new String[] { "description"};
            int[] to = new int[] { android.R.id.text1 };

            // Creating a SimpleAdapter for the AutoCompleteTextView
            SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), result, android.R.layout.simple_list_item_1, from, to);

            // Setting the adapter
            atvRenter_places.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUpdate:
                /*
                Validation class will check the error and display the error on respective fields
                but it won't resist the form submission, so we need to check again before submit
                 */
                if (checkValidation())
                {
                    //Toast.makeText(this, "Submitting form...", Toast.LENGTH_LONG).show();
                    Snackbar.make(v, "Updating your profile please wait...!", Snackbar.LENGTH_LONG).show();
                    modelMember _modelMember= getProfileData(v);
                    submitForm(v,_modelMember);
                }
                else
                    //Toast.makeText(this, "Form contains error", Toast.LENGTH_LONG).show();
                    Snackbar.make(v, "Form contains error please fill proper!", Snackbar.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPlayServices();
    }
    // Check if Google Playservices is installed in Device or not
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        // When Play services not found in device
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                // Show Error dialog to install Play services
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(
                        applicationContext,
                        "This device doesn't support Play services, App will not work normally",
                        Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        } else {
            Toast.makeText(
                    applicationContext,
                    "This device supports Play services, App will work normally",
                    Toast.LENGTH_LONG).show();
        }
        return true;
    }

    private void submitForm(View v,final modelMember modelMember)
    {
        try
        {
            final String ITEM_OWNER_ID="Owner_id";
            final String ITEM_SUBMIT_TYPE="submit_type";
            final String ITEM_USR="usr";
            final String ITEM_GCMREGID="gcmregid";
            final String ITEM_OWNER_NAME="Owner_name";
            final String ITEM_OWNER_CONTACT="Owner_contact";
            final String ITEM_AGE="Age";
            final String ITEM_RENTER_NAME="Renter_name";
            final String ITEM_RENTER_CONTACT="Renter_contact";
            final String ITEM_OWNER_LASTNAME="Owner_LastName";
            final String ITEM_RENTER_LASTNAME="Renter_LastName";
            final String ITEM_OWNER_ADDRESS="Owner_Address";
            final String ITEM_RENTER_ADDRESS="Renter_Address";
            final String ITEM_RENTER_LOCATION="Renter_Location";
            final String ITEM_OWNER_LOCATION="Owner_Location";
            final String ITEM_RENTER_AGE="Renter_age";
            final String ITEM_RENTER_EMAIL="Renter_email";
            final String ITEM_OWNER_EMAIL="Owner_email";

            try
            {
                //create a new child and add that to the group
                final modelMember _modelMember = new modelMember();
                PDLoadMember = new ProgressDialog(this);
                PDLoadMember.setTitle("Updating your profile...");
                PDLoadMember.setMessage("Please wait...");
                PDLoadMember.setCancelable(false);

                try {
                    PDLoadMember.show();
                    StringRequest postRequest = new StringRequest(Request.Method.POST, Const.URL_WS_CRUD_LOGIN,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        // prepare the list of all records--------
                                        model_Member  = new ArrayList<modelMember>();
                                        //----------------------------------------
                                        JSONObject jsonObject = new JSONObject(response);
                                        ArrayList<result_MemberJSON> resultjsonobj = new ArrayList<result_MemberJSON>();
                                        resultjsonobj = parseJsonResult(jsonObject);
                                        int success =resultjsonobj.get(0).getSuccess();
                                        int retid;
                                        if (success == 1) {
                                            PDLoadMember.dismiss();
                                            retid = Integer.parseInt(resultjsonobj.get(0).getOwner_id());
                                            refreshmemberInfoToClient(resultjsonobj.get(0).getMemberList());
                                        } // if ends
                                    } catch (JSONException e) {
                                        PDLoadMember.dismiss();
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            PDLoadMember.dismiss();
                            Toast.makeText(mContext,
                                    "failed to retrive infomations please check your network connection...", Toast.LENGTH_SHORT).show();
                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put(ITEM_OWNER_ID,"37");
                            params.put(ITEM_SUBMIT_TYPE,"ProfileUpdate");
                            params.put(ITEM_USR,modelMember.getusr());
                            params.put(ITEM_GCMREGID,modelMember.getgcmregid());
                            params.put(ITEM_OWNER_NAME,modelMember.getOwner_name());
                            params.put(ITEM_OWNER_CONTACT,modelMember.getOwner_contact());
                            params.put(ITEM_AGE,modelMember.getAge());
                            params.put(ITEM_RENTER_NAME,modelMember.getRenter_name());
                            params.put(ITEM_RENTER_CONTACT,modelMember.getRenter_contact());
                            params.put(ITEM_OWNER_LASTNAME,modelMember.getOwner_LastName());
                            params.put(ITEM_RENTER_LASTNAME,modelMember.getRenter_LastName());
                            params.put(ITEM_OWNER_ADDRESS,modelMember.getOwner_Address());
                            params.put(ITEM_RENTER_ADDRESS,modelMember.getRenter_Address());
                            params.put(ITEM_RENTER_LOCATION,modelMember.getRenter_Location());
                            params.put(ITEM_OWNER_LOCATION,modelMember.getOwner_Location());
                            params.put(ITEM_RENTER_AGE,modelMember.getRenter_age());
                            params.put(ITEM_RENTER_EMAIL,modelMember.getRenter_email());
                            params.put(ITEM_OWNER_EMAIL,modelMember.getOwner_email());

                            return params;
                        }
                    };
                    // Adding request to request queue
                    GlobalClassMyApplicationAppController.getInstance().addToReqQueue(postRequest);
                }
                catch (Exception Ex) {
                    Toast.makeText(this, Ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            catch(Exception Ex)
            {
                Toast.makeText(this, Ex.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
        catch(Exception Ex)
        {
            Snackbar.make(v, "Network error , check your network connection and try again!", Snackbar.LENGTH_LONG).show();
        }
    }


    private modelMember getProfileData(View v)
    {
        modelMember _modelMember= new modelMember();
        try
        {
//            Snackbar.make(v, mYregistrationId, Snackbar.LENGTH_LONG).show();

            _modelMember.setOwner_id("37");
            _modelMember.setusr(etOwnerUser.getText().toString());
            _modelMember.setgcmregid(mYregistrationId); //Auto Generate field update later on
            _modelMember.setOwner_name(etOwnerFirstname.getText().toString());
            _modelMember.setOwner_contact(etOwnerPhoneno.getText().toString());
            _modelMember.setAge(etOwnerAge.getText().toString());
            _modelMember.setRenter_name(etRenterFirstname.getText().toString());
            _modelMember.setRenter_contact(etRenterPhoneno.getText().toString());
            _modelMember.setOwner_LastName(etOwnerLastname.getText().toString());
            _modelMember.setRenter_LastName(etRenterLastname.getText().toString());
            _modelMember.setOwner_Address(etOwnerAddress.getText().toString());
            _modelMember.setRenter_Address(etRenterAddress.getText().toString());
            _modelMember.setRenter_Location(atvRenter_places.getText().toString());
            _modelMember.setOwner_Location(atvOwner_places.getText().toString());
            _modelMember.setRenter_age(etRenterAge.getText().toString());
            _modelMember.setRenter_email(etRenterEmail.getText().toString());
            _modelMember.setOwner_email(etOwnerEmail.getText().toString());
        }
        catch(Exception Ex)
        {
            Snackbar.make(v, "Error while retriving data from client machine!", Snackbar.LENGTH_LONG).show();
        }
        return _modelMember;
    }

    private boolean checkValidation() {
        boolean ret = true;

        if (!Validation.hasText(etOwnerFirstname)) ret = false;
        if (!Validation.hasText(etOwnerLastname)) ret = false;
        if (!Validation.hasText(etOwnerAge)) ret = false;
        if (!Validation.hasText(atvOwner_places)) ret = false;
        if (!Validation.hasText(etOwnerAddress)) ret = false;
        if (!Validation.isPhoneNumber(etOwnerPhoneno, true)) ret = false;

        if (!Validation.hasText(etRenterFirstname)) ret = false;
        if (!Validation.hasText(etRenterLastname)) ret = false;
        if (!Validation.hasText(etRenterAge)) ret = false;
        if (!Validation.hasText(etRenterAddress)) ret = false;
        if (!Validation.hasText(atvRenter_places)) ret = false;
        if (!Validation.isPhoneNumber(etRenterPhoneno,true)) ret = false;
        if (!Validation.isEmailAddress(etRenterEmail,true)) ret = false;

        return ret;
    }

}
