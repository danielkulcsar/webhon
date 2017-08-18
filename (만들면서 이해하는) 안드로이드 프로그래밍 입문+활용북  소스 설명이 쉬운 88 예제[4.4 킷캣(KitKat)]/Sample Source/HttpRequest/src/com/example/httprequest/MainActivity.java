package com.example.httprequest;

import java.io.*;
import java.net.*;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.json.*;

import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    EditText mEditAddr;
    TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mEditAddr = (EditText)findViewById(R.id.editAddr);
        mTextMessage = (TextView)findViewById(R.id.textMessage);
                
        if( isNetConnect() ) 
            mTextMessage.setText("Network is connected");
        else
            mTextMessage.setText("Network is disconnected");
    }

    public boolean isNetConnect() {
        try {
            ConnectivityManager conMgr = (ConnectivityManager) 
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

            if (netInfo != null && netInfo.isConnected())
                return true;
            } catch (Exception e) {
                Log.d("tag", "Connection state error");
            }
        return false;
    }

    public String getHttpConnResult(String strUrl) {
        String line, result = new String();

        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();

            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            while((line = reader.readLine()) != null) {
                result += line + '\n';
                if( result.length() > 2000 ) break;
            }
            reader.close();
            conn.disconnect();
        } catch(Exception e) {
            Log.d("tag", "HttpURLConnection error");
        }
        return result;
    }

    public void onBtnHttp1() {
        String addr = "http://m.cafe.naver.com/tizenity";
        new HttpReqTask().execute(addr);
        //String response = getHttpConnResult(addr);
        //mTextMessage.setText(response);
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.btnHttp1 : 
            onBtnHttp1();
            break;
        case R.id.btnHttp2 :
            onBtnHttp2();
            break;
        case R.id.btnHttp3 :
            onBtnHttp3();
            break;
        }
    }

    private class HttpReqTask extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg) {
            String response = "";
            if( arg.length == 1 ) {
                return (String)getHttpConnResult(arg[0]);
            }
            
            response = getHttpClientResult(arg[ arg.length-1 ]);
            if( arg.length == 3 ) {
                try {
                    JSONObject jOb = new JSONObject(response)
                        .getJSONArray("results").getJSONObject(0)
                        .getJSONObject("geometry").getJSONObject("location");
                    String lat = jOb.getString("lat");
                    String lng = jOb.getString("lng");
                    response = lat + " / " + lng;
                }
                catch(JSONException e) {
                    Log.d("tag", "Parse Error");
            	}
            }

            return response;
        }

        protected void onPostExecute(String result) {
            mTextMessage.setText(result);
        }
    }
    
    public String getHttpClientResult(String addr) {
        String line, result = new String();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(addr);

        try {
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if( statusCode == 200 ) {
                InputStream is = response.getEntity().getContent();
                BufferedReader reader = 
                        new BufferedReader(new InputStreamReader(is));

                while((line = reader.readLine()) != null) {
                    result += line + '\n';
                    if( result.length() > 2000 ) break;
                }
                reader.close();
            }
        } catch( IOException e) {
            Log.d("tag", "Http error-2");
        }
        return result;
    }

    public void onBtnHttp2() {
        String juso = mEditAddr.getText().toString();
        String addr = 
                "http://maps.googleapis.com/maps/api/geocode/json?address=" 
                + juso.replaceAll(" ", "%20") + "&sensor=false";
        new HttpReqTask().execute("", addr);
    }
    
    public void onBtnHttp3() {
        String juso = mEditAddr.getText().toString();
        String addr = 
                "http://maps.googleapis.com/maps/api/geocode/json?address="
                + juso.replaceAll(" ", "%20") + "&sensor=false";
        new HttpReqTask().execute("", "", addr);
    }

}
