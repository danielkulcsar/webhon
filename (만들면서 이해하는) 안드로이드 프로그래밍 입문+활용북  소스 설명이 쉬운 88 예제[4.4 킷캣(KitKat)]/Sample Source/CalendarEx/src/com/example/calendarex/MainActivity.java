package com.example.calendarex;

import java.util.*;

import android.app.*;
import android.os.*;
import android.util.*;
import android.widget.*;

public class MainActivity extends Activity {
    TextView mTextResult;
    TextView mTextCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextResult = (TextView)findViewById(R.id.textResult);
        mTextCalendar = (TextView)findViewById(R.id.textCalendar);

        String strResult = getDate();
        mTextResult.setText(strResult);
        strResult = getCalendarTable();
        mTextCalendar.setText(strResult);
    }

    public String getDate() {
        String strResult = "";
        Calendar calendar = Calendar.getInstance();
        //int maxDate = calendar.getMaximum(Calendar.DAY_OF_MONTH);
        //Log.d("tag", "Max data " + maxDate);

        int year = calendar.get(Calendar.YEAR);
        strResult += Integer.toString(year) + "/";
        int month = calendar.get(Calendar.MONTH);
        strResult += Integer.toString(month + 1) + "/";
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        strResult += Integer.toString(date) + " ";

        return strResult;
    }

    public String getCalendarTable() {
        String strItem = "", strTable = " Sun  Mon  Tue  Wed  Thu  Fri  Sat \n";
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        for(int i=1; i < dayOfWeek; i++)
            strTable += "*****";//"        ";
        //strTable += "  01";
        
        int maxDate = calendar.getActualMaximum(Calendar.DATE);

        for(int i=1; i <= maxDate; i++) {
            strItem = String.format("  %02d  ", i);
            strTable += strItem;

            dayOfWeek ++;
            if( dayOfWeek > Calendar.SATURDAY ) {
                dayOfWeek = Calendar.SUNDAY;
                strTable += "\n";
            }
        }

        return strTable;
    }

}
