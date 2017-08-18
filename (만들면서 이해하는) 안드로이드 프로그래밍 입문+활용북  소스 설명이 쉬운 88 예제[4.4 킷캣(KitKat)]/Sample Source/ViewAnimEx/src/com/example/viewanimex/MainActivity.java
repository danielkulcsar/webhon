package com.example.viewanimex;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;

public class MainActivity extends Activity {
    TextView mTextView1;
    Animation mAnimXml;
    ImageView mImageHeart;
    Animation mAnimTranslate;
    Animation mAnimScale;
    Animation mAniRotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mTextView1 = (TextView)findViewById(R.id.textView1);
        mAnimXml = AnimationUtils.loadAnimation(this, R.anim.move_alpha);

        mImageHeart = (ImageView)findViewById(R.id.imageHeart);
        mAnimTranslate = new TranslateAnimation(0, 0, 0, 300);
        mAnimTranslate.setDuration(1000);
        mAnimTranslate.setRepeatCount(1);
        mAnimTranslate.setRepeatMode( Animation.REVERSE );
        mAnimTranslate.setAnimationListener(mAniLis);

        mAnimScale = new ScaleAnimation(1, 2, 1, 2, 
        	    Animation.RELATIVE_TO_SELF, 0.5f,
        	    Animation.RELATIVE_TO_SELF, 0.5f);
        mAnimScale.setDuration(1000);
        mAnimScale.setAnimationListener(mAniLis);
 
        mAniRotate = new RotateAnimation(0, 360, 
        	    Animation.RELATIVE_TO_SELF, 0.5f,
        	    Animation.RELATIVE_TO_SELF, 0.5f);
        mAniRotate.setDuration(1000);
        mAniRotate.setAnimationListener(mAniLis);
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.btnAnimXml :
            mTextView1.startAnimation(mAnimXml);
            break;
        case R.id.btnAnimApi :
            mImageHeart.startAnimation(mAnimTranslate);
            break;
        }
    }

    public Animation.AnimationListener mAniLis 
            = new Animation.AnimationListener() {
        public void onAnimationEnd(Animation animation) {
            if( animation == mAnimTranslate )
                mImageHeart.startAnimation(mAnimScale);
            else if( animation == mAnimScale )
                mImageHeart.startAnimation(mAniRotate);
        }
        public void onAnimationRepeat(Animation animation) {
        }
        public void onAnimationStart(Animation animation) {
        }
    };

}
