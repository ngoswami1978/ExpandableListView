package com.neerajweb.expandablelistviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import com.neerajweb.expandablelistviewtest.ExpandableLayout;


/**
 * Created by Admin on 30/01/2016.
 */
public class ExpandableBouncerMainActivity extends AppCompatActivity {

    private ExpandableLayout mExpandableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandableview_bounce_main);

        View switcher = findViewById(R.id.switcher);
        mExpandableLayout = (ExpandableLayout) findViewById(R.id.expandableLayout);
        mExpandableLayout.setSwitcher(switcher);
        mExpandableLayout.setExpandInterpolator(new BounceInterpolator());
        mExpandableLayout.setCollapseInterpolator(new AccelerateDecelerateInterpolator());
        mExpandableLayout.setExpandDuration(800);
        mExpandableLayout.setCollapseDuration(400);

        mExpandableLayout.setOnStateChangedListener(new ExpandableLayout.OnStateChangedListener() {
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
                mExpandableLayout.toggle();
            }
        });


    }
}
