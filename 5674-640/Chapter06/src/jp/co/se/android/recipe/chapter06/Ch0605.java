package jp.co.se.android.recipe.chapter06;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

public class Ch0605 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }

    class MyView extends View {
        Paint mPaintStd = new Paint();
        Paint mPaintFill = new Paint();
        Rect mRectStd = new Rect();
        Rect mRectFill = new Rect();

        public MyView(Context context) {
            super(context);
            // 채움 없는 Paint 정의
            mPaintStd = new Paint();
            mPaintStd.setColor(Color.RED);
            mPaintStd.setStyle(Style.STROKE);
            mPaintStd.setStrokeWidth(5);
            // 채움 없는 Paint 정의
            mPaintFill = new Paint();
            mPaintFill.setColor(Color.RED);
            mPaintFill.setStyle(Style.FILL);

            // 채움 없는 직사각형 정의
            mRectStd.set(100, 100, 300, 300);
            // 채운 직사각형 정의
            mRectFill.set(100, 500, 300, 700);
        }

        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);

            // 채움 없는 직사각형 그리기
            canvas.drawRect(mRectStd, mPaintStd);
            // 채운 직사각형 그리기
            canvas.drawRect(mRectFill, mPaintFill);
        }
    }

}
