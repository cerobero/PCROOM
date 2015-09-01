package ui.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;





public class FoodDao {
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/product_db";
	private String uid = "root";
	private String pw = "hanbit";
	List<Food> foodlist;
	Food food;

	public FoodDao() {
		try {
			Class.forName(driver);

			con = DriverManager.getConnection(url, uid, pw);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insert(Food object){
		String sql= "insert into foods(name,maker,count,price)values"
				+ "(?,?,?,?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, object.getName());
			pstmt.setString(2, object.getMaker());
			pstmt.setInt(3, object.getCount());
			pstmt.setInt(4, object.getPrice());
			
			int result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("inset ¿¡·¯");
			e.printStackTrace();
		}
		
	}
	public void update(Food object){
		String sql = "update foods set count=? where name=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, object.getCount());
			pstmt.setString(2, object.getName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void delete(Food object){
		String sql = "delete from foods where name=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, object.getName());
			int result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public List<Food> showList(){
		List<Food> foodlist = new ArrayList<>();
		try {
			String sql ="select * from foods";
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Food food = new Food();
				food.setName(rs.getString(2));
				food.setMaker(rs.getString(3));
				food.setCount(rs.getInt(4));
				food.setPrice(rs.getInt(5));
				
			foodlist.add(food);
			}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return foodlist;
	}
	public void Daoexit() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}