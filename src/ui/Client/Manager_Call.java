package ui.Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Manager_Call extends JFrame {
	private String msg = "전송 메시지";
	private TextField call_Message = new TextField(msg, 35);
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private Font fontA;
	private Label labelA;
	private JButton send;
	private JButton cancel;
	
	private BufferedWriter bw;

	public Manager_Call() {

		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		fontA = new Font("Gothic", Font.BOLD, 45);
		labelA = new Label("관리자 호출");
		send = new JButton("보내기");
		cancel = new JButton("취소");

		send.addActionListener(new Consol());
		cancel.addActionListener(new Consol());

		panel1.setBackground(Color.ORANGE);
		panel2.setBackground(Color.ORANGE);
		panel3.setBackground(Color.ORANGE);

		labelA.setFont(fontA);
		call_Message.setEnabled(true);
		call_Message.addActionListener(new Consol());

		panel1.add(labelA, BorderLayout.CENTER);

		panel2.add(call_Message);

		panel3.setLayout(new FlowLayout());
		panel3.add(send);
		panel3.add(cancel);

		add(panel1, BorderLayout.NORTH);
		add(panel2, BorderLayout.CENTER);
		add(panel3, BorderLayout.SOUTH);

		setSize(300, 200);
		setTitle("관리자 호출");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		try {
			Socket socket=new Socket(InetAddress.getByName("localhost"),9999);
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			System.out.println("서버와 연결되지 않았습니다." + e);
		}

	}

	public static void main(String[] args) {
		Manager_Call c = new Manager_Call();

	}

	class Consol implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == send) {
				msg = call_Message.getText();
				try {
					bw.write(msg+"\n");
					bw.flush();
				} catch (IOException e1) {
					System.out.println("어떤 이유로 메시지가 전달되지 않았습니다." + e);
				}

			} else if (e.getSource() == cancel) {
				msg = "전송 메시지";
				call_Message.setText(msg);
			}

		}

	}
}
