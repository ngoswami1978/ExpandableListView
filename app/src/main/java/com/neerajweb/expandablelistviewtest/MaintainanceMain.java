package com.neerajweb.expandablelistviewtest;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.neerajweb.expandablelistviewtest.DateTimePicker.DateTime;
import com.neerajweb.expandablelistviewtest.DateTimePicker.DateTimePicker;
import com.neerajweb.expandablelistviewtest.DateTimePicker.SimpleDateTimePicker;
import com.neerajweb.expandablelistviewtest.JSONfunctions.memberJSON;
import com.neerajweb.expandablelistviewtest.Maintainance.ApartmentApplicationController;
import com.neerajweb.expandablelistviewtest.Model.modelMaintainanceLoadReportPeriods;
import com.neerajweb.expandablelistviewtest.QuickActionDialogFragment.AlignmentFlag;
import com.neerajweb.expandablelistviewtest.QuickActionDialogFragment.QuickActionDialogFragment;
import com.neerajweb.expandablelistviewtest.utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;



/**
 * Created by Admin on 06/12/2015.
 */

public class MaintainanceMain extends ActionBarActivity implements DateTimePicker.OnDateTimeSetListener {

    //  Use QuickActionDialogFragment here to show report
    private MyQuickDialogFragment myQuickDialogFragment;

    private MaintainanceCollectionExpandableBouncerLayout mMaintainanceCollectionExpandableBouncerLayout;
    private modelMaintainanceLoadReportPeriods model_rptPeriods;

    private static Context mContext; //not used yet we can use it later on
    JSONObject jsonobject;
    JSONArray jsonarray;

    ArrayList<String> Arraylst_Spinner_Member;
    ArrayList<memberJSON> member;
    ArrayList<HashMap<String, String>> arraylist;
    ArrayList<HashMap<String, String>> fillMaps;

    EditText edtAmount;
    Spinner mySpinner;
    Spinner my_paymodespinner;

    TextView txtownername;
    TextView txtrentername;
    TextView txtflatnumber;
    TextView txtlastPaidMonth;
    TextView txtlastPaidYear;
    TextView txtlastPaidAmount;
    TextView txtlastPaidEntrydt;
    TextView txtfltdiscription;
    TextView txtlastPaidby;

    LinearLayout bubblecontentWithBackgrounddate;
    LinearLayout bubblecontentWithBackgroundcash;

    String Error = null;
    String flatDescription;
    String renterName;
    boolean isSpinnerInitial ;
    View dialogView=null;
    boolean blnIsValidation;
    boolean IsSaveMaintainance;

    String mMonth;
    String mMonthCode;
    String mYear;

    private String strRptMonth;
    private String strRptYear;

    //MaintainanceMain update progress dialog
    ProgressDialog MPD;
    ProgressDialog PD;

    // create hash map
    HashMap<String, String> hashmapmaintaincneInput = new HashMap<String, String>();

    Date mCurrentDate;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private AnimationDrawable d;

    TextView texttvDisprpttext;
    TextView tvCollectionDisptext;

    TextView tvCalenderJAN;
    TextView tvCalenderFEB;
    TextView tvCalenderMAR;
    TextView tvCalenderAPR;
    TextView tvCalenderMAY;
    TextView tvCalenderJUN;
    TextView tvCalenderJUL;
    TextView tvCalenderAUG;
    TextView tvCalenderSEP;
    TextView tvCalenderOCT;
    TextView tvCalenderNOV;
    TextView tvCalenderDEC;


    TextView textviewjan;
    TextView textviewfeb;
    TextView textviewmar;
    TextView textviewapr;
    TextView textviewmay;
    TextView textviewjun;
    TextView textviewjul;
    TextView textviewaug;
    TextView textviewsep;
    TextView textviewoct;
    TextView textviewnov;
    TextView textviewdec;

    TextView txtviewjantotal;
    TextView txtviewfebtotal;
    TextView txtviewmartotal;
    TextView txtviewaprtotal;
    TextView txtviewmaytotal;
    TextView txtviewjuntotal;
    TextView txtviewjultotal;
    TextView txtviewaugtotal;
    TextView txtviewseptotal;
    TextView txtviewocttotal;
    TextView txtviewnovtotal;
    TextView txtviewdectotal;

    TextView txtviewjantotalamount;
    TextView txtviewfebtotalamount;
    TextView txtviewmartotalamount;
    TextView txtviewaprtotalamount;
    TextView txtviewmaytotalamount;
    TextView txtviewjuntotalamount;
    TextView txtviewjultotalamount;
    TextView txtviewaugtotalamount;
    TextView txtviewseptotalamount;
    TextView txtviewocttotalamount;
    TextView txtviewnovtotalamount;
    TextView txtviewdectotalamount;


    Drawable[] myTextViewCompoundDrawablesJAN;
    Drawable[] myTextViewCompoundDrawablesFEB;
    Drawable[] myTextViewCompoundDrawablesMAR;
    Drawable[] myTextViewCompoundDrawablesAPR;
    Drawable[] myTextViewCompoundDrawablesMAY;
    Drawable[] myTextViewCompoundDrawablesJUN;
    Drawable[] myTextViewCompoundDrawablesJUL;
    Drawable[] myTextViewCompoundDrawablesAUG;
    Drawable[] myTextViewCompoundDrawablesSEP;
    Drawable[] myTextViewCompoundDrawablesOCT;
    Drawable[] myTextViewCompoundDrawablesNOV;
    Drawable[] myTextViewCompoundDrawablesDEC;

    int MAX_LEVEL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintainancemain);
        IsSaveMaintainance=false;
        mContext=getApplicationContext();

        //Code for total Collection using Expandable Bounce effect
        View switcher = findViewById(R.id.switcher);
        mMaintainanceCollectionExpandableBouncerLayout = (MaintainanceCollectionExpandableBouncerLayout) findViewById(R.id.maintainancecollection);
        mMaintainanceCollectionExpandableBouncerLayout.setSwitcher(switcher);
        mMaintainanceCollectionExpandableBouncerLayout.setExpandInterpolator(new BounceInterpolator());
        mMaintainanceCollectionExpandableBouncerLayout.setCollapseInterpolator(new AccelerateDecelerateInterpolator());
        mMaintainanceCollectionExpandableBouncerLayout.setExpandDuration(800);
        mMaintainanceCollectionExpandableBouncerLayout.setCollapseDuration(400);

        mMaintainanceCollectionExpandableBouncerLayout.setOnStateChangedListener(new MaintainanceCollectionExpandableBouncerLayout.OnStateChangedListener() {
            @Override
            public void onPreExpand() {
                Log.d("ExpandableLayout", "onPreExpand");
            }

            @Override
            public void onPreCollapse() {
                Log.d("ExpandableLayout", "onPreCollapse");
            }

            @Override
            public void onExpanded() {
                Log.d("ExpandableLayout", "onExpanded");
            }

            @Override
            public void onCollapsed() {
                Log.d("ExpandableLayout", "onCollapsed");
            }
        });
        findViewById(R.id.btnTitle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaintainanceCollectionExpandableBouncerLayout.toggle();
            }
        });
        //end Code for total Collection using Expandable Bounce effect

        MPD = new ProgressDialog(this);
        MPD.setMessage("please wait your payment is posting.....");
        MPD.setCancelable(false);

        mCurrentDate = new Date();
        int intCurrentMonth =mCurrentDate.getMonth();

        ImageView paymentcard = (ImageView) findViewById(R.id.imagePay);

        ImageView imageViewJAN = (ImageView) findViewById(R.id.imageViewJAN);
        imageViewJAN.setImageDrawable(getResources().getDrawable(R.drawable.jangray));
        enableDisableMonth(imageViewJAN ,intCurrentMonth,0 );

        ImageView imageViewFEB = (ImageView) findViewById(R.id.imageViewFEB);
        imageViewFEB.setImageDrawable(getResources().getDrawable(R.drawable.febgray));
        enableDisableMonth(imageViewFEB, intCurrentMonth, 1);

        ImageView imageViewMAR = (ImageView) findViewById(R.id.imageViewMAR);
        imageViewMAR.setImageDrawable(getResources().getDrawable(R.drawable.margray));
        enableDisableMonth(imageViewMAR, intCurrentMonth, 2);

        ImageView imageViewAPR = (ImageView) findViewById(R.id.imageViewAPR);
        imageViewAPR.setImageDrawable(getResources().getDrawable(R.drawable.aprgray));
        enableDisableMonth(imageViewAPR, intCurrentMonth, 3);

        ImageView imageViewMAY = (ImageView) findViewById(R.id.imageViewMAY);
        imageViewMAY.setImageDrawable(getResources().getDrawable(R.drawable.maygray));
        enableDisableMonth(imageViewMAY, intCurrentMonth, 4);

        ImageView imageViewJUN = (ImageView) findViewById(R.id.imageViewJUN);
        imageViewJUN.setImageDrawable(getResources().getDrawable(R.drawable.jungray));
        enableDisableMonth(imageViewJUN, intCurrentMonth, 5);

        ImageView imageViewJUL = (ImageView) findViewById(R.id.imageViewJUL);
        imageViewJUL.setImageDrawable(getResources().getDrawable(R.drawable.julgray));
        enableDisableMonth(imageViewJUL, intCurrentMonth, 6);

        ImageView imageViewAUG = (ImageView) findViewById(R.id.imageViewAUG);
        imageViewAUG.setImageDrawable(getResources().getDrawable(R.drawable.auggray));
        enableDisableMonth(imageViewAUG, intCurrentMonth, 7);

        ImageView imageViewSEP = (ImageView) findViewById(R.id.imageViewSEP);
        imageViewSEP.setImageDrawable(getResources().getDrawable(R.drawable.sepgray));
        enableDisableMonth(imageViewSEP, intCurrentMonth, 8);

        ImageView imageViewOCT = (ImageView) findViewById(R.id.imageViewOCT);
        imageViewOCT.setImageDrawable(getResources().getDrawable(R.drawable.octgray));
        enableDisableMonth(imageViewOCT, intCurrentMonth, 9);

        ImageView imageViewNOV = (ImageView) findViewById(R.id.imageViewNOV);
        imageViewNOV.setImageDrawable(getResources().getDrawable(R.drawable.novgray));
        enableDisableMonth(imageViewNOV, intCurrentMonth, 10);

        ImageView imageViewDEC = (ImageView) findViewById(R.id.imageViewDEC);
        imageViewDEC.setImageDrawable(getResources().getDrawable(R.drawable.decgray));
        enableDisableMonth(imageViewDEC, intCurrentMonth, 11);

        /*
        we need to call a webservice to obtain the last 12 month series
        here series means month name and year
        set these series to the textview
        * */
        tvCollectionDisptext= (TextView) findViewById(R.id.tvCollectionDisptext);
        texttvDisprpttext = (TextView) findViewById(R.id.tvDisprpttext);
        tvCalenderJAN= (TextView) findViewById(R.id.tvCalenderJAN);
        tvCalenderFEB= (TextView) findViewById(R.id.tvCalenderFEB);
        tvCalenderMAR= (TextView) findViewById(R.id.tvCalenderMAR);
        tvCalenderAPR= (TextView) findViewById(R.id.tvCalenderAPR);
        tvCalenderMAY= (TextView) findViewById(R.id.tvCalenderMAY);
        tvCalenderJUN= (TextView) findViewById(R.id.tvCalenderJUN);
        tvCalenderJUL= (TextView) findViewById(R.id.tvCalenderJUL);
        tvCalenderAUG= (TextView) findViewById(R.id.tvCalenderAUG);
        tvCalenderOCT= (TextView) findViewById(R.id.tvCalenderSEP);
        tvCalenderSEP= (TextView) findViewById(R.id.tvCalenderOCT);
        tvCalenderNOV= (TextView) findViewById(R.id.tvCalenderNOV);
        tvCalenderDEC= (TextView) findViewById(R.id.tvCalenderDEC);

        textviewjan= (TextView) findViewById(R.id.tvDispMaintjan);
        textviewfeb= (TextView) findViewById(R.id.tvDispMaintfeb);
        textviewmar= (TextView) findViewById(R.id.tvDispMaintmar);
        textviewapr= (TextView) findViewById(R.id.tvDispMaintapr);
        textviewmay= (TextView) findViewById(R.id.tvDispMaintmay);
        textviewjun= (TextView) findViewById(R.id.tvDispMaintjun);
        textviewjul= (TextView) findViewById(R.id.tvDispMaintjul);
        textviewaug= (TextView) findViewById(R.id.tvDispMaintaug);
        textviewsep= (TextView) findViewById(R.id.tvDispMaintsep);
        textviewoct= (TextView) findViewById(R.id.tvDispMaintoct);
        textviewnov= (TextView) findViewById(R.id.tvDispMaintnov);
        textviewdec= (TextView) findViewById(R.id.tvDispMaintdec);


        txtviewjantotal= (TextView) findViewById(R.id.txtviewjantotal);
        txtviewfebtotal= (TextView) findViewById(R.id.txtviewfebtotal);
        txtviewmartotal= (TextView) findViewById(R.id.txtviewmartotal);
        txtviewaprtotal= (TextView) findViewById(R.id.txtviewaprtotal);
        txtviewmaytotal= (TextView) findViewById(R.id.txtviewmaytotal);
        txtviewjuntotal= (TextView) findViewById(R.id.txtviewjuntotal);
        txtviewjultotal= (TextView) findViewById(R.id.txtviewjultotal);
        txtviewaugtotal= (TextView) findViewById(R.id.txtviewaugtotal);
        txtviewseptotal= (TextView) findViewById(R.id.txtviewseptotal);
        txtviewocttotal= (TextView) findViewById(R.id.txtviewocttotal);
        txtviewnovtotal= (TextView) findViewById(R.id.txtviewnovtotal);
        txtviewdectotal= (TextView) findViewById(R.id.txtviewdectotal);

        txtviewjantotalamount= (TextView) findViewById(R.id.txtviewjantotalamount);
        txtviewfebtotalamount= (TextView) findViewById(R.id.txtviewfebtotalamount);
        txtviewmartotalamount= (TextView) findViewById(R.id.txtviewmartotalamount);
        txtviewaprtotalamount= (TextView) findViewById(R.id.txtviewaprtotalamount);
        txtviewmaytotalamount= (TextView) findViewById(R.id.txtviewmaytotalamount);
        txtviewjuntotalamount= (TextView) findViewById(R.id.txtviewjuntotalamount);
        txtviewjultotalamount= (TextView) findViewById(R.id.txtviewjultotalamount);
        txtviewaugtotalamount= (TextView) findViewById(R.id.txtviewaugtotalamount);
        txtviewseptotalamount= (TextView) findViewById(R.id.txtviewseptotalamount);
        txtviewocttotalamount= (TextView) findViewById(R.id.txtviewocttotalamount);
        txtviewnovtotalamount= (TextView) findViewById(R.id.txtviewnovtotalamount);
        txtviewdectotalamount= (TextView) findViewById(R.id.txtviewdectotalamount);

//        setCustomFontface();

        myTextViewCompoundDrawablesJAN = textviewjan.getCompoundDrawables();
        myTextViewCompoundDrawablesFEB = textviewfeb.getCompoundDrawables();
        myTextViewCompoundDrawablesMAR = textviewmar.getCompoundDrawables();
        myTextViewCompoundDrawablesAPR = textviewapr.getCompoundDrawables();
        myTextViewCompoundDrawablesMAY = textviewmay.getCompoundDrawables();
        myTextViewCompoundDrawablesJUN = textviewjun.getCompoundDrawables();
        myTextViewCompoundDrawablesJUL= textviewjul.getCompoundDrawables();
        myTextViewCompoundDrawablesAUG = textviewaug.getCompoundDrawables();
        myTextViewCompoundDrawablesSEP = textviewsep.getCompoundDrawables();
        myTextViewCompoundDrawablesOCT = textviewoct.getCompoundDrawables();
        myTextViewCompoundDrawablesNOV = textviewnov.getCompoundDrawables();
        myTextViewCompoundDrawablesDEC = textviewdec.getCompoundDrawables();

        MAX_LEVEL = 10000;

        final FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_refresh_btn);
        floatingActionButton.setTranslationY(floatingActionButton.getHeight() + 20);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_refresh_btn);
                floatingActionButton.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
            }
        },5000);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

//                    final FloatingActionButton floatingActionButton = (FloatingActionButton)  v.findViewById(R.id.fab_refresh_btn);
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            final OvershootInterpolator interpolator = new OvershootInterpolator();
//                            ViewCompat.animate(floatingActionButton).rotation(360f).withLayer().setDuration(MAX_LEVEL).setInterpolator(interpolator).start();
//
//                        }
//                    },2000);

                    loadJSONManageRptPeriods();
                } catch (Exception Ex) {
                    Toast.makeText(MaintainanceMain.this, Ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        final TouchImageView touchimage = (TouchImageView) findViewById(R.id.ivrpt);
        touchimage.setOnTouchImageViewListener(new TouchImageView.OnTouchImageViewListener() {
            @Override
            public void onMove() {
                RectF rect = touchimage.getZoomedRect();
                float currentZoom = touchimage.getCurrentZoom();
                boolean  isZoomed = touchimage.isZoomed();
                //Log.e("sfsdfdsf", ""+currentZoom+","+isZoomed);
                //Do whater ever stuff u want
            }
        });


        paymentcard.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentcard();
            }
        });

            textviewjan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                    //----------------Round Moving clock wise animation code------------------------
//              for(Drawable drawable: myTextViewCompoundDrawablesJAN) {
//              if(drawable == null)
//                  continue;
//                  ObjectAnimator anim = ObjectAnimator.ofInt(drawable, "level", 0, MAX_LEVEL);
//                  //anim.setRepeatCount(Animation.INFINITE);
//                  anim.start();
//                  }

                StringTokenizer tokens = new StringTokenizer(textviewjan.getTag().toString() , ":");
                strRptMonth= tokens.nextToken();
                strRptYear= tokens.nextToken();

                myQuickDialogFragment=  MyQuickDialogFragment.newInstance(strRptMonth, strRptYear, MaintainanceMain.this,textviewjan);
                myQuickDialogFragment.setAnchorView(textviewjan);
                myQuickDialogFragment.setAligmentFlags(AlignmentFlag.ALIGN_ANCHOR_VIEW_LEFT | AlignmentFlag.ALIGN_ANCHOR_VIEW_BOTTOM);
                myQuickDialogFragment.show(getSupportFragmentManager(), null);

//                textviewjan.setCompoundDrawablesWithIntrinsicBounds(R.drawable.monthlyreport_book, 0, 0, 0);
//                myTextViewCompoundDrawablesJAN = textviewjan.getCompoundDrawables();
//
//                for (Drawable drawableJAN : myTextViewCompoundDrawablesJAN) {
//                if (drawableJAN == null)
//                    continue;
//                    if (drawableJAN.getAlpha()==0)
//                    {
//                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableJAN, PropertyValuesHolder.ofInt("alpha", 255));
//                        animator.setTarget(drawableJAN);
//                        animator.setDuration(2000);
//                        animator.start();
//                    }
//                    else
//                    {
//                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableJAN, PropertyValuesHolder.ofInt("alpha", 0));
//                        animator.setTarget(drawableJAN);
//                        animator.setDuration(2000);
//                        animator.start();
//                    }
//                }
            }
        });


        textviewfeb.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                try
                {
                    StringTokenizer tokens = new StringTokenizer(textviewfeb.getTag().toString() , ":");
                    strRptMonth= tokens.nextToken();
                    strRptYear= tokens.nextToken();

                    myQuickDialogFragment=  MyQuickDialogFragment.newInstance(strRptMonth, strRptYear, MaintainanceMain.this,textviewfeb);
                    myQuickDialogFragment.setAnchorView(textviewfeb);
                    myQuickDialogFragment.setAligmentFlags(AlignmentFlag.ALIGN_ANCHOR_VIEW_LEFT | AlignmentFlag.ALIGN_ANCHOR_VIEW_BOTTOM);
                    myQuickDialogFragment.show(getSupportFragmentManager(), null);

    //                textviewfeb.setCompoundDrawablesWithIntrinsicBounds( R.drawable.monthlyreport_book, 0,0, 0);
    //                myTextViewCompoundDrawablesFEB = textviewfeb.getCompoundDrawables();
    //
    //                for (Drawable drawableFEB : myTextViewCompoundDrawablesFEB) {
    //                    if (drawableFEB == null)
    //                        continue;
    //                    if (drawableFEB.getAlpha()==0)
    //                    {
    //                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableFEB, PropertyValuesHolder.ofInt("alpha", 255));
    //                        animator.setTarget(drawableFEB);
    //                        animator.setDuration(2000);
    //                        animator.start();
    //                    }
    //                    else
    //                    {
    //                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableFEB, PropertyValuesHolder.ofInt("alpha", 0));
    //                        animator.setTarget(drawableFEB);
    //                        animator.setDuration(2000);
    //                        animator.start();
    //                    }
    //                }
                }
                catch(Exception Ex)
                {
                    Toast.makeText(MaintainanceMain.this,
                            "we are unable to reach please check your network connection...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textviewmar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    StringTokenizer tokens = new StringTokenizer(textviewmar.getTag().toString(), ":");
                    strRptMonth = tokens.nextToken();
                    strRptYear = tokens.nextToken();

                    myQuickDialogFragment = MyQuickDialogFragment.newInstance(strRptMonth, strRptYear, MaintainanceMain.this, textviewmar);
                    myQuickDialogFragment.setAnchorView(textviewmar);
                    myQuickDialogFragment.setAligmentFlags(AlignmentFlag.ALIGN_ANCHOR_VIEW_LEFT | AlignmentFlag.ALIGN_ANCHOR_VIEW_BOTTOM);
                    myQuickDialogFragment.show(getSupportFragmentManager(), null);

//                textviewmar.setCompoundDrawablesWithIntrinsicBounds( R.drawable.monthlyreport_book, 0,0,0);
//                myTextViewCompoundDrawablesMAR = textviewmar.getCompoundDrawables();
//                for (Drawable drawableMAR : myTextViewCompoundDrawablesMAR) {
//                    if (drawableMAR == null)
//                        continue;
//                    if (drawableMAR.getAlpha()==0)
//                    {
//                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableMAR, PropertyValuesHolder.ofInt("alpha", 255));
//                        animator.setTarget(drawableMAR);
//                        animator.setDuration(2000);
//                        animator.start();
//                    }
//                    else
//                    {
//                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableMAR, PropertyValuesHolder.ofInt("alpha", 0));
//                        animator.setTarget(drawableMAR);
//                        animator.setDuration(2000);
//                        animator.start();
//                    }
//                }
                }
                catch(Exception Ex)
                {
                    Toast.makeText(MaintainanceMain.this,
                            "we are unable to reach please check your network connection...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textviewapr.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                StringTokenizer tokens = new StringTokenizer(textviewapr.getTag().toString() , ":");
                strRptMonth= tokens.nextToken();
                strRptYear= tokens.nextToken();

                myQuickDialogFragment=  MyQuickDialogFragment.newInstance(strRptMonth, strRptYear, MaintainanceMain.this,textviewapr);
                myQuickDialogFragment.setAnchorView(textviewapr);
                myQuickDialogFragment.setAligmentFlags(AlignmentFlag.ALIGN_ANCHOR_VIEW_LEFT | AlignmentFlag.ALIGN_ANCHOR_VIEW_BOTTOM);
                myQuickDialogFragment.show(getSupportFragmentManager(), null);


//                textviewapr.setCompoundDrawablesWithIntrinsicBounds( R.drawable.monthlyreport_book, 0,0, 0);
//                myTextViewCompoundDrawablesAPR = textviewapr.getCompoundDrawables();
//                for (Drawable drawableAPR : myTextViewCompoundDrawablesAPR ) {
//                    if (drawableAPR == null)
//                        continue;
//                    if (drawableAPR.getAlpha()==0)
//                    {
//                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableAPR, PropertyValuesHolder.ofInt("alpha", 255));
//                        animator.setTarget(drawableAPR);
//                        animator.setDuration(2000);
//                        animator.start();
//                    }
//                    else
//                    {
//                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableAPR, PropertyValuesHolder.ofInt("alpha", 0));
//                        animator.setTarget(drawableAPR);
//                        animator.setDuration(2000);
//                        animator.start();
//                    }
//                }
                }
                catch(Exception Ex)
                {
                    Toast.makeText(MaintainanceMain.this,
                            "we are unable to reach please check your network connection...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textviewmay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                StringTokenizer tokens = new StringTokenizer(textviewmay.getTag().toString() , ":");
                strRptMonth= tokens.nextToken();
                strRptYear= tokens.nextToken();

                myQuickDialogFragment=  MyQuickDialogFragment.newInstance(strRptMonth, strRptYear, MaintainanceMain.this,textviewmay);
                myQuickDialogFragment.setAnchorView(textviewmay);
                myQuickDialogFragment.setAligmentFlags(AlignmentFlag.ALIGN_ANCHOR_VIEW_LEFT | AlignmentFlag.ALIGN_ANCHOR_VIEW_BOTTOM);
                myQuickDialogFragment.show(getSupportFragmentManager(), null);

//                textviewmay.setCompoundDrawablesWithIntrinsicBounds( R.drawable.monthlyreport_book, 0,0, 0);
//                myTextViewCompoundDrawablesMAY = textviewmay.getCompoundDrawables();
//                for (Drawable drawableMAY : myTextViewCompoundDrawablesMAY) {
//                    if (drawableMAY == null)
//                        continue;
//                    if (drawableMAY.getAlpha()==0)
//                    {
//                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableMAY, PropertyValuesHolder.ofInt("alpha", 255));
//                        animator.setTarget(drawableMAY);
//                        animator.setDuration(2000);
//                        animator.start();
//                    }
//                    else
//                    {
//                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableMAY, PropertyValuesHolder.ofInt("alpha", 0));
//                        animator.setTarget(drawableMAY);
//                        animator.setDuration(2000);
//                        animator.start();
//                    }
//                }
                }
                catch(Exception Ex)
                {
                    Toast.makeText(MaintainanceMain.this,
                            "we are unable to reach please check your network connection...", Toast.LENGTH_SHORT).show();
                }

            }
        });
        textviewjun.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                StringTokenizer tokens = new StringTokenizer(textviewjun.getTag().toString() , ":");
                strRptMonth= tokens.nextToken();
                strRptYear= tokens.nextToken();

                myQuickDialogFragment=  MyQuickDialogFragment.newInstance(strRptMonth, strRptYear, MaintainanceMain.this,textviewjun);
                myQuickDialogFragment.setAnchorView(textviewjun);
                myQuickDialogFragment.setAligmentFlags(AlignmentFlag.ALIGN_ANCHOR_VIEW_LEFT | AlignmentFlag.ALIGN_ANCHOR_VIEW_BOTTOM);
                myQuickDialogFragment.show(getSupportFragmentManager(), null);


//                textviewjun.setCompoundDrawablesWithIntrinsicBounds( R.drawable.monthlyreport_book, 0,0, 0);
//                myTextViewCompoundDrawablesJUN = textviewjun.getCompoundDrawables();
//                for (Drawable drawableJUN : myTextViewCompoundDrawablesJUN) {
//                    if (drawableJUN == null)
//                        continue;
//                    if (drawableJUN.getAlpha()==0)
//                    {
//                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableJUN, PropertyValuesHolder.ofInt("alpha", 255));
//                        animator.setTarget(drawableJUN);
//                        animator.setDuration(2000);
//                        animator.start();
//                    }
//                    else
//                    {
//                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableJUN, PropertyValuesHolder.ofInt("alpha", 0));
//                        animator.setTarget(drawableJUN);
//                        animator.setDuration(2000);
//                        animator.start();
//                    }
//                }
                }
                catch(Exception Ex)
                {
                    Toast.makeText(MaintainanceMain.this,
                            "we are unable to reach please check your network connection...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textviewjul.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                StringTokenizer tokens = new StringTokenizer(textviewjul.getTag().toString() , ":");
                strRptMonth= tokens.nextToken();
                strRptYear= tokens.nextToken();

                myQuickDialogFragment=  MyQuickDialogFragment.newInstance(strRptMonth, strRptYear, MaintainanceMain.this,textviewjul);
                myQuickDialogFragment.setAnchorView(textviewjul);
                myQuickDialogFragment.setAligmentFlags(AlignmentFlag.ALIGN_ANCHOR_VIEW_LEFT | AlignmentFlag.ALIGN_ANCHOR_VIEW_BOTTOM);
                myQuickDialogFragment.show(getSupportFragmentManager(), null);


//                textviewjul.setCompoundDrawablesWithIntrinsicBounds( R.drawable.monthlyreport_book, 0,0, 0);
//                myTextViewCompoundDrawablesJUL = textviewjul.getCompoundDrawables();
//                for (Drawable drawableJUL : myTextViewCompoundDrawablesJUL) {
//                    if (drawableJUL == null)
//                        continue;
//                    if (drawableJUL.getAlpha()==0)
//                    {
//                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableJUL, PropertyValuesHolder.ofInt("alpha", 255));
//                        animator.setTarget(drawableJUL);
//                        animator.setDuration(2000);
//                        animator.start();
//                    }
//                    else
//                    {
//                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableJUL, PropertyValuesHolder.ofInt("alpha", 0));
//                        animator.setTarget(drawableJUL);
//                        animator.setDuration(2000);
//                        animator.start();
//                    }
//                }
                }
                catch(Exception Ex)
                {
                    Toast.makeText(MaintainanceMain.this,
                            "we are unable to reach please check your network connection...", Toast.LENGTH_SHORT).show();
                }

            }
        });
        textviewaug.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                StringTokenizer tokens = new StringTokenizer(textviewaug.getTag().toString() , ":");
                strRptMonth= tokens.nextToken();
                strRptYear= tokens.nextToken();

                myQuickDialogFragment=  MyQuickDialogFragment.newInstance(strRptMonth, strRptYear, MaintainanceMain.this,textviewaug);
                myQuickDialogFragment.setAnchorView(textviewaug);
                myQuickDialogFragment.setAligmentFlags(AlignmentFlag.ALIGN_ANCHOR_VIEW_LEFT | AlignmentFlag.ALIGN_ANCHOR_VIEW_BOTTOM);
                myQuickDialogFragment.show(getSupportFragmentManager(), null);


//                textviewaug.setCompoundDrawablesWithIntrinsicBounds( R.drawable.monthlyreport_book, 0,0, 0);
//                myTextViewCompoundDrawablesAUG = textviewaug.getCompoundDrawables();
//                for (Drawable drawableAUG : myTextViewCompoundDrawablesAUG) {
//                    if (drawableAUG == null)
//                        continue;
//                    if (drawableAUG.getAlpha()==0)
//                    {
//                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableAUG, PropertyValuesHolder.ofInt("alpha", 255));
//                        animator.setTarget(drawableAUG);
//                        animator.setDuration(2000);
//                        animator.start();
//                    }
//                    else
//                    {
//                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableAUG, PropertyValuesHolder.ofInt("alpha", 0));
//                        animator.setTarget(drawableAUG);
//                        animator.setDuration(2000);
//                        animator.start();
//                    }
//                }
                }
                catch(Exception Ex)
                {
                    Toast.makeText(MaintainanceMain.this,
                            "we are unable to reach please check your network connection...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textviewsep.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                StringTokenizer tokens = new StringTokenizer(textviewsep.getTag().toString() , ":");
                strRptMonth= tokens.nextToken();
                strRptYear= tokens.nextToken();

                myQuickDialogFragment=  MyQuickDialogFragment.newInstance(strRptMonth, strRptYear, MaintainanceMain.this,textviewsep);
                myQuickDialogFragment.setAnchorView(textviewsep);
                myQuickDialogFragment.setAligmentFlags(AlignmentFlag.ALIGN_ANCHOR_VIEW_LEFT | AlignmentFlag.ALIGN_ANCHOR_VIEW_BOTTOM);
                myQuickDialogFragment.show(getSupportFragmentManager(), null);

//                textviewsep.setCompoundDrawablesWithIntrinsicBounds( R.drawable.monthlyreport_book, 0,0, 0);
//                myTextViewCompoundDrawablesSEP = textviewsep.getCompoundDrawables();
//                for (Drawable drawableSEP : myTextViewCompoundDrawablesSEP) {
//                    if (drawableSEP == null)
//                        continue;
//                    if (drawableSEP.getAlpha()==0)
//                    {
//                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableSEP, PropertyValuesHolder.ofInt("alpha", 255));
//                        animator.setTarget(drawableSEP);
//                        animator.setDuration(2000);
//                        animator.start();
//                    }
//                    else
//                    {
//                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableSEP, PropertyValuesHolder.ofInt("alpha", 0));
//                        animator.setTarget(drawableSEP);
//                        animator.setDuration(2000);
//                        animator.start();
//                    }
//                }
                }
                catch(Exception Ex)
                {
                    Toast.makeText(MaintainanceMain.this,
                            "we are unable to reach please check your network connection...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textviewoct.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                StringTokenizer tokens = new StringTokenizer(textviewoct.getTag().toString() , ":");
                strRptMonth= tokens.nextToken();
                strRptYear= tokens.nextToken();

                myQuickDialogFragment=  MyQuickDialogFragment.newInstance(strRptMonth, strRptYear, MaintainanceMain.this,textviewoct);
                myQuickDialogFragment.setAnchorView(textviewoct);
                myQuickDialogFragment.setAligmentFlags(AlignmentFlag.ALIGN_ANCHOR_VIEW_LEFT | AlignmentFlag.ALIGN_ANCHOR_VIEW_BOTTOM);
                myQuickDialogFragment.show(getSupportFragmentManager(), null);

//                textviewoct.setCompoundDrawablesWithIntrinsicBounds( R.drawable.monthlyreport_book, 0,0, 0);
//                myTextViewCompoundDrawablesOCT = textviewoct.getCompoundDrawables();
//                for (Drawable drawableOCT : myTextViewCompoundDrawablesOCT) {
//                    if (drawableOCT == null)
//                        continue;
//                    if (drawableOCT.getAlpha()==0)
//                    {
//                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableOCT, PropertyValuesHolder.ofInt("alpha", 255));
//                        animator.setTarget(drawableOCT);
//                        animator.setDuration(2000);
//                        animator.start();
//                    }
//                    else
//                    {
//                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableOCT, PropertyValuesHolder.ofInt("alpha", 0));
//                        animator.setTarget(drawableOCT);
//                        animator.setDuration(2000);
//                        animator.start();
//                    }
//                }
                }
                catch(Exception Ex)
                {
                    Toast.makeText(MaintainanceMain.this,
                            "we are unable to reach please check your network connection...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textviewnov.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                StringTokenizer tokens = new StringTokenizer(textviewnov.getTag().toString() , ":");
                strRptMonth= tokens.nextToken();
                strRptYear= tokens.nextToken();

                myQuickDialogFragment=  MyQuickDialogFragment.newInstance(strRptMonth, strRptYear, MaintainanceMain.this,textviewnov);
                myQuickDialogFragment.setAnchorView(textviewnov);
                myQuickDialogFragment.setAligmentFlags(AlignmentFlag.ALIGN_ANCHOR_VIEW_LEFT | AlignmentFlag.ALIGN_ANCHOR_VIEW_BOTTOM);
                myQuickDialogFragment.show(getSupportFragmentManager(), null);

//                textviewnov.setCompoundDrawablesWithIntrinsicBounds( R.drawable.monthlyreport_book, 0,0, 0);
//                myTextViewCompoundDrawablesNOV = textviewnov.getCompoundDrawables();
//                for (Drawable drawableNOV : myTextViewCompoundDrawablesNOV) {
//                    if (drawableNOV == null)
//                        continue;
//                    if (drawableNOV.getAlpha()==0)
//                    {
//                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableNOV, PropertyValuesHolder.ofInt("alpha", 255));
//                        animator.setTarget(drawableNOV);
//                        animator.setDuration(2000);
//                        animator.start();
//                    }
//                    else
//                    {
//                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableNOV, PropertyValuesHolder.ofInt("alpha", 0));
//                        animator.setTarget(drawableNOV);
//                        animator.setDuration(2000);
//                        animator.start();
//                    }
//                }
                }
                catch(Exception Ex)
                {
                    Toast.makeText(MaintainanceMain.this,
                            "we are unable to reach please check your network connection...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textviewdec.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                StringTokenizer tokens = new StringTokenizer(textviewdec.getTag().toString() , ":");
                strRptMonth= tokens.nextToken();
                strRptYear= tokens.nextToken();

                myQuickDialogFragment=  MyQuickDialogFragment.newInstance(strRptMonth, strRptYear, MaintainanceMain.this,textviewdec);
                myQuickDialogFragment.setAnchorView(textviewdec);
                myQuickDialogFragment.setAligmentFlags(AlignmentFlag.ALIGN_ANCHOR_VIEW_LEFT | AlignmentFlag.ALIGN_ANCHOR_VIEW_BOTTOM);
                myQuickDialogFragment.show(getSupportFragmentManager(), null);


//                textviewdec.setCompoundDrawablesWithIntrinsicBounds( R.drawable.monthlyreport_book, 0,0, 0);
//                myTextViewCompoundDrawablesDEC   = textviewdec.getCompoundDrawables();
//                for (Drawable drawableDEC : myTextViewCompoundDrawablesDEC) {
//                    if (drawableDEC == null)
//                        continue;
//                    if (drawableDEC.getAlpha()==0)
//                    {
//                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableDEC, PropertyValuesHolder.ofInt("alpha", 255));
//                        animator.setTarget(drawableDEC);
//                        animator.setDuration(2000);
//                        animator.start();
//                    }
//                    else
//                    {
//                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableDEC, PropertyValuesHolder.ofInt("alpha", 0));
//                        animator.setTarget(drawableDEC);
//                        animator.setDuration(2000);
//                        animator.start();
//                    }
//                }
                }
                catch(Exception Ex)
                {
                    Toast.makeText(MaintainanceMain.this,
                            "we are unable to reach please check your network connection...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void setCustomFontface(String fontName)
    {
        try
        {
            if (!fontName.isEmpty()) {
                // Font path
                //String fontPath = "fonts/gtw.ttf";

                String fontPath = "fonts/" + fontName;
                // Loading Font Face
                Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
                // Applying font
                tvCollectionDisptext.setTypeface(tf);
                texttvDisprpttext.setTypeface(tf);

                tvCalenderJAN.setTypeface(tf);
                tvCalenderFEB.setTypeface(tf);
                tvCalenderMAR.setTypeface(tf);
                tvCalenderAPR.setTypeface(tf);
                tvCalenderMAY.setTypeface(tf);
                tvCalenderJUN.setTypeface(tf);
                tvCalenderJUL.setTypeface(tf);
                tvCalenderAUG.setTypeface(tf);
                tvCalenderOCT.setTypeface(tf);
                tvCalenderSEP.setTypeface(tf);
                tvCalenderNOV.setTypeface(tf);
                tvCalenderDEC.setTypeface(tf);

                textviewjan.setTypeface(tf);
                textviewfeb.setTypeface(tf);
                textviewmar.setTypeface(tf);
                textviewapr.setTypeface(tf);
                textviewmay.setTypeface(tf);
                textviewjun.setTypeface(tf);
                textviewjul.setTypeface(tf);
                textviewaug.setTypeface(tf);
                textviewsep.setTypeface(tf);
                textviewoct.setTypeface(tf);
                textviewnov.setTypeface(tf);
                textviewdec.setTypeface(tf);

                txtviewjantotal.setTypeface(tf);
                txtviewfebtotal.setTypeface(tf);
                txtviewmartotal.setTypeface(tf);
                txtviewaprtotal.setTypeface(tf);
                txtviewmaytotal.setTypeface(tf);
                txtviewjuntotal.setTypeface(tf);
                txtviewjultotal.setTypeface(tf);
                txtviewaugtotal.setTypeface(tf);
                txtviewseptotal.setTypeface(tf);
                txtviewocttotal.setTypeface(tf);
                txtviewnovtotal.setTypeface(tf);
                txtviewdectotal.setTypeface(tf);

                txtviewjantotalamount.setTypeface(tf);
                txtviewfebtotalamount.setTypeface(tf);
                txtviewmartotalamount.setTypeface(tf);
                txtviewaprtotalamount.setTypeface(tf);
                txtviewmaytotalamount.setTypeface(tf);
                txtviewjuntotalamount.setTypeface(tf);
                txtviewjultotalamount.setTypeface(tf);
                txtviewaugtotalamount.setTypeface(tf);
                txtviewseptotalamount.setTypeface(tf);
                txtviewocttotalamount.setTypeface(tf);
                txtviewnovtotalamount.setTypeface(tf);
                txtviewdectotalamount.setTypeface(tf);
            }
        }
        catch(Exception Ex)
        {
            Toast.makeText(this, Ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // LoadJSON with Report Periods
    private void loadJSONManageRptPeriods() {
        // JSON Node names
        final String ITEM_MONTH="Month";
        final String ITEM_YEAR="Year";
        final String ITEM_MONTHNAME="MonthName";
        final String ITEM_MONTHNAMEYEAR="MonthNameYear";
        final String ITEM_TOTALAMOUNT="Amount";
        final String ITEM_CUSTOMFONTNAME="customfontName";


        PD = new ProgressDialog(this);
        PD.setMessage("getting information " + "\n" + "please wait.....");
        PD.setCancelable(false);

        try {
            PD.show();
            StringRequest postRequest = new StringRequest(Request.Method.POST, Const.URL_WS_LOADREPORT,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                // prepare the list of all records--------
                                ArrayList<HashMap<String, String>> fillMaps=new ArrayList<HashMap<String, String>>();
                                ArrayList<modelMaintainanceLoadReportPeriods> objModelMaintainancerptPeriods =new ArrayList<modelMaintainanceLoadReportPeriods>();
                                //----------------------------------------

                                JSONObject jsonObject = new JSONObject(response);
                                int success =jsonObject.getInt("success");
                                if (success == 1) {
                                    JSONArray ja = jsonObject.getJSONArray("loaddates");

                                    for (int i = 0; i < ja.length(); i++) {
                                        model_rptPeriods = new modelMaintainanceLoadReportPeriods();

                                        JSONObject jobj = ja.getJSONObject(i);
                                        HashMap<String, String> map = new HashMap<String, String>();

                                        map.put(ITEM_MONTH,jobj.getString(ITEM_MONTH));
                                        map.put(ITEM_YEAR,jobj.getString(ITEM_YEAR));
                                        map.put(ITEM_MONTHNAME,jobj.isNull(ITEM_MONTHNAME) ? "" : jobj.getString(ITEM_MONTHNAME));
                                        map.put(ITEM_MONTHNAMEYEAR, jobj.isNull(ITEM_MONTHNAMEYEAR) ? "" : jobj.getString(ITEM_MONTHNAMEYEAR));
                                        map.put(ITEM_TOTALAMOUNT, jobj.isNull(ITEM_TOTALAMOUNT) ? "" : jobj.getString(ITEM_TOTALAMOUNT));
                                        map.put(ITEM_CUSTOMFONTNAME, jobj.isNull(ITEM_CUSTOMFONTNAME) ? "" : jobj.getString(ITEM_CUSTOMFONTNAME));

                                        model_rptPeriods.setMonth(Integer.parseInt(jobj.getString(ITEM_MONTH)));
                                        model_rptPeriods.setYear(Integer.parseInt(jobj.getString(ITEM_YEAR)));
                                        model_rptPeriods.setMonthName(jobj.isNull(ITEM_MONTHNAME) ? "" : jobj.getString(ITEM_MONTHNAME));
                                        model_rptPeriods.setMonthNameYear(jobj.isNull(ITEM_MONTHNAMEYEAR) ? "" : jobj.getString(ITEM_MONTHNAMEYEAR));
                                        model_rptPeriods.setTotalAmount(jobj.isNull(ITEM_TOTALAMOUNT) ? 0 : Integer.parseInt(jobj.getString(ITEM_TOTALAMOUNT)));
                                        model_rptPeriods.setcustomfontName(jobj.isNull(ITEM_CUSTOMFONTNAME) ? "" : jobj.getString(ITEM_CUSTOMFONTNAME));

                                        objModelMaintainancerptPeriods.add(model_rptPeriods);
                                        fillMaps.add(map);
                                    } // for loop ends
                                    PD.dismiss();
                                    reDrawReportControl(objModelMaintainancerptPeriods, fillMaps);
                                } // if ends
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    PD.dismiss();
                    Toast.makeText(MaintainanceMain.this,
                            "failed to retrive infomations please check your network connection...", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("mth",hashmapMonthYear.get("MONTH"));
//                    params.put("yr",hashmapMonthYear.get("YEAR"));
                    return params;
                }
            };

            // Adding request to request queue
            ApartmentApplicationController.getInstance().addToReqQueue(postRequest);
        }
        catch (Exception Ex) {
            Toast.makeText(this, Ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    private void reDrawReportControl(ArrayList<modelMaintainanceLoadReportPeriods> objModelMaintainancerptPeriods,ArrayList<HashMap<String, String>> fillMaps)
    {
        int inttotalperiod;
        try {
            inttotalperiod=0;

            Enumeration e = Collections.enumeration(objModelMaintainancerptPeriods);
            while(e.hasMoreElements())
            {
                modelMaintainanceLoadReportPeriods objperiod = (modelMaintainanceLoadReportPeriods) e.nextElement();

                if (inttotalperiod==11) //design first TextView control
                {
                    textviewjan.setTag(objperiod.getMonth() +":" + objperiod.getYear());
                    textviewjan.setText(objperiod.getMonthNameYear());
                    txtviewjantotal.setText(objperiod.getMonthNameYear()  );
                    txtviewjantotalamount.setText(String.valueOf(objperiod.getTotalAmount()));
                    setCustomFontface(objperiod.getcustomfontName());
                }

                if (inttotalperiod==10) //design first TextView control
                {
                    textviewfeb.setTag(objperiod.getMonth() +":" + objperiod.getYear());
                    textviewfeb.setText(objperiod.getMonthNameYear());
                    txtviewfebtotal.setText(objperiod.getMonthNameYear() );
                    txtviewfebtotalamount.setText(String.valueOf(objperiod.getTotalAmount()));
                }
                if (inttotalperiod==9) //design first TextView control
                {
                    textviewmar.setTag(objperiod.getMonth() +":" + objperiod.getYear());
                    textviewmar.setText(objperiod.getMonthNameYear());
                    txtviewmartotal.setText(objperiod.getMonthNameYear());
                    txtviewmartotalamount.setText(String.valueOf(objperiod.getTotalAmount()));
                }
                if (inttotalperiod==8) //design first TextView control
                {
                    textviewapr.setTag(objperiod.getMonth() +":" + objperiod.getYear());
                    textviewapr.setText(objperiod.getMonthNameYear());
                    txtviewaprtotal.setText(objperiod.getMonthNameYear() );
                    txtviewaprtotalamount.setText(String.valueOf(objperiod.getTotalAmount()));
                }
                if (inttotalperiod==7) //design first TextView control
                {
                    textviewmay.setTag(objperiod.getMonth() +":" + objperiod.getYear());
                    textviewmay.setText(objperiod.getMonthNameYear());
                    txtviewmaytotal.setText(objperiod.getMonthNameYear() );
                    txtviewmaytotalamount.setText(String.valueOf(objperiod.getTotalAmount()));
                }
                if (inttotalperiod==6) //design first TextView control
                {
                    textviewjun.setTag(objperiod.getMonth() +":" + objperiod.getYear());
                    textviewjun.setText(objperiod.getMonthNameYear());
                    txtviewjuntotal.setText(objperiod.getMonthNameYear()  );
                    txtviewjuntotalamount.setText(String.valueOf(objperiod.getTotalAmount()));
                }
                if (inttotalperiod==5) //design first TextView control
                {
                    textviewjul.setTag(objperiod.getMonth() + ":" + objperiod.getYear());
                    textviewjul.setText(objperiod.getMonthNameYear());
                    txtviewjultotal.setText(objperiod.getMonthNameYear() );
                    txtviewjultotalamount.setText(String.valueOf(objperiod.getTotalAmount()));
                }
                if (inttotalperiod==4) //design first TextView control
                {
                    textviewaug.setTag(objperiod.getMonth() +":" + objperiod.getYear());
                    textviewaug.setText(objperiod.getMonthNameYear());
                    txtviewaugtotal.setText(objperiod.getMonthNameYear()  );
                    txtviewaugtotalamount.setText(String.valueOf(objperiod.getTotalAmount()));
                }
                if (inttotalperiod==3) //design first TextView control
                {
                    textviewsep.setTag(objperiod.getMonth() + ":" + objperiod.getYear());
                    textviewsep.setText(objperiod.getMonthNameYear());
                    txtviewseptotal.setText(objperiod.getMonthNameYear() );
                    txtviewseptotalamount.setText(String.valueOf(objperiod.getTotalAmount()));

                }
                if (inttotalperiod==2) //design first TextView control
                {
                    textviewoct.setTag(objperiod.getMonth() + ":" + objperiod.getYear());
                    textviewoct.setText(objperiod.getMonthNameYear());
                    txtviewocttotal.setText(objperiod.getMonthNameYear() );
                    txtviewocttotalamount.setText(String.valueOf(objperiod.getTotalAmount()));
                }
                if (inttotalperiod==1) //design first TextView control
                {
                    textviewnov.setTag(objperiod.getMonth() +":" + objperiod.getYear());
                    textviewnov.setText(objperiod.getMonthNameYear());
                    txtviewnovtotal.setText(objperiod.getMonthNameYear() );
                    txtviewnovtotalamount.setText(String.valueOf(objperiod.getTotalAmount()));
                }
                if (inttotalperiod==0) //design first TextView control
                {
                    textviewdec.setTag(objperiod.getMonth() + ":" + objperiod.getYear());
                    textviewdec.setText(objperiod.getMonthNameYear());
                    txtviewdectotal.setText(objperiod.getMonthNameYear() );
                    txtviewdectotalamount.setText(String.valueOf(objperiod.getTotalAmount()));
                }

                inttotalperiod++;
//               System.out.println(e.nextElement());
            }
        }
        catch(Exception Ex)
        {
            Toast.makeText(this, Ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            loadJSONManageRptPeriods();
        }
        catch  (Exception Ex)
        {
            Toast.makeText(this, Ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    private void enableDisableMonthReportIcon(boolean isEnable) //Not In Use
    {
        try {
            if (!isEnable)
            {
                for(Drawable drawableJAN: myTextViewCompoundDrawablesJAN) {
                    if(drawableJAN == null)
                        continue;
                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableJAN, PropertyValuesHolder.ofInt("alpha", 0));
                        animator.setTarget(drawableJAN);
                        animator.setDuration(10000);
                        animator.start();
                }
                for(Drawable drawableFEB: myTextViewCompoundDrawablesFEB) {
                    if(drawableFEB == null)
                        continue;
                    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableFEB, PropertyValuesHolder.ofInt("alpha", 0));
                    animator.setTarget(drawableFEB);
                    animator.setDuration(10000);
                    animator.start();
                }
                for(Drawable drawableMAR: myTextViewCompoundDrawablesMAR) {
                    if(drawableMAR == null)
                        continue;
                    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableMAR, PropertyValuesHolder.ofInt("alpha", 0));
                    animator.setTarget(drawableMAR);
                    animator.setDuration(10000);
                    animator.start();
                }
                for(Drawable drawableAPR: myTextViewCompoundDrawablesAPR) {
                    if(drawableAPR == null)
                        continue;
                    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableAPR, PropertyValuesHolder.ofInt("alpha", 0));
                    animator.setTarget(drawableAPR);
                    animator.setDuration(10000);
                    animator.start();
                }
                for(Drawable drawableMAY: myTextViewCompoundDrawablesMAY) {
                    if(drawableMAY == null)
                        continue;
                    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableMAY, PropertyValuesHolder.ofInt("alpha", 0));
                    animator.setTarget(drawableMAY);
                    animator.setDuration(10000);
                    animator.start();
                }
                for(Drawable drawableJUN: myTextViewCompoundDrawablesJUN) {
                    if(drawableJUN == null)
                        continue;
                    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableJUN, PropertyValuesHolder.ofInt("alpha", 0));
                    animator.setTarget(drawableJUN);
                    animator.setDuration(10000);
                    animator.start();
                }
                for(Drawable drawableJUL: myTextViewCompoundDrawablesJUL) {
                    if(drawableJUL == null)
                        continue;
                    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableJUL, PropertyValuesHolder.ofInt("alpha", 0));
                    animator.setTarget(drawableJUL);
                    animator.setDuration(10000);
                    animator.start();
                }
                for(Drawable drawableAUG: myTextViewCompoundDrawablesAUG) {
                    if(drawableAUG == null)
                        continue;
                    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableAUG, PropertyValuesHolder.ofInt("alpha", 0));
                    animator.setTarget(drawableAUG);
                    animator.setDuration(10000);
                    animator.start();
                }
                for(Drawable drawableSEP: myTextViewCompoundDrawablesSEP) {
                    if(drawableSEP == null)
                        continue;
                    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableSEP, PropertyValuesHolder.ofInt("alpha", 0));
                    animator.setTarget(drawableSEP);
                    animator.setDuration(10000);
                    animator.start();
                }
                for(Drawable drawableOCT: myTextViewCompoundDrawablesOCT) {
                    if(drawableOCT == null)
                        continue;
                    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableOCT, PropertyValuesHolder.ofInt("alpha", 0));
                    animator.setTarget(drawableOCT);
                    animator.setDuration(10000);
                    animator.start();
                }
                for(Drawable drawableNOV: myTextViewCompoundDrawablesNOV) {
                    if(drawableNOV == null)
                        continue;
                    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableNOV, PropertyValuesHolder.ofInt("alpha", 0));
                    animator.setTarget(drawableNOV);
                    animator.setDuration(10000);
                    animator.start();
                }
                for(Drawable drawableDEC: myTextViewCompoundDrawablesDEC) {
                    if(drawableDEC == null)
                        continue;
                    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableDEC, PropertyValuesHolder.ofInt("alpha", 0));
                    animator.setTarget(drawableDEC);
                    animator.setDuration(10000);
                    animator.start();
                }
            }
            else
            {
                for(Drawable drawable: myTextViewCompoundDrawablesJAN) {
                    if(drawable == null)
                        continue;
                    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawable, PropertyValuesHolder.ofInt("alpha", 255));
                    animator.setTarget(drawable);
                    animator.setDuration(10000);
                    animator.start();
                }
                for(Drawable drawable: myTextViewCompoundDrawablesFEB) {
                    if(drawable == null)
                        continue;
                    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawable, PropertyValuesHolder.ofInt("alpha", 255));
                    animator.setTarget(drawable);
                    animator.setDuration(10000);
                    animator.start();
                }
                for(Drawable drawable: myTextViewCompoundDrawablesMAR) {
                    if(drawable == null)
                        continue;
                    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawable, PropertyValuesHolder.ofInt("alpha", 255));
                    animator.setTarget(drawable);
                    animator.setDuration(10000);
                    animator.start();
                }
                for(Drawable drawable: myTextViewCompoundDrawablesAPR) {
                    if(drawable == null)
                        continue;
                    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawable, PropertyValuesHolder.ofInt("alpha", 255));
                    animator.setTarget(drawable);
                    animator.setDuration(10000);
                    animator.start();
                }
                for(Drawable drawable: myTextViewCompoundDrawablesMAY) {
                    if(drawable == null)
                        continue;
                    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawable, PropertyValuesHolder.ofInt("alpha", 255));
                    animator.setTarget(drawable);
                    animator.setDuration(10000);
                    animator.start();
                }
                for(Drawable drawable: myTextViewCompoundDrawablesJUN) {
                    if(drawable == null)
                        continue;
                    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawable, PropertyValuesHolder.ofInt("alpha", 255));
                    animator.setTarget(drawable);
                    animator.setDuration(10000);
                    animator.start();
                }
                for(Drawable drawable: myTextViewCompoundDrawablesJUL) {
                    if(drawable == null)
                        continue;
                    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawable, PropertyValuesHolder.ofInt("alpha", 255));
                    animator.setTarget(drawable);
                    animator.setDuration(10000);
                    animator.start();
                }
                for(Drawable drawable: myTextViewCompoundDrawablesAUG) {
                    if(drawable == null)
                        continue;
                    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawable, PropertyValuesHolder.ofInt("alpha", 255));
                    animator.setTarget(drawable);
                    animator.setDuration(10000);
                    animator.start();
                }
                for(Drawable drawable: myTextViewCompoundDrawablesSEP) {
                    if(drawable == null)
                        continue;
                    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawable, PropertyValuesHolder.ofInt("alpha", 255));
                    animator.setTarget(drawable);
                    animator.setDuration(10000);
                    animator.start();
                }
                for(Drawable drawable: myTextViewCompoundDrawablesOCT) {
                    if(drawable == null)
                        continue;
                    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawable, PropertyValuesHolder.ofInt("alpha", 255));
                    animator.setTarget(drawable);
                    animator.setDuration(10000);
                    animator.start();
                }
                for(Drawable drawable: myTextViewCompoundDrawablesNOV) {
                    if(drawable == null)
                        continue;
                    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawable, PropertyValuesHolder.ofInt("alpha", 255));
                    animator.setTarget(drawable);
                    animator.setDuration(10000);
                    animator.start();
                }
                for(Drawable drawable: myTextViewCompoundDrawablesDEC) {
                    if(drawable == null)
                        continue;
                    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawable, PropertyValuesHolder.ofInt("alpha", 255));
                    animator.setTarget(drawable);
                    animator.setDuration(10000);
                    animator.start();
                }
            }
        }
        catch (Exception Ex)
        {
            Log.v("TAG_ENABLEDISABLEICON", "Enable Disable Icon: " + Ex.getMessage());
        }
    }

    private void enableDisableMonth(ImageView imgView, Integer intMth,int mthCode)
    {
        try
        {
            switch (mthCode) {
                case 0:
                    if (intMth== mthCode) {
                        imgView.setImageDrawable(getResources().getDrawable(R.drawable.jan));
                        imgView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                funpayMaintainance(true, "JAN");
                            }
                        });
                    }
                    break;
                case 1:
                    if (intMth== mthCode){
                    imgView.setImageDrawable(getResources().getDrawable(R.drawable.feb));
                    imgView.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            funpayMaintainance(true,"FEB");
                        }
                    });
                    }
                    break;
                case 2:
                    if (intMth== mthCode) {
                        imgView.setImageDrawable(getResources().getDrawable(R.drawable.mar));
                        imgView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                funpayMaintainance(true, "MAR");
                            }
                        });
                    }
                    break;
                case 3:
                    if (intMth== mthCode) {
                        imgView.setImageDrawable(getResources().getDrawable(R.drawable.apr));
                        imgView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                funpayMaintainance(true, "APR");
                            }
                        });
                    }
                    break;
                case 4:
                    if (intMth== mthCode) {
                        imgView.setImageDrawable(getResources().getDrawable(R.drawable.may));
                        imgView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                funpayMaintainance(true, "MAY");
                            }
                        });
                    }
                    break;
                case 5:
                    if (intMth== mthCode) {
                        imgView.setImageDrawable(getResources().getDrawable(R.drawable.jun));
                        imgView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                funpayMaintainance(true, "JUN");
                            }
                        });
                        break;
                    }
                case 6:
                    if (intMth== mthCode) {
                        imgView.setImageDrawable(getResources().getDrawable(R.drawable.jul));
                        imgView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                funpayMaintainance(true, "JUL");
                            }
                        });
                    }
                    break;
                case 7:
                    if (intMth== mthCode) {
                        imgView.setImageDrawable(getResources().getDrawable(R.drawable.aug));
                        imgView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                funpayMaintainance(true, "AUG");
                            }
                        });
                    }
                    break;
                case 8:
                    if (intMth== mthCode) {
                        imgView.setImageDrawable(getResources().getDrawable(R.drawable.sep));
                        imgView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                funpayMaintainance(true, "SEP");
                            }
                        });
                    }
                    break;
                case 9:
                    if (intMth== mthCode) {
                        imgView.setImageDrawable(getResources().getDrawable(R.drawable.oct));
                        imgView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                funpayMaintainance(true, "OCT");
                            }
                        });
                    }
                    break;
                case 10:
                    if (intMth== mthCode) {
                        imgView.setImageDrawable(getResources().getDrawable(R.drawable.nov));
                        imgView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                funpayMaintainance(true, "NOV");
                            }
                        });
                    }
                    break;
                case 11:
                    if (intMth== mthCode) {
                        imgView.setImageDrawable(getResources().getDrawable(R.drawable.dec));
                        imgView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                funpayMaintainance(true, "DEC");
                            }
                        });
                    }
                    break;
            }
        }
        catch(Exception Ex)
        {

        }
    }

    private void paymentcard() {
        // Create a SimpleDateTimePicker and Show it
        SimpleDateTimePicker simpleDateTimePicker = SimpleDateTimePicker.make(
                "Select  Date & Time",
                new Date(),
                this,
                getSupportFragmentManager()
        );
        // Show It
        simpleDateTimePicker.show(false,"");
    }

    private void funpayMaintainance(boolean isCurentDateRequiredOnly,String mnth) {
        // Or we can chain it to simplify
        if (mnth!="")
        {
            SimpleDateTimePicker.make(
                    "Select  Date & Time",
                    new Date(),
                    this,
                    getSupportFragmentManager()
            ).show(isCurentDateRequiredOnly,mnth);
        }
        else {
            SimpleDateTimePicker.make(
                    "Select  Date & Time",
                    new Date(),
                    this,
                    getSupportFragmentManager()
            ).show(isCurentDateRequiredOnly,"");
        }
    }

    @Override
    public void DateTimeSet(Date date) {
        // This is the DateTime class we created earlier to handle the conversion
        // of Date to String Format of Date String Format to Date object
        DateTime mDateTime = new DateTime(date);
        // Show in the LOGCAT the selected Date and Time
        Log.v("TEST_TAG", "Date and Time selected: " + mDateTime.getDateString());

        showMaintainanceEntryDialog(mDateTime.getMonthOfYear(), mDateTime.getYear());
    }

    public void showMaintainanceEntryDialog(int intMonth, int intYear) {
        try {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            dialogView = inflater.inflate(R.layout.maintainance_entry_dialog, null);
            dialogBuilder.setView(dialogView);

            mYear =String.valueOf(intYear);

//            // load the origial BitMap (500 x 500 px)
//            Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(),
//                    R.drawable.jan);
//
//            int width = bitmapOrg.getWidth();
//            int height = bitmapOrg.getHeight();
//            int newWidth = 200;
//            int newHeight = 200;
//
//            // calculate the scale - in this case = 0.4f
//            float scaleWidth = ((float) newWidth) / width;
//            float scaleHeight = ((float) newHeight) / height;
//
//            // createa matrix for the manipulation
//            Matrix matrix = new Matrix();
//            // resize the bit map
//            matrix.postScale(scaleWidth, scaleHeight);
//            // rotate the Bitmap
//            //matrix.postRotate(45);
//
//            // recreate the new Bitmap
//            Bitmap resizedBitmap = Bitmap.createBitmap(bitmapOrg, 0, 0,
//                    width, height, matrix, true);
//
//            // make a Drawable from Bitmap to allow to set the BitMap
//            // to the ImageView, ImageButton or what ever
//            BitmapDrawable bmd = new BitmapDrawable(resizedBitmap);

            switch (intMonth) {
                case 0:
                    dialogBuilder.setIcon(R.mipmap.ic_jan);
                    mMonth= String.valueOf("1");
                    mMonthCode= getMonthCode(mMonth);
                    break;
                case 1:
                    dialogBuilder.setIcon(R.mipmap.ic_feb);
                    mMonth= String.valueOf("2");
                    mMonthCode= getMonthCode(mMonth);
                    break;
                case 2:
                    dialogBuilder.setIcon(R.mipmap.ic_mar);
                    mMonth= String.valueOf("3");
                    mMonthCode= getMonthCode(mMonth);
                    break;
                case 3:
                    dialogBuilder.setIcon(R.mipmap.ic_apr);
                    mMonth= String.valueOf("4");
                    mMonthCode= getMonthCode(mMonth);
                    break;
                case 4:
                    dialogBuilder.setIcon(R.mipmap.ic_may);
                    mMonth= String.valueOf("5");
                    mMonthCode= getMonthCode(mMonth);
                    break;
                case 5:
                    dialogBuilder.setIcon(R.mipmap.ic_jun);
                    mMonth= String.valueOf("6");
                    mMonthCode= getMonthCode(mMonth);
                    break;
                case 6:
                    dialogBuilder.setIcon(R.mipmap.ic_jul);
                    mMonth= String.valueOf("7");
                    mMonthCode= getMonthCode(mMonth);
                    break;
                case 7:
                    dialogBuilder.setIcon(R.mipmap.ic_aug);
                    mMonth= String.valueOf("8");
                    mMonthCode= getMonthCode(mMonth);
                    break;
                case 8:
                    dialogBuilder.setIcon(R.mipmap.ic_sep);
                    mMonth= String.valueOf("9");
                    mMonthCode= getMonthCode(mMonth);
                    break;
                case 9:
                    dialogBuilder.setIcon(R.mipmap.ic_oct);
                    mMonth= String.valueOf("10");
                    mMonthCode= getMonthCode(mMonth);
                    break;
                case 10:
                    dialogBuilder.setIcon(R.mipmap.ic_nov);
                    mMonth= String.valueOf("11");
                    mMonthCode= getMonthCode(mMonth);
                    break;
                case 11:
                    dialogBuilder.setIcon(R.mipmap.ic_dec);
                    mMonth= String.valueOf("12");
                    mMonthCode= getMonthCode(mMonth);
                    break;
            }

            edtAmount = (EditText) dialogView.findViewById(R.id.editAmt);
            // Locate the spinner and other controls in maintainance_entry_dialog.xml
            mySpinner = (Spinner) dialogView.findViewById(R.id.my_flatspinner);
            my_paymodespinner = (Spinner) dialogView.findViewById(R.id.my_paymodespinner);
            txtownername = (TextView) dialogView.findViewById(R.id.ownername);
            txtrentername = (TextView) dialogView.findViewById(R.id.rentername);
            txtflatnumber = (TextView) dialogView.findViewById(R.id.flatnumber);
            txtfltdiscription = (TextView) dialogView.findViewById(R.id.fltdiscription);
            txtlastPaidEntrydt= (TextView) dialogView.findViewById(R.id.lastPaidEntrydt);;
            txtlastPaidMonth=(TextView) dialogView.findViewById(R.id.lastPaidMonth);
            txtlastPaidYear=(TextView) dialogView.findViewById(R.id.lastPaidYear);
            txtlastPaidAmount=(TextView) dialogView.findViewById(R.id.lastPaidAmount);
            txtlastPaidby=(TextView) dialogView.findViewById(R.id.lastPaidby);
//              descSeperator =(LinearLayout)dialogView.findViewById(R.id.descSeperator);

            bubblecontentWithBackgrounddate=(LinearLayout)dialogView.findViewById(R.id.bubblecontentWithBackground);
            bubblecontentWithBackgroundcash=(LinearLayout)dialogView.findViewById(R.id.bubblecontentWithBackgroundcash);

            txtfltdiscription.setVisibility(View.INVISIBLE);
            txtlastPaidEntrydt.setVisibility(View.INVISIBLE);
            txtlastPaidMonth.setVisibility(View.INVISIBLE);
            txtlastPaidYear.setVisibility(View.INVISIBLE);
            txtlastPaidAmount.setVisibility(View.INVISIBLE);
            txtlastPaidby.setVisibility(View.INVISIBLE);
//              descSeperator.setVisibility(View.INVISIBLE);
            bubblecontentWithBackgrounddate.setVisibility(View.INVISIBLE);
            bubblecontentWithBackgroundcash.setVisibility(View.INVISIBLE);

            //--------------------------------------------------------------------------------------
            String chars = getResources().getString(R.string.PayMaintaincence);
            SpannableString str = new SpannableString(chars);
            str.setSpan(new ForegroundColorSpan(Color.WHITE), 0, chars.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            dialogBuilder.setTitle(String.valueOf(intYear) + " " + str); //getMonthShortName(intMonth)

//          final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
//          globalVariable.getMonthShortName(intMonth);

            dialogBuilder.setMessage("Fill Maintenance Amount");
            dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //do something with edtAmount.getText().toString();
                    //All of the validation happens inside the CustomListener.
                    //I had to move it to enable data validation.
                }
            });
            dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //pass
                }
            });

            AlertDialog b = dialogBuilder.create();
            b.show();
            Button theButton = b.getButton(DialogInterface.BUTTON_POSITIVE);
            theButton.setOnClickListener(new CustomListener(b));

            // Download JSON data AsyncTask
            new DownloadMemberDetailwithLastPaymentRecordJSON().execute();
        } catch (Exception e) {
            Error = e.getMessage();
        }
    }

//  CUSTOMLISTENER CLASS USED FOR VALIDATION AND CALL MAINTAINANCE WEBSERVICE
    class CustomListener implements View.OnClickListener {
        private final Dialog paydialog;
        public CustomListener(Dialog dialog) {
            this.paydialog = dialog;
        }
        @Override
        public void onClick(View v) {
            // put your code here
            blnIsValidation = validateInputs();

            if (!blnIsValidation) {
                //edtAmount.setError("Please enter MaintainanceMain amount!"); //
                //Toast.makeText(MaintainanceMain.this, "Invalid data", Toast.LENGTH_SHORT).show();
            } else {

                hashmapmaintaincneInput.put("AMOUNT", edtAmount.getText().toString());
                hashmapmaintaincneInput.put("PAYBY", my_paymodespinner.getSelectedItem().toString());

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MaintainanceMain.this);
                // Setting Dialog Title
                alertDialog.setTitle("Save MaintainanceMain...");
                // Setting Dialog Message
                alertDialog.setMessage("Do you want to save MaintainanceMain  of flat " + hashmapmaintaincneInput.get("FLATNUMBER").toString() + " amounting to Rs." + hashmapmaintaincneInput.get("AMOUNT").toString());
                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.save);
                alertDialog.setCancelable(false);

                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed YES button. Write Logic Here
                        //call MaintainanceMain deposit Web servicce here to insertMaintainanceDetailTOServer deposit amount into server
                        insertMaintainanceDetailTOServer(dialogView, paydialog);
                    }
                });

                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed No button. Write Logic Here
                        Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                    }
                });

                // Setting Netural "Cancel" Button
                alertDialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed Cancel button. Write Logic Here
                        paydialog.dismiss();
//                        Toast.makeText(getApplicationContext(), "You clicked on Cancel",
//                                Toast.LENGTH_SHORT).show();
                    }
                });
                // Showing Alert Message
                alertDialog.show();
                //dialog.dismiss();
            }
        }
    }

    public boolean validateInputs()
    {
        boolean isValidate=true;
        String strOwner_id;
        String strFlat_id;

        if( edtAmount.getText().toString().length() == 0 ) {
            edtAmount.setError("Please provide MaintainanceMain Amount!");
            isValidate=false;
            return isValidate;
        }

        String st =mySpinner.getSelectedItem().toString();
        int pos =mySpinner.getSelectedItemPosition();

        //Toast.makeText(this,
        //        "Selected position !!" + pos , Toast.LENGTH_LONG)
        //        .show();

        Object valueO = hashmapmaintaincneInput.get("OWNER_ID");
        Object valueF = hashmapmaintaincneInput.get("FLT_ID");

        if (valueO != null) {
            strOwner_id =hashmapmaintaincneInput.get("OWNER_ID");
            if (strOwner_id.equals("0"))
            {
                Toast.makeText(this,
                        "Owner does not exists for the selected Flat!", Toast.LENGTH_LONG)
                        .show();
                isValidate=false;
                return isValidate;
            }
        }

        if (valueF!= null)
        {
            strFlat_id =hashmapmaintaincneInput.get("FLT_ID");
            if (strFlat_id.equals("0"))
            {
                Toast.makeText(this,
                        "Flat id does not exists!", Toast.LENGTH_LONG)
                        .show();
                isValidate=false;
                return isValidate;
            }
        }

        if(pos!=0)
        {
            String flat_type = mySpinner.getSelectedItem().toString();
//            Toast.makeText(this,
//                    "Thankyou for the MaintainanceMain payment of selected flat !!" + flat_type  , Toast.LENGTH_LONG)
//                    .show();
            isValidate=true;
            return isValidate;
        }
        else{
            Toast.makeText(this,
                    "Please Select the flat !!", Toast.LENGTH_LONG)
                    .show();
            isValidate=false;
            return isValidate;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MaintainanceMain Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.neerajweb.expandablelistviewtest/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MaintainanceMain Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.neerajweb.expandablelistviewtest/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    // Download JSON data AsyncTask
    private class DownloadMemberDetailwithLastPaymentRecordJSON extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;
        final ApartmentApplicationController globalVariable = (ApartmentApplicationController) getApplicationContext();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MaintainanceMain.this);
            progressDialog.setTitle("Processing...");
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
//          Locate the memberJSON Class
            member = new ArrayList<memberJSON>();
//          Create an array to populate the spinner
            Arraylst_Spinner_Member = new ArrayList<String>();

            try {
//                  JSON file URL address
//                  jsonobject = memberJSON
//                        .getJSONfromURL("http://myandroidng.com/member_detail.php");

                jsonobject = memberJSON
                        .getJSONfromURL(Const.URL_WS_MEMBERDETAIL);

//              Locate the NodeList name
                jsonarray = jsonobject.getJSONArray("register_member");

//              Set Initial Value in Spinner
                memberJSON Initialmemberpop = new memberJSON();

                Initialmemberpop.setownerid(0);
                Initialmemberpop.setfltid(0);
                Initialmemberpop.setownername("");
                Initialmemberpop.setrentername("");
                Initialmemberpop.setflatnumber("");
                Initialmemberpop.setflattype("");
                Initialmemberpop.setlastPaidMonth("");
                Initialmemberpop.setlastPaidYear("");
                Initialmemberpop.setlastPaidAmount("");
                Initialmemberpop.setlastPaidEntrydt("");
                Initialmemberpop.setlastPaidby("");

                member.add(Initialmemberpop);
                Arraylst_Spinner_Member.add("Choose Flat....");


                for (int i = 0; i < jsonarray.length(); i++) {
                    jsonobject = jsonarray.getJSONObject(i);

                    memberJSON memberpop = new memberJSON();

                    memberpop.setownerid(jsonobject.optInt("Owner_id"));
                    memberpop.setfltid(jsonobject.optInt("flt_id"));
                    memberpop.setownername(jsonobject.optString("Owner_name"));
                    memberpop.setrentername(jsonobject.isNull("Renter_name") ? "" : jsonobject.getString("Renter_name")); // Renters might be null sometimes
                    memberpop.setflatnumber(jsonobject.optString("flt_no"));
                    memberpop.setflattype(jsonobject.optString("flt_type"));

                    memberpop.setlastPaidMonth(jsonobject.isNull("LastPaidMonth") ? "" : jsonobject.optString("LastPaidMonth"));
                    memberpop.setlastPaidYear(jsonobject.isNull("LastPaidYear") ? "" : jsonobject.optString("LastPaidYear"));
                    memberpop.setlastPaidAmount(jsonobject.isNull("LastPaidAmount") ? "" : jsonobject.optString("LastPaidAmount"));
                    memberpop.setlastPaidEntrydt(jsonobject.isNull("LastPaidEntrydt") ? "" : jsonobject.optString("LastPaidEntrydt"));
                    memberpop.setlastPaidby(jsonobject.isNull("LastPaidby") ? "" : jsonobject.optString("LastPaidby"));

                    member.add(memberpop);

                    // Populate spinner with flat name
                    Arraylst_Spinner_Member.add(jsonobject.optString("flt_no") + "      " +jsonobject.optString("flt_type"));
                }
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            try
            {
                //progressDialog.dismiss();

                if (!(Arraylst_Spinner_Member.isEmpty())) {

                //Setting Payment Mode spinner adapter
                ArrayAdapter<String> spinnerpaymodeArrayAdapter = new ArrayAdapter<String>(MaintainanceMain.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.paymentmode));
                my_paymodespinner.setAdapter(spinnerpaymodeArrayAdapter);

                // Setting Flat Spinner adapter
                mySpinner.setAdapter(new ArrayAdapter<String>(MaintainanceMain.this , android.R.layout.simple_spinner_dropdown_item, Arraylst_Spinner_Member));

                isSpinnerInitial=true;
                mySpinner.setSelection(-1);
                // Spinner on item click listener
                mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0,
                                               View arg1, int position, long arg3) {
                        // TODO Auto-generated method stub
                        // Locate the textviews in expandable_list_view_activity_mainst_view_activity_main.xml
                        if (isSpinnerInitial){
                            isSpinnerInitial = false;
                            return;
                        }
                        else {
                            // clear hash map
                            hashmapmaintaincneInput.clear();

                            //Fill hash map
                            hashmapmaintaincneInput.put("MONTH", mMonth);
                            hashmapmaintaincneInput.put("YEAR", mYear);
                            hashmapmaintaincneInput.put("MONTHCODE", mMonthCode);
                            hashmapmaintaincneInput.put("FLT_ID", String.valueOf(member.get(position).getfltid()));
                            hashmapmaintaincneInput.put("OWNER_ID", String.valueOf(member.get(position).getownerid()) );
                            hashmapmaintaincneInput.put("OWNERNAME", member.get(position).getownername().toString());
                            hashmapmaintaincneInput.put("RENTERNAME", member.get(position).getrentername().toString());
                            hashmapmaintaincneInput.put("FLATNUMBER", member.get(position).getflatnumber().toString());
                            hashmapmaintaincneInput.put("FLATTYPE", member.get(position).getflattype().toString());

                            flatDescription = txtfltdiscription.getText().toString();
                            String newString;

                            renterName = member.get(position).getrentername();
                            if ( !renterName.contains("Not Available") )
                            {
                                renterName = "Renter -  " + renterName;
                                //newString ="The Last MaintainanceMain deposit detail from " + member.get(position).getownername().toString() + "/" + renterName+ "."  + String.valueOf(member.get(position).getownerid()) + " - " + String.valueOf(member.get(position).getfltid()) ;
                                newString ="The Last MaintainanceMain deposit detail from " + member.get(position).getownername().toString() + "/" + renterName+ ".";
                            }
                            else
                            {
                                //renterName = "Self";
                                //newString ="The Last MaintainanceMain deposit detail from " + member.get(position).getownername().toString() + "."  + String.valueOf(member.get(position).getownerid()) + " - " + String.valueOf(member.get(position).getfltid()) ;
                                newString ="The Last MaintainanceMain deposit detail from " + member.get(position).getownername().toString() + ".";
                            }

                            txtfltdiscription.setText(newString);
                            txtfltdiscription.setVisibility(View.VISIBLE);
                            txtlastPaidEntrydt.setVisibility(View.VISIBLE);
                            txtlastPaidMonth.setVisibility(View.VISIBLE);
                            txtlastPaidYear.setVisibility(View.VISIBLE);
                            txtlastPaidAmount.setVisibility(View.VISIBLE);
                            txtlastPaidby.setVisibility(View.VISIBLE);

                            String dateStr =member.get(position).getlastPaidEntrydt().toString();
                            if (dateStr!="")
                            {
                                bubblecontentWithBackgrounddate.setVisibility(View.VISIBLE);
                                bubblecontentWithBackgroundcash.setVisibility(View.VISIBLE);
                            }
                            else
                            {
                                bubblecontentWithBackgrounddate.setVisibility(View.INVISIBLE);
                                bubblecontentWithBackgroundcash.setVisibility(View.INVISIBLE);
                            }

                            SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            SimpleDateFormat targetFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy hh:mm a" );
                            Date date;
                            String strTargetDate="";
                            try {
                                date = originalFormat.parse(dateStr);
//                              System.out.println("Old Format :   " + originalFormat.format(date));
//                              System.out.println("New Format :   " + targetFormat.format(date));
                                strTargetDate = targetFormat.format(date).toString();
                            } catch (ParseException ex) {
                                Toast.makeText(MaintainanceMain.this,
                                        "Last MaintainanceMain deposit detail not found!", Toast.LENGTH_LONG)
                                        .show();
                            }

                            txtlastPaidEntrydt.setText("Date : "
                                    + strTargetDate);
                            txtlastPaidMonth.setText("Month : "
                                    + member.get(position).getlastPaidMonth());
                            txtlastPaidYear.setText("Year : "
                                    + member.get(position).getlastPaidYear());
                            txtlastPaidAmount.setText("Amount : "
                                    + member.get(position).getlastPaidAmount());
                            txtlastPaidby.setText("paid by : "
                                    + member.get(position).getlastPaidby());
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }
                });
                }
                progressDialog.cancel();
            } catch (Exception e) {
               Error = e.getMessage();
               cancel(true);
            }
        }
    }

    public void insertMaintainanceDetailTOServer(final View v, final Dialog PAYDIALOG) {
//        final ApartmentApplicationController globalVariable = (ApartmentApplicationController) getApplicationContext();
        IsSaveMaintainance=false;
        MPD.show();
        final String flat_type = mySpinner.getSelectedItem().toString();

        StringRequest postRequest = new StringRequest(Request.Method.POST, Const.URL_WS_CRUD_MAINTAINANCE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        MPD.dismiss();
                        PAYDIALOG.dismiss();
                        IsSaveMaintainance=true;
                        //item_et.setText("");
                        Toast.makeText(getApplicationContext(),
                                "Response Object " + response
                                ,Toast.LENGTH_SHORT).show();

                            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(),R.style.MyCustomTheme);
                            builder.setTitle("Payment Success");
                            builder.setMessage("Thankyou for MaintainanceMain payment of Flat-" + flat_type.toString().trim() + " for the period " + hashmapmaintaincneInput.get("MONTHCODE") + " " + hashmapmaintaincneInput.get("YEAR") + "!!" );
                            builder.setIcon(R.drawable.success);
                            builder.setCancelable(true);
                            final AlertDialog dlg = builder.create();
                            dlg.show();
                            final Timer t = new Timer();
                            t.schedule(new TimerTask() {
                                public void run() {
                                    dlg.dismiss(); // when the task active then close the dialog
                                    t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
                                }
                            }, 10000); // after 2 second (or 2000 miliseconds), the task will be active.

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MPD.dismiss();
                IsSaveMaintainance=false;
                Toast.makeText(getApplicationContext(),
                        "failed to update...", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                String strMONTH=hashmapmaintaincneInput.get("MONTH");
                String strYEAR=hashmapmaintaincneInput.get("YEAR");
                String strOWNER_ID=hashmapmaintaincneInput.get("OWNER_ID");
                String strFLT_ID=hashmapmaintaincneInput.get("FLT_ID");
                String strAMOUNT=hashmapmaintaincneInput.get("AMOUNT");
                String strPAYBY=hashmapmaintaincneInput.get("PAYBY");

                params.put("mth",strMONTH);
                params.put("yr",strYEAR);
                params.put("O_id",strOWNER_ID);
                params.put("f_id", strFLT_ID);
                params.put("amt", strAMOUNT);
                params.put("pdby",strPAYBY);
                return params;
            }
        };

        // Adding request to request queue
        ApartmentApplicationController.getInstance().addToReqQueue(postRequest);
    }

    private String getMonthCode(String month) {

        if (month.equalsIgnoreCase("1")) return "JAN";
        if (month.equalsIgnoreCase("2")) return "FEB";
        if (month.equalsIgnoreCase("3")) return "MAR";
        if (month.equalsIgnoreCase("4")) return "APR";
        if (month.equalsIgnoreCase("5")) return "MAY";
        if (month.equalsIgnoreCase("6")) return "JUN";
        if (month.equalsIgnoreCase("7")) return "JUL";
        if (month.equalsIgnoreCase("8")) return "AUG";
        if (month.equalsIgnoreCase("9")) return "SEP";
        if (month.equalsIgnoreCase("10")) return "OCT";
        if (month.equalsIgnoreCase("11")) return "NOV";
        if (month.equalsIgnoreCase("12")) return "DEC";

        //return -1 if month code not found
        return "";
    }

    public void read(View v) {
        //Intent read_intent = new Intent(MaintainanceMain.this, ReadData.class);
        //startActivity(read_intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // must dismiss this dialog before orientation change to avoid AnchorView is deleted!
        if (myQuickDialogFragment!= null && myQuickDialogFragment.isVisible()) {
            myQuickDialogFragment.dismiss();
        }
        super.onSaveInstanceState(outState);
    }

    public static class MyQuickDialogFragment extends QuickActionDialogFragment {

        private static int intMonth;
        private static int intYear;
        private static Activity myContext;
        private static TextView myTextView;

        CustomDialogMaintainance dialog;

        public MyQuickDialogFragment(){
            super();
        }

        static MyQuickDialogFragment newInstance(String mnth,String year,Activity mContext ,TextView textViewControl) {
            MyQuickDialogFragment f = new MyQuickDialogFragment();

            try
            {
                Bundle args = new Bundle();
                intMonth = Integer.parseInt(mnth);
                intYear = Integer.parseInt(year);
                myContext=mContext;
                myTextView = textViewControl;
                args.putInt("month", intMonth);
                args.putInt("year", intYear);
                f.setArguments(args);
            }
            catch(Exception Ex)
            {
                Toast.makeText(myContext, Ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return f;
        }

        @Override
        protected int getArrowImageViewId() {
            return R.id.ivArrow;
//            return 0; that mean you donot have an arrow
        }

        @Override
        protected int getLayout() {
            return R.layout.quickactiondialog_view;
        }

        @Override
        protected boolean isStatusBarVisible() {
            return true;
        }

        @Override
        protected boolean isCanceledOnTouchOutside() {
            return true;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            intMonth = getArguments() != null ? getArguments().getInt("month") : 1;
            intYear = getArguments() != null ? getArguments().getInt("year") : 2015;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = super.onCreateView(inflater, container, savedInstanceState);
            TextView myAwesomeTextView = (TextView)view.findViewById(R.id.btnSampleTitle);
            myAwesomeTextView.setText("To generate/regenerate monthly report please press generate button");

            view.findViewById(R.id.btnGeneraterpt).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Drawable[] myTextViewCompoundDrawables;
                        myTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.monthlyreport_book, 0, 0, 0);
                        myTextViewCompoundDrawables = myTextView.getCompoundDrawables();

                        for (Drawable drawableJAN : myTextViewCompoundDrawables) {
                            if (drawableJAN == null)
                                continue;
                            if (drawableJAN.getAlpha()==0)
                            {
                                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableJAN, PropertyValuesHolder.ofInt("alpha", 255));
                                animator.setTarget(drawableJAN);
                                animator.setDuration(2000);
                                animator.start();
                            }
                            else
                            {
                                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawableJAN, PropertyValuesHolder.ofInt("alpha", 0));
                                animator.setTarget(drawableJAN);
                                animator.setDuration(2000);
                                animator.start();

                                animator = ObjectAnimator.ofPropertyValuesHolder(drawableJAN, PropertyValuesHolder.ofInt("alpha", 255));
                                animator.setTarget(drawableJAN);
                                animator.setDuration(2000);
                                animator.start();
                            }
                        }


                        Toast.makeText(mContext, "generating report keep patience!! - " + getMonth(intMonth) + " - " + String.valueOf(intYear), Toast.LENGTH_SHORT).show();
                        dialog = new CustomDialogMaintainance(myContext, String.valueOf(intMonth), String.valueOf(intYear));
                    } catch (Exception Ex) {
                        Toast.makeText(mContext, "Please check network connection...", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            view.findViewById(R.id.btnshowrpt).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        dialog.show();
                    }
                    catch (Exception Ex)
                    {
                        Toast.makeText(mContext, "Error while generating report , try again to click generate button...", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            return view;
        }
        public String getMonth(int month) {
            return new DateFormatSymbols().getMonths()[month-1];
        }
    }
}