package com.example.changetextcolor;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    MainView mView;
    String mCaptionText;
    int mIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mView = new MainView(this);
        FrameLayout frame = (FrameLayout)findViewById(R.id.mainLayout);
        frame.addView(mView, 0);
        mCaptionText = "가을엔 편지를 하겠어요 누구라도 그러하듯이";
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.buttonStart :
            mIndex = 0;
            mTimer.sendEmptyMessage(0);
            break;
        }
    }

    Handler mTimer = new Handler() {
        public void handleMessage(Message msg) {
            mIndex ++;
            mView.invalidate();
            if (mIndex < mCaptionText.length())
                mTimer.sendEmptyMessageDelayed(0, 1000);
        }
    };

    protected class MainView extends View {
        public MainView(Context context) {
            super(context);
            }

        public void onDraw(Canvas canvas) {
            canvas.drawColor(Color.LTGRAY);
            Paint pnt = new Paint();
            pnt.setAntiAlias(true);
            pnt.setTextSize(23);
            pnt.setColor(Color.BLUE);
            int nX = 10;
            int nY = 60;
            canvas.drawText(mCaptionText, nX, nY, pnt);

            pnt.setColor(Color.MAGENTA);
            String strPassed = mCaptionText.substring(0, mIndex);
            canvas.drawText(strPassed, nX, nY, pnt);
        }
    }

}
