package com.neerajweb.expandablelistviewtest.report;

/**
 * Created by Admin on 13/01/2016.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.neerajweb.expandablelistviewtest.Model.modelMaintainancedetail;
import com.neerajweb.expandablelistviewtest.R;
import com.neerajweb.expandablelistviewtest.app.AppController;

public class rptMaintainance extends ListActivity {

        String url = "http://www.myandroidng.com/Apartment/WS/ws_search_maintainancedetail.php";
        ArrayList<HashMap<String, String>> Item_List;
        ArrayList<modelMaintainancedetail> objModelMaintainancedetail;

        ProgressDialog PD;
        ListAdapter adapter;

        // JSON Node names
        public static final String ITEM_SRNO="Srno";
        public static final String ITEM_FLT_ID="flt_id";
        public static final String ITEM_FLATNO="flt_no";
        public static final String ITEM_FLATTYPE="flt_type";
        public static final String ITEM_FLATOWNERID= "Owner_id";
        public static final String ITEM_FLATOWNERNAME="Owner_name";
        public static final String ITEM_FLATOWNERCONTACT="Owner_contact";
        public static final String ITEM_FLATOWNEREMAIL="email";
        public static final String ITEM_FLATRENTERNAME="Renter_name";
        public static final String ITEM_FLATRENTERCONTACT="Renter_contact";
        public static final String ITEM_PAIDMONTH="PaidMonth";
        public static final String ITEM_PAIDYEAR="PaidYear";
        public static final String ITEM_AMTPAID="PaidAmount";
        public static final String ITEM_DTL_ID="dtl_id";
        public static final String ITEM_ENTRYDATE="PaidEntrydt";
        public static final String ITEM_PAIDBY="Paidby";


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            Item_List = new ArrayList<HashMap<String, String>>();

            PD = new ProgressDialog(this);
            PD.setMessage("Loading.....");
            PD.setCancelable(false);

            getListView().setOnItemClickListener(new ListitemClickListener());

            ReadDataFromDB();
        }

    private void ReadDataFromDB() {
        PD.show();
        objModelMaintainancedetail = new ArrayList<modelMaintainancedetail>();

        JsonObjectRequest jreq = new JsonObjectRequest(Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int success = response.getInt("success");

                            if (success == 1) {
                                JSONArray ja = response.getJSONArray("maintainance_details");

                                // prepare the list of all records
                                List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();

                                for (int i = 0; i < ja.length(); i++) {

                                    JSONObject jobj = ja.getJSONObject(i);
                                    HashMap<String, String> item = new HashMap<String, String>();
                                    HashMap<String, String> map = new HashMap<String, String>();

                                    map.put(ITEM_SRNO, String.valueOf(jobj.getInt(ITEM_DTL_ID)));
                                    map.put(ITEM_FLATNO,jobj.getString(ITEM_FLATNO));
                                    map.put(ITEM_FLATTYPE,jobj.getString(ITEM_FLATTYPE));
                                    map.put(ITEM_FLATOWNERNAME,jobj.getString(ITEM_FLATOWNERNAME));
                                    map.put(ITEM_FLATOWNERCONTACT,jobj.getString(ITEM_FLATOWNERCONTACT));
                                    map.put(ITEM_FLATRENTERNAME,jobj.getString(ITEM_FLATRENTERNAME));
                                    map.put(ITEM_FLATRENTERCONTACT,jobj.getString(ITEM_FLATRENTERCONTACT));
                                    map.put(ITEM_AMTPAID,jobj.getString(ITEM_AMTPAID));
                                    map.put(ITEM_DTL_ID,String.valueOf(jobj.getInt(ITEM_DTL_ID)));

                                    fillMaps.add(map);

//                                    modelMaintainancedetail detailrpop = new modelMaintainancedetail();
//                                    detailrpop.setSrno(jobj.getInt(ITEM_DTL_ID));
//                                    detailrpop.setfltid(jobj.getInt(ITEM_FLT_ID));
//                                    detailrpop.setflatnumber(jobj.getString(ITEM_FLATNO));
//                                    detailrpop.setflattype(jobj.getString(ITEM_FLATTYPE));
//                                    detailrpop.setownerid(jobj.getInt(ITEM_FLATOWNERID));
//                                    detailrpop.setownername(jobj.getString(ITEM_FLATOWNERNAME));
//                                    detailrpop.setOwnercontact(jobj.getString(ITEM_FLATOWNERCONTACT));
//                                    detailrpop.setOwneremail(jobj.getString(ITEM_FLATOWNEREMAIL));
//                                    detailrpop.setRentername(jobj.getString(ITEM_FLATRENTERNAME));
//                                    detailrpop.setRentercontact(jobj.getString(ITEM_FLATRENTERCONTACT));
//                                    detailrpop.setPaidAmount(jobj.getString(ITEM_AMTPAID));
//                                    detailrpop.setPaidMonth(jobj.getString(ITEM_PAIDMONTH));
//                                    detailrpop.setPaidYear(jobj.getString(ITEM_PAIDYEAR));
//                                    detailrpop.setPaidEntrydt(jobj.getString(ITEM_ENTRYDATE));
//                                    detailrpop.setPaidby(jobj.getString(ITEM_PAIDBY));
//                                    detailrpop.setdtlid(jobj.getInt(ITEM_DTL_ID));

//                                    objModelMaintainancedetail.add(detailrpop);

//                                    item.put(ITEM_ID, jobj.getString(ITEM_ID));
//                                    item.put(ITEM_NAME,jobj.getString(ITEM_NAME));

//                                    Item_List.add(item);

                                } // for loop ends

                                String[] from = {ITEM_SRNO,ITEM_FLATNO,ITEM_FLATTYPE,ITEM_FLATOWNERNAME,ITEM_FLATOWNERCONTACT,ITEM_FLATRENTERNAME,ITEM_FLATRENTERCONTACT,ITEM_AMTPAID,ITEM_DTL_ID};
                                int[] to = { R.id.mta_SrNo, R.id.mta_FlatNo,R.id.mta_FlatType,R.id.mta_FlatOwnerName,R.id.mta_FlatOwnerContact,R.id.mta_FlatOwnerContact,R.id.mta_FlatRenterName,R.id.mta_FlatRenterContact,R.id.mta_AmtPaid,R.id.mta_dtl_id};

                                adapter = new SimpleAdapter(
                                        getApplicationContext(), fillMaps,
                                        R.layout.activity_rptmaintainance_detail_container, from, to);

                                setListAdapter(adapter);

                                PD.dismiss();

                            } // if ends

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                PD.dismiss();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jreq);
    }

    //On List Item Click move to UpdateDelete Activity
    class ListitemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
//            Intent modify_intent = new Intent(ReadData.this,UpdateDeleteData.class);
//            modify_intent.putExtra("item", Item_List.get(position));
//            startActivity(modify_intent);
        }

    }
}
