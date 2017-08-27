package com.corea.ServiceDemo;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

//Service�� ���� Ŭ����
public class MyService extends Service implements Runnable {

    //����ID
    private int mStartId;
    //Ÿ�̸� �ֱ� 3��
    private static final int TIMER_PERIOD = 3 * 1000;	
    //ó��ȸ��
    private static final int COUNT = 10;
    
    //ó��ī����. ���� ȸ�� ó���� �����ϸ� �����Ѵ�
    private int mCounter;
    //Service���� �����忡 ���õ� Handler
    private Handler mHandler;
    //������ Frag
    private boolean mRunning;

    //Service ���� �� ȣ���
    public void onCreate() {
        Log.i("MyService","MyService created.");
        super.onCreate();
        mHandler = new Handler();
        mRunning = false;
    }

    //Service�� ���ۿ䱸 �ÿ� ȣ��ȴ�
    //���������� startID�� stopSelf���� ������ ���� ����Ѵ�
    public void onStart(Intent intent, int startId) {
        Log.i("MyService","onStart id = " + startId);
        super.onStart(intent, startId);
        mStartId = startId;
        mCounter = COUNT;
        //�̹� �������� �ƴϸ� run�޼ҵ带 �����ð� �Ŀ� �⵿�Ѵ�
        if (! mRunning) {
            mHandler.postDelayed(this, TIMER_PERIOD);
            mRunning = true;
        }
    }	

    //Service�� ���� �ÿ� ȣ��ȴ�
    public void onDestroy() {
        Log.i("MyService","MyService stop.");
        mRunning = false;
        super.onDestroy();
    }

    //Service�� ó��
    public void run() {
        Log.i("MyService","run "+mCounter);

        if (! mRunning) {
        	//onDestory���� Service�� �����ϰ� ������ �ƹ��͵� ����  �ʰ� ����
            Log.i("MyService","run after destory");
            return;
        } else if (--mCounter <= 0) {
            //���� ȸ�� �����ϸ� ������ �����Ѵ�
            Log.i("MyService","stop Service id = "+mStartId);
            stopSelf(mStartId);
        } else {
        	//���� ó���� ���� �䱸�Ѵ�
            mHandler.postDelayed(this, TIMER_PERIOD);
        }
    }
    
    //Remote������ �޼ҵ�ȣ���� ���� ����Ѵ�
    //�޼ҵ�ȣ���� �������� ���� ��쿡�� null�� ��ȯ�Ѵ�
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
}
