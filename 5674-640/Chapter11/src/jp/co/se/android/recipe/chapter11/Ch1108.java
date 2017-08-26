package jp.co.se.android.recipe.chapter11;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import jp.co.se.android.recipe.utils.CameraUtil;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;

public class Ch1108 extends Activity {
    private static final String SAVE_PATH = "/AndroidRecipe";
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private Camera mCamera = null;
    private Size mPreviewSize;
    private List<Size> mSupportedPreviewSizes;
    private boolean mIsSave = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 풀스크린 표시 변경
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.ch1107_main);

        // 서페이스 뷰에서 카메라를 이용할 수 있도록 설정
        mSurfaceView = (SurfaceView) findViewById(R.id.Preview);

        // 카메라 뷰 처리
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(new Callback() {
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                // 카메라 종료 처리
                mCamera.stopPreview();
                mCamera.release();
                mCamera = null;
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                // 카메라 초기화 처리
                mCamera = Camera.open();
                if (mCamera != null) {
                    try {
                        mCamera.setPreviewDisplay(mSurfaceHolder);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e("Chapter833", e.toString(), e);
                    }
                    // 이용 가능한 프리뷰 크기를 구하기
                    mSupportedPreviewSizes = mCamera.getParameters()
                            .getSupportedPreviewSizes();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                    int width, int height) {
                if (mCamera != null) {
                    Parameters params = mCamera.getParameters();
                    if (mSupportedPreviewSizes != null) {
                        // 단말 화면 크기에 최적인 카메라를 미리 크기를 선택한다
                        mPreviewSize = CameraUtil.getOptimalPreviewSize(
                                mSupportedPreviewSizes, height, width);

                        // 카메라의 프리뷰 사이즈를 설정
                        params.setPreviewSize(mPreviewSize.width,
                                mPreviewSize.height);

                        // 카메라의 촬영 사이즈를 설정
                        Size pictureSize = CameraUtil
                                .getSupportPictureSize(mCamera);
                        if (pictureSize != null) {
                            params.setPictureSize(pictureSize.width,
                                    pictureSize.height);
                        }
                        mCamera.setParameters(params);
                    }

                    // 프리뷰 시작
                    mCamera.startPreview();
                }
            }
        });

        // 카메라 프리뷰를 터치한 때
        mSurfaceView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (mCamera != null) {
                        // 앞 회에 촬영한 이미지를 저장 중인지 확인
                        if (!mIsSave) {
                            // 사진을 촬영
                            mCamera.takePicture(null, null, mPictureCallBack);
                            mIsSave = true;
                        }
                    }
                }
                return true;
            }
        });
    }

    /**
     * 촬영한 JPEG 이미지 데이터 생성이 완료했을 때 호출되는 콜백
     */
    @SuppressLint("SimpleDateFormat")
    private Camera.PictureCallback mPictureCallBack = new Camera.PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {
            // 데이터가 없는 경우는 처리하지 않는다
            if (data == null) {
                return;
            }

            // 저장처 설정
            String savePath = Environment.getExternalStorageDirectory()
                    .getPath() + SAVE_PATH;
            File file = new File(savePath);
            if (!file.exists()) {
                file.mkdir();
            }
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String imgPath = savePath + "/" + "IMG_"
                    + sdFormat.format(cal.getTime()) + ".jpg";

            // 생성한 이미지 데이터를 저장
            try {
                FileOutputStream fos = new FileOutputStream(imgPath, true);
                fos.write(data);
                fos.close();

                // 컨텐츠 프로바이더를 갱신
                ContentValues values = new ContentValues();
                values.put(Images.Media.MIME_TYPE, "image/jpeg");
                values.put("_data", imgPath);
                getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            } catch (Exception e) {
            }

            // 카메라 프리뷰를  시작
            mIsSave = false;
            mCamera.startPreview();
        }
    };
}
