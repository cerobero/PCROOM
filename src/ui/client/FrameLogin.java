package ui.client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import asset.GeneralSet;
import dao.Join;
import dao.JoinDao;

public class FrameLogin extends JFrame {

	private JTextField textfield_id;
	private JTextField textfield_pw;
	private JButton button_join;
	private JButton button_login;
	////////////////////////////////////////
	JoinDao dao = new JoinDao();
	Join findId;
	////////////////////////////////////////
	

	public FrameLogin() {
		setLayout(null);

		JLabel label_title = new JLabel("PC�� �̸�");
		label_title.setFont(new Font("���� ���", Font.PLAIN, 50));
		label_title.setBounds(5, 5, 500, 60);
		add(label_title);

		JPanel panel_login = new JPanel();
		panel_login.setBounds(450, 50, 250, 300);
		add(panel_login);

		panel_login.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		panel_login.setLayout(null);

		JLabel label_login = new JLabel("�α���");
		label_login.setFont(new Font("���� ���", Font.PLAIN, 30));
		label_login.setBounds(80, 30, 200, 50);
		panel_login.add(label_login);
		
		JLabel label_id = new JLabel("ID");
		label_id.setBounds(50, 120, 50, 20);
		panel_login.add(label_id);

		textfield_id = new JTextField();
		textfield_id.setBounds(100, 120, 100, 20);
		panel_login.add(textfield_id);

		
		JLabel label_pw = new JLabel("PW");
		label_pw.setBounds(50, 170, 50, 20);
		panel_login.add(label_pw);

		
		textfield_pw = new JPasswordField();
		textfield_pw.setBounds(100, 170, 100, 20);
		panel_login.add(textfield_pw);
		
		
		button_join = new JButton("ȸ������");
		panel_login.add(button_join);
		button_join.setBounds(20, 250, 100, 30);
		
		
		button_login = new JButton("�α���");
		panel_login.add(button_login);
		button_login.setBounds(130, 250, 100, 30);
		
		MyListener my = new MyListener();
		button_join.addActionListener(my);
		button_login.addActionListener(my);
		
	
		setSize(750, 400);
		setTitle("PC�� ���α׷�");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}
	
	class MyListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == button_join){
		

				FrameJoin frameJoin =new FrameJoin();
				
				
			}else if(e.getSource() == button_login){
			
			findId=dao.select(textfield_id.getText());
			
			if(findId !=null){
			//	GeneralSet.print(findId.getUserId());
			//	GeneralSet.print(findId.getPassword());
			//	GeneralSet.print(textfield_pw.getText());
				if(findId.getPassword().equals(textfield_pw.getText())){
					GeneralSet.print("�α��� ����!");
					textfield_id.setText("");
					textfield_pw.setText("");
				
					userPay userpay = new userPay();
					
					dao.exit();
					dispose();
					
					
				}else{
					GeneralSet.print("��й�ȣ ����");
					textfield_pw.setText("");
				}
			}else{
				GeneralSet.print("���̵� ����");
				textfield_id.setText("");
				textfield_pw.setText("");
			}
			
		
			}
		}
		
	
	}

	
	
	
	public static void main(String[] args) {
		new FrameLogin();
	}

}
