package com.corea.ServiceDemo;

import com.corea.ServiceDemo.R;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//������ ������ �䱸�ϴ� Activity
public class ServiceDemo extends Activity {

    ComponentName mService;  //�⵿�� ������ �̸�
    TextView mTextView;      //������  ���¸� ǥ���Ѵ�
    /*
     *������ ���۹�ư�� �����ư�� ����Ѵ�
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mTextView = (TextView)findViewById(R.id.text_view_id);
        Button start = (Button)findViewById(R.id.start_button_id);
        start.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                initiateService();
            }});
        Button stop = (Button)findViewById(R.id.stop_button_id);
        stop.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                terminateService();
            }});
    }

    /*
     * ���� ���ø����̼� ���� HelloService�� �����Ѵ�
     * startService������ �⵿��   ������ �̸��� ��ȯ�ȴ�
     */
    private void initiateService() {
        mService = 
            startService(new Intent(this, MyService.class));
        //mTextView.append(mService.toShortString()+" started.\n");
        mTextView.append("MyService started.\n");
    }

    /*
     * �⵿�� ���񽺸� �����Ѵ�
     * startService���� �����   ������ �̸��� ������ ����Ʈ�� ����Ѵ�
     */
    private void terminateService() {
        if (mService == null) {
            mTextView.append("No requested service.\n");
            return;
        }
        Intent i = new Intent();
        i.setComponent(mService);
        if (stopService(i))
            //mTextView.append(mService.toShortString()+" is stopped.\n");
        	mTextView.append("MyService stopped.\n");
        else
            //mTextView.append(mService.toShortString()+" is alrady stopped.\n");
        	mTextView.append("MyService already stopped.\n");
    }
}
