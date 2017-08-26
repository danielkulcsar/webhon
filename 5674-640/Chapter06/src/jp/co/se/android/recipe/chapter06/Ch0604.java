package jp.co.se.android.recipe.chapter06;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.view.View;

public class Ch0604 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }

    class MyView extends View {
        Paint mPaintStd = new Paint();
        Paint mPaintFill = new Paint();

        public MyView(Context context) {
            super(context);
            // 채움 없이 Paint 정의
            mPaintStd = new Paint();
            mPaintStd.setColor(Color.RED);
            mPaintStd.setStyle(Style.STROKE);
            mPaintStd.setStrokeWidth(5);

            // 채움 없이 Paint 정의
            mPaintFill = new Paint();
            mPaintFill.setColor(Color.RED);
            mPaintFill.setStyle(Style.FILL);
        }

        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);

            // 채움 없이 원을 그리기
            canvas.drawCircle(200, 200, 100, mPaintStd);
            // 채운 원을 그리기
            canvas.drawCircle(200, 500, 100, mPaintFill);
        }
    }

}
