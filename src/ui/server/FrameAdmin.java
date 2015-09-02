package ui.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
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
	private int visitGuestCount = 0;

	private PanelUserInform[] userManagePanes = new PanelUserInform[8];
//	private Map<Integer, PanelUserInform> userManagePanes = new HashMap<Integer, PanelUserInform>();
	private Map<Integer, ClientHandler> clientList = new HashMap<>();

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
		setTitle("PC¹æ °ü¸®Ã¢");
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
		textPane.setText("PC¹æ °ü¸®Ã¢");
		textPane.setOpaque(false);
		textPane.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));

		JPanel titleRightPane = new JPanel();
		titlePane.add(titleRightPane, BorderLayout.EAST);
		titleRightPane.setLayout(new BoxLayout(titleRightPane, BoxLayout.Y_AXIS));

		dateField = new JTextField();
		dateField.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		dateField.setHorizontalAlignment(SwingConstants.RIGHT);
		dateField.setEditable(false);
		dateField.setBorder(null);
		dateField.setOpaque(false);
		dateField.setColumns(10);
		titleRightPane.add(dateField);

		timeField = new JTextField();
		timeField.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		timeField.setHorizontalAlignment(SwingConstants.RIGHT);
		timeField.setEditable(false);
		timeField.setBorder(null);
		timeField.setOpaque(false);
		timeField.setColumns(10);
		titleRightPane.add(timeField);

		guestCountField = new JTextField();
		guestCountField.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		guestCountField.setHorizontalAlignment(SwingConstants.RIGHT);
		guestCountField.setText("¿À´Ã ¼Õ´Ô¼ö : " + visitGuestCount + "¸í");
		guestCountField.setEditable(false);
		guestCountField.setBorder(null);
		guestCountField.setOpaque(false);
		guestCountField.setColumns(10);
		titleRightPane.add(guestCountField);

		JPanel centerPane = new JPanel();

		for (int i = 0; i < 8; i++)
		{
//			userManagePanes.put(i, new PanelUserInform());
//			centerPane.add(userManagePanes.get(i));
			userManagePanes[i] = new PanelUserInform(clientList);
			userManagePanes[i].setBackGroundColor(Color.DARK_GRAY);
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

		JLabel label = new JLabel("±âÅ¸ Á¤º¸¶õ");
		informPane.add(label);

		JPanel buttonPane = new JPanel();
		menuPane.add(buttonPane);
		buttonPane.setLayout(new GridLayout(0, 1, 0, 0));

		JButton button = new JButton("À½½Ä °ü¸®");
		button.setPreferredSize(new Dimension(100, 0));
		button.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
		button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Foodadmin foodAdmin = new Foodadmin();
				foodAdmin.setVisible(true);
			}
		});
		buttonPane.add(button);

		JButton button_1 = new JButton("¸Þ´º 1");
		button_1.setPreferredSize(new Dimension(100, 0));
		button_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
		buttonPane.add(button_1);

//		JButton button_2 = new JButton("¸Þ´º 2");
//		button_2.setPreferredSize(new Dimension(100, 0));
//		button_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
//		buttonPane.add(button_2);
//
//		JButton button_3 = new JButton("¸Þ´º 3");
//		button_3.setPreferredSize(new Dimension(100, 0));
//		button_3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
//		buttonPane.add(button_3);

		/* GEONWOO-CHO 0821 FrameAdmin */
		setVisible(true);
		threadStart();
	}

	private void threadStart()
	{
		SetInform setInform = new SetInform();
		Server server = new Server(this);

		setInform.start();
		server.start();
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
				guestCountField.setText("¿À´Ã ¼Õ´Ô¼ö : " + visitGuestCount + "¸í");

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

	public class Server extends Thread
	{
		private ServerSocket server;
		private Socket socket;
		private JFrame frame;
//		private List<ClientHandler> clientList = new ArrayList<ClientHandler>();
//		private Map<Integer, ClientHandler> clientList = new HashMap<>();
		private int numOfUser = 0;

		public Server(JFrame frame)
		{
			this.frame = frame;
		}

		public void clientExit(ClientHandler thread)
		{
			userManagePanes[thread.getPcNum() - 1].setBackGroundColor(Color.DARK_GRAY);
			userManagePanes[thread.getPcNum() - 1].reset();
			clientList.remove(thread);
			if (clientList.get(thread.getPcNum() - 1) != null)
			{
				clientList.put(thread.getPcNum() - 1, null);
			}
			numOfUser--;
		}

		public void panelUpdate(ClientHandler thread)
		{
			int pcNum = thread.getPcNum();
//			GeneralSet.print((thread.getPcNum() - 1) + "");
			userManagePanes[pcNum - 1].setPcNum(pcNum);
			userManagePanes[pcNum - 1].setUserID(thread.getUserId());
			userManagePanes[pcNum - 1].refresh();
//			userManagePanes.get(thread.getPcNum() - 1).setPcNum(thread.getPcNum());
//			userManagePanes.get(thread.getPcNum() - 1).refresh();
		}

		@Override
		public void run()
		{
			try
			{
				server = new ServerSocket(9999);
				for (;;)
				{
					socket = server.accept();

					if (numOfUser < 8)
					{
						for (int i = 0; i < 8; i++)
						{
							if (clientList.get(i) == null)
							{
								ClientHandler clientThread = new ClientHandler(this, socket, frame, i + 1);
								clientList.put(i, clientThread);
								userManagePanes[i].setBackGroundColor(UIManager.getColor("Panel.background"));
								visitGuestCount++;
								numOfUser++;

								clientThread.start();
								break;
							}
						}

					}
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}