package ui.client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Foodorder extends JFrame implements ActionListener {
	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	JButton cancelbtn;
	JButton oderbtn;
	JCheckBox b;
	List<JCheckBox> checkBoxList = new ArrayList<>();
	List<Food> foodlist = new ArrayList<>();
	Food food = null;
	JPanel panel;
	JTextArea dlabel;
	StringBuffer sb = new StringBuffer();
	private BufferedWriter bw;

	// private JCheckBox noodle,welch,mandoo;
	public Foodorder() {
		String driver = "com.mysql.jdbc.Driver";
		String db_url = "jdbc:mysql://localhost:3306/product_db";
		String db_id = "root";
		String do_pw = "hanbit";
		String sql = null;
		String str;

		try {
			Socket socket = new Socket(InetAddress.getByName("203.236.209.14"), 9999);
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			Class.forName(driver);

			con = DriverManager.getConnection(db_url, db_id, do_pw);
			stmt = con.createStatement();
			sql = "select name,price from foods";
			rs = stmt.executeQuery(sql);
			// int i=0;
			while (rs.next() == true) {
				// foodlist.add(i, rs.getString(1));
				// foodlist.add(rs.getString(1)+"
				// "+(Integer.toString(rs.getInt(2)))+"원");
				food = new Food();
				food.setName(rs.getString(1));
				food.setPrice(rs.getInt(2));
				foodlist.add(food);
				// i++;

			}
			// for(String s:foodlist){
			// System.out.println(s);
			// }
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

		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panel_3 = new JPanel();
		panel.add(panel_3);

		JScrollPane scrollPane = new JScrollPane(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

		for (int i = 0; i < foodlist.size(); i++) {
			b = new JCheckBox(foodlist.get(i).getName() + " " + foodlist.get(i).getPrice());

			panel_3.add(b);
			checkBoxList.add(b);

		}
		// JCheckBox checkBox = new JCheckBox("New check box");
		// panel_3.add(checkBox);
		panel.add(scrollPane);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		oderbtn = new JButton("보내기");
		panel_1.add(oderbtn);
		oderbtn.addActionListener(this);

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);

		cancelbtn = new JButton("취소");
		panel_1.add(cancelbtn);
		cancelbtn.addActionListener(this);

		JPanel panel_4 = new JPanel();
		getContentPane().add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel = new JLabel("음식 주문");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		panel_4.add(lblNewLabel);

		setSize(300, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelbtn) {
			dispose();
		} else {

			for (int i = 0; i < checkBoxList.size(); i++) {
				if (checkBoxList.get(i).isSelected() == true) {
					sb.append(checkBoxList.get(i).getText() + "\n");
					System.out.println(checkBoxList.get(i).getText());
				}
			}
			String text = sb.toString();

			try {
				bw.write(text + "\n");
				bw.flush();
			} catch (Exception e1) {
				System.out.println("클라이언트의 접속 불량:" + e1);
				e1.printStackTrace();
			}
			System.out.println(sb);
			JDialog dlog = new JDialog();
			dlabel = new JTextArea(sb.toString());
			dlabel.setOpaque(true);
			dlabel.setEditable(false);
			dlog.add(dlabel);
			dlog.setLocationRelativeTo(panel);
			dlog.setSize(100, 200);
			dlog.setVisible(true);

		}
	}

	public static void main(String args[]) {
		new Foodorder();
	}

}
