package com.example.touchevent;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity 
        implements View.OnTouchListener {
    TextView mTextMsg1;
    TextView mTextMsg2;
    TextView mTextArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMsg1 = (TextView)findViewById(R.id.textMsg1);
        mTextMsg2 = (TextView)findViewById(R.id.textMsg2);
        mTextArea = (TextView)findViewById(R.id.textArea);
        mTextArea.setOnTouchListener(this);
    }

    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int x1 = (int) event.getX();
        int y1 = (int) event.getY();
        String message = "Touch - ";

        switch( event.getAction() ) {
        case MotionEvent.ACTION_DOWN :
            message += ("DOWN : " + x1 + " / " + y1);
            mTextMsg1.setText(message);
            break;
        case MotionEvent.ACTION_MOVE :
            message += ("MOVE : " + x1 + " / " + y1);
            mTextMsg1.setText(message);
            break;
        case MotionEvent.ACTION_UP :
            message += ("UP : " + x1 + " / " + y1);
            mTextMsg1.setText(message);
            break;
        }
        return true;
    }

    public boolean onTouch(View v, MotionEvent event) {
        int x1 = (int) event.getX();
        int y1 = (int) event.getY();
        String message = "TextView Touch - ";

        switch( event.getAction() ) {
        case MotionEvent.ACTION_DOWN :
            message += ("DOWN : " + x1 + " / " + y1);
            mTextMsg2.setText(message);
            break;
        case MotionEvent.ACTION_MOVE :
            message += ("MOVE : " + x1 + " / " + y1);
            mTextMsg2.setText(message);
            break;
        case MotionEvent.ACTION_UP :
            message += ("UP : " + x1 + " / " + y1);
            mTextMsg2.setText(message);
            break;
        }
        return true;
    }

}
