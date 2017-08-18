package com.example.fragmentex;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    boolean mMultiPanel = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View panelBody = findViewById(R.id.panelBody);
        if( panelBody != null && panelBody.getVisibility() == View.VISIBLE ) {
            mMultiPanel = true;
            showBodyPanel(0);
        }
    }

    public void showBodyPanel(int index) {
        String[] strPet = { "Dog", "Cat", "Cow" };
        String strParam = strPet[index];
    	
        if( mMultiPanel ) {
            FragmentManager fm = getFragmentManager();
            BodyFragment bf = (BodyFragment)fm.findFragmentById(R.id.panelBody);
            if( bf == null || bf.mPet != strParam ) {
                bf = BodyFragment.makeObj(strParam);
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.panelBody, bf).commit();
            }
        }
        else {
            Intent intent = new Intent(getApplicationContext(), SubActivity.class);
            intent.putExtra("pet", strParam);
            startActivity(intent);
        }
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.btnDog :
            showBodyPanel(0);
            break;
        case R.id.btnCat :
            showBodyPanel(1);
            break;
        case R.id.btnCow :
            showBodyPanel(2);
            break;
        }
    }

    public static class ListFragment extends Fragment {

        public View onCreateView(LayoutInflater inflater, ViewGroup container, 
                Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_list, container, false);
            return v;
        }
    }

    public static class BodyFragment extends Fragment {
        String mPet;
    	
        public static BodyFragment makeObj(String strPet) {
            BodyFragment bf = new BodyFragment();
            bf.mPet = strPet;
            return bf;
        }
    	
        public View onCreateView(LayoutInflater inflater, ViewGroup container, 
                Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_body, container, false);
            TextView textMsg = (TextView)v.findViewById(R.id.textMessage);
            textMsg.setText(mPet);
            return v;
        }
    }

}
