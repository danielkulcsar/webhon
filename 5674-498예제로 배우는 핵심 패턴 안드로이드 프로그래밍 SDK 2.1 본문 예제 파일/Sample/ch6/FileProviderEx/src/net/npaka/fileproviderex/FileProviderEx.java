package net.npaka.fileproviderex;
import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.webkit.*;
import java.io.*;

//파일을 제공하는 컨텐츠 프로바이더의 이용
public class FileProviderEx extends Activity {
	
	//초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//Raw 리소스 파일의 저장 （1）
		try {
			raw2file(this,R.raw.index,"index.html");
			raw2file(this,R.raw.image,"image.jpg");
		} catch (Exception e) {
		}
		
		//웹 뷰의 생성
		WebView webView=new WebView(this);
		setContentView(webView);
		
		//컨텐츠 프로바이더가 제공하는 파일의 액세스 （2）
		webView.loadUrl("content://net.npaka.fileprovider/index.html");
	}
	
	//Raw 리소스 파일의 저장 （1）
	private void raw2file(Context context,
		int resID,String fileName) throws Exception {
		InputStream in=null;
		String path=context.getFilesDir().getAbsolutePath()+"/"+fileName;
		if (!(new File(path)).exists()) {
			in=context.getResources().openRawResource(resID);
			in2file(context,in,fileName);
		}
	}
	
	//입력 스트림 파일의 저장
	private void in2file(Context context,
		InputStream in,String fileName)
		throws Exception {
		int size;
		byte[] w=new byte[1024];
		OutputStream out=null;
		try {
			out=context.openFileOutput(fileName,
				Context.MODE_WORLD_READABLE);
			while (true) {
				size=in.read(w);
				if (size<=0) break;
				out.write(w,0,size);
			};
			out.close();
			in.close();
		} catch (Exception e) {
			try {
				if (in !=null) in.close();
				if (out!=null) out.close();
			} catch (Exception e2) {
			}
			throw e;
		}
	}
}