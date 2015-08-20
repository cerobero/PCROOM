package ui.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class FrameAdmin extends JFrame
{

	private JPanel contentPane;
	private JTextField blankFieldLeft;
	private JTextField dateField;
	private JTextField timeField;
	private JTextField guestCountField;
	private int visitGuestCount;

	private PanelUserInform[] userManagePanes = new PanelUserInform[8];
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					FrameAdmin frame = new FrameAdmin();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameAdmin()
	{
		setTitle("PC방 관리창");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel titlePane = new JPanel();
		contentPane.add(titlePane, BorderLayout.NORTH);
		titlePane.setLayout(new BorderLayout(0, 0));

		JPanel titleLeftPane = new JPanel();
		titlePane.add(titleLeftPane, BorderLayout.WEST);
		titleLeftPane.setLayout(new BoxLayout(titleLeftPane, BoxLayout.Y_AXIS));

		blankFieldLeft = new JTextField();
		blankFieldLeft.setEnabled(false);
		blankFieldLeft.setEditable(false);
		blankFieldLeft.setBorder(null);
		titleLeftPane.add(blankFieldLeft);
		blankFieldLeft.setColumns(10);

		JPanel titleCenterPane = new JPanel();
		titlePane.add(titleCenterPane);

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		titleCenterPane.add(textPane);
		textPane.setText("PC방 관리창");
		textPane.setOpaque(false);
		textPane.setFont(new Font("맑은 고딕", Font.BOLD, 25));

		JPanel titleRightPane = new JPanel();
		titlePane.add(titleRightPane, BorderLayout.EAST);
		titleRightPane.setLayout(new BoxLayout(titleRightPane, BoxLayout.Y_AXIS));

		dateField = new JTextField();
		dateField.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		dateField.setHorizontalAlignment(SwingConstants.RIGHT);
		dateField.setEditable(false);
		dateField.setBorder(null);
		dateField.setOpaque(false);
		dateField.setColumns(10);
		titleRightPane.add(dateField);

		timeField = new JTextField();
		timeField.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		timeField.setHorizontalAlignment(SwingConstants.RIGHT);
		timeField.setEditable(false);
		timeField.setBorder(null);
		timeField.setOpaque(false);
		timeField.setColumns(10);
		titleRightPane.add(timeField);

		guestCountField = new JTextField();
		guestCountField.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		guestCountField.setHorizontalAlignment(SwingConstants.RIGHT);
		visitGuestCount = 5;
		guestCountField.setText("오늘 손님수 : " + visitGuestCount + "명");
		guestCountField.setEditable(false);
		guestCountField.setBorder(null);
		guestCountField.setOpaque(false);
		guestCountField.setColumns(10);
		titleRightPane.add(guestCountField);

		JPanel centerPane = new JPanel();

		for (int i = 0; i < userManagePanes.length; i++)
		{
			userManagePanes[i] = new PanelUserInform();
			centerPane.add(userManagePanes[i]);
		}

		contentPane.add(centerPane, BorderLayout.CENTER);
		centerPane.setLayout(new GridLayout(2, 4));

		JPanel menuPane = new JPanel();
		menuPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(menuPane, BorderLayout.EAST);
		menuPane.setLayout(new BoxLayout(menuPane, BoxLayout.Y_AXIS));

		JPanel informPane = new JPanel();
		informPane.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 5, 0), new LineBorder(new Color(0, 0, 0), 2, true)));
		menuPane.add(informPane);

		JLabel label = new JLabel("기타 정보란");
		informPane.add(label);

		JPanel buttonPane = new JPanel();
		menuPane.add(buttonPane);
		buttonPane.setLayout(new GridLayout(0, 1, 0, 0));

		JButton button = new JButton("음식 관리");
		button.setPreferredSize(new Dimension(100, 0));
		button.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		buttonPane.add(button);

		JButton button_1 = new JButton("메뉴 1");
		button_1.setPreferredSize(new Dimension(100, 0));
		button_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		buttonPane.add(button_1);

		JButton button_2 = new JButton("메뉴 2");
		button_2.setPreferredSize(new Dimension(100, 0));
		button_2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		buttonPane.add(button_2);

		JButton button_3 = new JButton("메뉴 3");
		button_3.setPreferredSize(new Dimension(100, 0));
		button_3.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		buttonPane.add(button_3);
		
		threadStart();
	}
	
	private void threadStart()
	{
		SetInform setInform = new SetInform();
		ReadUserMessage readMessage = new ReadUserMessage(this);
		
		setInform.start();
		readMessage.start();
	}

	public class SetInform extends Thread
	{
		private Calendar cal;
		
		private int year;
		private int month;
		private int date;
		
		private int hour;
		private int min;
		private int sec;

		@Override
		public void run()
		{
			for (;;)
			{
				cal = Calendar.getInstance();
				
				year = cal.get(cal.YEAR);
				month = cal.get(cal.MONTH) + 1;
				date = cal.get(cal.DATE);
				
				hour = cal.get(cal.HOUR_OF_DAY);
				min = cal.get(cal.MINUTE);
				sec = cal.get(cal.SECOND);

				dateField.setText(String.format("%02d/%02d/%02d", year, month, date));
				timeField.setText(String.format("%02d:%02d:%02d", hour, min, sec));

				try
				{
					Thread.sleep(1000);
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}

		public int getYear()
		{
			return year;
		}

		public int getMonth()
		{
			return month;
		}

		public int getDate()
		{
			return date;
		}

		public int getHour()
		{
			return hour;
		}

		public int getMin()
		{
			return min;
		}

		public int getSec()
		{
			return sec;
		}
	}
	
	public class ReadUserMessage extends Thread
	{
		private ServerSocket server;
		private Socket socket;
		private BufferedReader reader;
		private PanelMessagePopup userMessagePane;
		private JFrame frame;
		
		public ReadUserMessage(JFrame frame)
		{
			this.frame = frame;
		}
		
		@Override
		public void run()
		{
			try
			{
				server = new ServerSocket(9999);
				userMessagePane = new PanelMessagePopup();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			for (;;)
			{
				try
				{
					socket = server.accept();
					reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					userMessagePane.setSize(new Dimension(300, 200));
					userMessagePane.setLocationRelativeTo(frame);
					userMessagePane.setVisible(true);
					userMessagePane.setPcNum(Integer.parseInt(reader.readLine()));
					userMessagePane.setUserMessage(reader.readLine());

					Thread.sleep(5000);
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
