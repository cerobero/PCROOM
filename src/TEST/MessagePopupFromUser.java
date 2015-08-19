package TEST;

import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class MessagePopupFromUser extends JPanel
{
	private int pcNum;
	private String userMessage;

	/**
	 * Create the panel.
	 */
	public MessagePopupFromUser()
	{
		setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2, true), new EmptyBorder(5, 5, 5, 5)));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pcInformPane = new JPanel();
		add(pcInformPane, BorderLayout.NORTH);
		
		JLabel pcInformLabel = new JLabel(pcNum + "번 PC 메시지");
		pcInformPane.add(pcInformLabel);
		
		JTextArea messageArea = new JTextArea();
		messageArea.setBorder(new LineBorder(new Color(128, 128, 128), 1, true));
		messageArea.setEnabled(false);
		messageArea.setOpaque(false);
		messageArea.setEditable(false);
		messageArea.append(userMessage);
		add(messageArea, BorderLayout.CENTER);
		
		JPanel confirmPane = new JPanel();
		add(confirmPane, BorderLayout.SOUTH);
		
		JButton confirmButton = new JButton("확인");
		confirmPane.add(confirmButton);

	}

}
