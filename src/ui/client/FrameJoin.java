package ui.client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.Join;
import dao.JoinDao;

@SuppressWarnings("serial")
public class FrameJoin extends JFrame {
	private JTextField textfield_id;
	private JTextField textfield_pw;
	private JTextField textfield_phone;
	private JTextField textfield_birth;
	private JButton button_join;
	private JButton button_cancel;
	////////////////////////////////////////////////////////////////////
	JoinDao dao = new JoinDao();
	Join newJoin;

	////////////////////////////////////////////////////////////////////
	public FrameJoin() {

		setLayout(null);

		JLabel label_title = new JLabel("회원가입");
		label_title.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		label_title.setBounds(80, 30, 200, 50);
		add(label_title);

		JLabel label_id = new JLabel("ID");
		label_id.setBounds(40, 100, 50, 20);
		add(label_id);

		textfield_id = new JTextField();
		textfield_id.setBounds(130, 100, 100, 20);
		add(textfield_id);

		JLabel label_pw = new JLabel("PW");
		label_pw.setBounds(40, 130, 50, 20);
		add(label_pw);

		textfield_pw = new JTextField();
		textfield_pw.setBounds(130, 130, 100, 20);
		add(textfield_pw);

		JLabel label_phone = new JLabel("핸드폰");
		label_phone.setBounds(40, 160, 50, 20);
		add(label_phone);

		textfield_phone = new JTextField();
		textfield_phone.setBounds(130, 160, 100, 20);
		add(textfield_phone);

		JLabel label_birth = new JLabel("생년월일");
		label_birth.setBounds(40, 190, 80, 20);
		add(label_birth);

		textfield_birth = new JTextField();
		textfield_birth.setBounds(130, 190, 100, 20);
		add(textfield_birth);

		button_join = new JButton("회원가입");
		button_join.setBounds(30, 250, 100, 30);
		add(button_join);

		button_cancel = new JButton("취소");
		button_cancel.setBounds(150, 250, 100, 30);
		add(button_cancel);

		// 이벤트
		MyListener my = new MyListener();
		button_join.addActionListener(my);
		button_cancel.addActionListener(my);

		setSize(300, 350);
		setTitle("회원가입");
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		// 위치정중앙
		setLocationRelativeTo(null);

	}

	///////////////////////////////////////////////////////
	class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == button_join) {

				boolean isJoin = false;

				isJoin = (textfield_id.getText() != null && textfield_id.getText().length() > 0
						&& textfield_pw.getText() != null && textfield_pw.getText().length() > 0);

				if (isJoin) {
					System.out.println("test:" + textfield_id.getText());
					newJoin = new Join();
					newJoin.setUserId(textfield_id.getText());
					newJoin.setPassword(textfield_pw.getText());
					newJoin.setCellPhone(textfield_phone.getText());
					newJoin.setBirth(textfield_birth.getText());

					dao.insert(newJoin);

					dao.exit();
					dispose();
				}
				// else{
				// System.out.println("input 모자람");
				// return;
				// }

			} else if (e.getSource() == button_cancel) {
				dispose();
			}
			// dao.exit();
		}

	}

	///////////////////////////////////////////////////////
	//
	//
	// public static void main(String[] args) {
	// new FrameJoin();
	// }
	//
}
