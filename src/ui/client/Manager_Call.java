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
	private String msg = "���� �޽���";
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
		fontA = new Font("���� ���", Font.BOLD, 45);
		labelA = new Label("������ ȣ��");
		send = new JButton("������");
		cancel = new JButton("���");

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
		setTitle("������ ȣ��");
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		//��ġ ���߾�
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
					 * GEONWOO-CHO 0821 ���� PC ��ȣ �����ϰ� �޽��� ����
					 */
					bw.write(ClientAct.SEND_MESSAGE.name() + '\n');
					bw.flush();
					bw.write("1" + '\n');
					bw.flush();
					bw.write(msg + "\n");
					bw.flush();
					//�����ϸ� �ؽ�Ʈ�ʵ��� ���� ����
					call_Message.setText(null);
				} catch (IOException e1) {
					System.out.println("� ������ �޽����� ���޵��� �ʾҽ��ϴ�." + e);
				}

			} else if (e.getSource() == cancel) {
//				msg = "���� �޽���";
//				call_Message.setText(msg);
				dispose();
			}

		}
		/*
		 * ����Ű ������ ���� �����ϵ��� ��
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
					System.out.println("� ������ �޽����� ���޵��� �ʾҽ��ϴ�." + e2);
				}

			}
		}
		/*
		*����Ű�� ������ ���� �ؽ�Ʈ�ʵ��� ���� ������
		*/

		@Override
		public void keyReleased(KeyEvent arg0) {
			
			call_Message.setText(null);
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {}
	}
}
