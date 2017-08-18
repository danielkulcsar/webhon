package com.example.buttonex;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView)findViewById(R.id.textMessage);

        Button btn02 = (Button)findViewById(R.id.btn02);
        btn02.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                mTextMessage.setText("Button-2 Pressed");
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btnPress:
            mTextMessage.setText("Button Pressed");
            break;
        }
    }
}
