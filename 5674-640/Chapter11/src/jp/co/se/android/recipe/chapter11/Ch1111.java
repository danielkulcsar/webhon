package jp.co.se.android.recipe.chapter11;

import java.io.IOException;
import java.util.List;

import jp.co.se.android.recipe.utils.CameraUtil;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.Camera;
import android.hardware.Camera.Face;
import android.hardware.Camera.FaceDetectionListener;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Toast;

public class Ch1111 extends Activity {
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private Camera mCamera = null;
    private Size mPreviewSize;
    private List<Size> mSupportedPreviewSizes;
    private FaceMarkerView mFaceMarkerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 풀 스크린 표시로 변경
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.ch1107_main);

        // 서페이스 뷰에서 카메라를 이용할 수 있도록 설정
        mSurfaceView = (SurfaceView) findViewById(R.id.Preview);

     // 카메라 뷰 처리
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(new Callback() {
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                // 얼굴 인식 종료
                mCamera.stopFaceDetection();

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
                    // 이용 가능한 프리뷰 사이즈를 구한다
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
                        // 단말 화면 사이즈에 최적인 카메라를 미리 크기를 선택한다
                        mPreviewSize = CameraUtil.getOptimalPreviewSize(
                                mSupportedPreviewSizes, height, width);

                        // 카메라의 프리뷰 사이즈를 세팅
                        params.setPreviewSize(mPreviewSize.width,
                                mPreviewSize.height);
                        mCamera.setParameters(params);
                    }

                    // 프리뷰 시작
                    mCamera.startPreview();

                    // 카메라가 검출 가능한 얼굴의 수를 구하기
                    int maxFaces = params.getMaxNumDetectedFaces();

                    if (maxFaces > 0) {
                        //얼굴 인식을 검출하는 리스너를 설정
                        mCamera.setFaceDetectionListener(new FaceDetectionListener() {
                            @Override
                            public void onFaceDetection(Face[] faces,
                                    Camera camera) {
                                // 얼굴을 검출하면 얼굴 인식 표지 표시 뷰에 값을 전달
                                mFaceMarkerView.faces = faces;
                                mFaceMarkerView.invalidate();
                            }
                        });
                        Toast.makeText(
                                Ch1111.this,
                                getString(R.string.ch1111_camera_maxface,
                                        maxFaces), Toast.LENGTH_SHORT).show();
                    }

                    // 얼굴 인식 시작
                    mCamera.startFaceDetection();
                }
            }
        });

        // 얼굴 인식 마커를 표시하는 투명의 뷰를 레이아웃에 추가
        mFaceMarkerView = new FaceMarkerView(this);
        addContentView(mFaceMarkerView, new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

    }

    /**
     * 얼굴 인식 마커를 표시하는 뷰
     */
    @SuppressLint("DrawAllocation")
    private class FaceMarkerView extends View {
        Face[] faces;

        public FaceMarkerView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            // 오버레이 표시하기 때문에 배경을 투명하게 한다
            canvas.drawColor(Color.TRANSPARENT);

            // 마커의 색이나 테두리를 설정
            Paint paint = new Paint();
            paint.setColor(Color.CYAN);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);
            paint.setTextSize(30);

            // 얼굴이 검출되면 마커를 그린다
            if (faces != null) {
                for (int i = 0; i < faces.length; i++) {
                    // 변경 전 Canvas의 상태를 저장
                    int saveState = canvas.save();

                  //카메라 드라이버에서 취득하는 값은-1000~1000로 되어 있기 때문에, 
                  // Matrix을 사용하여 좌표로 변환할 수 있도록 한다
                    Matrix matrix = new Matrix();
                    matrix.postScale(getWidth() / 2000f, getHeight() / 2000f);
                    matrix.postTranslate(getWidth() / 2f, getHeight() / 2f);
                    canvas.concat(matrix);

                    // 검출한 얼굴을 중심으로 직사각형으로 인식 정밀도를 그리기
                    float x = (faces[i].rect.right + faces[i].rect.left) / 2;
                    float y = (faces[i].rect.top + faces[i].rect.bottom) / 2;
                    String score = String.valueOf(faces[i].score);
                    canvas.drawText(score, x, y, paint);
                    canvas.drawRect(faces[i].rect, paint);

                    // Canvas를 원래의 상태로 되돌린다
                    canvas.restoreToCount(saveState);
                }
            }
        }
    }
}
