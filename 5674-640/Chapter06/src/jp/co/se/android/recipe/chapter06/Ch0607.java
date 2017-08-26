package jp.co.se.android.recipe.chapter06;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class Ch0607 extends Activity {
    private static final String TEXT_MESSAGE = "소크라테스는 고대 그리스의 철학자이다. 기원전 469년 고대 그리스 아테네에서 태어나 일생을 철학의 제 문제에 관한 토론으로 일관한 서양 철학의 위대한 인물로 평가되고 있다.";
    private static final int TEXT_SIZE = 38;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }

    class MyView extends View {
        Paint mPaint;

        public MyView(Context context) {
            super(context);
            mPaint = new Paint();
            mPaint.setColor(Color.RED);
            mPaint.setTextSize(TEXT_SIZE);
        }

        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);

            // 1행 표시
            canvas.drawText(TEXT_MESSAGE, 0, 100, mPaint);

            int maxWidth = canvas.getWidth();
            int lineBreakPoint = Integer.MAX_VALUE;
            int currentIndex = 0;
            int linePointY = 200;

            // 개행 표시
            while (lineBreakPoint != 0) {
                String mesureString = TEXT_MESSAGE.substring(currentIndex);
                lineBreakPoint = mPaint.breakText(mesureString, true, maxWidth,
                        null);
                if (lineBreakPoint != 0) {
                    String line = TEXT_MESSAGE.substring(currentIndex,
                            currentIndex + lineBreakPoint);
                    canvas.drawText(line, 0, linePointY, mPaint);
                    linePointY += TEXT_SIZE;
                    currentIndex += lineBreakPoint;
                }
            }
        }

    }
}
