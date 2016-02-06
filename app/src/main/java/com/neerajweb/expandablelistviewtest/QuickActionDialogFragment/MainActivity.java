package com.neerajweb.expandablelistviewtest.QuickActionDialogFragment;

/**
 * Created by Admin on 07/01/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormatSymbols;

import com.neerajweb.expandablelistviewtest.CustomDialogMaintainance;
import com.neerajweb.expandablelistviewtest.R;

public class MainActivity extends AppCompatActivity {
    private Context mContext;

    private CustomDialogMaintainance dialog ;
    private String strMonth;
    private String strYear;
    private static Activity myContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quickactiondialogactivity_main);

//      dialog  = new CustomDialogMaintainance(this);
        mContext=this;
        myContext=MainActivity.this;

        final View buttonShow = findViewById(R.id.btnShow);
        final View buttonShow1 = findViewById(R.id.btnNavigation);
        final View buttonShow2 = findViewById(R.id.btnNavigation1);

        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strMonth="1";
                strYear="2016";

                mySampleDialogFragment=  MySampleDialogFragment.newInstance(strMonth,strYear,myContext);
                mySampleDialogFragment.setAnchorView(buttonShow);
                mySampleDialogFragment.setAligmentFlags(AlignmentFlag.ALIGN_ANCHOR_VIEW_LEFT | AlignmentFlag.ALIGN_ANCHOR_VIEW_BOTTOM);
                mySampleDialogFragment.show(getSupportFragmentManager(), null);

//                mySampleDialogFragment = new MyQuickDialogFragment(strMonth,strYear);
//                mySampleDialogFragment.setAnchorView(buttonShow);
//                mySampleDialogFragment.setAligmentFlags(AlignmentFlag.ALIGN_ANCHOR_VIEW_LEFT | AlignmentFlag.ALIGN_ANCHOR_VIEW_BOTTOM);
//                mySampleDialogFragment.show(getSupportFragmentManager(), null);

            }
        });

        buttonShow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMonth="9";
                strYear="2015";

                mySampleDialogFragment=  MySampleDialogFragment.newInstance(strMonth,strYear,myContext);
//              mySampleDialogFragment = new MyQuickDialogFragment(strMonth,strYear);
                mySampleDialogFragment.setAnchorView(buttonShow1);
                mySampleDialogFragment.setAligmentFlags(AlignmentFlag.ALIGN_ANCHOR_VIEW_LEFT | AlignmentFlag.ALIGN_ANCHOR_VIEW_BOTTOM);
                mySampleDialogFragment.show(getSupportFragmentManager(), null);

            }
        });

        buttonShow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMonth="10";
                strYear="2015";

                mySampleDialogFragment=  MySampleDialogFragment.newInstance(strMonth,strYear,myContext);
                mySampleDialogFragment.setAnchorView(buttonShow2);
                mySampleDialogFragment.setAligmentFlags(AlignmentFlag.ALIGN_ANCHOR_VIEW_LEFT | AlignmentFlag.ALIGN_ANCHOR_VIEW_BOTTOM);
                mySampleDialogFragment.show(getSupportFragmentManager(), null);

//              dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
////            dialog.showDialogWithMsg("New Information from Main");
//              dialog.show();
            }
        });
    }

    private MySampleDialogFragment mySampleDialogFragment;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // must dismiss this dialog before orientation change to avoid AnchorView is deleted!
        if (mySampleDialogFragment != null && mySampleDialogFragment.isVisible()) {
            mySampleDialogFragment.dismiss();
        }
        super.onSaveInstanceState(outState);
    }


    public static class MySampleDialogFragment extends QuickActionDialogFragment {

        private static int intMonth;
        private static int intYear;
        private static Activity myContext;
        CustomDialogMaintainance dialog;

        public MySampleDialogFragment(){
            super();
        }

        static MySampleDialogFragment newInstance(String mnth,String year,Activity mContext ) {
            MySampleDialogFragment f = new MySampleDialogFragment();

            try
            {
                Bundle args = new Bundle();
                intMonth = Integer.parseInt(mnth);
                intYear = Integer.parseInt(year);
                myContext=mContext;
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
            //in your OnCreate() method
            myAwesomeTextView.setText("To generate/regenerate monthly report please press generate button");

            view.findViewById(R.id.btnGeneraterpt).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Toast.makeText(getContext(), "generating report keep patience!! - " + getMonth(intMonth) + " - " + String.valueOf(intYear), Toast.LENGTH_SHORT).show();
                        dialog = new CustomDialogMaintainance(myContext, String.valueOf(intMonth), String.valueOf(intYear));

//                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//                        dialog.show();
                    } catch (Exception Ex) {
                        Toast.makeText(getContext(), Ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

            view.findViewById(R.id.btnshowrpt).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
//                        Toast.makeText(getContext(), "Button inside Dialog!! - " + String.valueOf(intMonth) + " - " + String.valueOf(intYear), Toast.LENGTH_SHORT).show();
//                        dialog = new CustomDialogMaintainance(myContext, String.valueOf(intMonth), String.valueOf(intYear));

                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        dialog.show();
                    }
                    catch (Exception Ex)
                    {
                        Toast.makeText(getContext(), Ex.getMessage(), Toast.LENGTH_SHORT).show();
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