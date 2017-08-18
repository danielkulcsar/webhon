package com.example.tcpsocket;

import java.io.*;
import java.net.*;

import android.app.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    EditText mEditAddr;
    EditText mEditPort;
    EditText mEditSend;
    TextView mTextMessage;
    Socket mSock = null;
    BufferedReader mReader = null;
    BufferedWriter mWriter = null;
    String mRecvData = "";
    CheckRecv mCheckRecv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditAddr = (EditText)findViewById(R.id.editAddr);
        mEditAddr.setText("172.16.206.17");
        mEditPort = (EditText)findViewById(R.id.editPort);
        mEditSend = (EditText)findViewById(R.id.editSend);
        mTextMessage = (TextView)findViewById(R.id.textMessage);
    }

    private class ConnectTask extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg) {
            try {
                int nPort = Integer.parseInt(arg[1]);

                mSock = new Socket(arg[0], nPort);
                mWriter = new BufferedWriter(
                        new OutputStreamWriter(mSock.getOutputStream()));
                mReader = new BufferedReader(
                        new InputStreamReader(mSock.getInputStream()));
                mCheckRecv = new CheckRecv(); 
                mCheckRecv.start();
            } catch(Exception e) {
                Log.d("tag", "Socket connect error.");
                return "Connect Fail";
            }
            return "Connect Succeed";
        }

        protected void onPostExecute(String result) {
            mTextMessage.setText(result);
        }
    }

    public void onBtnConnect() {
        if( mSock != null )
            return;
        String serverAddr = mEditAddr.getText().toString();
        String strPort = mEditPort.getText().toString();
        new ConnectTask().execute(serverAddr, strPort);
    }

    private class CloseTask extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg) {
            try {
                if( mSock != null ) {
                    mSock.close();
                    mSock = null;
                    mCheckRecv.destroy();
                    mCheckRecv = null;
                }
            } catch(Exception e) {
                Log.d("tag", "Socket close error.");
                return "Close Fail";
            }
            return "Closed";
        }

        protected void onPostExecute(String result) {
            mTextMessage.setText(result);
        }
    }

    public void onBtnClose() {
        if( mSock == null )
            return;

        new CloseTask().execute();
    }

    private class SendTask extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg) {
            if( mWriter == null )
                return "Can not Send";

            try {
                PrintWriter out = new PrintWriter(mWriter, true);
                out.println(arg[0]);
            } catch(Exception e) {
                Log.d("tag", "Data send error.");
                return "Send Fail";
            }
            return "Send Succeed";
        }

        protected void onPostExecute(String result) {
            mTextMessage.setText(result);
        }
    }

    public void onBtnSend() {
        String strSend = mEditSend.getText().toString();
        mEditSend.setText("");
        new SendTask().execute(strSend);
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.btnConnect :
            onBtnConnect();
            break;
        case R.id.btnClose :
            onBtnClose();
            break;
        case R.id.btnSend :
            onBtnSend();
            break;
        }
    }

    Handler mReceiver = new Handler() {
        public void handleMessage(Message msg) {
            mTextMessage.setText(mRecvData);
        }
    };

    public class CheckRecv extends Thread {
        public void run() {
            try {            	
                while( !Thread.currentThread().isInterrupted() ) {
                    mRecvData = mReader.readLine();
                    mReceiver.sendEmptyMessage(0);
                }
            } catch (Exception e) {
                Log.d("tag", "Receive error");
            }
        }
    }

}
