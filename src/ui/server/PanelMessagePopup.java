package ui.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class PanelMessagePopup extends JDialog
{
	private int pcNum;
	private String userMessage;
	private JLabel pcInformLabel;
	private JTextArea messageArea;

	/**
	 * Create the panel
	 */
	public PanelMessagePopup()
	{
		//		setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2, true), new EmptyBorder(5, 5, 5, 5)));
		setTitle("메시지");
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel pcInformPane = new JPanel();
		getContentPane().add(pcInformPane, BorderLayout.NORTH);

		pcInformLabel = new JLabel();
		pcInformPane.add(pcInformLabel);

		messageArea = new JTextArea();
		messageArea.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new LineBorder(new Color(128, 128, 128), 1, true)));
		messageArea.setEnabled(false);
		messageArea.setOpaque(false);
		messageArea.setEditable(false);
		getContentPane().add(messageArea, BorderLayout.CENTER);

		JPanel confirmPane = new JPanel();
		getContentPane().add(confirmPane, BorderLayout.SOUTH);

		JButton confirmButton = new JButton("확인");
		confirmButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});

		confirmPane.add(confirmButton);
		
		threadStart();
	}
	
	private void threadStart()
	{
		Thread update = new Thread(new UpdateData());
		
		update.start();
	}

	public int getPcNum()
	{
		return pcNum;
	}

	public void setPcNum(int pcNum)
	{
		this.pcNum = pcNum;
	}

	public String getUserMessage()
	{
		return userMessage;
	}

	public void setUserMessage(String userMessage)
	{
		this.userMessage = userMessage;
	}

	private class UpdateData implements Runnable
	{
		@Override
		public void run()
		{
			for (;;)
			{
				pcInformLabel.setText(pcNum + "번 PC 메시지");
				messageArea.setText(userMessage);

				try
				{
					Thread.sleep(1000);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

}
