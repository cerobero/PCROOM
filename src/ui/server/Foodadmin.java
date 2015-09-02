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

import ui.client.Food;
import ui.client.FoodDao;


@SuppressWarnings("serial")
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
		String do_pw = "hanbit";
		String sql = null;
		try {
			Class.forName(driver);

			con = DriverManager.getConnection(db_url, db_id, do_pw);
			stmt = con.createStatement();
			// ������� ����db���� 
			sql = "select name,maker,count,price from foods";
			rs = stmt.executeQuery(sql);

			while (rs.next() == true) {
				// �ݺ������� Į���� ����

				food = new Food(); //setter , getter�� �̷���� Ŭ����
				food.setName(rs.getString(1));//�޼ҵ带 ����ؼ�DB �����Ͱ����� GET�ؼ� FOOD�� SET ����
				food.setMaker(rs.getString(2));
				food.setCount(rs.getInt(3));
				food.setPrice(rs.getInt(4));
				foodlist.add(food); //�����ϰ� ����Ʈ�� ����!

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

		JLabel label_2 = new JLabel("���İ���");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("����", Font.BOLD, 20));
		panela.add(label_2, BorderLayout.NORTH);
		//	Food food = new Food();


		addbutton = new JButton("�߰�");
		addbutton.addActionListener(new AListener()); //��ư ������ �߰� �Է�â�̶�
		panelb.add(addbutton, BorderLayout.SOUTH);

		button_d = new JButton("����");
		button_d.addActionListener(new AListener());//��ư ������ ���� �Է�â�̶�
		panelb.add(button_d, BorderLayout.SOUTH);


		txtForintI = new JTextArea();
		txtForintI.setFont(new Font("����", Font.PLAIN, 13));
		txtForintI.setEditable(false);
		txtForintI.setEnabled(false);

		for(Food f: foodlist){ //�ݺ��������� �ؽ�Ʈ ���� db���� ������ƿ� foodlist�� ������� �ϳ���
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
				name = new JTextField(20);
				maker = new JTextField(20);
				count = new JTextField(20);
				price = new JTextField(20);
				enter = new JButton("enter");
				panel.add(new JLabel("ǰ��:"));
				panel.add(name);
				panel.add(new JLabel("������:"));
				panel.add(maker);
				panel.add(new JLabel("����:"));
				panel.add(count);
				panel.add(new JLabel("����:"));
				panel.add(price);
				panel.add(enter);
				enter.addActionListener(new BListener());//enter ������ �Է¼ҽ����� �����
				dlog.add(panel);
				dlog.setTitle("�����߰�");
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
				button_d = new JButton("����");
				button_d.addActionListener(new BListener());//������ư ������ ������ �����
				panel.add(new JLabel("ǰ��:"));
				panel.add(name);

				panel.add(button_d);
				dlog.add(panel);
				dlog.setTitle("���Ļ���");
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
				food.setName(name.getText());
				food.setMaker(maker.getText());
				food.setCount(Integer.parseInt(count.getText()));
				food.setPrice(Integer.parseInt(price.getText()));

				dao.insert(food);
				JOptionPane.showMessageDialog(panela, "�Է¼���");
				txtForintI.setText("");//�ٽ��ѹ� ����
				foodlist = dao.showList();//�ٽþ��� �����Ƽ� db���ִ³��� ���������۾��� dao�� �ʾ������.
				for(Food f: foodlist){
					//System.out.println(f.toString());
					txtForintI.append(f.toString()+"\n");
				}
				dlog.dispose();

			}
			else{
				food.setName(name.getText());
				dao.delete(food);
				JOptionPane.showMessageDialog(panela, "��������");
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
