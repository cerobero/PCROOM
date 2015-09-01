package ui.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class PanelUserInform extends JPanel
{
	private int pcNum;
	private String userID;
	private String userRate;
	private Time useTime;
	private String playedGame;
	
	private JPanel contentPane;
	private JLabel pcNumLabel;
	private JLabel userIDLabel;
	private JLabel userRateLabel;
	private JLabel userTimeLabel;
	private JLabel playedGameLabel;
	private JButton button;
	
	private ClientHandler handler;

	/**
	 * 
	 * Create the panel.
	 */
	public PanelUserInform(Map<Integer, ClientHandler> clientList)
	{
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		contentPane = new JPanel();
		contentPane.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2, true), new EmptyBorder(5, 5, 5, 5)));
		contentPane.setLayout(new GridLayout(0, 1));
		
		pcNumLabel = new JLabel();
		pcNumLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		pcNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(pcNumLabel);
		
		userIDLabel = new JLabel();
		userIDLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		contentPane.add(userIDLabel);
		
		userRateLabel = new JLabel();
		userRateLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		contentPane.add(userRateLabel);
		
		userTimeLabel = new JLabel();
		userTimeLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		contentPane.add(userTimeLabel);
		
		playedGameLabel = new JLabel();
		playedGameLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		contentPane.add(playedGameLabel);
		
		button = new JButton("ªÁøÎ¡æ∑·");
		button.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				handler = clientList.get(pcNum - 1);
				
				if (handler != null)
				{
					handler.closeAll();
				}
			}
		});
		contentPane.add(button);
		
		add(contentPane);
		
		refresh();
	}
	
	public void refresh()
	{
		pcNumLabel.setText("Pc No. " + pcNum);
		userIDLabel.setText("ID : " + userID);
		userRateLabel.setText("µÓ±ﬁ : " + userRate);
		userTimeLabel.setText("ªÁøÎΩ√∞£ : " + useTime);
		playedGameLabel.setText("Ω««‡¡ﬂ¿Œ ∞‘¿” : " + playedGame);
	}
	
	public void reset()
	{
		pcNum = 0;
		userID = null;
		userRate = null;
		useTime = null;
		playedGame = null;
		
		refresh();
	}
	
	public int getPcNum()
	{
		return pcNum;
	}

	public void setPcNum(int pcNum)
	{
		this.pcNum = pcNum;
	}

	public String getUserID()
	{
		return userID;
	}

	public void setUserID(String userID)
	{
		this.userID = userID;
	}

	public String getUserRate()
	{
		return userRate;
	}

	public void setUserRate(String userRate)
	{
		this.userRate = userRate;
	}

	public Time getUseTime()
	{
		return useTime;
	}

	public void setUseTime(Time useTime)
	{
		this.useTime = useTime;
	}

	public String getPlayedGame()
	{
		return playedGame;
	}

	public void setPlayedGame(String playedGame)
	{
		this.playedGame = playedGame;
	}
	
	public JButton getButton()
	{
		return button;
	}
	
	public void setBackGroundColor(Color color)
	{
		contentPane.setBackground(color);
	}
}
