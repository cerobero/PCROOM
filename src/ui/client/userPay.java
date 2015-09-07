package ui.client;

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
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import asset.GeneralSet;
import asset.GeneralSet.ServerAct;

//public class userPay implements Runnable {
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

	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;

	private String userId;
	private int pcNum;
	
	private JButton game1Button;
	private JButton game2Button;
	private JButton game3Button;
	private JButton game4Button;
	////////////////////////////////////////
	private Date startDate = null;// ���ð� ���
	public long intervalMilli; //���ð�,�����ð� ���

	private String useMoney = "0"; // �����
	public long useTime = 0; // ����� ���
	public double secMoney = 1000.0 / (60 * 60); // �ʴ� ���
	public int addMoney = 0; // ���ӹ�ư���� �߰��Ǵ� �ݾ�

	public boolean start = true; // �Ͻ�����
	public int stopRestart = 0; // ��ư : �Ͻ�����<->�ٽý���
	
	public int g1=0; //�Ͻ����� �Ŀ��� ���ȴ� ��ư �����ְ�
	public int g2=0;
	public int g3=0;
	public int g4=0;
	
	public Date stopDate =null; //�Ͻ�����,�ٽý��� �� ���ð�
	public Date restartDate = null;
	long reDiff=0;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// userPay window = new userPay();
	// // window.frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the application.
	 */
	public userPay(String userId, Date startDate) {
		this.userId = userId;
		this.startDate = startDate; // �ð����

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

		// ��ġ���߾�
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(76, 59, 10, 10);
		frame.getContentPane().add(panel);

		numberLabel = new JLabel("     ��ȣ");
		numberLabel.setFont(new Font("����", Font.PLAIN, 19));
		numberLabel.setBounds(27, 20, 72, 35);
		frame.getContentPane().add(numberLabel);

		payLabel = new JLabel("�������");
		payLabel.setFont(new Font("����", Font.PLAIN, 19));
		payLabel.setBounds(150, 20, 114, 35);
		frame.getContentPane().add(payLabel);

		game1Button = new JButton("����1");
		game1Button.setBounds(319, 28, 103, 23);
		frame.getContentPane().add(game1Button);

		game2Button = new JButton("����2");
		game2Button.setBounds(319, 74, 103, 23);
		frame.getContentPane().add(game2Button);

		game3Button = new JButton("����3");
		game3Button.setBounds(319, 122, 103, 23);
		frame.getContentPane().add(game3Button);

		game4Button = new JButton("����4");
		game4Button.setBounds(319, 166, 103, 23);
		frame.getContentPane().add(game4Button);

		Mylistener li = new Mylistener();
		game1Button.addActionListener(li);
		game2Button.addActionListener(li);
		game3Button.addActionListener(li);
		game4Button.addActionListener(li);

		orderButton = new JButton("���� �ֹ�");
		orderButton.setBounds(319, 212, 103, 23);
		frame.getContentPane().add(orderButton);
		orderButton.addActionListener(li);

		adminCall = new JButton("������ ȣ��");
		adminCall.setBounds(176, 212, 103, 23);
		frame.getContentPane().add(adminCall);
		adminCall.addActionListener(li);

		pauseButton = new JButton("�Ͻ� ����");
		pauseButton.setBounds(30, 212, 97, 23);
		frame.getContentPane().add(pauseButton);
		pauseButton.addActionListener(li);

		spareTimeText = new JTextField();
		spareTimeText.setEditable(false);
		spareTimeText.setBounds(134, 120, 145, 23);
		frame.getContentPane().add(spareTimeText);
		spareTimeText.setColumns(10);

		usingFeeText = new JTextField();
		usingFeeText.setEditable(false);
		usingFeeText.setColumns(10);
		usingFeeText.setBounds(134, 166, 145, 23);
		frame.getContentPane().add(usingFeeText);

		usedTimeText = new JTextField();
		usedTimeText.setEditable(false);
		usedTimeText.setColumns(10);
		usedTimeText.setBounds(134, 74, 145, 23);
		frame.getContentPane().add(usedTimeText);

		JLabel usedTimeLabel = new JLabel("\uC0AC\uC6A9\uC2DC\uAC04 : ");
		usedTimeLabel.setBounds(46, 78, 63, 19);
		frame.getContentPane().add(usedTimeLabel);

		JLabel usingFeeLabel = new JLabel("\uC0AC\uC6A9\uC694\uAE08 : ");
		usingFeeLabel.setBounds(47, 167, 63, 19);
		frame.getContentPane().add(usingFeeLabel);

		JLabel spareTimeLabel = new JLabel("\uB0A8\uC740\uC2DC\uAC04 : ");
		spareTimeLabel.setBounds(46, 122, 63, 19);
		frame.getContentPane().add(spareTimeLabel);

		try {
			socket = new Socket(InetAddress.getByName("localhost"), 9999);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			writer.write(userId + '\n');
			writer.flush();
			payLabel.setText(userId);

			pcNum = Integer.parseInt(reader.readLine());
			numberLabel.setText(pcNum + "�� PC");

			threadStart();
		} catch (IOException e) {
			e.printStackTrace();
		}

		TimeThread tt = new TimeThread();
		tt.start();//���ð� ���

	}

	public void threadStart() {
		ReadThread rt = new ReadThread();

		rt.start();
	}

	///////////////////////////////////////////////////////////////////////////////////////
	class Mylistener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == orderButton) {
				new Foodorder(writer);
			} else if (e.getSource() == adminCall) {
				new Manager_Call(writer);
			} else if (e.getSource() == pauseButton) {
				if (stopRestart == 0) {
					stopTime();
					
					numberLabel.setText("");
					payLabel.setText("�Ͻ�����");
					pauseButton.setText("�ٽý���");
					game1Button.setEnabled(false);
					game2Button.setEnabled(false);
					game3Button.setEnabled(false);
					game4Button.setEnabled(false);
					orderButton.setEnabled(false);
					adminCall.setEnabled(false);
					
					stopRestart = 1;
					//////////////////////////////////////////////////////
				} else if (stopRestart == 1) {
					restartTime();

//					numberLabel.setText("��ȣ");
//					payLabel.setText("����ڼ��ĺ�");
					numberLabel.setText(pcNum + "�� PC");
					payLabel.setText(userId);
					pauseButton.setText("�Ͻ�����");
					if(g1==0){
					game1Button.setEnabled(true);
					}
					if(g2==0){
					game2Button.setEnabled(true);
					}
					if(g3==0){
					game3Button.setEnabled(true);
					}
					if(g4==0){
					game4Button.setEnabled(true);
					}	
					orderButton.setEnabled(true);
					adminCall.setEnabled(true);
				
					stopRestart = 0;
				}
			} else if (e.getSource() == game1Button) {
				addMoney += 500;
				game1Button.setEnabled(false);
				g1=1;
				
				try
				{
					writer.write(GeneralSet.ClientAct.PLAY_GAME.name() + '\n');
					writer.flush();
					writer.write("1\n");
					writer.flush();
					writer.write("����1\n");
					writer.flush();
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}

			} else if (e.getSource() == game2Button) {
				addMoney += 1000;
				game2Button.setEnabled(false);
				g2=1;
				
				try
				{
					writer.write(GeneralSet.ClientAct.PLAY_GAME.name() + '\n');
					writer.flush();
					writer.write("1\n");
					writer.flush();
					writer.write("����2\n");
					writer.flush();
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}

			} else if (e.getSource() == game3Button) {
				addMoney += 1500;
				game3Button.setEnabled(false);
				g3=1;
				
				try
				{
					writer.write(GeneralSet.ClientAct.PLAY_GAME.name() + '\n');
					writer.flush();
					writer.write("1\n");
					writer.flush();
					writer.write("����3\n");
					writer.flush();
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}

			} else if (e.getSource() == game4Button) {
				addMoney += 2000;
				game4Button.setEnabled(false);
				g4=1;
				
				try
				{
					writer.write(GeneralSet.ClientAct.PLAY_GAME.name() + '\n');
					writer.flush();
					writer.write("1\n");
					writer.flush();
					writer.write("����4\n");
					writer.flush();
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
			
		}
		
	}

	///////////////////////////////////////////////////////////////////////////////
	// ���ð��� ����ϴ� �޼ҵ�
	private String useTime(Date udate) {
		Calendar nowTime = Calendar.getInstance();
		nowTime.setTimeInMillis(System.currentTimeMillis());
		
		
		intervalMilli = (nowTime.getTimeInMillis() - udate.getTime())-reDiff; 

		useTime = intervalMilli / 1000; // ����� ���

		long minute = 60;
		long hour = minute * 60;

		int useHour = (int) (useTime / hour);
		int useMinute = (int) ((useTime % hour) / minute);
		int useSecond = (int) ((useTime % hour) % minute);

		String tmp = (useHour < 10) ? "0" + useHour : useHour + "";
		tmp += ":";
		tmp += (useMinute < 10) ? "0" + useMinute : useMinute + "";
		tmp += ":";
		tmp += (useSecond < 10) ? "0" + useSecond : useSecond + "";
		return tmp;
	}

	private String spareTime() {
		Calendar oneHour = Calendar.getInstance();
		oneHour.setTimeInMillis(60 * 60 * 1000);
		
		long differ = (oneHour.getTimeInMillis() - intervalMilli) / 1000;

		long minute = 60;
		long hour = minute * 60;

		int useHour = (int) (differ / hour);
		int useMinute = (int) ((differ % hour) / minute);
		int useSecond = (int) ((differ % hour) % minute);

		String tmp = (useHour < 10) ? "0" + useHour : useHour + "";
		tmp += ":";
		tmp += (useMinute < 10) ? "0" + useMinute : useMinute + "";
		tmp += ":";
		tmp += (useSecond < 10) ? "0" + useSecond : useSecond + "";
		return tmp;

	}

	public void addMoney() {

		int useMoney = (int) (useTime * secMoney) + addMoney;
		this.useMoney = useMoney + ""; // ��� String���� ����
	}

	public void stopTime() {
		start = false;
		stopDate= new Date();
	}

	public void restartTime() {
		start = true;
		restartDate= new Date();
		reDiff += restartDate.getTime()-stopDate.getTime();
		
		TimeThread tt2 = new TimeThread();
		tt2.start();

	}

	private class TimeThread extends Thread {
		@Override
		public void run() {
			while (start) {

				addMoney();
				usedTimeText.setText(useTime(startDate));
				spareTimeText.setText(spareTime());
				usingFeeText.setText(useMoney + "��");
				
//				if (sec >= 5)
//				{
					try
					{
						writer.write(GeneralSet.ClientAct.SEND_TIME.name() + '\n');
						writer.flush();
						writer.write("2\n");
						writer.flush();
						writer.write(useTime(startDate) + '\n');
						writer.flush();
						writer.write(useMoney + '\n');
						writer.flush();
					}
					catch (SocketException e)
					{
						e.printStackTrace();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
//				}

				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private class ReadThread extends Thread {
		@Override
		public void run() {
			for (;;) {
				try {
					GeneralSet.print("Read READY");
					String act = reader.readLine();
					GeneralSet.print(act);
					if (act != null && !act.equals(""))
					{
						switch (ServerAct.valueOf(act))
						{
						case SET_PCNUM:
							GeneralSet.print("SET_PCNUM");
							pcNum = Integer.parseInt(reader.readLine());
							numberLabel.setText(pcNum + "�� PC");
							break;
						default:
							break;
						}
					} else if (act == null) {
						frame.dispose();
						System.exit(0);
						return;
					}
				} catch (SocketException e) {
					frame.dispose();
					System.exit(0);
					return;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
