package com.example.imagegalleryex;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity 
        implements ImageGallery.ItemSelectedListener { 
    TextView mTextMessage;
    ImageGallery mComplexGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView)findViewById(R.id.textMessage);

        mComplexGallery = (ImageGallery)findViewById(R.id.imageGallery1);
        mComplexGallery.addImage(R.drawable.puzzle01);
        mComplexGallery.addImage(R.drawable.puzzle02);
        mComplexGallery.addImage(R.drawable.puzzle03);
        mComplexGallery.addImage(R.drawable.puzzle08);
        mComplexGallery.setItemSelectedListener(this);
    }

    public void onItemSelected(View view, int position, int resId) {
        mTextMessage.setText("Image - " + position);
    }

}
