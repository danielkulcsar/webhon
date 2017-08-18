package MunDap.ch08.dap;
import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.*;
public class AddrDao {
		
	
		private Connection conn;
		private PreparedStatement pstmt;
		private ResultSet rs;
		private Vector<AddrBean>	vlist = new Vector<AddrBean>();
		DataSource ds;
		
		
		private static AddrDao instance = new AddrDao();
		public static AddrDao getInstance(){
		return instance;
	}

public Connection getConnection() throws Exception{
Context ctx = new InitialContext();
DataSource ds =  (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
		Connection con = ds.getConnection();
		return con;
	}


	
		public Vector<AddrBean> getData(String name) {
			try {
     conn=getConnection();
	String sql = "select * from Addrlist where name = ?";
		pstmt = conn.prepareStatement(sql);
	   pstmt.setString(1,name);
	rs= pstmt.executeQuery();
	while(rs.next()){
		AddrBean sBean = new AddrBean();
		sBean.setName(rs.getString("name"));
		sBean.setAge(rs.getInt("age"));
		sBean.setTel(rs.getString("tel"));
		vlist.add(sBean);
				}
			} catch (Exception e) {
				System.out.println("sql error:"+e);
			} finally {
				
			}
			return vlist;
		}
	}


