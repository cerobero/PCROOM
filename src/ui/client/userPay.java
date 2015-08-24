package ui.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import ui.client.FrameJoin.MyListener;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class userPay {

	JFrame frame;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField;
	private JButton btnNewButton_4;
	private JButton btnNewButton_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userPay window = new userPay();
					// window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public userPay() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JPanel panel = new JPanel();
		panel.setBounds(76, 59, 10, 10);
		frame.getContentPane().add(panel);

		JLabel label = new JLabel("     \uBC88\uD638");
		label.setFont(new Font("굴림", Font.PLAIN, 19));
		label.setBounds(27, 20, 72, 35);
		frame.getContentPane().add(label);

		JLabel lblNewLabel = new JLabel("    \uC0AC\uC6A9\uC790 \uC120\uD6C4\uBD88");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 19));
		lblNewLabel.setBounds(111, 20, 168, 35);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(134, 10, -1, 0);
		frame.getContentPane().add(lblNewLabel_1);

		JButton btnNewButton = new JButton("\uAC8C\uC7841");
		btnNewButton.setBounds(319, 28, 103, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\uAC8C\uC7842");
		btnNewButton_1.setBounds(319, 74, 103, 23);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("\uAC8C\uC7843");
		btnNewButton_2.setBounds(319, 122, 103, 23);
		frame.getContentPane().add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("\uAC8C\uC7844");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_3.setBounds(319, 166, 103, 23);
		frame.getContentPane().add(btnNewButton_3);

		btnNewButton_4 = new JButton("\uC74C\uC2DD \uC8FC\uBB38");
		btnNewButton_4.setBounds(319, 212, 103, 23);
		frame.getContentPane().add(btnNewButton_4);
		Mylistener li = new Mylistener();
		btnNewButton_4.addActionListener(li);

		JButton btnNewButton_5 = new JButton("\uAD00\uB9AC\uC790 \uD638\uCD9C");
		btnNewButton_5.setBounds(176, 212, 103, 23);
		frame.getContentPane().add(btnNewButton_5);

		btnNewButton_6 = new JButton("\uC77C\uC2DC\uC815\uC9C0");
		btnNewButton_6.setBounds(30, 212, 97, 23);
		frame.getContentPane().add(btnNewButton_6);
		// 일시정지 이벤트
		btnNewButton_6.addActionListener(li);

		textField_1 = new JTextField();
		textField_1.setText("          \uB0A8\uC740\uC2DC\uAC04");
		textField_1.setBounds(134, 120, 145, 23);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setText("          \uC0AC\uC6A9\uC694\uAE08");
		textField_2.setColumns(10);
		textField_2.setBounds(134, 166, 145, 23);
		frame.getContentPane().add(textField_2);

		textField = new JTextField();
		textField.setText("          \uC0AC\uC6A9\uC2DC\uAC04");
		textField.setColumns(10);
		textField.setBounds(134, 74, 145, 23);
		frame.getContentPane().add(textField);

		JLabel lblNewLabel_2 = new JLabel("     \uC0AC\uC6A9\uC2DC\uAC04 : ");
		lblNewLabel_2.setBounds(30, 76, 98, 19);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel label_2 = new JLabel("     \uC0AC\uC6A9\uC694\uAE08 : ");
		label_2.setBounds(30, 168, 98, 19);
		frame.getContentPane().add(label_2);

		JLabel label_1 = new JLabel("     \uB0A8\uC740\uC2DC\uAC04 : ");
		label_1.setBounds(30, 124, 98, 19);
		frame.getContentPane().add(label_1);
	}
	///////////////////////////////////////////////////////////////////////////////////////

	class Mylistener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnNewButton_4) {
				new Foodorder();
			} else if (e.getSource() == btnNewButton_6) {
				new stop();
			}
		}

	}

}
