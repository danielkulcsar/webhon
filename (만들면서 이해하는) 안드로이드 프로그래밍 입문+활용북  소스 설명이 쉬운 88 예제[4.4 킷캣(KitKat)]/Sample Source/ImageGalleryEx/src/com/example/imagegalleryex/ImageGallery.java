package com.example.imagegalleryex;

import java.util.*;

import android.content.*;
import android.util.*;
import android.view.*;
import android.view.View.OnTouchListener;
import android.widget.*;

public class ImageGallery extends RelativeLayout 
        implements OnTouchListener {
    Context mContext;
    ImageView mImageMain;
    LinearLayout mScrollLayout1;
    ArrayList<ImageView> arImageView = new ArrayList<ImageView>();
    ArrayList<Integer> arImageRes = new ArrayList<Integer>();

    public ImageGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.image_gallery, this, true);

        mImageMain = (ImageView)findViewById(R.id.imageMain);
        mScrollLayout1 = (LinearLayout)findViewById(R.id.scrollLayout1);
    }

    public void addImage(int imgId) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(imgId);
        mScrollLayout1.addView(imageView);

        ViewGroup.LayoutParams params =  imageView.getLayoutParams();
        params.width = 200;
        
        imageView.setOnTouchListener(this);

        arImageView.add(imageView);
        arImageRes.add(imgId);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if( event.getAction() == MotionEvent.ACTION_UP ) {
            for(int i=0; i < arImageView.size(); i++) {
                ImageView imageView = arImageView.get(i);
                if( v == imageView) {
                    int resId = arImageRes.get(i);
                    mImageMain.setImageResource( arImageRes.get(i) );
                    if( mItemSelectedListener != null )
                        mItemSelectedListener.onItemSelected(imageView, i, resId);
                }
            }
        }
        return true;
    }

    public interface ItemSelectedListener {
        void onItemSelected(View view, int position, int resId);
    }

    private ItemSelectedListener mItemSelectedListener = null;

    public void setItemSelectedListener(ItemSelectedListener listener) {
        this.mItemSelectedListener = listener;
    }

}
