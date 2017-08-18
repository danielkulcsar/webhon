package com.example.jsliderex;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity 
        implements JSlider.JSliderListener {
    TextView mTextMessage;
    JSlider mJSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mTextMessage = (TextView)this.findViewById(R.id.textMessage);
        mJSlider = (JSlider)this.findViewById(R.id.jSlider);
        mJSlider.setRange(-50, 50);
        mJSlider.setValue(30);
        // 커스텀 슬라이더 컨트롤에 스킨 이미지 적용
        mJSlider.setBackgroundBitmap(R.drawable.slider_back);
        mJSlider.setBarNormalBitmap(R.drawable.slider_bar_n);
        mJSlider.setBarPressedBitmap(R.drawable.slider_bar_p);
        mJSlider.setListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // 커스텀 슬라이더 컨트롤 드래그 이벤트 
    public void onMoved(int nValue) {
        String strText = String.format("Value : %d", nValue);
        mTextMessage.setText(strText);
    }

    // 커스텀 슬라이더 컨트롤 Touch Released 이벤트
    public void onReleased(int nValue) {
        String strText = String.format("Value : %d", nValue);
        mTextMessage.setText(strText);
    }

}
