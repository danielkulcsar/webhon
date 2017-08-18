package com.example.tabscreenanim;

import android.app.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;

public class MainActivity extends Activity
        implements ActionBar.TabListener {
    ActionBar mAb = null;
    ImageView  mImageView1;
    LinearLayout mLinearLayout1;
    boolean m1stTab = true;
    Animation mAnimLeftIn;
    Animation mAnimRightOut;
    Animation mAnimLeftOut;
    Animation mAnimRightIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mImageView1 = (ImageView)findViewById(R.id.imageView1);
        mLinearLayout1 = (LinearLayout)findViewById(R.id.linearLayout1);

        mAb = getActionBar();
        mAb.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        addActionBarTab("Tab1");
        addActionBarTab("Tab2");
        
        mAnimLeftIn = AnimationUtils.loadAnimation(this, R.anim.left_in);
        mAnimRightOut = AnimationUtils.loadAnimation(this, R.anim.right_out);
        mAnimLeftOut = AnimationUtils.loadAnimation(this, R.anim.left_out);
        mAnimRightIn = AnimationUtils.loadAnimation(this, R.anim.right_in);
    }

    public void addActionBarTab(String strCaption) {
        ActionBar.Tab tab = mAb.newTab();
        tab.setText(strCaption);
        tab.setTabListener(this);
        mAb.addTab(tab);
    }

    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        if( m1stTab ) {
            m1stTab = false;
            return;
        }
    	
        int index = mAb.getSelectedNavigationIndex();
        Log.d("tag", "onTabSelected " + index);
        
        if( index == 0 ) {
            mLinearLayout1.setVisibility(View.INVISIBLE);
            mLinearLayout1.startAnimation(mAnimRightOut);
            mImageView1.startAnimation(mAnimRightIn);
        }
        else { 
            mLinearLayout1.setVisibility(View.VISIBLE);
            mLinearLayout1.startAnimation(mAnimLeftIn);
            mImageView1.startAnimation(mAnimLeftOut);
        }
    }

    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {}

    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {}

}
