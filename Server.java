/*
 * Author: Colter Therrell
 * 
 * Title: Server.java
 * 
 * Usage: Simple server with minimal GUI to be used in server/client model
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Server 
{
	private static final int PORT = 1234;
	public static int totalConnections = 0;
	
	public static void main(String[] args) throws Exception
	{
		JFrame serverMainFrame = new JFrame("Server");
		JPanel serverNorthPanel = new JPanel();
		JPanel serverSouthPanel = new JPanel();
		JPanel serverMiddlePanel = new JPanel();
		final JLabel serverStatus = new JLabel("Server not running");
		
		serverMainFrame.getContentPane().setLayout(new BorderLayout());
		
		serverMainFrame.setPreferredSize(new Dimension(500, 500));
		
		serverNorthPanel.setPreferredSize(new Dimension(150, 150));
		serverSouthPanel.setPreferredSize(new Dimension(150, 150));
		
		serverMainFrame.add(serverNorthPanel, BorderLayout.NORTH);
		serverMainFrame.add(serverSouthPanel, BorderLayout.SOUTH);
		serverMainFrame.add(serverMiddlePanel, BorderLayout.CENTER);
		
		JButton startButton = new JButton("Start Server");
		JButton endButton = new JButton("Close Server");
		
		serverNorthPanel.add(startButton);
		serverSouthPanel.add(serverStatus);
		serverMiddlePanel.add(endButton);
		
		serverMainFrame.setVisible(true);
		serverMainFrame.pack();
		
		final ServerSocket server = new ServerSocket(PORT);
		
		startButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.print("Server running, waiting for connection..." + "\n");
				serverStatus.setText("Server running, waiting for connection..."); //not working, has something to do with server.accept()
				try
				{
					Socket socket = server.accept();
					
					if(socket.isConnected()) //not working for multiple clients
					{
						totalConnections++;
					}
					
					/*
					java.io.InputStream inputStream = socket.getInputStream();
					ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
					
					int readInt = (int) objectInputStream.readObject();
					
					for(int i = 0; i < readInt; i++)
					{
						totalConnections++;
					}
					*/
					
					
					if(totalConnections >= 1)
					{
						System.out.print(totalConnections + " Client(s) are connected" + "\n");
						serverStatus.setText(totalConnections + " Client(s) are connected");
						
					}
					
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		
		endButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					server.close();
					System.out.println("Server has been closed" + "\n");
					serverStatus.setText("Server has been closed");
					
					
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		
		
	}

}
