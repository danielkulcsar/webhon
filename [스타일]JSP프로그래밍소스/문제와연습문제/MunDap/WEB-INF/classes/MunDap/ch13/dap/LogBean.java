package MunDap.ch13.dap;

import java.util.Date;

public class LogBean {
private String	request_uri;
private String	  remote_address;
private String	  server_name ;
private String	  session_id;

public String getRequest_uri() {
	return request_uri;
}
public void setRequest_uri(String request_uri) {
	this.request_uri = request_uri;
}


public String getRemote_address() {
	return remote_address;
}
public void setRemote_address(String remote_address) {
	this.remote_address = remote_address;
}
public String getServer_name() {
	return server_name;
}
public void setServer_name(String server_name) {
	this.server_name = server_name;
}
public String getSession_id() {
	return session_id;
}
public void setSession_id(String session_id) {
	this.session_id = session_id;
}




}
