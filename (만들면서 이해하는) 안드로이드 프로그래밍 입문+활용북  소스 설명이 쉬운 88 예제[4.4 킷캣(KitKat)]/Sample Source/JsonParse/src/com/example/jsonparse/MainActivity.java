package com.example.jsonparse;

import org.json.*;

import android.app.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    TextView mTextSource;
    TextView mTextMessage;
    String mStrJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextSource = (TextView)findViewById(R.id.textSource);
        mTextMessage = (TextView)findViewById(R.id.textMessage);

        mStrJson = "[{name:Obama, math:50, "
                + "phone: {mobile:111-1111, home:222-2222}},\n"
                + "{name:Psy, math:70, "
                + "phone: {mobile:333-3333, home:444-4444}},\n"
                + "{name:Yuna, math:65, "
                + "phone: {mobile:555-5555, home:666-6666}}]";
         mTextSource.setText(mStrJson);
     }
    
    public void onBtnParse1() {
        String strJson = "[11, 22, 33, 44, 55]";
        String strData = "Score:";
        try {
            JSONArray jAr = new JSONArray(strJson);
            for(int i=0; i < jAr.length(); i++) {
                strData += " - " + jAr.getInt(i);
            }
        } catch (JSONException e) {
            Log.d("tag", "Parse Error");
        }
        mTextMessage.setText(strData);
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.btnParse1 :
            onBtnParse1();
            break;
        case R.id.btnParse2 :
            onBtnParse2();
            break;
        case R.id.btnParse3 :
            onBtnParse3();
            break;
        }
    }

    public void onBtnParse2() {
        String strData = "";
        try {
            JSONArray jAr = new JSONArray(mStrJson);
            for(int i=0; i < jAr.length(); i++) {
                JSONObject student = jAr.getJSONObject(i);
                strData += student.getString("name") + " - " 
                        + student.getInt("math") + "\n";
            }
        } catch (JSONException e) {
            Log.d("tag", "Parse Error");
        }
        mTextMessage.setText(strData);
    }
    
    public void onBtnParse3() {
        String strData = "";
        try {
            JSONArray jAr = new JSONArray(mStrJson);
            for(int i=0; i < jAr.length(); i++) {
                JSONObject student = jAr.getJSONObject(i);
                JSONObject phone = student.getJSONObject("phone");
                strData += student.getString("name") + " - " +
                        phone.getString("mobile") + " - " + phone.getString("home") + "\n";
            }
        } catch (JSONException e) {
            Log.d("tag", "Parse Error");
        }
        mTextMessage.setText(strData);
    }

}
