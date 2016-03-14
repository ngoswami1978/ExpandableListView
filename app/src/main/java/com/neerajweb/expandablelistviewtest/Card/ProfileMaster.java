package com.neerajweb.expandablelistviewtest.Card;

/**
 * Created by Admin on 07/03/2016.
 */

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.mingle.widget.ShapeLoadingDialog;
import com.neerajweb.expandablelistviewtest.Adapter.adapter_Profile_Master;
import com.neerajweb.expandablelistviewtest.Maintainance.ApartmentApplicationController;
import com.neerajweb.expandablelistviewtest.Model.modelMemberProfile;
import com.neerajweb.expandablelistviewtest.R;
import com.neerajweb.expandablelistviewtest.utils.Const;


public class ProfileMaster extends Activity {
    // Log tag
    private static final String TAG = ProfileMaster.class.getSimpleName();
    private ShapeLoadingDialog shapeLoadingDialog;

    // profiles json url
//    private static final String url = "http://api.androidhive.info/json/profiles.json";
    private ProgressDialog pDialog;
    private List<modelMemberProfile> profileList = new ArrayList<modelMemberProfile>();
    private ListView listView;
    private adapter_Profile_Master adapter;

    String ITEM_FLT_ID="flt_id";
    String ITEM_FLT_NO="flt_no";
    String ITEM_FLT_TYPE="flt_type";
    String ITEM_OWNER_ID="Owner_id";
    String ITEM_OWNER_NAME="Owner_name";
    String ITEM_APPROVE_STATUS="approve_status";
    String ITEM_USR="usr";
    String ITEM_EMAIL="email";
    String ITEM_OWNER_CONTACT="Owner_contact";
    String ITEM_RENTER_NAME="Renter_name";
    String ITEM_RENTER_CONTACT="Renter_contact";
    String ITEM_LASTPAIDMONTH="LastPaidMonth";
    String ITEM_LASTPAIDYEAR="LastPaidYear";
    String ITEM_LASTPAIDAMOUNT="LastPaidAmount";
    String ITEM_LASTPAIDENTRYDT="LastPaidEntrydt";
    String ITEM_LASTPAIDADMINAPPROVAL="LastPaidAdminApproval";
    String ITEM_LASTPAIDBY="LastPaidby";
    String ITEM_CUSTOMFONTNAME="CustomFontName";
    String ITEM_OWNER_EMAIL="Owner_email";
    String ITEM_OWNER_ADDRESS="Owner_Address";
    String ITEM_OWNER_LOCATION="Owner_Location";
    String ITEM_RENTER_ADDRESS="Renter_Address";
    String ITEM_RENTER_LOCATION="Renter_Location";
    String ITEM_RENTER_AGE="Renter_age";
    String ITEM_RENTER_EMAIL="Renter_email";
    String ITEM_PHOTONAME="photoname";
    String ITEM_PHOTOPATH="photopath";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_master_activity_main);

        listView = (ListView) findViewById(R.id.list);
        adapter = new adapter_Profile_Master(this, profileList);
        listView.setAdapter(adapter);

//        pDialog = new ProgressDialog(this);
//        // Showing progress dialog before making http request
//        pDialog.setMessage("Getting profile...");
//        pDialog.show();


        shapeLoadingDialog=new ShapeLoadingDialog(this);
        shapeLoadingDialog.setCanceledOnTouchOutside(false);
        shapeLoadingDialog.setLoadingText("Getting profile...");
        shapeLoadingDialog.show();
        // changing action bar color
//        getActionBar().setBackgroundDrawable(
//                new ColorDrawable(Color.parseColor("#1b1b1b")));

        // Creating volley request obj
        JsonArrayRequest profileReq = new JsonArrayRequest(Const.URL_WS_MEMBERPROFILE_LIST,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject jobj = response.getJSONObject(i);
                                modelMemberProfile profile = new modelMemberProfile();

                                profile.setflt_id(jobj.isNull(ITEM_FLT_ID) ? "" : jobj.getString(ITEM_FLT_ID));
                                profile.setflt_no(jobj.isNull(ITEM_FLT_NO) ? "" : jobj.getString(ITEM_FLT_NO));
                                profile.setflt_type(jobj.isNull(ITEM_FLT_TYPE) ? "" : jobj.getString(ITEM_FLT_TYPE));
                                profile.setOwner_id(jobj.isNull(ITEM_OWNER_ID) ? "" : jobj.getString(ITEM_OWNER_ID));
                                profile.setOwner_name(jobj.isNull(ITEM_OWNER_NAME) ? "" : jobj.getString(ITEM_OWNER_NAME));
                                profile.setapprove_status(jobj.isNull(ITEM_APPROVE_STATUS) ? "0" : jobj.getString(ITEM_APPROVE_STATUS));
                                profile.setusr(jobj.isNull(ITEM_USR) ? "" : jobj.getString(ITEM_USR));
                                profile.setemail(jobj.isNull(ITEM_EMAIL) ? "" : jobj.getString(ITEM_EMAIL));
                                profile.setOwner_contact(jobj.isNull(ITEM_OWNER_CONTACT) ? "" : jobj.getString(ITEM_OWNER_CONTACT));
                                profile.setRenter_name(jobj.isNull(ITEM_RENTER_NAME) ? "" : jobj.getString(ITEM_RENTER_NAME));
                                profile.setRenter_contact(jobj.isNull(ITEM_RENTER_CONTACT) ? "" : jobj.getString(ITEM_RENTER_CONTACT));
                                profile.setLastPaidMonth(jobj.isNull(ITEM_LASTPAIDMONTH) ? "" : jobj.getString(ITEM_LASTPAIDMONTH));
                                profile.setLastPaidYear(jobj.isNull(ITEM_LASTPAIDYEAR) ? "" : jobj.getString(ITEM_LASTPAIDYEAR));
                                profile.setLastPaidAmount(jobj.isNull(ITEM_LASTPAIDAMOUNT) ? "" : jobj.getString(ITEM_LASTPAIDAMOUNT));
                                profile.setLastPaidEntrydt(jobj.isNull(ITEM_LASTPAIDENTRYDT) ? "" : jobj.getString(ITEM_LASTPAIDENTRYDT));
                                profile.setLastPaidAdminApproval(jobj.isNull(ITEM_LASTPAIDADMINAPPROVAL) ? "" : jobj.getString(ITEM_LASTPAIDADMINAPPROVAL));
                                profile.setLastPaidby(jobj.isNull(ITEM_LASTPAIDBY) ? "" : jobj.getString(ITEM_LASTPAIDBY));
                                profile.setCustomFontName(jobj.isNull(ITEM_CUSTOMFONTNAME) ? "" : jobj.getString(ITEM_CUSTOMFONTNAME));
                                profile.setOwner_email(jobj.isNull(ITEM_OWNER_EMAIL) ? "" : jobj.getString(ITEM_OWNER_EMAIL));
                                profile.setOwner_Address(jobj.isNull(ITEM_OWNER_ADDRESS) ? "" : jobj.getString(ITEM_OWNER_ADDRESS));
                                profile.setOwner_Location(jobj.isNull(ITEM_OWNER_LOCATION) ? "" : jobj.getString(ITEM_OWNER_LOCATION));
                                profile.setRenter_Address(jobj.isNull(ITEM_RENTER_ADDRESS) ? "" : jobj.getString(ITEM_RENTER_ADDRESS));
                                profile.setRenter_Location(jobj.isNull(ITEM_RENTER_LOCATION) ? "" : jobj.getString(ITEM_RENTER_LOCATION));
                                profile.setRenter_age(jobj.isNull(ITEM_RENTER_AGE) ? "" : jobj.getString(ITEM_RENTER_AGE));
                                profile.setRenter_email(jobj.isNull(ITEM_RENTER_EMAIL) ? "" : jobj.getString(ITEM_RENTER_EMAIL));
                                profile.setphotoname(jobj.isNull(ITEM_PHOTONAME) ? "" : jobj.getString(ITEM_PHOTONAME));
                                profile.setphotopath(jobj.isNull(ITEM_PHOTOPATH) ? "" : jobj.getString(ITEM_PHOTOPATH));
                                profile.setThumbnailUrl(jobj.isNull(ITEM_PHOTOPATH) ? "" : jobj.getString(ITEM_PHOTOPATH) + "/" + jobj.getString(ITEM_PHOTONAME));

                                // adding profile to profiles array
                                profileList.add(profile);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        ApartmentApplicationController.getInstance().addToReqQueue(profileReq);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (shapeLoadingDialog!= null) {
            shapeLoadingDialog.dismiss();
            shapeLoadingDialog= null;
        }

//        if (pDialog != null) {
//            pDialog.dismiss();
//            pDialog = null;
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}