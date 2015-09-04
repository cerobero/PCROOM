package ui.server;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import asset.GeneralSet;

@SuppressWarnings("serial")
public class LoginAdmin extends JFrame {
	private JTextField textfield_id;
	private JTextField textfield_pw;
	private JButton button_login;

	public LoginAdmin() {
		setLayout(null);

		JLabel label_title = new JLabel("������ �α���");
		label_title.setFont(new Font("���� ���", Font.PLAIN, 30));
		label_title.setBounds(40, 30, 200, 50);
		add(label_title);
		
		JLabel label_id = new JLabel("ID");
		label_id.setBounds(50, 120, 50, 20);
		add(label_id);
		
		textfield_id = new JTextField();
		textfield_id.setBounds(100, 120, 100, 20);
		add(textfield_id);
		
		JLabel label_pw = new JLabel("PW");
		label_pw.setBounds(50, 170, 50, 20);
		add(label_pw);
		
		/* GEONWOO-CHO 0821 Set to JPasswordField
		textfield_pw = new JTextField();
		*/
		textfield_pw = new JPasswordField();
		textfield_pw.setBounds(100, 170, 100, 20);
		add(textfield_pw);
		
		button_login = new JButton("�α���");
		button_login.setBounds(90, 250, 100, 30);
		add(button_login);
		//�̺�Ʈ
		button_login.addActionListener(new MyListener());
		/* GEONWOO-CHO 0821 Enter Login */
		textfield_id.addActionListener(new MyListener());
		textfield_pw.addActionListener(new MyListener());
		
		setSize(300, 350);
		setTitle("������ �α���");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	class MyListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//������ ���̵�:abcd
			//������ ��й�ȣ:1234
			if(textfield_id.getText().equals(GeneralSet.ADMIN_ID)){
				if(textfield_pw.getText().equals(GeneralSet.ADMIN_PW)){
					System.out.println("�α��� ����!");
					textfield_id.setText("");
					textfield_pw.setText("");
					
					/* GEONWOO-CHO 0821 FrameAdmin */
					new FrameAdmin();
					dispose();
				}else{
					System.out.println("��й�ȣ ����");
					textfield_pw.setText("");
				}
			}else{
				System.out.println("���̵� ����");
				textfield_id.setText("");
				textfield_pw.setText("");
			}

			
			
		}
		
	
	}

	
	
	public static void main(String[] args) {
		new LoginAdmin();
	}
}
