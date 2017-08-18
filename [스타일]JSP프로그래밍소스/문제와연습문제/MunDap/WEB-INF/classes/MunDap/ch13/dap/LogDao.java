package MunDap.ch13.dap;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

public class LogDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Vector<LogBean>	vlist = new Vector<LogBean>();
	DataSource ds;
	
	
	private static LogDao instance = new LogDao();
	public static LogDao getInstance(){
	return instance;
}


public Connection getConnection() throws Exception{
	Context ctx = new InitialContext();
	DataSource ds =  (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
	Connection con = ds.getConnection();
	return con;
}


public void insertDAO(LogBean  lbean) throws Exception{
	
	Connection con = null; 
	PreparedStatement pstmt = null;
	 
	String sql = "insert into db_log values (?,?,?,?)";
	try{
		con = getConnection();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, lbean.getRequest_uri());
		pstmt.setString(2, lbean.getRemote_address());
		pstmt.setString(3,lbean.getServer_name());
		pstmt.setString(4, lbean.getSession_id());
	
		 pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
	}catch (Exception e) {
		e.printStackTrace();
		
	}
			
}




public ArrayList<LogBean> selectList() throws Exception{
	
	ArrayList<LogBean> list = new ArrayList<LogBean>();
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	String query = "select * from db_log";
	try{
		con = getConnection();
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);
		
		while(rs.next()){
			LogBean Lbean = new LogBean();
		
			Lbean.setRemote_address(rs.getString("remote_address"));
			Lbean.setRequest_uri(rs.getString("request_uri"));
			Lbean.setServer_name(rs.getString("server_name"));
			Lbean.setSession_id(rs.getString("session_id"));
		
			list.add(Lbean);
		}
	}catch (Exception e) {
		e.printStackTrace();
		
	}
	
	rs.close();
	stmt.close();
	con.close();
	
	return list;
}

}