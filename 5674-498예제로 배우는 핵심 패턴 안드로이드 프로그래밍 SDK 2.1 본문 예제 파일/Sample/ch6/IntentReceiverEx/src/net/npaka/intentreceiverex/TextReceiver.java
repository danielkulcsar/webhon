package net.npaka.intentreceiverex;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

//텍스트 리시버
public class TextReceiver extends BroadcastReceiver {// （2）
	//인텐트 수신 （2）
	@Override
		public void onReceive(Context context,Intent intent) {
		//파라미터 구하기
		Bundle bundle=intent.getExtras();
		String text=bundle.getString("TEXT");
		
		//토스트 표시
		showToast(context,text);
	}
	
	//토스트 표시 （3）
	private static void showToast(Context context,String text) {
		Toast toast=Toast.makeText(context,text,Toast.LENGTH_SHORT);
		toast.show();
	}
}