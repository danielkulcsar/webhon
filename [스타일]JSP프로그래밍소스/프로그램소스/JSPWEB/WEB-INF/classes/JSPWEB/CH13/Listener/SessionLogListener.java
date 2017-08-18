package JSPWEB.CH13.Listener;

import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;
public class SessionLogListener implements HttpSessionListener {

	private static int cnt = 0;
	private static String connectorInfo;

	 
    public void sessionCreated(HttpSessionEvent se) {
        if(cnt >= 1){
            connectorInfo="현재 다른 사용자가 계정을 사용하고 있습니다.";
        }else{
            cnt++;
           
            connectorInfo= "사이트 접속자 추가: " + se.getSession().getId()+"<br>현재 접속자 수      : " + cnt;
        }
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        if(cnt > 0){
            cnt--;
        }
      
        connectorInfo= "사이트 접속자 해제: " + se.getSession().getId() +"현재 접속자 수      : " + cnt;
    }
    public static String getActiveSessions() {
        return connectorInfo;
    }

}
