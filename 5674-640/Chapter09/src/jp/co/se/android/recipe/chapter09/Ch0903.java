package jp.co.se.android.recipe.chapter09;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ch0903 extends Activity implements OnClickListener {
    private GraphUser mGraphUser;
    private StatusCallback mStatusCallback = new SessionStatusCallback();
    private Button mBtnLogin;
    private Button mBtnPost;
    private EditText mEtInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch0903_main);

        mBtnLogin = (Button) findViewById(R.id.login);
        mBtnLogin.setOnClickListener(this);
        mBtnPost = (Button) findViewById(R.id.post);
        mBtnPost.setOnClickListener(this);
        mEtInput = (EditText) findViewById(R.id.input);

        // 세션을 얻기
        Session session = Session.getActiveSession();
        if (session == null) {
            // 세션을 저장하는 경우는 복귀
            if (savedInstanceState != null) {
                session = Session.restoreSession(this, null, mStatusCallback,
                        savedInstanceState);
            }
            // 세션이 없는 경우는 새롭게 생성
            if (session == null) {
                session = new Session(this);
            }
            // 세션 상태를 설정
            Session.setActiveSession(session);
      
           // 토큰이 이미 존재할 때는 세션 상태를 요구
            if (session.getState().equals(SessionState.CREATED_TOKEN_LOADED)) {
                session.openForRead(new Session.OpenRequest(this)
                        .setCallback(mStatusCallback));
            }
        } else {
            // 세션이 있는 경우는 프로필을 얻는다
            getMyProfile(session);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // 세션 스테이터스 콜백을 등록
        Session.getActiveSession().addCallback(mStatusCallback);
    }

    @Override
    public void onStop() {
        super.onStop();
        // 세션 스테이터스 콜백을 파기
        Session.getActiveSession().removeCallback(mStatusCallback);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 로그인 결과를 FacebookSDK으로 전달한다
        Session.getActiveSession().onActivityResult(this, requestCode,
                resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // 세션 정보를 저장
        Session session = Session.getActiveSession();
        Session.saveSession(session, outState);
    }

    /**
     * 세션 스테이터스 콜백
     */
    private class SessionStatusCallback implements Session.StatusCallback {
        @Override
        public void call(final Session session, SessionState state,
                Exception exception) {
            // 가져온 세션을 바탕으로 프로필을 얻기
            getMyProfile(session);
        }
    }

    /**
     * 프로필 얻기
     * 
     * @param session
     */
    private void getMyProfile(final Session session) {
        if (session != null && session.isOpened()) {
            // 자신의 사용자 정보를 얻기
            Request request = Request.newMeRequest(session,
                    new Request.GraphUserCallback() {
                        @Override
                        public void onCompleted(GraphUser user,
                                Response response) {
                            if (session == Session.getActiveSession()) {
                                if (user != null) {
                                    mGraphUser = user;
                                    updateView();
                                }
                            }
                        }
                    });
            Request.executeBatchAsync(request);
        }
    }

    /**
     * 화면을 갱신
     */
    private void updateView() {
        Session session = Session.getActiveSession();
        if (mGraphUser != null && session.isOpened()) {
            mBtnLogin.setText("로그아웃");
            mBtnPost.setEnabled(true);
            mEtInput.setEnabled(true);
        } else {
            mBtnLogin.setText("로그인");
            mBtnPost.setEnabled(false);
            mEtInput.setEnabled(false);
            mGraphUser = null;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.login) {
            Session session = Session.getActiveSession();
            if (session.isOpened()) {
                // 로그아웃 처리
                onClickLogout();
            } else {
                // 로그인 처리
                onClickLogin();
            }
        } else if (id == R.id.post) {
            String message = mEtInput.getText().toString();
            postWall(message);
        }
    }

    /**
     * Facebook에 로그인
     */
    private void onClickLogin() {
        Session session = Session.getActiveSession();
        if (!session.isOpened() && !session.isClosed()) {
            session.openForRead(new Session.OpenRequest(this)
                    .setCallback(mStatusCallback));
        } else {
            Session.openActiveSession(this, true, mStatusCallback);
        }
    }

    /**
     * Facebook에서 로그아웃
     */
    private void onClickLogout() {
        Session session = Session.getActiveSession();
        if (!session.isClosed()) {
            session.closeAndClearTokenInformation();
            updateView();
        }
    }

    /**
     *근황을 업데이트.
     * 
     * @param message
     */
    private void postWall(String message) {
        Request.newStatusUpdateRequest(Session.getActiveSession(), message,
                null, null, new Request.Callback() {
                    @Override
                    public void onCompleted(Response response) {
                        mEtInput.setText("");
                        Toast.makeText(Ch0903.this, "근황을 업데이트 했습니다",
                                Toast.LENGTH_SHORT).show();
                    }
                }).executeAsync();
    }
}
