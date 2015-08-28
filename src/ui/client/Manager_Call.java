package ui.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import asset.GeneralSet.ClientAct;

public class Manager_Call extends JDialog {
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

	public Manager_Call(BufferedWriter writer) {

		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		fontA = new Font("맑은 고딕", Font.BOLD, 45);
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
		call_Message.addKeyListener(new Consol());

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
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		//위치 정중앙
		setLocationRelativeTo(null);

//			Socket socket=new Socket(InetAddress.getByName("localhost"),9999);
			bw = writer;

	}

//	public static void main(String[] args) {
//		Manager_Call c = new Manager_Call();
//
//	}

	class Consol implements ActionListener, KeyListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == send) {
				msg = call_Message.getText();
				try {
					/*
					 * GEONWOO-CHO 0821 먼저 PC 번호 전송하고 메시지 전송
					 */
					bw.write(ClientAct.SEND_MESSAGE.name() + '\n');
					bw.flush();
					bw.write("1" + '\n');
					bw.flush();
					bw.write(msg + "\n");
					bw.flush();
					//전송하면 텍스트필드의 글을 지움
					call_Message.setText(null);
				} catch (IOException e1) {
					System.out.println("어떤 이유로 메시지가 전달되지 않았습니다." + e);
				}

			} else if (e.getSource() == cancel) {
//				msg = "전송 메시지";
//				call_Message.setText(msg);
				dispose();
			}

		}
		/*
		 * 엔터키 눌러도 전송 가능하도록 함
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				msg = call_Message.getText();
				try {
					bw.write(ClientAct.SEND_MESSAGE.name() + '\n');
					bw.flush();
					bw.write("1" + '\n');
					bw.flush();
					bw.write(msg + "\n");
					bw.flush();
				} catch (IOException e2) {
					System.out.println("어떤 이유로 메시지가 전달되지 않았습니다." + e2);
				}

			}
		}
		/*
		*엔터키를 눌렀다 떼면 텍스트필드의 글이 지워짐
		*/

		@Override
		public void keyReleased(KeyEvent arg0) {
			
			call_Message.setText(null);
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {}
	}
}
