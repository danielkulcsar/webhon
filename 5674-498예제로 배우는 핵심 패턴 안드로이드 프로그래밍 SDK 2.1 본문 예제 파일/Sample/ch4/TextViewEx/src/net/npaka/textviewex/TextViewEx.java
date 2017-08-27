package net.npaka.textviewex;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;

//텍스트 뷰와 이미지 뷰
public class TextViewEx extends Activity {
	//초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//레이아웃 생성 (1）
		LinearLayout layout=new LinearLayout(this);
		layout.setBackgroundColor(Color.rgb(255,255,255));
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		
		//텍스트 뷰 생성 (2）
		TextView textView=new TextView(this);
		textView.setText("이것은 텍스트입니다");
		textView.setTextSize(16.0f);
		textView.setTextColor(Color.rgb(0,0,0));
		
		//컴포넌트 크기 지정 (3）
		setLLParams(textView);
		
		//레이아웃 컴포넌트 추가 (4）
		layout.addView(textView);
		
		//그림 읽어 들이기
		Bitmap bitmap=BitmapFactory.decodeResource(
			getResources(),R.drawable.sample);
		
		//이미지 뷰 생성 (5）
		ImageView imageView=new ImageView(this);
		imageView.setImageBitmap(bitmap);
		setLLParams(imageView);
		layout.addView(imageView);
	}
	
	//리니어 레이아웃의 파라미터 지정
	private static void setLLParams(View view) {
		view.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.WRAP_CONTENT,
			LinearLayout.LayoutParams.WRAP_CONTENT));
	}
}