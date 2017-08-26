package jp.co.se.android.recipe.chapter08;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Ch0816 extends Activity implements OnClickListener {
    private static final String TAG = Ch0816.class.getSimpleName();
    private TextView mTextView;
    private Button mBtnRun;
    private Button mBtnCancel;
    private AsyncTask<Uri, Void, String> mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch0817_main);

        mBtnRun = (Button) findViewById(R.id.btnRun);
        mBtnCancel = (Button) findViewById(R.id.btnCancel);
        mTextView = (TextView) findViewById(R.id.text);

        mBtnRun.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
    }

    private void sendText(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextView.append(text);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnRun) {
            requestGetMethod();
        } else if (id == R.id.btnCancel) {
            if (mTask != null) {
                mTask.cancel(false);
                mTextView.append("\n통신을 취소합니다...");
            } else {
                mTextView.append("\n통신하지 않습니다. 또는, 통신 취소 중입니다...");
            }
        }
    }

    private void requestGetMethod() {
        mTextView.setText("");
        sendText("통신 준비");

        // URL을 다루기 편한 Uri 형으로 
        Uri baseUri = Uri
                .parse("http://android-recipe.herokuapp.com/samples/ch08/json");

        // 파라미터 부여
        Uri uri = baseUri.buildUpon().appendQueryParameter("param1", "hoge")
                .build();

        if (mTask == null) {
            mTask = new AsyncTask<Uri, Void, String>() {
                /** 통신에서 발생한 에러  */
                private Throwable mError = null;

                @Override
                protected String doInBackground(Uri... params) {
                    String result = request(params[0]);

                    if (isCancelled()) {
                        sendText("\nn통신은 취소되었다");
                        return result;
                    }

                    return result;
                }

                private String request(Uri uri) {
                    DefaultHttpClient httpClient = new DefaultHttpClient();

                    // 타임아웃 설정
                    HttpParams httpParams = httpClient.getParams();
                    // 접속 확립까지 타임 아웃 설정 (밀리초)
                    HttpConnectionParams.setConnectionTimeout(httpParams,
                            5 * 1000);
                    // 접속 후까지 타임 아웃 설정 (밀리초)
                    HttpConnectionParams.setSoTimeout(httpParams, 5 * 1000);

                    String result = null;
                    HttpGet request = new HttpGet(uri.toString());
                    try {
                        sendText("\n통신 시작");
                        result = httpClient.execute(request,
                                new ResponseHandler<String>() {
                                    @Override
                                    public String handleResponse(
                                            HttpResponse response)
                                            throws ClientProtocolException,
                                            IOException {
                                        int statusCode = response
                                                .getStatusLine()
                                                .getStatusCode();
                                        sendText("\n스테이터스 코드 : " + statusCode);
                                        if (statusCode == HttpStatus.SC_OK) {
                                            String result = EntityUtils
                                                    .toString(response
                                                            .getEntity());
                                            return result;
                                        } else if (statusCode == HttpStatus.SC_NOT_FOUND) {
                                            throw new RuntimeException(
                                                    "404 NOT FOUND");
                                        } else {
                                            throw new RuntimeException(
                                                    "그 외의 에러");
                                        }
                                    }
                                });
                        sendText("\n통신 종료");
                    } catch (RuntimeException e) {
                        mError = e;
                        sendText("\n통신 실패" + e.getClass().getSimpleName());
                        Log.e(TAG, "통신 실패", e);
                    } catch (ClientProtocolException e) {
                        mError = e;
                        sendText("\n통신 실패" + e.getClass().getSimpleName());
                        Log.e(TAG, "통신 실패敗", e);
                    } catch (IOException e) {
                        mError = e;
                        sendText("\n통신 실패" + e.getClass().getSimpleName());
                        Log.e(TAG, "통신 실패", e);
                    } finally {
                        // 리소스를 개방한다
                        httpClient.getConnectionManager().shutdown();
                    }

                    return result;
                }

                @Override
                protected void onPostExecute(String result) {
                    sendText("\nonPostExecute(String result)");

                    if (mError == null) {
                        sendText("\n통신성공：");
                        sendText("\n 수신한 데이터 : " + result);
                    } else {
                        sendText("\n통신실패：");
                        sendText("\n에러 : " + mError.getMessage());
                    }

                    mTask = null;
                }

                @Override
                protected void onCancelled() {
                    onCancelled(null);
                }

                @Override
                protected void onCancelled(String result) {
                    sendText("\nonCancelled(String result), result=" + result);

                    mTask = null;
                }
            }.execute(uri);
        } else {
        	// 현재 통신의 작업이 실행 중.중복하여 실행되지 않도록 제어.
        }
    }
}
