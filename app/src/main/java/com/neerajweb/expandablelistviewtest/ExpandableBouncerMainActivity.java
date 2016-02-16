package com.neerajweb.expandablelistviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;


/**
 * Created by Admin on 30/01/2016.
 */
public class ExpandableBouncerMainActivity extends AppCompatActivity {

    private MaintainanceCollectionExpandableBouncerLayout mMaintainanceCollectionExpandableBouncerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandableview_bounce_main);

        View switcher = findViewById(R.id.switcher);
        mMaintainanceCollectionExpandableBouncerLayout = (MaintainanceCollectionExpandableBouncerLayout) findViewById(R.id.expandableLayout);
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


    }
}
