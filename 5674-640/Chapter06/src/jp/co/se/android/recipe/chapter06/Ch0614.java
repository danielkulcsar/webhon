package jp.co.se.android.recipe.chapter06;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Ch0614 extends Activity implements OnClickListener {
    private static final int REQ_SELECT_GALLERY = 100;
    private static final int REFLECTIONGAP = 4;
    private static final int MAX_IMAGE_SIZE = 1024;

    private ImageView mImageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch0614_main);

        mImageView1 = (ImageView) findViewById(R.id.imageView1);

        findViewById(R.id.button1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button1) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, REQ_SELECT_GALLERY);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_SELECT_GALLERY && resultCode == RESULT_OK) {
            setMirrorBitmap(data.getData());
        }
    }

    private void setMirrorBitmap(Uri imageuri) {
        // Bitmap 이미지 읽기
        Bitmap bitmapOriginal = loadBitmap(imageuri);
        // 반전된 이미지를 작성하는 Matrix를 생성
        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);
        int height = bitmapOriginal.getHeight();
        int width = bitmapOriginal.getWidth();
        // 반전된 이미지를 사용한 Bitmap를 생성
        Bitmap reflectionImage = Bitmap.createBitmap(bitmapOriginal, 0,
                height / 2, width, height / 2, matrix, false);
        // 원본 이미지에 반전된 영상을 합성한 이미지를 사용한 Bitmap를 생성
        Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
                (height + height / 2), Config.ARGB_8888);

        // 최종적인 이미지를 사용해 Canvas를 생성
        Canvas canvas = new Canvas(bitmapWithReflection);
        // 원본 이미지 그리기
        canvas.drawBitmap(bitmapOriginal, 0, 0, null);
        // 원본 이미지 그리기
        Paint deafaultPaint = new Paint();
        //이음매를 눈에 띄지 않게 기본색을 그리기
        canvas.drawRect(0, height, width, height + REFLECTIONGAP, deafaultPaint);
        // 반전된 이미지를 그리기
        canvas.drawBitmap(reflectionImage, 0, height + REFLECTIONGAP, null);
        // 반전된 부분의 아래에 갈수록 하얗게 되도록 그라데이션을 설정
        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0,
                bitmapOriginal.getHeight(), 0, bitmapWithReflection.getHeight()
                        + REFLECTIONGAP, 0x70ffffff, 0x00ffffff, TileMode.CLAMP);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        // 그라데이션 그리기
        canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
                + REFLECTIONGAP, paint);

        mImageView1.setImageBitmap(bitmapWithReflection);
    }

    private Bitmap loadBitmap(Uri imageuri) {
        InputStream input = null;
        try {

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;

            input = getContentResolver().openInputStream(imageuri);
            BitmapFactory.decodeStream(input, null, options);
            int inSampleSize = calculateInSampleSize(options, MAX_IMAGE_SIZE,
                    MAX_IMAGE_SIZE);
            input.close();
            input = null;

            options.inJustDecodeBounds = false;
            options.inSampleSize = inSampleSize;

            input = getContentResolver().openInputStream(imageuri);
            Bitmap bitmap = BitmapFactory.decodeStream(input, null, options);

            return bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    private int calculateInSampleSize(BitmapFactory.Options options,
            int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }

}
