package ui.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JTextField usedTimeText;
	private JTextField spareTimeText;
	private JTextField usingFeeText;

	/**
	 * Launch the application.
//	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stop window = new stop();
					//window.frame.setVisible(true);
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
		frame.setVisible(true);
		
		
		JFrame frame_1 = new JFrame();
		frame_1.setBounds(-10008, -10030, 450, 300);
		frame_1.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(76, 59, 10, 10);
		frame_1.getContentPane().add(panel);

		JLabel payLabel = new JLabel("      �Ͻ�������");
		payLabel.setFont(new Font("����", Font.PLAIN, 19));
		payLabel.setBounds(111, 20, 168, 35);
		frame.getContentPane().add(payLabel);
		
		JButton game1Button = new JButton("����1");
		game1Button.setBounds(319, 28, 103, 23);
		frame.getContentPane().add(game1Button);
		
		JLabel usedTimeLabel = new JLabel("     ���ð� : ");
		usedTimeLabel.setBounds(30, 76, 98, 19);
		frame.getContentPane().add(usedTimeLabel);
		
		usedTimeText = new JTextField();
		usedTimeText.setText("          ��� �ð�");
		usedTimeText.setColumns(10);
		usedTimeText.setBounds(134, 74, 145, 23);
		frame.getContentPane().add(usedTimeText);
		
		JButton game2Button = new JButton("����2");
		game2Button.setBounds(319, 74, 103, 23);
		frame.getContentPane().add(game2Button);
		
		JLabel spareTimeLabel = new JLabel("     �����ð� : ");
		spareTimeLabel.setBounds(30, 124, 98, 19);
		frame.getContentPane().add(spareTimeLabel);
		
		spareTimeText = new JTextField();
		spareTimeText.setText("          ���� �ð�");
		spareTimeText.setBounds(134, 120, 145, 23);
		frame.getContentPane().add(spareTimeText);
		spareTimeText.setColumns(10);
		
		//����3
		JButton game3Button = new JButton("����3");
		game3Button.setBounds(319, 122, 103, 23);
		frame.getContentPane().add(game3Button);
		
		JLabel usingFeeLabel = new JLabel("     ����� : ");
		usingFeeLabel.setBounds(30, 168, 98, 19);
		frame.getContentPane().add(usingFeeLabel);
		
		usingFeeText = new JTextField();
		usingFeeText.setText("          ��� ���");
		usingFeeText.setColumns(10);
		usingFeeText.setBounds(134, 166, 145, 23);
		frame.getContentPane().add(usingFeeText);
		
		//����4
		JButton game4Button = new JButton("����4");
		game4Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		game4Button.setBounds(319, 166, 103, 23);
		frame.getContentPane().add(game4Button);
		
		//�ٽý���
		JButton restartButton = new JButton("�ٽ� ����");
		restartButton.setBounds(30, 212, 97, 23);
		frame.getContentPane().add(restartButton);
		
		restartButton.addActionListener(new Mylistener());
		
		JButton adminCall = new JButton("������ ȣ��");
		adminCall.setBounds(176, 212, 103, 23);
		frame.getContentPane().add(adminCall);
		
		//�����ֹ�
		JButton orderButton = new JButton("���� �ֹ�");
		orderButton.setBounds(319, 212, 103, 23);
		frame.getContentPane().add(orderButton);
		
		frame.setSize(500, 300);
	}
	class Mylistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			frame.dispose();
		}
		
	}
	
}
