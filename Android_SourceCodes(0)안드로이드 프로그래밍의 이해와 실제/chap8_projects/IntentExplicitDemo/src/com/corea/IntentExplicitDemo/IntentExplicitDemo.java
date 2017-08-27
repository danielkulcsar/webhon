package com.corea.IntentExplicitDemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class IntentExplicitDemo extends Activity implements OnClickListener{
    
    static final int[] BUTTONS = {
        R.id.start_activity,
        R.id.start_activity_for_result
    };
	 
    private static final String TAG = "IntentActivity";
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        for(int buttonId: BUTTONS) {
            Button button = (Button)findViewById(buttonId);
            button.setOnClickListener(this);
        }
    }
    
    public void onClick(View v) {
        int id = v.getId();
        switch(id){
        case R.id.start_activity: 
            initiateActivity();
            break;
        case R.id.start_activity_for_result: 
        	initiateActivityForResult();
            break;
        default:
        	Log.e(TAG, "Unkowon Button:"+v.toString());
        	break;
        }
    }

    private void initiateActivity() {
        Intent intent = new Intent(getApplicationContext(),
                                   AnotherActivity.class);
        startActivity(intent);	
    }
    
   // Activity�� ����䱸 �ڵ�
    private static final int ANOTHER_ACTIVITY = 1;

    // Activity�� �����ϴ�
    private void initiateActivityForResult() {
        Intent intent = new Intent(getApplicationContext(),
                                   AnotherActivity.class);
        //�� ��° �������ڿ� ����䱸 �ڵ带 �����ϴ�
        startActivityForResult(intent, ANOTHER_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int reqCode,
                                    int resCode,
                                    Intent resIntent) {
        //�ǵ��� ��  ����䱸 �ڵ忡�� ��� Activity�� �����ߴ����� �Ǵ��ϴ�
        if (reqCode == ANOTHER_ACTIVITY) {
            TextView textView = (TextView)findViewById(R.id.result);
            if (resCode != RESULT_OK) {
            	 //����� OK�� �ƴ϶�� ������ ǥ��
                textView.setText("Error.");
            } else {
            	//����Ʈ�� ������ �ؽ�Ʈ�� ȣ��
                String result = resIntent.getExtras().getString(
                		AnotherActivity.TEXT_RESULT);
                //�ؽ�Ʈ�� �ؽ�Ʈ ��� ǥ��
                textView.setText(result);
            }
        } else {
            super.onActivityResult(reqCode, resCode, resIntent);
        }
    }
}