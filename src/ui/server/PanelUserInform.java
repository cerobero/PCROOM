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
//	private String userRate;
	private String userCharge;
	private String useTime;
	private String playedGame;
	
	private JPanel contentPane;
	private JLabel pcNumLabel;
	private JLabel userIDLabel;
//	private JLabel userRateLabel;
	private JLabel userChargeLabel;
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
		pcNumLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		pcNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(pcNumLabel);
		
		userIDLabel = new JLabel();
		userIDLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		contentPane.add(userIDLabel);
		
//		userRateLabel = new JLabel();
//		userRateLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
//		contentPane.add(userRateLabel);
		
		userChargeLabel = new JLabel();
		userChargeLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		contentPane.add(userChargeLabel);
		
		userTimeLabel = new JLabel();
		userTimeLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		contentPane.add(userTimeLabel);
		
		playedGameLabel = new JLabel();
		playedGameLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		contentPane.add(playedGameLabel);
		
		button = new JButton("»ç¿ëÁ¾·á");
		button.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
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
		
		reset();
		refresh();
	}
	
	public void refresh()
	{
		pcNumLabel.setText("Pc No. " + pcNum);
		userIDLabel.setText("ID : " + userID);
//		userRateLabel.setText("µî±Þ : " + userRate);
		userChargeLabel.setText("»ç¿ë¿ä±Ý : " + userCharge + "¿ø");
		userTimeLabel.setText("»ç¿ë½Ã°£ : " + useTime);
		playedGameLabel.setText("½ÇÇàÁßÀÎ °ÔÀÓ : " + playedGame);
	}
	
	@SuppressWarnings("deprecation")
	public void reset()
	{
		pcNum = 0;
		userID = null;
//		userRate = null;
		userCharge = "0";
		useTime = "00:00:00";
		playedGame = "¾øÀ½";
		
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

//	public String getUserRate()
//	{
//		return userRate;
//	}

//	public void setUserRate(String userRate)
//	{
//		this.userRate = userRate;
//	}

	public String getUserCharge()
	{
		return userCharge;
	}

	public void setUserCharge(String userCharge)
	{
		this.userCharge = userCharge;
	}

	public String getUseTime()
	{
		return useTime;
	}

	public void setUseTime(String useTime)
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
