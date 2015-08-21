package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ui.client.GameList;

public class GameAdministrationDao {
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/game_db";
	private static final String DB_ID = "root";
	private static final String DB_PW = "hanbit";

	public GameAdministrationDao() {
		// ����̹� �ε� �� Ŀ�ؼ� ����
		try {
			Class.forName(DRIVER_NAME);
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����:" + e);
		} catch (SQLException e) {
			System.out.println("Ŀ�ؼ� ���� ����:" + e);
		}
	}

	// ���� �߰�
	public void insert(GameList game) {
		try {
			String sql = "insert into games(game_name, game_price)" + "values(?,?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, game.getGame_name());
			pstmt.setInt(2, game.getGame_price());

			int result = pstmt.executeUpdate();
			System.out.println("insert result:" + result);
		} catch (SQLException e) {
			System.out.println("Dao insert ����:" + e);
		}
	}

	// ���� ����
	public void delete(String gameName) {
		try {
			String sql = "delete from games where game_name=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, gameName);

			int result = pstmt.executeUpdate();
			System.out.println("delete result:" + result);
		} catch (SQLException e) {
			System.out.println("Dao delete ����:" + e);
		}
	}

	/////////////////////////////////////////////////////////////////////
	// ���Ӱ��� ���α׷� ����
	public void BankDaoExit() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
	}

}
