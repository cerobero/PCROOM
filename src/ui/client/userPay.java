package ui.client;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import asset.GeneralSet;
import asset.GeneralSet.ServerAct;

public class userPay {

	JFrame frame;
	private JTextField spareTimeText;
	private JTextField usingFeeText;
	private JTextField usedTimeText;
	private JButton orderButton;
	private JButton pauseButton;
	private JButton adminCall;
	private JLabel numberLabel;
	private JLabel payLabel;

	private BufferedReader reader;
	private BufferedWriter writer;

	private String userId;

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					userPay window = new userPay();
	//					// window.frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	/**
	 * Create the application.
	 */
	public userPay(String userId) {
		this.userId = userId;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		//��ġ���߾�
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(76, 59, 10, 10);
		frame.getContentPane().add(panel);

		numberLabel = new JLabel("     ��ȣ");
		numberLabel .setFont(new Font("����", Font.PLAIN, 19));
		numberLabel .setBounds(27, 20, 72, 35);
		frame.getContentPane().add(numberLabel );

		payLabel = new JLabel("      ����� ���ĺ�");
		payLabel.setFont(new Font("����", Font.PLAIN, 19));
		payLabel.setBounds(111, 20, 168, 35);
		frame.getContentPane().add(payLabel);

		JButton game1Button = new JButton("����1");
		game1Button.setBounds(319, 28, 103, 23);
		frame.getContentPane().add(game1Button);

		JButton game2Button = new JButton("����2");
		game2Button.setBounds(319, 74, 103, 23);
		frame.getContentPane().add(game2Button);

		JButton game3Button = new JButton("����3");
		game3Button.setBounds(319, 122, 103, 23);
		frame.getContentPane().add(game3Button);

		JButton game4Button = new JButton("����4");
		game4Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		game4Button.setBounds(319, 166, 103, 23);
		frame.getContentPane().add(game4Button);

		orderButton = new JButton("���� �ֹ�");
		orderButton.setBounds(319, 212, 103, 23);
		frame.getContentPane().add(orderButton);
		Mylistener li = new Mylistener();
		orderButton.addActionListener(li);

		adminCall = new JButton("������ ȣ��");
		adminCall.setBounds(176, 212, 103, 23);
		frame.getContentPane().add(adminCall);
		adminCall.addActionListener(li);
		
		pauseButton = new JButton("�Ͻ� ����");
		pauseButton.setBounds(30, 212, 97, 23);
		frame.getContentPane().add(pauseButton);
		// �Ͻ����� �̺�Ʈ
		pauseButton.addActionListener(li);

		spareTimeText = new JTextField();
		spareTimeText.setText("          ���� �ð�");
		spareTimeText.setBounds(134, 120, 145, 23);
		frame.getContentPane().add(spareTimeText);
		spareTimeText.setColumns(10);

		usingFeeText = new JTextField();
		usingFeeText.setText("          ��� ���");
		usingFeeText.setColumns(10);
		usingFeeText.setBounds(134, 166, 145, 23);
		frame.getContentPane().add(usingFeeText);

		usedTimeText = new JTextField();
		usedTimeText.setText("          ��� �ð�");
		usedTimeText.setColumns(10);
		usedTimeText.setBounds(134, 74, 145, 23);
		frame.getContentPane().add(usedTimeText);

		JLabel usedTimeLabel = new JLabel("     ���ð� : ");
		usedTimeLabel.setBounds(30, 76, 98, 19);
		frame.getContentPane().add(usedTimeLabel);

		JLabel usingFeeLabel = new JLabel("     ����� : ");
		usingFeeLabel.setBounds(30, 168, 98, 19);
		frame.getContentPane().add(usingFeeLabel);

		JLabel spareTimeLabel = new JLabel("     �����ð� : ");
		spareTimeLabel.setBounds(30, 124, 98, 19);
		frame.getContentPane().add(spareTimeLabel);

		try
		{
			Socket socket = new Socket(InetAddress.getByName("localhost"), 9999);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			writer.write(userId + '\n');
			writer.flush();

			threadStart();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void threadStart()
	{
		ReadThread rt = new ReadThread();

		rt.start();
	}
	///////////////////////////////////////////////////////////////////////////////////////

	class Mylistener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == orderButton) {
				new Foodorder(writer);
			} else if (e.getSource() == pauseButton) {
				new stop();
			}
			else if (e.getSource() == adminCall)
			{
				new Manager_Call(writer);
			}
		}

	}

	private class ReadThread extends Thread
	{
		@Override
		public void run()
		{
			for (;;)
			{
				try
				{
					GeneralSet.print("Read READY");
					String act = reader.readLine();
					GeneralSet.print(act);
					if (act != null && !act.equals(""))
					{
						switch (ServerAct.valueOf(act))
						{
//						case EXIT_USE:
//							GeneralSet.print("EXIT ACT");
//							frame.dispose();
//							System.exit(0);
//							break;
						default:
							break;
						}
					}
					else if (act == null)
					{
						frame.dispose();
						System.exit(0);
						return;
					}
				}
				catch (SocketException e)
				{
					frame.dispose();
					System.exit(0);
					return;
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

}
