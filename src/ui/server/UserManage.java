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
			// ������� ����db���� 
			sql = "select user_id, password, cellphone, birth from join_tb";
			rs = stmt.executeQuery(sql);

			while (rs.next() == true) {
				// �ݺ������� Į���� ����

				join = new Join(); //setter , getter�� �̷���� Ŭ����
				join.setUserId(rs.getString(1));//�޼ҵ带 ����ؼ�DB �����Ͱ����� GET�ؼ� FOOD�� SET ����
				join.setPassword(rs.getString(2));
				join.setCellPhone(rs.getString(3));
				join.setBirth(rs.getString(4));
				joinList.add(join); //�����ϰ� ����Ʈ�� ����!

				//DB ������ ���ܼ��ϰ�
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

		JLabel label_2 = new JLabel("ȸ������");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("����", Font.BOLD, 20));
		panela.add(label_2, BorderLayout.NORTH);

		addbutton = new JButton("�߰�");
		addbutton.addActionListener(new AListener()); //��ư ������ �߰� �Է�â�̶�
		panelb.add(addbutton, BorderLayout.SOUTH);

		button_d = new JButton("����");
		button_d.addActionListener(new AListener());//��ư ������ ���� �Է�â�̶�
		panelb.add(button_d, BorderLayout.SOUTH);


		txtForintI = new JTextArea();
		txtForintI.setFont(new Font("����", Font.PLAIN, 13));
		txtForintI.setEditable(false);
		//		txtForintI.setEnabled(false);
		txtForintI.append("ID\t��ȭ��ȣ\t�������\n");

		for(Join f: joinList){ //�ݺ��������� �ؽ�Ʈ ���� db���� ������ƿ� foodlist�� ������� �ϳ���
			//�ٿ��� �̾����
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
	private class AListener implements ActionListener{//�Է�,���� ��ư ������ ��Ÿ���� â��

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
				cpPanel.add(new JLabel("��ȭ��ȣ : "));
				cpPanel.add(cellPhone);
				brPanel.add(new JLabel("���� : "));
				brPanel.add(birth);
				panel.add(idPanel);
				panel.add(pwPanel);
				panel.add(cpPanel);
				panel.add(brPanel);
				panel.add(enter);
				enter.addActionListener(new BListener());//enter ������ �Է¼ҽ����� �����
				dlog.add(panel);
				dlog.setTitle("ȸ���߰�");
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
				button_d = new JButton("����");
				button_d.addActionListener(new BListener());//������ư ������ ������ �����
				panel.add(new JLabel("ID :"));
				panel.add(userId);

				panel.add(button_d);
				dlog.add(panel);
				dlog.setTitle("ȸ��Ż��");
				dlog.setLocationRelativeTo(panel);
				dlog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				dlog.setSize(300,100);
				dlog.setVisible(true);
			}
		}

	}
	private class BListener implements ActionListener{//�Է¹�ư�� ������ư�� ��ɱ���

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==enter){
				join.setUserId(userId.getText());
				join.setPassword(password.getText());
				join.setCellPhone(cellPhone.getText());
				join.setBirth(birth.getText());

				joinDao.insert(join);
				JOptionPane.showMessageDialog(panela, "�Է¼���");
				txtForintI.setText("");//�ٽ��ѹ� ����
				txtForintI.append("ID\t��ȭ��ȣ\t�������\n");
				joinList = joinDao.selectList();//�ٽþ��� �����Ƽ� db���ִ³��� ���������۾��� dao�� �ʾ������.
				for(Join f: joinList){
					txtForintI.append(f.toString()+"\n");
				}
				dlog.dispose();

			}
			else{
				join.setUserId(userId.getText());
				joinDao.delete(join.getUserId());
				JOptionPane.showMessageDialog(panela, "��������");
				txtForintI.setText("");
				txtForintI.append("ID\t��ȭ��ȣ\t�������\n");
				joinList = joinDao.selectList();
				for(Join f: joinList){
					txtForintI.append(f.toString()+"\n");
				}
				dlog.dispose();
			}

		}

	}	
}
