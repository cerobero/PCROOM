package ui.server;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.Join;
import dao.JoinDao;


@SuppressWarnings("serial")
public class UserManage extends JDialog{
	private JPanel panela;
	private JPanel panelb;
	private JPanel panelc;
	private JButton addbutton;
	private JButton button_d;
	private JButton enter;
	private JTextField userId;
	private JTextField password;
	private JTextField cellPhone;
	private JTextField birth;
	private JDialog dlog;
	private Connection con = null;
	private Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Join join = null;
	JoinDao joinDao = new JoinDao();
	private JTextArea txtForintI;
	List<Join> joinList = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserManage frame = new UserManage();
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
	public UserManage() {
		String driver = "com.mysql.jdbc.Driver";
		String db_url = "jdbc:mysql://localhost:3306/join_db";
		String db_id = "root";
		String do_pw = "1234";
		String sql = null;
		try {
			Class.forName(driver);

			con = DriverManager.getConnection(db_url, db_id, do_pw);
			stmt = con.createStatement();
			// 여기까지 흔한db연결 
			sql = "select user_id, password, cellphone, birth from join_tb";
			rs = stmt.executeQuery(sql);

			while (rs.next() == true) {
				// 반복문으로 칼럼을 꺼냄

				join = new Join(); //setter , getter로 이루어진 클래스
				join.setUserId(rs.getString(1));//메소드를 사용해서DB 데이터값들을 GET해서 FOOD에 SET 해줌
				join.setPassword(rs.getString(2));
				join.setCellPhone(rs.getString(3));
				join.setBirth(rs.getString(4));
				joinList.add(join); //세팅하고 리스트에 저장!

				//DB 나갈때 문단속하고
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

		JLabel label_2 = new JLabel("회원관리");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("굴림", Font.BOLD, 20));
		panela.add(label_2, BorderLayout.NORTH);

		addbutton = new JButton("추가");
		addbutton.addActionListener(new AListener()); //버튼 누르면 추가 입력창이뜸
		panelb.add(addbutton, BorderLayout.SOUTH);

		button_d = new JButton("삭제");
		button_d.addActionListener(new AListener());//버튼 누르면 삭제 입력창이뜸
		panelb.add(button_d, BorderLayout.SOUTH);


		txtForintI = new JTextArea();
		txtForintI.setFont(new Font("굴림", Font.PLAIN, 13));
		txtForintI.setEditable(false);
		//		txtForintI.setEnabled(false);
		txtForintI.append("ID\t전화번호\t생년월일\n");

		for(Join f: joinList){ //반복문을통해 텍스트 에어리어에 db에서 꺼내담아온 foodlist의 내용들을 하나씩
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
				JPanel idPanel = new JPanel();
				JPanel pwPanel = new JPanel();
				JPanel cpPanel = new JPanel();
				JPanel brPanel = new JPanel();
				userId = new JTextField(16);
				password = new JTextField(16);
				cellPhone = new JTextField(16);
				birth = new JTextField(16);
				enter = new JButton("enter");
				idPanel.add(new JLabel("ID : "));
				idPanel.add(userId);
				pwPanel.add(new JLabel("PW : "));
				pwPanel.add(password);
				cpPanel.add(new JLabel("전화번호 : "));
				cpPanel.add(cellPhone);
				brPanel.add(new JLabel("생일 : "));
				brPanel.add(birth);
				panel.add(idPanel);
				panel.add(pwPanel);
				panel.add(cpPanel);
				panel.add(brPanel);
				panel.add(enter);
				enter.addActionListener(new BListener());//enter 누르면 입력소스들이 실행됨
				dlog.add(panel);
				dlog.setTitle("회원추가");
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
				userId = new JTextField(20);
				button_d = new JButton("삭제");
				button_d.addActionListener(new BListener());//삭제버튼 누르면 삭제가 실행됨
				panel.add(new JLabel("ID :"));
				panel.add(userId);

				panel.add(button_d);
				dlog.add(panel);
				dlog.setTitle("회원탈퇴");
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
				join.setUserId(userId.getText());
				join.setPassword(password.getText());
				join.setCellPhone(cellPhone.getText());
				join.setBirth(birth.getText());

				joinDao.insert(join);
				JOptionPane.showMessageDialog(panela, "입력성공");
				txtForintI.setText("");//다시한번 비우고
				txtForintI.append("ID\t전화번호\t생년월일\n");
				joinList = joinDao.selectList();//다시쓰기 귀찮아서 db에있는내용 꺼내오는작업을 dao에 너어버렸음.
				for(Join f: joinList){
					txtForintI.append(f.toString()+"\n");
				}
				dlog.dispose();

			}
			else{
				join.setUserId(userId.getText());
				joinDao.delete(join.getUserId());
				JOptionPane.showMessageDialog(panela, "삭제성공");
				txtForintI.setText("");
				txtForintI.append("ID\t전화번호\t생년월일\n");
				joinList = joinDao.selectList();
				for(Join f: joinList){
					txtForintI.append(f.toString()+"\n");
				}
				dlog.dispose();
			}

		}

	}	
}
