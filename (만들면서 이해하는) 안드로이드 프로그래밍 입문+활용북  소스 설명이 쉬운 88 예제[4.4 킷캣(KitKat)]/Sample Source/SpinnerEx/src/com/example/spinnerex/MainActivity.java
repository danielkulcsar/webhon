package com.example.spinnerex;

import java.util.*;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity 
        implements AdapterView.OnItemSelectedListener {
    Spinner mSpinner1;
    TextView mTextMessage;
    ArrayList<String> mArGeneral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView)findViewById(R.id.textMessage);
        initSpinner();
        mSpinner1.setOnItemSelectedListener(this);
    }
    
    public void initSpinner() {
        String[] strTextList = {"Seoul", "Tokyo", "Newyork", "Londeon", "Baijing", 
                "Kongga", "Moscuba", "Singgapol", "Pusan", "Hongkong"};
        mArGeneral = new ArrayList<String>();
        for(int i=0 ; i < 10 ; i ++)
            mArGeneral.add(strTextList[i]);

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mArGeneral);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner1 = (Spinner)findViewById(R.id.spinner1);
        mSpinner1.setAdapter(adapter);
    }

    //AdapterView.OnItemSelectedListener mItemSelectedListener =
    //	    new AdapterView.OnItemSelectedListener() {
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String strItem = mArGeneral.get(position);
        mTextMessage.setText(strItem);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        mTextMessage.setText("No Item Selected.");
    }
    //};

}
