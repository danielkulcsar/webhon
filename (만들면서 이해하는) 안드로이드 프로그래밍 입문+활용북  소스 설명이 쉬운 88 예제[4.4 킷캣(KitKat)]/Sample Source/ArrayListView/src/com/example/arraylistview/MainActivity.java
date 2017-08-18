package com.example.arraylistview;

import java.util.*;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    ListView mListMember;
    TextView mTextMessage;
    ArrayList<String> mArGeneral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView)findViewById(R.id.textMessage);
        initListView();
    }

    public void initListView() {
        String[] strTextList = {"Seoul", "Tokyo", "Newyork", "Londeon",
        	    "Baijing", "Kongga", "Moscuba", "Singgapol", "Pusan", "Hongkong"};
        mArGeneral = new ArrayList<String>();
        for(int i=0 ; i < 10 ; i ++)
            mArGeneral.add(strTextList[i]);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        	    android.R.layout.simple_list_item_1, mArGeneral);

        mListMember = (ListView)findViewById(R.id.listMember);
        mListMember.setAdapter(adapter);
        mListMember.setOnItemClickListener(mItemClickListener);
    }

    AdapterView.OnItemClickListener mItemClickListener =
    	    new AdapterView.OnItemClickListener() {

        public void onItemClick(AdapterView parent, View view, int position, long id) {
            String strItem = mArGeneral.get(position);
            mTextMessage.setText("Select : " + position + " - " + strItem);
        }
    };

}
