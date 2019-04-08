import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;     //including Java packages used by this program
import java.awt.event.*;

public class SocketClient {

	public static void main(String[] args) throws IOException {

		JFrame f = new JFrame("Socket Client");

		JButton sendButton = new JButton("Send");
		JButton exitButton = new JButton("Exit");

		JLabel clientMessageLabel, serverMessageLabel;
		JLabel serverIPLabel, serverPortLabel;

		JTextField clientTextField = new JTextField();
		JTextField serverTextField = new JTextField();
		JTextField serverIPField = new JTextField();
		JTextField serverPortField = new JTextField();

		clientMessageLabel = new JLabel("Client Message");
		serverMessageLabel = new JLabel("Server Message");
		serverIPLabel = new JLabel("Server IP Address");
		serverPortLabel = new JLabel("Server Port Number");

		serverIPLabel.setBounds(50, 50, 150, 30);
		serverIPField.setBounds(250, 50, 150, 30);
		serverPortLabel.setBounds(50, 100, 150, 30);
		serverPortField.setBounds(250, 100, 150, 30);
		clientMessageLabel.setBounds(50, 150, 100, 30);
		clientTextField.setBounds(200, 150, 200, 30);
		serverMessageLabel.setBounds(50, 200, 100, 30);
		serverTextField.setBounds(200, 200, 200, 30);
		sendButton.setBounds(50, 250, 95, 30);
		exitButton.setBounds(200, 250, 95, 30);

		//Socket client;
		//String IPAddress = serverIPField.getText();
		//String PortNumber = serverPortField.getText();

		//client = new Socket(IPAddress, Integer.valueOf(PortNumber));

		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Socket client;
				String IPAddress = serverIPField.getText();
				String PortNumber = serverPortField.getText();
				if(!IPAddress.equals("") && !PortNumber.equals("")) {
					try {
	     				client = new Socket(IPAddress, Integer.parseInt(PortNumber));

	     				ObjectOutputStream OOS = new ObjectOutputStream(client.getOutputStream());
	     				String clientMsg = clientTextField.getText();
	     				System.out.println(clientMsg);
	     				OOS.writeObject(clientMsg);

						ObjectInputStream OIS = new ObjectInputStream(client.getInputStream());
						String serverMsg = (String) OIS.readObject();
						System.out.println(serverMsg);
      					serverTextField.setText(serverMsg);

						OIS.close();
						OOS.close();
            			client.close();
					} catch (UnknownHostException ex) {
						System.err.println("can't locate server: " + IPAddress);
						return;
					} catch (IOException IOE) {
						IOE.printStackTrace();
					} catch (ClassNotFoundException CNFE) {
						CNFE.printStackTrace();
					}
  				}
			}
		});

		f.add(serverIPLabel);
		f.add(serverIPField);
		f.add(serverPortLabel);
		f.add(serverPortField);
		f.add(clientMessageLabel);
		f.add(clientTextField);
		f.add(serverMessageLabel);
		f.add(serverTextField);
		f.add(sendButton);
		f.add(exitButton);

		f.setSize(500, 350);
		f.setLayout(null);
		f.setVisible(true);

		/*

		Socket client;
      InputStream sin = null;
      OutputStream sout  = null;

      if (args.length != 2) {
	       System.err.println("Usage: java SocketClient server port");
	       return;
      }
      try {
	     client = new Socket(args[0], Integer.parseInt(args[1]));
	     sin = client.getInputStream();
	     sout = client.getOutputStream();
      }
      catch (UnknownHostException e) {
         System.err.println("can't locate server: " + args[0]);
	     return;
      }
      String s;
      byte[] b = new byte[1024];
      //BufferedReader in = new BufferedReader(new InputStreamReader(sin));
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      System.out.println("connection to " + args[0] + " established");
      do {
	      System.out.print("> ");
	      System.out.flush();
	      s = in.readLine();
	      if (s.length() == 0) continue;  // make sure it doesn�t hang
              sout.write(s.getBytes());
	      sout.flush();
	      int i = sin.read(b);
	      System.out.write(b, 0, i);
	      System.out.println();
      } while (!s.equals("exit"));

      */
  }
}
