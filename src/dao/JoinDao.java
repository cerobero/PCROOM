package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class JoinDao {

	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static final String DriverName ="com.mysql.jdbc.Driver";
	private static final String URL="jdbc:mysql://localhost:3306/join_db"; 
	private static final String ID= "root";
	private static final String PW= "hanbit";
	
	public JoinDao(){
		try {
			Class.forName(DriverName);
			con = DriverManager.getConnection(URL,ID,PW);
			
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 오류");
		} catch (SQLException e) {
			System.out.println("커넥션 생성 오류");
		}
	}
	
	public void insert(Join join){
		
		try {
			String sql="Insert into join_tb(user_id,password,cellphone,birth)"
					+ "values(?,?,?,?)";
			pstmt= con.prepareStatement(sql);
			
			pstmt.setString(1, join.getUserId());
			pstmt.setString(2, join.getPassword());
			pstmt.setString(3, join.getCellPhone());
			pstmt.setString(4, join.getBirth());
			
			int result = pstmt.executeUpdate();
			System.out.println("insert 완료 :" +result);
			
			
		} catch (SQLException e) {
			System.out.println("insert 오류 :"+e);
		}
		
		
	}
	
	
	public Join select(String id){
		Join result=null;
		try {
			String sql="select * from join_tb where user_id=? ";
			pstmt= con.prepareStatement(sql);
			
			pstmt.setString(1,id);
			
			rs =pstmt.executeQuery();
			
			if(rs.next()){
				result = new Join();
				result.setUserId(rs.getString(1));
				result.setPassword(rs.getString(2));
				result.setCellPhone(rs.getString(3));
				result.setBirth(rs.getString(4));
			}
			
			
		} catch (SQLException e) {
			System.out.println("select 1 오류 :"+e);
		}		
		return result;
	}
	
	


	
	
	public void exit(){
		if(rs != null){
			try {rs.close();} catch (SQLException e) {}
		}
		
		if(pstmt != null){
			try {pstmt.close();} catch (SQLException e) {}
		}
		
		if(stmt != null){
			try {stmt.close();} catch (SQLException e) {}
		}
		
		if(con != null){
			try {con.close();} catch (SQLException e) {}
		}
		
	}
	
	
}
