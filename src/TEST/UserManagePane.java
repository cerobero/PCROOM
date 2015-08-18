package TEST;

import java.sql.Time;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.border.CompoundBorder;

public class UserManagePane extends JPanel
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
	public UserManagePane()
	{
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2, true), new EmptyBorder(5, 5, 5, 5)));
		contentPane.setLayout(new GridLayout(0, 1));
		
		JLabel pcNumLabel = new JLabel("PC No. " + pcNum);
		pcNumLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		pcNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(pcNumLabel);
		
		JLabel userIDLabel = new JLabel("ID : " + userID);
		userIDLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		contentPane.add(userIDLabel);
		
		JLabel userRateLabel = new JLabel("등급 : " + userRate);
		userRateLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		contentPane.add(userRateLabel);
		
		JLabel userTimeLabel = new JLabel("사용시간 : " + useTime);
		userTimeLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		contentPane.add(userTimeLabel);
		
		JLabel playedGameLabel = new JLabel("실행중인 게임 : " + playedGame);
		playedGameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		contentPane.add(playedGameLabel);
		
		JButton button = new JButton("사용종료");
		button.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		contentPane.add(button);
		
		add(contentPane);
	}

}
