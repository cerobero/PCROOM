package ui.server;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JFrame;

import asset.GeneralSet;
import asset.GeneralSet.ClientAct;
import ui.server.FrameAdmin.Server;

public class ClientHandler extends Thread
{
	private Server server;
	private Socket socket;
	private JFrame frame;
	private BufferedReader reader;
	private BufferedWriter writer;
	private int pcNum;

	public ClientHandler(Server server, Socket socket, JFrame frame)
	{
		this.server = server;
		this.socket = socket;
		this.frame = frame;

		try
		{
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			pcNum = Integer.parseInt(reader.readLine());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void receiveMessage(String msg)
	{
		PanelMessagePopup messagePane = new PanelMessagePopup();

		messagePane.setPcNum(pcNum);
		messagePane.setUserMessage(msg);

		messagePane.setSize(new Dimension(300, 200));
		messagePane.setLocationRelativeTo(frame);
		messagePane.setVisible(true);
	}

	public void orderFood(String msg)
	{

	}

	@Override
	public void run()
	{
		for (;;)
		{
			try
			{
				String act = reader.readLine();
				GeneralSet.print(act);

				switch (ClientAct.valueOf(act))
				{
				case SEND_MESSAGE:
					receiveMessage(reader.readLine());
					break;
				case ORDER_FOOD:
					orderFood(reader.readLine());
					break;
				default:
					GeneralSet.print("올바르지 않은 Client 동작");
					break;
				}
			}
			catch (SocketException e)
			{
				try
				{
					GeneralSet.print("Client 연결이 종료됨");
					reader.close();
					writer.close();
					socket.close();
					server.clientExit(this);
					return;
				}
				catch (IOException e2)
				{
					e.printStackTrace();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
