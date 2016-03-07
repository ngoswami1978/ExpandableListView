package com.neerajweb.expandablelistviewtest;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.neerajweb.expandablelistviewtest.JSONfunctions.result_MemberJSON;
import com.neerajweb.expandablelistviewtest.Maintainance.ApartmentApplicationController;
import com.neerajweb.expandablelistviewtest.Model.modelMember;
import com.neerajweb.expandablelistviewtest.SqlLiteHelper.SQLiteHandler;
import com.neerajweb.expandablelistviewtest.utils.Const;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class LoginMainActivity extends AppCompatActivity {

    ProgressDialog PDLoadRegister;
    ProgressDialog PDLoadLogin;

    ArrayList<modelMember> model_Member;
    private ArrayList<result_MemberJSON> resultMember;
    public Context mContext;
    private SessionManager session;
    private SQLiteHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity_main);
        mContext = this;
        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Session manager
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);
//            finish();
        }

        final MaterialLoginView login = (MaterialLoginView) findViewById(R.id.login);

        login.setListener(new MaterialLoginViewListener() {
            @Override
            public void onRegister(TextInputLayout registerUser, Spinner flatspinner) {
                String user = registerUser.getEditText().getText().toString().trim();
                if (user.isEmpty()) {
                    registerUser.setError("Email can't be empty");
                    return;
                }
                registerUser.setError("");

                TextView errorText = (TextView)flatspinner.getSelectedView();
                if (errorText.getText()=="Choose Flat") {
                    errorText.setError("Please select your flat");
                    return;
                }
                register(user,login.intFlatId);
                //Snackbar.make(login, "Register success!", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onLogin(TextInputLayout loginUser, TextInputLayout loginPass) {
                String user = loginUser.getEditText().getText().toString();
                if (user.isEmpty()) {
                    loginUser.setError("User name can't be empty");
                    return;
                }
                loginUser.setError("");

                String pass = loginPass.getEditText().getText().toString();
                if (pass.isEmpty()) {
                    loginPass.setError("Password can't bt empty");
                    return;
                }
                loginPass.setError("");
//                Snackbar.make(login, "Login success!", Snackbar.LENGTH_LONG).show();

                login(user, pass);
            }
            @Override
            public void register(final String user,final int flatid)
            {
//                Snackbar.make(login, "Selected flat id !" + String.valueOf(flatid), Snackbar.LENGTH_LONG).show();
                registerUser objRegister = new registerUser();
                objRegister.registerNew(login, user, flatid);
            }

            public void login(final String user,final String pass)
            {
                loginUser objLogin = new loginUser ();
                objLogin.Login(login, user, pass);
            }
        });
    }


    private class loginUser {
        public loginUser() {
            PDLoadLogin = new ProgressDialog(mContext);
            PDLoadLogin.setTitle("Authenticating");
            PDLoadLogin.setMessage("Please wait...");
            PDLoadLogin.setCancelable(false);
        }
        private void Login(final MaterialLoginView loginView,final String strUser,final String strPass)
        {
            final String ITEM_SUBMIT_TYPE="submit_type";
            final String ITEM_PASS="pass";
            final String ITEM_EMAIL="email";
            // Tag used to cancel the request
            String tag_string_req = "req_login";
            try
            {
                //create a new child and add that to the group
                final modelMember _modelMember = new modelMember();
                try {

                    PDLoadLogin.show();
                    StringRequest postRequest = new StringRequest(Request.Method.POST, Const.URL_WS_CRUD_LOGIN,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        // prepare the list of all records--------
                                        model_Member = new ArrayList<modelMember>();
                                        //----------------------------------------
                                        JSONObject jsonObject = new JSONObject(response);
                                        ArrayList<result_MemberJSON> resultjsonobj = new ArrayList<result_MemberJSON>();
                                        resultjsonobj = parseJsonResult(jsonObject);
                                        int success = resultjsonobj.get(0).getSuccess();
                                        String retmessage,retOwnerId;

                                        if (success == 1) {
                                            PDLoadLogin.dismiss();
                                            retmessage = resultjsonobj.get(0).getMessage();
                                            retOwnerId= resultjsonobj.get(0).getOwner_id();

                                            if(retOwnerId.toString().trim()!="")
                                            {
                                                // user successfully logged in
                                                // Create login session
                                                session.setLogin(true);
                                                // Inserting row in users table
                                                db.addUser(resultjsonobj.get(0).getMemberList().get(0).getOwner_name(), strUser, resultjsonobj.get(0).getOwner_id(), resultjsonobj.get(0).getMemberList().get(0).getdt());
                                            }
                                            Snackbar.make(loginView, retmessage + " " + "!", Snackbar.LENGTH_LONG).show();
                                            ApartmentApplicationController.getInstance().getRequestQueue().getCache().clear();

                                            //Toast.makeText(mContext ,retmessage, Toast.LENGTH_SHORT).show();
                                            startTimer(loginView);
                                        } // if ends
                                    } catch (JSONException e) {
                                        PDLoadLogin.dismiss();
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            PDLoadLogin.dismiss();
                            Toast.makeText(mContext,
                                    "we are unable to reach server! failed to login", Toast.LENGTH_SHORT).show();
                            Snackbar.make(loginView, "we are unable to reach server! failed to login! please try again", Snackbar.LENGTH_LONG).show();
                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put(ITEM_EMAIL, strUser);
                            params.put(ITEM_PASS, strPass);
                            params.put(ITEM_SUBMIT_TYPE, "Login");
                            return params;
                        }
                    };
                    // Adding request to request queue
                    ApartmentApplicationController.getInstance().addToReqQueue(postRequest,tag_string_req);
                }
                catch(Exception Ex){
                    PDLoadLogin.dismiss();
                    Toast.makeText(mContext, Ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            catch(Exception Ex)
            {
                Toast.makeText(mContext , Ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class registerUser {
        public registerUser() {
            PDLoadRegister = new ProgressDialog(mContext);
            PDLoadRegister.setTitle("Creating Account");
            PDLoadRegister.setMessage("Please wait...");
            PDLoadRegister.setCancelable(false);
        }
        private void registerNew(final MaterialLoginView loginView,final String strUser,final int intFlatid)
        {
            final String ITEM_SUBMIT_TYPE="submit_type";
            final String ITEM_FLT_ID="flt_id";
            final String ITEM_EMAIL="email";
            // Tag used to cancel the request
            String tag_string_req = "req_register";

            try
            {
                //create a new child and add that to the group
                final modelMember _modelMember = new modelMember();
                try {

                    PDLoadRegister.show();
                        StringRequest postRequest = new StringRequest(Request.Method.POST, Const.URL_WS_CRUD_LOGIN,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        // prepare the list of all records--------
                                        model_Member = new ArrayList<modelMember>();
                                        //----------------------------------------
                                        JSONObject jsonObject = new JSONObject(response);
                                        ArrayList<result_MemberJSON> resultjsonobj = new ArrayList<result_MemberJSON>();
                                        resultjsonobj = parseJsonResult(jsonObject);
                                        int success = resultjsonobj.get(0).getSuccess();
                                        String retmessage,retOwnerId;
                                        if (success == 1) {
                                            PDLoadRegister.dismiss();
                                            retmessage = resultjsonobj.get(0).getMessage();
                                            retOwnerId= resultjsonobj.get(0).getOwner_id();
                                            if(retOwnerId.toString().trim()!="")
                                            {
                                                // Inserting row in users table
                                                db.addUser(resultjsonobj.get(0).getMemberList().get(0).getOwner_name(), strUser, resultjsonobj.get(0).getOwner_id(), resultjsonobj.get(0).getMemberList().get(0).getdt());
                                            }
                                            Snackbar.make(loginView, retmessage + " " + "!", Snackbar.LENGTH_LONG).show();
                                            ApartmentApplicationController.getInstance().getRequestQueue().getCache().clear();
                                            //Toast.makeText(mContext ,retmessage, Toast.LENGTH_SHORT).show();
                                          startTimer(loginView);
                                        } // if ends
                                    } catch (JSONException e) {
                                        PDLoadRegister.dismiss();
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener()
                            {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                PDLoadRegister.dismiss();
                                Toast.makeText(mContext,
                                        "we are unable to reach server! failed to register", Toast.LENGTH_SHORT).show();
                            }
                            })
                            {
                                @Override
                                protected Map<String, String> getParams() {
                                    Map<String, String> params = new HashMap<String, String>();
                                    params.put(ITEM_EMAIL, strUser);
                                    params.put(ITEM_FLT_ID, String.valueOf(intFlatid));
                                    params.put(ITEM_SUBMIT_TYPE, "Register");
                                    return params;
                                }
                            };
                    // Adding request to request queue
                    ApartmentApplicationController.getInstance().addToReqQueue(postRequest,tag_string_req);
                    }
                    catch(Exception Ex){
                        PDLoadRegister.dismiss();
                        Toast.makeText(mContext, Ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
            }

            catch(Exception Ex)
            {
                Toast.makeText(mContext , Ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startTimer(final MaterialLoginView loginView)
    {
        final int I=60;
        final Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            int TimeCounter=0;
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        if (TimeCounter == I) {
                            Snackbar.make(loginView, "System is ready ! please signup with different crediantial", Snackbar.LENGTH_LONG).show();
                            t.cancel();
                            return;
                        }
                        TimeCounter++;
                    }
                });
            }
        }, 0, 1000);
    }

    public static long getMinutesDifference(long timeStart,long timeStop){
        long diff = timeStop - timeStart;
        long diffMinutes = diff / (60 * 1000);

        return  diffMinutes;
    }

    private ArrayList<result_MemberJSON> parseJsonResult(JSONObject response) {
        result_MemberJSON item= new result_MemberJSON();
        resultMember= new ArrayList<result_MemberJSON>();
        //get the children for the group
        ArrayList<modelMember> memberList = item.getMemberList();

        String ITEM_OWNER_ID="Owner_id";
        String ITEM_APPROVE_STATUS="approve_status";
        String ITEM_OWNER_NAME="Owner_name";
        String ITEM_DT="dt";
        String ITEM_SUCCESS="success";
        String ITEM_MESSAGE="message";

        String ITEM_USR="usr";
        String ITEM_EMAIL="email";
        String ITEM_REGIP="regIP";
        String ITEM_GCMREGID="gcmregid";
        String ITEM_FLT_ID="flt_id";
        String ITEM_FLT_TYPE="flt_type";
        String ITEM_FLT_NO="flt_no";
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

}
