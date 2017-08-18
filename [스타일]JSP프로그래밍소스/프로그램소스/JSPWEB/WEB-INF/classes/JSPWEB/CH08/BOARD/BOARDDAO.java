package JSPWEB.CH08.BOARD;
import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;
import java.sql.Date;
import java.util.*;

public class BOARDDAO {
	//싱글톤 객체 방식, 공용변수로서 객체가 한개만 만들어져있게 하는 방식  
	private static BOARDDAO instance = new BOARDDAO();
	
	//static 메소드므로 클래스 이름으로 접근 가능, 싱글톤 객체를 리턴해주는 메소드
	public static BOARDDAO getInstance(){
		return instance;
	}
	
	public Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds =  (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
		Connection con = ds.getConnection();
		return con;
	}
	
	public void deleteDAO(int no)
	{
		
		Connection con = null; 
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM board  WHERE no=?";
		
		try{
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,no);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
	
		
	}
	public void insertDAO(BOARDBean boardbean) throws Exception{
		
		Connection con = null; 
		PreparedStatement pstmt = null;
		String sql = "insert into board values (seq_board.nextval,?,?,?,sysdate,?)";
		try{
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardbean.getName());
			pstmt.setString(2, boardbean.getEmail());
			pstmt.setString(3, boardbean.getHomepage());
						pstmt.setString(4, boardbean.getContents());
			 pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
			
		}
				
	}
	
	
	public void updateDAO(BOARDBean boardbean) throws Exception{
		
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		String sql = "update  board set name=?, email=? ,homepage=?,regist_date=sysdate,contents=? where no=?";
		try{
			con = getConnection();
			pstmt = con.prepareStatement(sql);
		
			pstmt.setString(1, boardbean.getName());
			pstmt.setString(2, boardbean.getEmail());
			pstmt.setString(3, boardbean.getHomepage());

			pstmt.setString(4, boardbean.getContents());
			pstmt.setInt(5,boardbean.getNo());
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
	
		
	}
	
	public ArrayList<BOARDBean> selectList() throws Exception{
		
		ArrayList<BOARDBean> list = new ArrayList<BOARDBean>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "select * from board";
		try{
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				BOARDBean boardbean = new BOARDBean();
				boardbean.setNo(rs.getInt("no"));
				boardbean.setName(rs.getString("name"));
				boardbean.setEmail(rs.getString("email"));
				boardbean.setHomepage(rs.getString("homepage"));
				boardbean.setDate(rs.getDate("regist_date"));
				boardbean.setContents(rs.getString("contents"));
				list.add(boardbean);
			}
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
		rs.close();
		stmt.close();
		con.close();
		
		return list;
	}
	
	public BOARDBean searchNo(int no) throws Exception{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BOARDBean boardbean = new BOARDBean();
		String query = "select * from board where no=?";
		
		try{
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1,no);
			rs = pstmt.executeQuery();
			
			if(rs.next()){			
				boardbean.setNo(rs.getInt("no"));
				boardbean.setName(rs.getString("name"));
				boardbean.setEmail(rs.getString("email"));
				boardbean.setHomepage(rs.getString("homepage"));
				boardbean.setDate(rs.getDate("regist_date"));
				boardbean.setContents(rs.getString("contents"));
				}			
			
			else{
				boardbean = null;
				
			}			
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
		rs.close();
		pstmt.close();
		con.close();
		
		return boardbean;
	}
	
	



}
