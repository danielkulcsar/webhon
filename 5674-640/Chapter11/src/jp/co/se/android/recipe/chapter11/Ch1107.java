package jp.co.se.android.recipe.chapter11;

import java.io.IOException;
import java.util.List;

import jp.co.se.android.recipe.utils.CameraUtil;
import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.Window;

public class Ch1107 extends Activity {
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private Camera mCamera = null;
    private Size mPreviewSize;
    private List<Size> mSupportedPreviewSizes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 풀 스크린 표시로 변경
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.ch1107_main);

        // 서페이스 뷰에서 카메라를 이용할 수 있도록 설정
        mSurfaceView = (SurfaceView) findViewById(R.id.Preview);

        // 카메라 프리뷰 처리
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

                        // 카메라의 프리뷰 크기를 설정
                        params.setPreviewSize(mPreviewSize.width,
                                mPreviewSize.height);
                        mCamera.setParameters(params);
                    }

                    // 프리뷰 시작
                    mCamera.startPreview();
                }
            }
        });
    }
}
