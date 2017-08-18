package JSPWEB.CH08;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;
public class ADDRESSDAO {

		//�̱��� ��ü ���, ���뺯���μ� ��ü�� �Ѱ��� ��������ְ� �ϴ� ���  
		private static ADDRESSDAO instance = new ADDRESSDAO();
		
		//static �޼ҵ�Ƿ� Ŭ���� �̸����� ���� ����, �̱��� ��ü�� �������ִ� �޼ҵ�
		public static ADDRESSDAO getInstance(){
			return instance;
		}
		
		public Connection getConnection() throws Exception{
			Context ctx = new InitialContext();
			DataSource ds =  (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
			Connection con = ds.getConnection();
			return con;
		}
		
		public int deleteDAO(String name)
		{
			int num = 0;
			Connection con = null; 
			PreparedStatement pstmt = null;
			String sql = "DELETE FROM AddressList  WHERE NAME=?";
			
			try{
				con = getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
				num = pstmt.executeUpdate();
				pstmt.close();
				con.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return num;
			
		}
		public int insertDAO(ADDRESSBean addrBean) throws Exception{
			int num = 0;
			Connection con = null; 
			PreparedStatement pstmt = null;
			String sql = "insert into AddressList values (?,?,?)";
			try{
				con = getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, addrBean.getName());
				pstmt.setString(2, addrBean.getTel());
				pstmt.setString(3, addrBean.getAddr());
				num = pstmt.executeUpdate();
				
				pstmt.close();
				con.close();
			}catch (Exception e) {e.printStackTrace();}
			
			return num;
		}
		
		
		public int updateDAO(ADDRESSBean addrBean) throws Exception{
			int num = 0;
			Connection con = null; 
			PreparedStatement pstmt = null;
			String sql = "update  AddressList set tel=?, addr=? where name=?";
			try{
				con = getConnection();
				pstmt = con.prepareStatement(sql);
			
				pstmt.setString(1, addrBean.getTel());
				pstmt.setString(2, addrBean.getAddr());
				pstmt.setString(3, addrBean.getName());
				num = pstmt.executeUpdate();
				
				pstmt.close();
				con.close();
			}catch (Exception e) {e.printStackTrace();}
			
			return num;
		}
		
		public ArrayList<ADDRESSBean> selectList() throws Exception{
			ArrayList<ADDRESSBean> list = new ArrayList<ADDRESSBean>();
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			String query = "select * from AddressList";
			try{
				con = getConnection();
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				
				while(rs.next()){
					ADDRESSBean addrbean = new ADDRESSBean();
					addrbean.setName(rs.getString("name"));
					addrbean.setTel(rs.getString("tel"));
					addrbean.setAddr(rs.getString("addr"));
					list.add(addrbean);
				}
			}catch (Exception e) {e.printStackTrace();}
			
			rs.close();
			stmt.close();
			con.close();
			
			return list;
		}
		
		public ADDRESSBean searchName(String name) throws Exception{
			
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			ADDRESSBean addrbean = new ADDRESSBean();
			String query = "select * from AddressList where name=";
			query = query + "'"+name+"'";
			
			try{
				con = getConnection();
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				
				if(rs.next()){			
					addrbean.setName(rs.getString("name"));
					addrbean.setTel(rs.getString("tel"));
					addrbean.setAddr(rs.getString("addr"));
					
					}			
				
				else{
					addrbean = null;
					
				}			
				
			}catch (Exception e) {e.printStackTrace();}
			
			rs.close();
			stmt.close();
			con.close();
			
			return addrbean;
			
		}
		
	}

