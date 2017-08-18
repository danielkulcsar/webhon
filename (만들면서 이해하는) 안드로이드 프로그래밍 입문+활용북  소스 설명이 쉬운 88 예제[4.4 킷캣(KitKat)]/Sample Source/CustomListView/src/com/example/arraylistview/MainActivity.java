package com.example.arraylistview;

import java.util.*;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    ListView mListMember;
    TextView mTextMessage;
    //ArrayList<String> mArGeneral;
    ArrayList<MyItem> mArGeneral;

    public class MyItem {
        String mText1;
        String mText2;
        int mIcon;

        MyItem(String str1, int iconId, String str2) {
            mText1 = str1;
            mIcon = iconId;
            mText2 = str2;
        }
    }
    
    public class MyListAdapter extends BaseAdapter {
        Context mMaincon;
        LayoutInflater mInflater;
        ArrayList<MyItem> mArSrc;
        int layout;

        MyListAdapter(Context context, int alayout, ArrayList<MyItem> aarSrc) {
            mMaincon = context;
            mInflater = (LayoutInflater)context.getSystemService(
            	    Context.LAYOUT_INFLATER_SERVICE);
            mArSrc = aarSrc;
            layout = alayout;
        }

        public int getCount() {
            return mArSrc.size();
        }

        public String getItem(int position) {
            return mArSrc.get(position).mText1;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            final int pos = position;
            if (convertView == null) {
                convertView = mInflater.inflate(layout, parent, false);
            }

            TextView textView1 = (TextView)convertView.findViewById(R.id.text1);
            textView1.setText(mArSrc.get(position).mText1);
            ImageView img = (ImageView)convertView.findViewById(R.id.img);
            img.setImageResource(mArSrc.get(position).mIcon);
            TextView textView2 = (TextView)convertView.findViewById(R.id.text2);
            textView2.setText(mArSrc.get(position).mText1);

            return convertView;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView)findViewById(R.id.textMessage);
        initListView();
    }

    public void initListView() {
        mArGeneral = new ArrayList<MyItem>();
        MyItem mi;
        mi = new MyItem("New York", R.drawable.icon_alarm, "New York");
        mArGeneral.add(mi);
        mi = new MyItem("Pary", R.drawable.icon_call, "Pary");
        mArGeneral.add(mi);
        mi = new MyItem("Lundon", R.drawable.icon_home, "Lundon");
        mArGeneral.add(mi);
        mi = new MyItem("Seoul", R.drawable.icon_message, "Seoul");
        mArGeneral.add(mi);

        MyListAdapter MyAdapter = 
        	    new MyListAdapter(this, R.layout.custom_list_item, mArGeneral);
        mListMember = (ListView)findViewById(R.id.listMember);
        mListMember.setAdapter(MyAdapter);
        mListMember.setOnItemClickListener(mItemClickListener);
    }

    AdapterView.OnItemClickListener mItemClickListener =
    	    new AdapterView.OnItemClickListener() {

        public void onItemClick(AdapterView parent, View view, int position, long id) {
            MyItem myItem = mArGeneral.get(position);
            mTextMessage.setText("Select : " + position + " - " + myItem.mText1);
        }
    };

}
