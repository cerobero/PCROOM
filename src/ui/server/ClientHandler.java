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
	private String userId;

	private ReadThread rt;
	private WriteThread wt; 

	public ClientHandler(Server server, Socket socket, JFrame frame, int pcNum)
	{
		this.server = server;
		this.socket = socket;
		this.frame = frame;
		this.pcNum = pcNum;

		try
		{
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			userId = reader.readLine();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void closeAll()
	{
		try
		{
			socket.close();
			reader.close();
			writer.close();
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
		receiveMessage(msg);
	}

	public void stopThread()
	{
		rt.interrupt();
		wt.interrupt();
	}

	@Override
	public void run()
	{
		rt = new ReadThread(this);
		wt = new WriteThread(this); 

		rt.start();
		wt.start();
	}

	private class ReadThread extends Thread
	{
		private ClientHandler handler;

		public ReadThread(ClientHandler handler)
		{
			this.handler = handler;
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

					if (act != null && !act.equals(""))
					{
						Thread timer = new Thread(new Timer());
						String msg = "";
						int lineNum = Integer.parseInt(reader.readLine());
						System.out.println(lineNum);

						timer.start();

						for (int i = 0; i < lineNum; i++)
						{
							msg += reader.readLine() + '\n'; 
						}

						timer.interrupt();

						switch (ClientAct.valueOf(act))
						{
						case SEND_MESSAGE:
							receiveMessage(msg);
							break;
						case ORDER_FOOD:
							orderFood(msg);
							break;
						default:
							GeneralSet.print("ReadThread:구현되지 않은 Client 동작");
							break;
						}
					}
				}
				catch (SocketException e)
				{
					try
					{
						GeneralSet.print("ReadThread:Client 연결이 종료됨");
						reader.close();
						writer.close();
						socket.close();
						server.clientExit(handler);
						handler.stopThread();
						return;
					}
					catch (IOException e2)
					{
						e.printStackTrace();
						handler.stopThread();
						return;
					}
				}
				catch (IllegalArgumentException e)
				{
					GeneralSet.print("ReadThread:올바르지 않은 Client 동작");
				}
				catch (IOException e)
				{
					e.printStackTrace();
					handler.stopThread();
					return;
				}
			}
		}
	}

	private class WriteThread extends Thread
	{
		private ClientHandler handler;

		public WriteThread(ClientHandler handler)
		{
			this.handler = handler;
		}

		@Override
		public void run()
		{
			for (;;)
			{
				try
				{
					//					writer.write("string");
					//					writer.flush();
					server.panelUpdate(handler);
					Thread.sleep(1000 * 60);
				}
				//				catch (SocketException e)
				//				{
				//					try
				//					{
				//						GeneralSet.print("WriteThread:Client 연결이 종료됨");
				//						reader.close();
				//						writer.close();
				//						socket.close();
				//						server.clientExit(handler);
				//						handler.stopThread();
				//						return;
				//					}
				//					catch (IOException e2)
				//					{
				//						e.printStackTrace();
				//						handler.stopThread();
				//						return;
				//					}
				//				}
				//				catch (IOException e)
				//				{
				////					e.printStackTrace();
				//					GeneralSet.print("WriteThread:EXIT");
				//					handler.stopThread();
				//					return;
				//				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
					handler.stopThread();
					return;
				}
			}
		}
	}

	private class Timer extends Thread
	{
		@Override
		public void run()
		{
			try
			{
				Thread.sleep(2000);
				socket.close();
			}
			catch (InterruptedException e)
			{
				GeneralSet.print("Timer interrupted");
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public int getPcNum()
	{
		return pcNum;
	}

	public String getUserId()
	{
		return userId;
	}
}
