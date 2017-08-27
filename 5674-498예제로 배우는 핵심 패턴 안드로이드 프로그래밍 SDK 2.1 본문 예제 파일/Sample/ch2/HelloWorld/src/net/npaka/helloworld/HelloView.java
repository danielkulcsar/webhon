package net.npaka.helloworld;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

//HelloWorld
public class HelloView extends View {//(7)
    //コンストラクタ
    public HelloView(Context context) {
        super(context);
        setBackgroundColor(Color.WHITE);//(8)
    }

    //描画
    @Override 
    protected void onDraw(Canvas canvas) {
        Paint paint=new Paint();//(9)       
        canvas.drawText("Hello World!",0,12,paint);//(10)
    }
}