package com.example.eventlistener;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity
        implements Button.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.button2);
        btn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, 
                	    "In Parameter", Toast.LENGTH_SHORT).show();
            }
        });
        
        btn = (Button)findViewById(R.id.button3);
        btn.setOnClickListener(mBtn3Lis);
        
        btn = (Button)findViewById(R.id.button4);
        btn.setOnClickListener(this);
    }

    public void buttonClick(View v) {
        switch(v.getId()) {
        case R.id.button1 :
            Toast.makeText(this, "Button onClick Function", 
            	    Toast.LENGTH_SHORT).show();
            break;
        }
    }

    Button.OnClickListener mBtn3Lis = new Button.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "Outside Listener", 
            	    Toast.LENGTH_SHORT).show();
        }
    };

    public void onClick(View v) {
        switch(v.getId()) {
        case R.id.button4 :
            Toast.makeText(this, "Implement Listener", 
            	    Toast.LENGTH_SHORT).show();
            break;
        }
    }

}
