package net.npaka.texttospeechex;
import java.util.Locale;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

// 음성 합성
public class TextToSpeechEx extends Activity
implements View.OnClickListener,TextToSpeech.OnInitListener {
	private EditText editText; // 에디트 텍스트
	private Button button; // 버튼
	private TextToSpeech tts; // TTS
	
	// 어플리케이션의 초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		// 레이아웃의 생성
		LinearLayout layout=new LinearLayout(this);
		layout.setBackgroundColor(Color.rgb(255,255,255));
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		
		// 에디트 텍스트의 생성
		editText=new EditText(this);
		editText.setText("This is TEST!",
			EditText.BufferType.NORMAL);
		setLLParams(editText,
			LinearLayout.LayoutParams.FILL_PARENT,
			LinearLayout.LayoutParams.WRAP_CONTENT);
		layout.addView(editText);
		
		// 버튼의 생성
		button=new Button(this);
		button.setText("음성 합성");
		button.setOnClickListener(this);
		setLLParams(button);
		layout.addView(button);
		
		// 음성 합성의 준비 (1)
		tts=new TextToSpeech(this,this);
	}
	
	// 음성 합성의 준비 완료 시 불린다. (1)
	public void onInit(int status) {
		if (status==TextToSpeech.SUCCESS) {
			// 음성 합성의 로케일 지정 (2)
			Locale locale=Locale.ENGLISH;
			if (tts.isLanguageAvailable(locale)>=
				TextToSpeech.LANG_AVAILABLE) {
				tts.setLanguage(locale);
			} else {
				Toast.makeText(TextToSpeechEx.this,
					"Unsupported Language",Toast.LENGTH_LONG).show();
			}
		} else if (status==TextToSpeech.ERROR) {
			Toast.makeText(TextToSpeechEx.this,
				"TextToSpeech.ERROR",Toast.LENGTH_LONG).show();
		}
	}
	
	// 어플리케이션 종료 시 불린다.
	protected void onDestroy() {
		super.onDestroy();
		// 음성 합성의 종료 (4)
		if (tts!=null) tts.shutdown();
	}
	
	// 버튼 클릭 이벤트의 처리 (3)
	public void onClick(View v) {
		String str=editText.getText().toString();
		if (str.length()>0) {
			// 스피치의 정지
			if (tts.isSpeaking()) tts.stop();
			// 스피치의 재생
			tts.setSpeechRate(1.0f);
			tts.speak(str,TextToSpeech.QUEUE_FLUSH,null);
		}
	}
	
	// 리니어 레이아웃의 파라미터 지정
	private static void setLLParams(View view) {
		view.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.WRAP_CONTENT,
			LinearLayout.LayoutParams.WRAP_CONTENT));
	}
	
	// 리니어 레이아웃의 파라미터 지정
	private static void setLLParams(View view,int w,int h) {
		view.setLayoutParams(new LinearLayout.LayoutParams(w,h));
	}
}