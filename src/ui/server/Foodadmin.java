package ui.server;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ui.client.Food;
import ui.client.FoodDao;

import java.awt.FlowLayout;
import java.awt.BorderLayout;


public class Foodadmin extends JDialog{
	private JPanel panela;
	private JPanel panelb;
	private JPanel panelc;
	private JButton addbutton;
	private JButton button_d;
	private JButton enter;
	private JTextField name;
	private JTextField maker;
	private JTextField count;
	private JTextField price;
	private JDialog dlog;
	private Connection con = null;
	private Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Food food = null;
	FoodDao foodDao =new FoodDao();
	private JTextArea txtForintI;
	List<Food> foodlist = new ArrayList<>();
	FoodDao dao = new FoodDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Foodadmin frame = new Foodadmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Foodadmin() {
		String driver = "com.mysql.jdbc.Driver";
		String db_url = "jdbc:mysql://localhost:3306/product_db";
		String db_id = "root";
		String do_pw = "1234";
		String sql = null;
		String str;
		try {
			Class.forName(driver);

			con = DriverManager.getConnection(db_url, db_id, do_pw);
			stmt = con.createStatement();
			// 여기까지 흔한db연결 
			sql = "select name,maker,count,price from foods";
			rs = stmt.executeQuery(sql);
			
			while (rs.next() == true) {
				// 반복문으로 칼럼을 꺼냄
				
				food = new Food(); //setter , getter로 이루어진 클래스
				food.setName(rs.getString(1));//메소드를 사용해서DB 데이터값들을 GET해서 FOOD에 SET 해줌
				food.setMaker(rs.getString(2));
				food.setCount(rs.getInt(3));
				food.setPrice(rs.getInt(4));
				foodlist.add(food); //세팅하고 리스트에 저장!
				
				//DB 다�㎱릿歐� 나갈때 문단속하고
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}

			}
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	
		setBounds(100, 100, 394, 338);
		panela = new JPanel();
		panelb = new JPanel();
		panelc = new JPanel();
		panela.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panela);
		panela.setLayout(new BorderLayout(0, 0));
		
		JLabel label_2 = new JLabel("음식관리");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("굴림", Font.BOLD, 20));
		panela.add(label_2, BorderLayout.NORTH);
	//	Food food = new Food();
		
		
		addbutton = new JButton("추가");
		addbutton.addActionListener(new AListener()); //버튼 누르면 추가 입력창이뜸
		panelb.add(addbutton, BorderLayout.SOUTH);
		
		button_d = new JButton("삭제");
		button_d.addActionListener(new AListener());//버튼 누르면 삭제 입력창이뜸
		panelb.add(button_d, BorderLayout.SOUTH);
		
		
		txtForintI = new JTextArea();
		txtForintI.setFont(new Font("굴림", Font.PLAIN, 13));
		txtForintI.setEditable(false);
		txtForintI.setEnabled(false);
		
		for(Food f: foodlist){ //반복문을통해 텍스트 에어리어에 db에서 꺼내담아온 foodlist의 내용들을 하나씩
			//붙여서 이어너음
			txtForintI.append(f.toString()+"\n");
		}
		panelc.setLayout(new BorderLayout(0, 0));
		panelc.add(txtForintI);
		txtForintI.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane(txtForintI);
		panelc.add(scrollPane);
		panela.add(panelc, BorderLayout.CENTER);
		panela.add(panelb, BorderLayout.SOUTH);
		
		
				
	
		
		
		
		
	}
	private class AListener implements ActionListener{//입력,삭제 버튼 누르면 나타나는 창들

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==addbutton){
				dlog = new JDialog();
				JPanel panel = new JPanel();
				name = new JTextField(20);
				maker = new JTextField(20);
				count = new JTextField(20);
				price = new JTextField(20);
				enter = new JButton("enter");
				panel.add(new JLabel("품명:"));
				panel.add(name);
				panel.add(new JLabel("제조사:"));
				panel.add(maker);
				panel.add(new JLabel("수량:"));
				panel.add(count);
				panel.add(new JLabel("가격:"));
				panel.add(price);
				panel.add(enter);
				enter.addActionListener(new BListener());//enter 누르면 입력소스들이 실행됨
				dlog.add(panel);
				dlog.setTitle("음식추가");
				dlog.setLocationRelativeTo(panel);
				dlog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				dlog.setSize(300,200);
				dlog.setVisible(true);
//				gameList = new GameList();
//				//gameDao = new GameAdministrationDao();
//				int temp = Integer.valueOf(textField_1.getText());
				
				

			}
			else{
				dlog = new JDialog();
				JPanel panel = new JPanel();
			    name = new JTextField(20);
				button_d = new JButton("삭제");
				button_d.addActionListener(new BListener());//삭제버튼 누르면 삭제가 실행됨
				panel.add(new JLabel("품명:"));
				panel.add(name);
				
				panel.add(button_d);
				dlog.add(panel);
				dlog.setTitle("음식삭제");
				dlog.setLocationRelativeTo(panel);
				dlog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				dlog.setSize(300,100);
				dlog.setVisible(true);
			}
		}
		
	}
	private class BListener implements ActionListener{//입력버튼과 삭제버튼의 기능구현

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==enter){
				food.setName(name.getText());
				food.setMaker(maker.getText());
				food.setCount(Integer.parseInt(count.getText()));
				food.setPrice(Integer.parseInt(price.getText()));
				
				dao.insert(food);
				JOptionPane joption =  new JOptionPane();
				joption.showMessageDialog(panela, "입력성공");
				txtForintI.setText("");//다시한번 비우고
				foodlist = dao.showList();//다시쓰기 귀찮아서 db에있는내용 꺼내오는작업을 dao에 너어버렸음.
				for(Food f: foodlist){
					//System.out.println(f.toString());
					txtForintI.append(f.toString()+"\n");
				}
				dlog.dispose();
				
			}
			else{
				food.setName(name.getText());
				dao.delete(food);
				JOptionPane joption =  new JOptionPane();
				joption.showMessageDialog(panela, "삭제성공");
				txtForintI.setText("");
				foodlist = dao.showList();
				for(Food f: foodlist){
					txtForintI.append(f.toString()+"\n");
				}
				dlog.dispose();
			}
			
		}
		
	}	
}
	