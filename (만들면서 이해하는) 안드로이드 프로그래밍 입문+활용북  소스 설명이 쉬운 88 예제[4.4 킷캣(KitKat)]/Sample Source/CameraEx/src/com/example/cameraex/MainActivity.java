package com.example.cameraex;

import java.io.*;
import java.util.*;

import android.app.*;
import android.graphics.*;
import android.hardware.*;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Size;
import android.hardware.Camera;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity
        implements SurfaceHolder.Callback 
        , AutoFocusCallback, PictureCallback {
    SurfaceHolder mSh;
    Camera mCamera = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        SurfaceView sv = (SurfaceView)findViewById(R.id.svPreView);
        mSh = sv.getHolder();
        mSh.addCallback(this);
        //mSh.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        mCamera = Camera.open();
        try {
            mCamera.setPreviewDisplay(mSh);
            mCamera.setDisplayOrientation(0);
        } catch (IOException e) {
            mCamera.release();
            mCamera = null;
            return;
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    	Camera.Parameters params = mCamera.getParameters();
        List<Size> arSize = params.getSupportedPreviewSizes();

        Size match = null;
        for( Size sPrev : arSize ) {
            if( match == null ) {
                match = sPrev;
                continue;
            }

            if( Math.abs(sPrev.height - height) < Math.abs(match.height - height) )
                match = sPrev;
        }

        params.setPreviewSize(match.width, match.height);
        mCamera.setParameters(params);
        mCamera.startPreview();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        if( mCamera != null ) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.btnShutter :
            mCamera.autoFocus(this);
            break;
        }
    }

    public void onAutoFocus(boolean success, Camera camera) {
        mCamera.takePicture(null, null, this);
    }

    public void onPictureTaken(byte[] data, Camera camera) {
        String sdRootPath = 
                Environment.getExternalStorageDirectory().getAbsolutePath();
        String filePath = sdRootPath + "/camerashot.jpg";

        File file = new File(filePath);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            Log.d("tag", "File Write Error");
            return;
        }

        Bitmap bmp = BitmapFactory.decodeFile(filePath);
        ImageView imageCamera = (ImageView)findViewById(R.id.imageCamera);
        imageCamera.setImageBitmap(bmp);

        mCamera.startPreview();
    }

}
