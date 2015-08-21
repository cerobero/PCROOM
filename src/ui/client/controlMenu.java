package ui.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class controlMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					controlMenu window = new controlMenu();
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
	public controlMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 286, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("        \uAD00\uB9AC\uBA54\uB274");
		label.setFont(new Font("±¼¸²", Font.PLAIN, 19));
		label.setBounds(48, 21, 168, 35);
		frame.getContentPane().add(label);
		
		JPanel panel = new JPanel();
		panel.setBounds(26, 71, 221, 129);
		frame.getContentPane().add(panel);
		
		JButton btnNewButton = new JButton("\uC800\uC7A5");
		btnNewButton.setBounds(26, 215, 97, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("\uCDE8\uC18C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(150, 215, 97, 23);
		frame.getContentPane().add(button);
	}

}
