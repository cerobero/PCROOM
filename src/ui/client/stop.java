package ui.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

public class stop {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stop window = new stop();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public stop() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		
		JFrame frame_1 = new JFrame();
		frame_1.setBounds(-10008, -10030, 450, 300);
		frame_1.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(76, 59, 10, 10);
		frame_1.getContentPane().add(panel);
		
		JLabel label = new JLabel("      \uC77C\uC2DC\uC815\uC9C0\uC911");
		label.setFont(new Font("±¼¸²", Font.PLAIN, 17));
		label.setBounds(98, 39, 151, 35);
		frame_1.getContentPane().add(label);
		
		JLabel label_2 = new JLabel("New label");
		label_2.setBounds(134, 10, -1, 0);
		frame_1.getContentPane().add(label_2);
		
		JButton button = new JButton("\uAC8C\uC7841");
		button.setBounds(319, 46, 103, 23);
		frame_1.getContentPane().add(button);
		
		JButton button_1 = new JButton("\uAC8C\uC7842");
		button_1.setBounds(319, 89, 103, 23);
		frame_1.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\uAC8C\uC7843");
		button_2.setBounds(319, 129, 103, 23);
		frame_1.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("\uAC8C\uC7844");
		button_3.setBounds(319, 169, 103, 23);
		frame_1.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("\uC74C\uC2DD \uC8FC\uBB38");
		button_4.setBounds(319, 212, 103, 23);
		frame_1.getContentPane().add(button_4);
		
		JButton button_5 = new JButton("\uAD00\uB9AC\uC790 \uD638\uCD9C");
		button_5.setBounds(176, 212, 103, 23);
		frame_1.getContentPane().add(button_5);
		
		JButton button_6 = new JButton("\uB2E4\uC2DC\uC2DC\uC791");
		button_6.setBounds(30, 212, 97, 23);
		frame_1.getContentPane().add(button_6);
		
		textField = new JTextField();
		textField.setText("        \uB0A8\uC740\uC2DC\uAC04");
		textField.setColumns(10);
		textField.setBounds(134, 127, 145, 23);
		frame_1.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setText("        \uC0AC\uC6A9\uC694\uAE08");
		textField_1.setColumns(10);
		textField_1.setBounds(134, 169, 145, 23);
		frame_1.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setText("        \uC0AC\uC6A9\uC2DC\uAC04");
		textField_2.setColumns(10);
		textField_2.setBounds(134, 89, 145, 23);
		frame_1.getContentPane().add(textField_2);
		
		JLabel label_3 = new JLabel("     \uC0AC\uC6A9\uC2DC\uAC04 : ");
		label_3.setBounds(30, 91, 98, 19);
		frame_1.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("     \uC0AC\uC6A9\uC694\uAE08 : ");
		label_4.setBounds(30, 171, 98, 19);
		frame_1.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("     \uB0A8\uC740\uC2DC\uAC04 : ");
		label_5.setBounds(30, 131, 98, 19);
		frame_1.getContentPane().add(label_5);
		frame_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JFrame frame_2 = new JFrame();
		frame_2.getContentPane().setLayout(null);
		
		JLabel label_7 = new JLabel("   \uC77C\uC2DC\uC911\uC9C0\uC911");
		label_7.setFont(new Font("±¼¸²", Font.PLAIN, 17));
		label_7.setBounds(111, 34, 168, 35);
		frame_2.getContentPane().add(label_7);
		
		JLabel label_8 = new JLabel("New label");
		label_8.setBounds(134, 10, -1, 0);
		frame_2.getContentPane().add(label_8);
		
		JButton button_7 = new JButton("\uAC8C\uC7841");
		button_7.setBounds(319, 46, 103, 23);
		frame_2.getContentPane().add(button_7);
		
		JButton button_8 = new JButton("\uAC8C\uC7842");
		button_8.setBounds(319, 89, 103, 23);
		frame_2.getContentPane().add(button_8);
		
		JButton button_9 = new JButton("\uAC8C\uC7843");
		button_9.setBounds(319, 129, 103, 23);
		frame_2.getContentPane().add(button_9);
		
		JButton button_10 = new JButton("\uAC8C\uC7844");
		button_10.setBounds(319, 169, 103, 23);
		frame_2.getContentPane().add(button_10);
		
		JButton button_11 = new JButton("\uC74C\uC2DD \uC8FC\uBB38");
		button_11.setBounds(319, 212, 103, 23);
		frame_2.getContentPane().add(button_11);
		
		JButton button_12 = new JButton("\uAD00\uB9AC\uC790 \uD638\uCD9C");
		button_12.setBounds(176, 212, 103, 23);
		frame_2.getContentPane().add(button_12);
		
		JButton button_13 = new JButton("\uC77C\uC2DC\uC815\uC9C0");
		button_13.setBounds(30, 212, 97, 23);
		frame_2.getContentPane().add(button_13);
		
		textField_3 = new JTextField();
		textField_3.setText("        \uB0A8\uC740\uC2DC\uAC04");
		textField_3.setColumns(10);
		textField_3.setBounds(134, 127, 145, 23);
		frame_2.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setText("        \uC0AC\uC6A9\uC694\uAE08");
		textField_4.setColumns(10);
		textField_4.setBounds(134, 169, 145, 23);
		frame_2.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setText("        \uC0AC\uC6A9\uC2DC\uAC04");
		textField_5.setColumns(10);
		textField_5.setBounds(134, 89, 145, 23);
		frame_2.getContentPane().add(textField_5);
		
		JLabel label_9 = new JLabel("     \uC0AC\uC6A9\uC2DC\uAC04 : ");
		label_9.setBounds(30, 91, 98, 19);
		frame_2.getContentPane().add(label_9);
		
		JLabel label_10 = new JLabel("     \uC0AC\uC6A9\uC694\uAE08 : ");
		label_10.setBounds(30, 171, 98, 19);
		frame_2.getContentPane().add(label_10);
		
		JLabel label_11 = new JLabel("     \uB0A8\uC740\uC2DC\uAC04 : ");
		label_11.setBounds(30, 131, 98, 19);
		frame_2.getContentPane().add(label_11);
		frame_2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame_2.setBounds(0, 10, 450, 300);
		
		JLabel label_13 = new JLabel("            \uC77C\uC2DC\uC815\uC9C0\uC911");
		label_13.setFont(new Font("±¼¸²", Font.PLAIN, 19));
		label_13.setBounds(30, 20, 249, 35);
		frame.getContentPane().add(label_13);
		
		JButton button_14 = new JButton("\uAC8C\uC7841");
		button_14.setBounds(319, 32, 103, 23);
		frame.getContentPane().add(button_14);
		
		JLabel label_14 = new JLabel("     \uC0AC\uC6A9\uC2DC\uAC04 : ");
		label_14.setBounds(30, 80, 98, 19);
		frame.getContentPane().add(label_14);
		
		textField_6 = new JTextField();
		textField_6.setText("        \uC0AC\uC6A9\uC2DC\uAC04");
		textField_6.setColumns(10);
		textField_6.setBounds(134, 78, 145, 23);
		frame.getContentPane().add(textField_6);
		
		JButton button_15 = new JButton("\uAC8C\uC7842");
		button_15.setBounds(319, 78, 103, 23);
		frame.getContentPane().add(button_15);
		
		JLabel label_15 = new JLabel("     \uB0A8\uC740\uC2DC\uAC04 : ");
		label_15.setBounds(30, 128, 98, 19);
		frame.getContentPane().add(label_15);
		
		textField_7 = new JTextField();
		textField_7.setText("        \uB0A8\uC740\uC2DC\uAC04");
		textField_7.setColumns(10);
		textField_7.setBounds(134, 124, 145, 23);
		frame.getContentPane().add(textField_7);
		
		JButton button_16 = new JButton("\uAC8C\uC7843");
		button_16.setBounds(319, 126, 103, 23);
		frame.getContentPane().add(button_16);
		
		JLabel label_16 = new JLabel("     \uC0AC\uC6A9\uC694\uAE08 : ");
		label_16.setBounds(30, 174, 98, 19);
		frame.getContentPane().add(label_16);
		
		textField_8 = new JTextField();
		textField_8.setText("        \uC0AC\uC6A9\uC694\uAE08");
		textField_8.setColumns(10);
		textField_8.setBounds(134, 172, 145, 23);
		frame.getContentPane().add(textField_8);
		
		JButton button_17 = new JButton("\uAC8C\uC7844");
		button_17.setBounds(319, 172, 103, 23);
		frame.getContentPane().add(button_17);
		
		JButton button_18 = new JButton("\uB2E4\uC2DC\uC2DC\uC791");
		button_18.setBounds(30, 217, 97, 23);
		frame.getContentPane().add(button_18);
		
		JButton button_19 = new JButton("\uAD00\uB9AC\uC790 \uD638\uCD9C");
		button_19.setBounds(176, 217, 103, 23);
		frame.getContentPane().add(button_19);
		
		JButton button_20 = new JButton("\uC74C\uC2DD \uC8FC\uBB38");
		button_20.setBounds(319, 217, 103, 23);
		frame.getContentPane().add(button_20);
		
		frame.setSize(500, 300);
	}
}
