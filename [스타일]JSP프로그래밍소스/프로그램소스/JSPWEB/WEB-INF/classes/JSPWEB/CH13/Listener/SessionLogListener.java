package JSPWEB.CH13.Listener;

import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;
public class SessionLogListener implements HttpSessionListener {

	private static int cnt = 0;
	private static String connectorInfo;

	 
    public void sessionCreated(HttpSessionEvent se) {
        if(cnt >= 1){
            connectorInfo="���� �ٸ� ����ڰ� ������ ����ϰ� �ֽ��ϴ�.";
        }else{
            cnt++;
           
            connectorInfo= "����Ʈ ������ �߰�: " + se.getSession().getId()+"<br>���� ������ ��      : " + cnt;
        }
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        if(cnt > 0){
            cnt--;
        }
      
        connectorInfo= "����Ʈ ������ ����: " + se.getSession().getId() +"���� ������ ��      : " + cnt;
    }
    public static String getActiveSessions() {
        return connectorInfo;
    }

}
