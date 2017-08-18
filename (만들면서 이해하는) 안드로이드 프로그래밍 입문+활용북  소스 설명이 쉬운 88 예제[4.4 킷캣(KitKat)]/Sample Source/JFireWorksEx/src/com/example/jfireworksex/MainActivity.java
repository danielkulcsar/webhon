package com.example.jfireworksex;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JFireWorks jFireWorks = (JFireWorks)this.findViewById(R.id.jFireWorks);
        jFireWorks.setSoundId(this, R.raw.fireworks_fire, R.raw.fireworks_boom);
    }
    
}
