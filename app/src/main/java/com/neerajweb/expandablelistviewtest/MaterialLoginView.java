package com.neerajweb.expandablelistviewtest;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import com.neerajweb.expandablelistviewtest.JSONfunctions.memberJSON;
import com.neerajweb.expandablelistviewtest.JSONfunctions.result_MemberJSON;
import com.neerajweb.expandablelistviewtest.Model.modelMember;
import com.neerajweb.expandablelistviewtest.utils.Const;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

/**
 * Created by shem on 1/15/16.
 */
public class MaterialLoginView extends FrameLayout {

    private MaterialLoginViewListener listener;

    private TextView registerBtn;
    private TextView registerTitle;
    private TextInputLayout registerUser;
    private Spinner flatspinner;

    public int intFlatId;
    private Context myContext;
    ProgressDialog PDLoadMember;
    ArrayList<modelMember> model_Member;
    private ArrayList<result_MemberJSON> resultMember;


//    private TextInputLayout registerPass;
//    private TextInputLayout registerPassRep;

    private TextView loginTitle;
    private TextInputLayout loginUser;
    private TextInputLayout loginPass;
    private TextView loginBtn;
    private FloatingActionButton registerFab;
    private View registerCancel;
    private View loginView;
    private View loginCard;
    private View registerView;
    private View registerCard;
    private int intFlag;

    ArrayList<String> Arraylst_Spinner_Member;
    JSONObject jsonobject;
    JSONArray jsonarray;
    ArrayList<memberJSON> member;
    boolean isSpinnerInitial ;
    String Error = null;
    ProgressDialog progressDialog;

    public MaterialLoginView(Context context) {
        this(context, null);
    }

    public MaterialLoginView(Context context,int _intFlag) {
        this(context, null);
        intFlag=_intFlag;
    }

    public MaterialLoginView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaterialLoginView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MaterialLoginView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        myContext= context;
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.login_view, this, true);

        loginView = findViewById(R.id.login_window);
        loginCard = findViewById(R.id.login_card);
        loginTitle = (TextView) findViewById(R.id.login_title);
        loginUser = (TextInputLayout) findViewById(R.id.login_user);
        loginPass = (TextInputLayout) findViewById(R.id.login_pass);
        loginBtn = (TextView) findViewById(R.id.login_btn);

        registerView = findViewById(R.id.register_window);
        registerCard = findViewById(R.id.register_card);
        registerTitle = (TextView) findViewById(R.id.register_title);
        registerUser = (TextInputLayout) findViewById(R.id.register_user);

//        registerPass = (TextInputLayout) findViewById(R.id.register_pass);
//        registerPassRep = (TextInputLayout) findViewById(R.id.register_pass_rep);

        flatspinner=(Spinner) findViewById(R.id.flatspinner);
        registerBtn = (TextView) findViewById(R.id.register_btn);

        registerFab = (FloatingActionButton) findViewById(R.id.register_fab);
        registerCancel = findViewById(R.id.register_cancel);


        registerBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                listener.onRegister(registerUser, registerPass, registerPassRep);
                listener.onRegister(registerUser, flatspinner);
            }
        });

        loginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLogin(loginUser, loginPass);
            }
        });

        registerFab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                animateRegister();
            }
        });

        registerCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                animateLogin();
            }
        });


        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.MaterialLoginView,
                0, 0);

        try {
            String string = a.getString(R.styleable.MaterialLoginView_loginTitle);
            if (string != null) {
                loginTitle.setText(string);
            }

            string = a.getString(R.styleable.MaterialLoginView_loginHint);
            if (string != null) {
                loginUser.setHint(string);
            }

            string = a.getString(R.styleable.MaterialLoginView_loginPasswordHint);
            if (string != null) {
                loginPass.setHint(string);
            }

            string = a.getString(R.styleable.MaterialLoginView_loginActionText);
            if (string != null) {
                loginBtn.setText(string);
            }

            string = a.getString(R.styleable.MaterialLoginView_registerTitle);
            if (string != null) {
                registerTitle.setText(string);
            }

            string = a.getString(R.styleable.MaterialLoginView_registerHint);
            if (string != null) {
                registerUser.setHint(string);
            }

//            string = a.getString(R.styleable.MaterialLoginView_registerPasswordHint);
//            if (string != null) {
//                registerPass.setHint(string);
//            }
//
//            string = a.getString(R.styleable.MaterialLoginView_registerRepeatPasswordHint);
//            if (string != null) {
//                registerPassRep.setHint(string);
//            }

            string = a.getString(R.styleable.MaterialLoginView_registerActionText);
            if (string != null) {
                registerBtn.setText(string);
            }

            int color = a.getColor(R.styleable.MaterialLoginView_loginTextColor, ContextCompat.getColor(getContext(), R.color.material_login_login_text_color));
            loginUser.getEditText().setTextColor(color);
            loginPass.getEditText().setTextColor(color);

            color = a.getColor(R.styleable.MaterialLoginView_registerTextColor, ContextCompat.getColor(getContext(), R.color.material_login_register_text_color));
            registerUser.getEditText().setTextColor(color);

//            registerPass.getEditText().setTextColor(color);
//            registerPassRep.getEditText().setTextColor(color);

            registerFab.setImageResource(
                    a.getResourceId(R.styleable.MaterialLoginView_registerIcon, R.drawable.ic_add_white));

            try {
                    // Download JSON data AsyncTask
                    new DownloadFlatMasterJSON().execute();
            }
            catch(Exception Ex)
            {}

        } finally {
            a.recycle();
        }
    }

    // Download JSON data AsyncTask
    class DownloadFlatMasterJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try
            {
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setTitle("Getting information");
                progressDialog.setMessage("Please wait...");
                progressDialog.setCancelable(false);
                progressDialog.show();
            }
            catch(Exception Ex) {   }
        }

        @Override
        protected Void doInBackground(Void... params) {

            member = new ArrayList<memberJSON>();
//          Create an array to populate the spinner
            Arraylst_Spinner_Member = new ArrayList<String>();

            try {
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
                Arraylst_Spinner_Member.add("Choose Flat");

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
                    Arraylst_Spinner_Member.add(jsonobject.optString("flt_no") + " , " +jsonobject.optString("flt_type"));
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
                if (!(Arraylst_Spinner_Member.isEmpty())) {
                    // Setting Flat Spinner adapter
                    flatspinner.setAdapter(new ArrayAdapter<String>(getContext() , R.layout.spinner_item, Arraylst_Spinner_Member));

                    isSpinnerInitial=true;
                    flatspinner.setSelection(-1);
                    // Spinner on item click listener
                    flatspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                                intFlatId= member.get(position).getfltid();
                                Toast.makeText(getContext(), String.valueOf(member.get(position).getfltid()), Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> arg0) {
                            // TODO Auto-generated method stub
                        }
                    });
                }
                else
                {
                    Toast.makeText(getContext(),"Network error , check your network connection and try again", Toast.LENGTH_SHORT).show();
                }
                progressDialog.cancel();
            } catch (Exception e) {
                Error = e.getMessage();
                cancel(true);
            }
        }
    }

    public ArrayList<result_MemberJSON> parseJsonResult(JSONObject response) {
        result_MemberJSON item= new result_MemberJSON();
        resultMember= new ArrayList<result_MemberJSON>();
        //get the children for the group
        ArrayList<modelMember> memberList = item.getMemberList();

        String ITEM_OWNER_ID="Owner_id";
        String ITEM_APPROVE_STATUS="approve_status";
        String ITEM_OWNER_NAME="Owner_name";
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
                itemrpop.setapprove_status(jobj.isNull(ITEM_APPROVE_STATUS) ? "" : jobj.getString(ITEM_APPROVE_STATUS));
                itemrpop.setOwner_name(jobj.isNull(ITEM_OWNER_NAME) ? "" : jobj.getString(ITEM_OWNER_NAME));
                memberList.add(0, itemrpop);
                item.setMemberList(memberList);
            }
        } catch (JSONException e) {
            // Something went wrong!
        }
        resultMember.add(item);
        return resultMember;
    }

    private void animateRegister() {
        Path path = new Path();
        RectF rect = new RectF(-241F, -40F, 41F, 242F);
        path.addArc(rect, -45F, 180F);
        path.lineTo(-0F, -50F);
        FabAnimation fabAnimation = new FabAnimation(path);
        fabAnimation.setDuration(400);
        fabAnimation.setInterpolator(new AccelerateInterpolator());

        fabAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                SupportAnimator animator = getCircularRevealAnimation(registerCard, registerView.getWidth() - 250, 400, 0f, 2F * registerView.getHeight());
                animator.setDuration(700);
                animator.setStartDelay(200);
                animator.addListener(new SupportAnimator.SimpleAnimatorListener() {
                    public void onAnimationStart() {
                        registerView.setVisibility(View.VISIBLE);
                    }

                    public void onAnimationEnd() {
                        loginView.setVisibility(View.GONE);
                    }
                });
                animator.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                registerFab.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        registerFab.startAnimation(fabAnimation);
    }

    private SupportAnimator getCircularRevealAnimation(View view, int centerX, int centerY, float startRadius, float endRadius) {
        return ViewAnimationUtils.createCircularReveal(
                view, centerX, centerY, startRadius, endRadius);
    }

    private void animateLogin() {
        registerCancel.animate().scaleX(0F).scaleY(0F).alpha(0F).rotation(90F).
                setDuration(200).setInterpolator(new AccelerateInterpolator()).start();
        SupportAnimator animator = getCircularRevealAnimation(registerCard, registerView.getWidth() / 2, registerView.getHeight() / 2, 1f * registerView.getHeight(), 0F);
        animator.setDuration(500);
        animator.setStartDelay(100);
        animator.addListener(new SupportAnimator.SimpleAnimatorListener() {
            public void onAnimationStart() {
                loginView.setVisibility(View.VISIBLE);
            }

            public void onAnimationEnd() {
                registerView.setVisibility(View.GONE);
                registerCancel.setScaleX(1F);
                registerCancel.setScaleY(1F);
                registerCancel.setAlpha(1F);
                registerCancel.setRotation(45F);
                registerFab.setVisibility(View.VISIBLE);

                ObjectAnimator animX = ObjectAnimator.ofFloat(registerFab, "scaleX", 0F, 1F);
                ObjectAnimator animY = ObjectAnimator.ofFloat(registerFab, "scaleY", 0F, 1F);
                ObjectAnimator alpha = ObjectAnimator.ofFloat(registerFab, "alpha", 0F, 1F);
                ObjectAnimator rotation = ObjectAnimator.ofFloat(registerFab, "rotation", 90F, 0F);
                AnimatorSet animator = new AnimatorSet();
                animator.playTogether(animX, animY, alpha, rotation);
                animator.setInterpolator(new AccelerateInterpolator());
                animator.setDuration(200);
                animator.start();
            }
        });
        animator.start();
    }

    public void setListener(MaterialLoginViewListener listener) {
        this.listener = listener;
    }

    class FabAnimation extends Animation {
        private PathMeasure measure;
        private float[] pos;

        public FabAnimation(Path path) {
            measure = new PathMeasure(path, false);
            pos = new float[]{0, 0};
        }

        protected void applyTransformation(float interpolatedTime, Transformation t) {
            measure.getPosTan(measure.getLength() * interpolatedTime, pos, null);
            Matrix matrix = t.getMatrix();
            matrix.setTranslate(pos[0], pos[1]);
            matrix.preRotate(interpolatedTime * 45);
            t.setAlpha(1 - interpolatedTime);
        }
    }
}
