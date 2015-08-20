package ui.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Time;

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

	/**
	 * 
	 * Create the panel.
	 */
	public PanelUserInform()
	{
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2, true), new EmptyBorder(5, 5, 5, 5)));
		contentPane.setLayout(new GridLayout(0, 1));
		
		JLabel pcNumLabel = new JLabel("PC No. " + pcNum);
		pcNumLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		pcNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(pcNumLabel);
		
		JLabel userIDLabel = new JLabel("ID : " + userID);
		userIDLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		contentPane.add(userIDLabel);
		
		JLabel userRateLabel = new JLabel("µÓ±ﬁ : " + userRate);
		userRateLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		contentPane.add(userRateLabel);
		
		JLabel userTimeLabel = new JLabel("ªÁøÎΩ√∞£ : " + useTime);
		userTimeLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		contentPane.add(userTimeLabel);
		
		JLabel playedGameLabel = new JLabel("Ω««‡¡ﬂ¿Œ ∞‘¿” : " + playedGame);
		playedGameLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		contentPane.add(playedGameLabel);
		
		JButton button = new JButton("ªÁøÎ¡æ∑·");
		button.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		contentPane.add(button);
		
		add(contentPane);
	}

}
