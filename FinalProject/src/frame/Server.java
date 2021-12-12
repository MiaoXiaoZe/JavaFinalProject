package frame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Server extends JFrame implements Runnable {
	public static Server instance = new Server();

	// Text area for displaying contents
	JTextArea ta;
	
	public static void main(String[] args) {
		instance.setVisible(true);
	}

	public Server() {
		super("Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ta = new JTextArea();
		this.add(ta);

		setSize(400, 200);
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		try {
			// Create a server socket
			ServerSocket serverSocket = new ServerSocket(8000);

			while (true) {

				// Listen for a connection request
				ta.append("listening for incoming connection\n");
				Socket socket = serverSocket.accept();
				ta.append("Got connection from" + socket.getInetAddress().getHostAddress());

				// Create data input and output streams
				DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
				DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
				// Receive radius from the client
				while (true) {
					try {
						double radius = inputFromClient.readDouble();

						// Compute area
						double area = radius * radius * Math.PI;

						// Send area back to the client
						outputToClient.writeDouble(area);
//			          ta.append("Connection from " + socket.getInetAddress().getHostAddress());
						ta.append("Radius received from client: " + radius + '\n');
						ta.append("Area is: " + area + '\n');

						Thread.sleep(1);
					} catch (IOException e) {
						System.err.println("connection lost");
						break;
					}
				}

			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
