package com.neerajweb.expandablelistviewtest;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neerajweb.expandablelistviewtest.Adapter.adapter_Maintainancedetail;
import com.neerajweb.expandablelistviewtest.Model.modelMaintainancedetail;
import com.neerajweb.expandablelistviewtest.Maintainance.GlobalClassMyApplicationAppController;
import com.neerajweb.expandablelistviewtest.utils.Const;

/**
 * Created by Admin on 20-11-2015.
 */
public class CustomDialogMaintainance extends Dialog{

    private TextView rptsubheader;
    private TextView rptheaderTitle;
    private ImageView mCancelButton;
    private Activity mContext;
    private WebView mWebView;
    private ProgressDialog mProgressDialog;
    private adapter_Maintainancedetail objMaintainanceAdapter;
    private ListView listView;
    private ArrayList<modelMaintainancedetail> objModelMaintainancedetail = new ArrayList<modelMaintainancedetail>();
    private int intMonth;
    private int intYear;


    List<HashMap<String, String>> fillMaps;

    // create hash map to contain webservice parameter detail
    HashMap<String, String> hashmapmaintaincneInput = new HashMap<String, String>();

    public CustomDialogMaintainance(Activity context) {
        super(context);
    }

    public CustomDialogMaintainance(Activity context, String strMonth, String strYear) {
        super(context);
        mContext = context;
        //Fill hash map for month and year
        hashmapmaintaincneInput.put("MONTH", strMonth);
        hashmapmaintaincneInput.put("YEAR", strYear);

        intMonth=Integer.parseInt(strMonth);
        intYear = Integer.parseInt(strYear);
        init();
    }

    public CustomDialogMaintainance(FragmentActivity context, int theme) {
        super(context);
        mContext = context;
        init();
    }

    private void init() {
        try
        {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.activity_rptmaintainance_detail_container);
            setCancelable(false);
            setCanceledOnTouchOutside(true);

            rptsubheader = (TextView) findViewById(R.id.rptsubheader);
            rptheaderTitle = (TextView) findViewById(R.id.rptheaderTitle);

            mWebView = (WebView) findViewById(R.id.webView);
            mCancelButton = (ImageView) findViewById(R.id.cancelBtn);
            listView= ( ListView ) findViewById( R.id.detail_list );  // List defined in XML ( See Below )

            //Add Header into maintainance Row (ListView)
            LayoutInflater inflater = getLayoutInflater();
            View rptheader = inflater.inflate(R.layout.activity_rptmaintainance_detail_header_view, listView, false);
            listView.addHeaderView(rptheader, null, false);

            objMaintainanceAdapter = new adapter_Maintainancedetail(mContext, objModelMaintainancedetail);
            listView.setAdapter(objMaintainanceAdapter);

//            rptsubheader.setText("Welcome Custome Dialog");
            rptsubheader.setText("Total Rs. ");
            rptheaderTitle.setText("PERIOD " + getMonth(intMonth).toUpperCase() + " " + String.valueOf(intYear));

            mCancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    dismiss();
                }
            });

            rptMaintainance objMain = new rptMaintainance(mContext);
            objMain.ReadDataFromDB(objModelMaintainancedetail,hashmapmaintaincneInput,rptsubheader);

            WindowManager manager = (WindowManager) mContext.getSystemService(Activity.WINDOW_SERVICE);
            int width, height;

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.FROYO) {
                width = manager.getDefaultDisplay().getWidth();
                height = manager.getDefaultDisplay().getHeight();
            } else {
                Point point = new Point();
                manager.getDefaultDisplay().getSize(point);
                width = point.x;
                height = point.y;
            }

            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.dimAmount = 0.7f; // Dim level. 0.0 - no dim, 1.0 - completely opaque
            lp.width = width;
            lp.height = height-height/10;
            getWindow().setAttributes(lp);
            getWindow().getAttributes().windowAnimations = R.style.SlideDialogAnimation;

        }
        catch (Exception Ex )
        {
            Toast.makeText(getContext(), Ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month-1];
    }

    public void showDialog() {
        if(!isShowing()){
            show();
        }
    }

    public void showDialogWithMsg(String msg) {
        if(!isShowing()){
            try
            {
                rptsubheader.setText(msg);
//                show();
            }
            catch (Exception Ex)
            {
                Toast.makeText(getContext(), Ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void showDialogWebView(String url) {
        if(!isShowing()){
            rptsubheader.setVisibility(View.GONE);
            mWebView.setVisibility(View.VISIBLE);
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.setVerticalScrollBarEnabled(true);
            mProgressDialog = new ProgressDialog(mContext);
            mWebView.loadUrl(url);
            mWebView.setWebViewClient(new myWebClient());
            show();
        }
    }

    public class myWebClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        public void onLoadResource (WebView view, String url) {
            if (mProgressDialog != null) {
                mProgressDialog.show();
            }
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            try {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;

        }
    }

    public class rptMaintainance {
//      String url = "http://www.myandroidng.com/Apartment/WS/ws_search_maintainancedetail.php";

        String url = Const.URL_WS_SEARCH_MAINTAINANCEDETAIL;
        ProgressDialog PD;

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

        private modelMaintainancedetail model_detail;

        public rptMaintainance(Activity context) {
            mContext = context;
            PD = new ProgressDialog(mContext);
            PD.setMessage("Loading Maintainance detail" + "\n" + "please wait.....");
            PD.setCancelable(false);
        }

        private void ReadDataFromDB(final ArrayList<modelMaintainancedetail> objModelMaintainancedetail,final HashMap<String, String> hashmapMonthYear,final TextView rptsubheader ) {
            try {
                PD.show();


                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    int success =jsonObject.getInt("success");
                                    int intTotalAmt=0;
                                    if (success == 1) {
                                        JSONArray ja = jsonObject.getJSONArray("maintainance_detail");

                                        // prepare the list of all records
                                        fillMaps = new ArrayList<HashMap<String, String>>();

                                        for (int i = 0; i < ja.length(); i++) {
                                            model_detail = new modelMaintainancedetail();

                                            JSONObject jobj = ja.getJSONObject(i);
                                            HashMap<String, String> item = new HashMap<String, String>();
                                            HashMap<String, String> map = new HashMap<String, String>();

                                            map.put(ITEM_SRNO, String.valueOf(i + 1));
                                            map.put(ITEM_FLATNO,jobj.getString(ITEM_FLATNO));
                                            map.put(ITEM_FLATTYPE,jobj.getString(ITEM_FLATTYPE));
                                            map.put(ITEM_FLATOWNERNAME,jobj.isNull(ITEM_FLATOWNERNAME) ? "" : jobj.getString(ITEM_FLATOWNERNAME));
                                            map.put(ITEM_FLATOWNERCONTACT,jobj.isNull(ITEM_FLATOWNERCONTACT) ? "" : jobj.getString(ITEM_FLATOWNERCONTACT));
                                            map.put(ITEM_FLATRENTERNAME,jobj.isNull(ITEM_FLATRENTERNAME) ? "" : jobj.getString(ITEM_FLATRENTERNAME));
                                            map.put(ITEM_FLATRENTERCONTACT,jobj.isNull(ITEM_FLATRENTERCONTACT) ? "" : jobj.getString(ITEM_FLATRENTERCONTACT));
                                            map.put(ITEM_AMTPAID, jobj.isNull(ITEM_AMTPAID) ? "" : jobj.getString(ITEM_AMTPAID));
                                            map.put(ITEM_ENTRYDATE, jobj.isNull(ITEM_ENTRYDATE) ? "" : jobj.getString(ITEM_ENTRYDATE));
                                            map.put(ITEM_DTL_ID, String.valueOf(jobj.isNull(ITEM_DTL_ID) ? 0 : jobj.getString(ITEM_DTL_ID))); //dlt_id may come null value

                                            intTotalAmt +=  jobj.isNull(ITEM_AMTPAID) ? 0 : Integer.parseInt(jobj.getString(ITEM_AMTPAID)) ;

                                            model_detail.setSrno(i + 1);
                                            model_detail.setflatnumber(jobj.getString(ITEM_FLATNO));
                                            model_detail.setflattype(jobj.getString(ITEM_FLATTYPE));
                                            model_detail.setownername(jobj.isNull(ITEM_FLATOWNERNAME) ? "" : jobj.getString(ITEM_FLATOWNERNAME));
                                            model_detail.setOwnercontact(jobj.isNull(ITEM_FLATOWNERCONTACT) ? "" : jobj.getString(ITEM_FLATOWNERCONTACT));
                                            model_detail.setRentername(jobj.isNull(ITEM_FLATRENTERNAME) ? "" : jobj.getString(ITEM_FLATRENTERNAME));
                                            model_detail.setRentercontact(jobj.isNull(ITEM_FLATRENTERCONTACT) ? "" : jobj.getString(ITEM_FLATRENTERCONTACT));
                                            model_detail.setPaidAmount(jobj.isNull(ITEM_AMTPAID) ? "" : jobj.getString(ITEM_AMTPAID));
                                            model_detail.setPaidEntrydt(jobj.isNull(ITEM_ENTRYDATE) ? "" : jobj.getString(ITEM_ENTRYDATE));
                                            model_detail.setdtlid(jobj.isNull(ITEM_DTL_ID) ? 0 : jobj.getInt(ITEM_DTL_ID));

                                            objModelMaintainancedetail.add(model_detail);
                                            fillMaps.add(map);
                                        } // for loop ends

                                        PD.dismiss();
                                        //showDialogWithMsg("Done......",objModelMaintainancedetail);
                                        objMaintainanceAdapter.notifyDataSetChanged();
                                        rptsubheader.setText("Total Rs. " + String.valueOf(intTotalAmt));
                                    } // if ends
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        PD.dismiss();
                        Toast.makeText(mContext,
                                "failed to retrive infomations please check your network connection...", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("mth",hashmapMonthYear.get("MONTH"));
                        params.put("yr",hashmapMonthYear.get("YEAR"));
                        return params;
                    }
                };
                // Adding request to request queue
                GlobalClassMyApplicationAppController.getInstance().addToReqQueue(postRequest);
            }
            catch (Exception Ex) {
                Toast.makeText(getContext(), Ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
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
}
