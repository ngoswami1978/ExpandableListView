package com.neerajweb.expandablelistviewtest;

/**
 * Created by Admin on 12/03/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.TextView;

import com.neerajweb.expandablelistviewtest.Card.ProfileMaster;
import com.neerajweb.expandablelistviewtest.LoadingDots.LoadingDots;

public class SplashScreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 7000;
    TextView tvApartmentName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final ViewGroup root = (ViewGroup) findViewById(R.id.root);
        tvApartmentName = (TextView) findViewById(R.id.tvApartmentName);

        setCustomFontface("milit.ttf", tvApartmentName);

        root.removeAllViews();
        LoadingDots loadingDots = new LoadingDots(this);
        loadingDots.setDotsCount(4);
        loadingDots.setDotsSizeRes(R.dimen.LoadingDots_dots_size_default);
        loadingDots.setLoopDuration(1200); // .LoadingDots_loop_duration="1200"
        loadingDots.setLoopStartDelay(600); // .LoadingDots_loop_start_delay="600"

        loadingDots.setDotsColor(Color.WHITE);
        root.addView(loadingDots, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, LoginMainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
    private void setCustomFontface(String fontName,TextView tview)
    {
        try
        {
            if (!fontName.isEmpty()) {
                // Font path
                //String fontPath = "fonts/gtw.ttf";
                String fontPath = "fonts/" + fontName;
                // Loading Font Face
                Typeface tf = Typeface.createFromAsset(this.getAssets(), fontPath);
                // Applying font
                tview.setTypeface(tf);            }
        }
        catch(Exception Ex)
        {
            //Toast.makeText(this, Ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}