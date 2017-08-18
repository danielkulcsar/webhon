package com.example.imagedownload;

import java.io.*;
import java.net.*;

import android.app.*;
import android.graphics.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    ImageView mImageView;
    Bitmap mBmp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView)findViewById(R.id.imageView);
    }

    public boolean loadWebImage(String strUrl) {
        try {
            InputStream is = new URL(strUrl).openStream();
            mBmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch(Exception e) {
            Log.d("tag", "Image Stream error.");
            return false;
        }
        return true;
    }

    public void onBtnLoad1() {
        String addr = 
                "http://bimage.interpark.com/goods_image/4/1/2/7/211714127g.jpg";
        new HttpReqTask().execute(addr);
        //if( loadWebImage(addr) )
        //    mImageView.setImageBitmap(mBmp);
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.btnLoad1 :
            onBtnLoad1();
            break;
        case R.id.btnLoad2 :
            onBtnParse2();
            break;
        }
    }

    private class HttpReqTask extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg) {
            boolean result = false;
            if( arg.length == 1 )
                result = loadWebImage(arg[0]);
            else {
                result = downloadFile(arg[0], arg[1]);
                if( result ) {
                    String sdRootPath = 
                            Environment.getDataDirectory().getAbsolutePath();
                    String filePath = sdRootPath + 
                            "/data/com.example.imagedownload/files/" + arg[1];
                    mBmp = BitmapFactory.decodeFile(filePath);
                }
            }

            if( result )
                return "True";
            return "";
        }

        protected void onPostExecute(String result) {
            if( result.length() > 0 )
                mImageView.setImageBitmap(mBmp);
        }
    }

    boolean downloadFile(String strUrl, String fileName) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream is = conn.getInputStream();
            FileOutputStream fos = openFileOutput(fileName, 0);

            byte[] buf = new byte[1024];
            int count;
            while( (count = is.read(buf)) > 0 ) {
                fos.write(buf, 0, count);
            }
            conn.disconnect();
            fos.close();
        } catch (Exception e) {
            Log.d("tag", "Image download error.");
            return false;
        }
        return true;
    }

    public void onBtnParse2() {
        String addr = 
                "http://bimage.interpark.com/goods_image/9/5/2/9/209329529g.jpg";
        String fileName = "download.jpg";
        new HttpReqTask().execute(addr, fileName);
    }

}
