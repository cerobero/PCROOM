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
import java.util.Calendar;
import java.util.Date;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import asset.GeneralSet;
import asset.GeneralSet.ServerAct;

public class userPay implements Runnable {

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
	
    private JButton game1Button;
    private JButton game2Button;
    private JButton game3Button;
    private JButton game4Button;
    ////////////////////////////////////////
    private Date startDate = null;// ���ð� ���
    public long intervalMilli;
    
    private String useMoney = "0"; // �����
    public long useTime = 0; // ����� ���
    public double secMoney = 1000.0 / (60 * 60); // �ʴ� ���
    public int addMoney = 0; // ���ӹ�ư���� �߰��Ǵ� �ݾ�
    
    public boolean a=true; //�Ͻ�����
    public int b=0; //�Ͻ�����,�ٽý���


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
    public userPay(String userId, Date startDate) {
        this. userId = userId;
        this. startDate = startDate; // �ð����

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
         game1Button.addActionListener( li);
         game2Button.addActionListener( li);
         game3Button.addActionListener( li);
         game4Button.addActionListener( li);

		
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
		// �Ͻ����� �̺�Ʈ
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
          int a=0;
          @Override
          public void actionPerformed(ActionEvent e) {
               
                if ( e.getSource() == orderButton) {
                      new Foodorder( writer);
               } else if ( e.getSource() == adminCall) {
                      new Manager_Call( writer);
               } else if ( e.getSource() == pauseButton) {
                      if( b==0){   
                           stopTime();
                            numberLabel.setText( "");
                            payLabel.setText( "�Ͻ�����");
                            pauseButton.setText( "�ٽý���");
                            game1Button.setEnabled( false);
                            game2Button.setEnabled( false);
                            game3Button.setEnabled( false);
                            game4Button.setEnabled( false);
                            orderButton.setEnabled( false);
                            adminCall.setEnabled( false);
                            b=1;
                      //////////////////////////////////////////////////////
                     } else if( b==1){
                           restartTime();
                            //restart�� �ȵ�~~~~~~
                            numberLabel.setText( "��ȣ");
                            payLabel.setText( "����ڼ��ĺ�" );
                            pauseButton.setText( "�Ͻ�����");
                            game1Button.setEnabled( true);
                            game2Button.setEnabled( true);
                            game3Button.setEnabled( true);
                            game4Button.setEnabled( true);
                            orderButton.setEnabled( true);
                            adminCall.setEnabled( true);
                            b=0;
                     }
               } else if ( e.getSource() == game1Button) {
                            addMoney += 500;
                            game1Button.setEnabled( false);
                           
               } else if ( e.getSource() == game2Button) {
                            addMoney += 1000;
                            game2Button.setEnabled( false);
               
               } else if ( e.getSource() == game3Button) {
                            addMoney += 1500;
                            game3Button.setEnabled( false);
               
               } else if ( e.getSource() == game4Button) {
                            addMoney += 2000;
                            game4Button.setEnabled( false);
               
               }
               
         }

   }

   ///////////////////////////////////////////////////////////////////////////////
   // ���ð��� ����ϴ� �޼ҵ�
   private String useTime(Date udate) {
         Calendar c2 = Calendar. getInstance();

          c2.setTimeInMillis(System. currentTimeMillis());

          intervalMilli = ( c2.getTimeInMillis() - udate.getTime());
          useTime = intervalMilli/ 1000; // ����� ���

          long minute = 60;
          long hour = minute * 60;

          int useHour = ( int) ( useTime / hour);
          int useMinute = ( int) (( useTime % hour) / minute);
          int useSecond = ( int) (( useTime % hour) % minute);

         String tmp = ( useHour < 10) ? "0" + useHour : useHour + "";
          tmp += ":";
          tmp += ( useMinute < 10) ? "0" + useMinute : useMinute + "";
          tmp += ":";
          tmp += ( useSecond < 10) ? "0" + useSecond : useSecond + "";
          return tmp;
   }
   
   private String spareTime(){
         Calendar c3 = Calendar. getInstance();
         
          c3.setTimeInMillis(60*60*1000);
          long differ=( c3.getTimeInMillis()- intervalMilli)/1000;
         
          long minute = 60;
          long hour = minute * 60;

          int useHour = ( int) ( differ / hour);
          int useMinute = ( int) (( differ % hour) / minute);
          int useSecond = ( int) (( differ % hour) % minute);

         String tmp = ( useHour < 10) ? "0" + useHour : useHour + "";
          tmp += ":";
          tmp += ( useMinute < 10) ? "0" + useMinute : useMinute + "";
          tmp += ":";
          tmp += ( useSecond < 10) ? "0" + useSecond : useSecond + "";
          return tmp;
         
   }

   public void addMoney() {

          int useMoney = ( int) ( useTime * secMoney) + addMoney;
          this. useMoney = useMoney + ""; // ��� String���� ����
   }

   public void stopTime(){
          a= false;
   }
   ///////////////////////////////////////////////////////
   public void restartTime(){
          a= true;
   }
   ///////////////////////////////////////////////////////
   @Override
   public void run() {
         
          while ( a) {
               
               addMoney();
                usedTimeText.setText(useTime( startDate));
                spareTimeText.setText(spareTime());
                usingFeeText.setText( useMoney + "��");
                try {
                     Thread. sleep(100);
               } catch (Exception e) {
                      e.printStackTrace();
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
