package com.example.datetime;

import java.util.*;

import android.app.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    TextView mTextResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextResult = (TextView)findViewById(R.id.textResult);
    }

    public void onBtnDateTime() {
        String strResult = "";
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        strResult += Integer.toString(year) + "/";
        int month = calendar.get(Calendar.MONTH);
        strResult += Integer.toString(month + 1) + "/";
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        strResult += Integer.toString(date) + "  ";
        strResult += ( calendar.AM_PM == Calendar.AM ) ? "AM " : "PM ";
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        strResult += Integer.toString(hour) + ":";
        int min = calendar.get(Calendar.MINUTE);
        strResult += Integer.toString(min) + ":";
        int sec = calendar.get(Calendar.SECOND);
        strResult += Integer.toString(sec) + " ";
        String[] strWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        strResult += strWeek[dayOfWeek - 1];

        mTextResult.setText(strResult);
    }

    public void onBtnPasedTime() {
        Calendar today = Calendar.getInstance();
        Calendar startday = Calendar.getInstance();
        startday.set(2014, 0, 1, 0, 0, 0);

        long gapSec = (today.getTimeInMillis() - startday.getTimeInMillis()) / 1000;
        long gapDay = gapSec / (60 * 60 * 24);
        gapSec -= gapDay * (60 * 60 * 24);
        long gapHour = gapSec / (60 * 60);
        gapSec -= gapHour * (60 * 60);
        long gapMin = gapSec / 60;
        gapSec -= gapMin * 60;

        String strResult = "Pased Time from 2014.01.01 00:00:00\n";
        strResult += (gapDay + "Day " + gapHour + "Hour " + gapMin
                + "Min " + gapSec + "Sec");
        mTextResult.setText(strResult);
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.btnDateTime :
            onBtnDateTime();
            break;
        case R.id.btnPasedTime :
            onBtnPasedTime();
            break;
        }
    }

}
