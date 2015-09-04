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

		JLabel label_title = new JLabel("관리자 로그인");
		label_title.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
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
		
		button_login = new JButton("로그인");
		button_login.setBounds(90, 250, 100, 30);
		add(button_login);
		//이벤트
		button_login.addActionListener(new MyListener());
		/* GEONWOO-CHO 0821 Enter Login */
		textfield_id.addActionListener(new MyListener());
		textfield_pw.addActionListener(new MyListener());
		
		setSize(300, 350);
		setTitle("관리자 로그인");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	class MyListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//관리자 아이디:abcd
			//관리자 비밀번호:1234
			if(textfield_id.getText().equals(GeneralSet.ADMIN_ID)){
				if(textfield_pw.getText().equals(GeneralSet.ADMIN_PW)){
					System.out.println("로그인 성공!");
					textfield_id.setText("");
					textfield_pw.setText("");
					
					/* GEONWOO-CHO 0821 FrameAdmin */
					new FrameAdmin();
					dispose();
				}else{
					System.out.println("비밀번호 오류");
					textfield_pw.setText("");
				}
			}else{
				System.out.println("아이디 오류");
				textfield_id.setText("");
				textfield_pw.setText("");
			}

			
			
		}
		
	
	}

	
	
	public static void main(String[] args) {
		new LoginAdmin();
	}
}
