package net.npaka.rpg;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

// 그래픽스
public class Graphics {
    private SurfaceHolder holder;
    private Paint         paint;
    private Canvas        canvas;

    // 그래픽스
    public Graphics(SurfaceHolder holder) {
        this.holder=holder;
        paint=new Paint();
        paint.setAntiAlias(true);
    }
    
    // 잠금
    public void lock() {
        canvas=holder.lockCanvas();
    }
    
    // 잠금해제
    public void unlock() {
        holder.unlockCanvasAndPost(canvas);
    }
    
    // 색 지정
    public void setColor(int color) {
        paint.setColor(color);
    }

    // 폰트 크기 지정
    public void setFontSize(int fontSize) {
        paint.setTextSize(fontSize);
    }
    
    // 문자폭 구하기
    public int stringWidth(String string) {
        return (int)paint.measureText(string);
    }

    // 사각형 그리기
    public void fillRect(int x,int y,int w,int h) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(new Rect(x,y,x+w,y+h),paint);
    }
    
    // 비트맵 그리기
    public void drawBitmap(Bitmap bitmap,int x,int y) {
        int w=bitmap.getWidth();
        int h=bitmap.getHeight();
        Rect src=new Rect(0,0,w,h);
        Rect dst=new Rect(x,y,x+w,y+h);
        canvas.drawBitmap(bitmap,src,dst,null);
    }
    
    // 문자열 그리기
    public void drawString(String string,int x,int y) {
        canvas.drawText(string,x,y,paint);
    }
}
