package com.example.imageviewex;

import android.app.*;
import android.content.res.*;
import android.graphics.drawable.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView)findViewById(R.id.imageView1);
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.button1 : {
            Resources res = getResources();
            BitmapDrawable bitmap = (BitmapDrawable) res.getDrawable(R.drawable.stamp2);
            mImageView.setImageDrawable(bitmap);

            int bitmapWidth = bitmap.getIntrinsicWidth();
            int bitmapHeight = bitmap.getIntrinsicHeight();
            mImageView.getLayoutParams().width = bitmapWidth * 3;
            mImageView.getLayoutParams().height = bitmapHeight * 3;
        }
        break;
        }
    }

}
