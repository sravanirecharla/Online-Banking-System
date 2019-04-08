/******************************************************************************
 *	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
 *	Date: September, 2012													  *
 *******************************************************************************/

import java.awt.event.ActionEvent;
import java.io.*;
import java.net.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

public class SocketClient extends JFrame implements ActionListener
{
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;

    private static JTextArea chatArea;
    private JTextField textField;
    private JLabel l1;
    private JButton sendButton;


    SocketClient()
    {
        BufferedReader in;
        PrintWriter out;
        setTitle("Client Machine");
        chatArea = new JTextArea(22, 40);
        JLabel l1 = new JLabel("Message");
        textField = new JTextField(35);
        sendButton = new JButton("Send");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        add(new JScrollPane(chatArea));
      //JPanel LPanel=new JPanel();
        add(l1);
        add(textField);
        add(sendButton);
        setSize(470, 470);
        setVisible(true);
        chatArea.setEditable(false);
        sendButton.addActionListener(this);
        textField.addActionListener(this);
    }

    public static void main(String[] args) throws IOException, UnknownHostException, IOException, ClassNotFoundException
    {
        JFrame frame = new SocketClient(); //initialize a JFrame object
        frame.show(); //display the frame
        Socket client;
        oos = null;
        ois = null;

        if (args.length != 2) {
            System.err.println("Usage: java SocketClient server port");
            return;
        }
        try {
            client = new Socket(args[0], Integer.parseInt(args[1]));

            oos = new ObjectOutputStream(client.getOutputStream());
            ois = new ObjectInputStream(client.getInputStream());
        }
        catch (UnknownHostException e) {
            System.err.println("can't locate server: " + args[0]);
            return;
        }
       // String s;
      //  byte[] b = new byte[1024];
        /* BufferedReader in = new BufferedReader(new InputStreamReader(sin)); */
       // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("connection to " + args[0] + " established");

//        do {
//            System.out.print("> ");
//            System.out.flush();
//            s = in.readLine();
//            if (s.length() == 0) continue;  // make sure it doesn’t hang
//            oos.writeObject(s.getBytes());
//            oos.flush();
////            int i = (int) ois.readObject();
////            System.out.write(b, 0, i);
////            System.out.println();
//        } while (!s.equals("exit"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String arg = e.getActionCommand();

        if (arg.equals("Send")) { //determine which button is clicked

            try {
                oos.writeObject(textField.getText());
                oos.flush();
                String o = (String)ois.readObject();
                chatArea.append(o);
                chatArea.append("\n");
                System.out.println("["+o+"]");
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }
}
