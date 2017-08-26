package jp.co.se.android.recipe.chapter09;

import jp.co.se.android.recipe.chapter09.R;

import com.facebook.LoggingBehavior;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.facebook.Settings;
import com.facebook.model.GraphUser;
import com.facebook.widget.ProfilePictureView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Ch0902 extends Activity implements OnClickListener {
    private GraphUser mGraphUser;
    private StatusCallback mStatusCallback = new SessionStatusCallback();
    private Button mBtnLogin;
    private ProfilePictureView mPpvImage;
    private TextView mTvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch0902_main);

        mPpvImage = (ProfilePictureView) findViewById(R.id.image);
        mTvName = (TextView) findViewById(R.id.name);
        mBtnLogin = (Button) findViewById(R.id.login);
        mBtnLogin.setOnClickListener(this);

        // 세션 구하기
        Session session = Session.getActiveSession();
        if (session == null) {
            // 세션을 저장한 경우는 복구
            if (savedInstanceState != null) {
                session = Session.restoreSession(this, null, mStatusCallback,
                        savedInstanceState);
            }
            // 세션이 없는 경우는 새롭게 생성
            if (session == null) {
                session = new Session(this);
            }
            // 세션의 상태를 설정
            Session.setActiveSession(session);
            // 토큰이 이미 존재할 때는 세션 상태를 요구
            if (session.getState().equals(SessionState.CREATED_TOKEN_LOADED)) {
                session.openForRead(new Session.OpenRequest(this)
                        .setCallback(mStatusCallback));
            }
        } else {
            // 세션이 있을 경우 프로필을 취득
            getMyProfile(session);
        }

        // 액세스 토큰을 요구했을 때의 로그 출력을 ON으로 한다
        Settings.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
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
        // 로그인 결과를 FacebookSDK로 전달한다
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
     * 프로필 얻기.
     * 
     * @param session
     */
    private void getMyProfile(final Session session) {
        if (session != null && session.isOpened()) {
            // 자신의 사용자 정보를 가져오기 
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
     *화면을 갱신
     */
    private void updateView() {
        Session session = Session.getActiveSession();
        if (mGraphUser != null && session.isOpened()) {
            mBtnLogin.setText(getString(R.string.label_logout));
            mPpvImage.setProfileId(mGraphUser.getId());
            mTvName.setText(mGraphUser.getName());
        } else {
            mBtnLogin.setText(getString(R.string.label_login));
            mPpvImage.setProfileId(null);
            mTvName.setText("");
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
        }
    }

    /**
     * Facebook에 로그인.
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
}
