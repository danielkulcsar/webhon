package com.example.fragmentex;

import android.os.Bundle;
import android.app.*;
import android.content.res.*;
import android.view.Menu;

public class SubActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sub);

        String strPet = getIntent().getExtras().getString("pet");
        com.example.fragmentex.MainActivity.BodyFragment bodyFrag = 
                com.example.fragmentex.MainActivity.BodyFragment.makeObj( strPet );
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(android.R.id.content, bodyFrag).commit();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if( newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ) {
            finish();
        }
    }

}
