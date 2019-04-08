/******************************************************************************
 *	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
 *	Date: September, 2012													  *
 *******************************************************************************/

import java.io.*;
import java.net.*;

public class SocketServer
{
    public static void main(String[] args) throws IOException {
        Socket echoSocket;
        if (args.length != 1) {
            System.err.println("Usage: java SocketServer port");
            return;
        }
        try {
			ServerSocket s = new ServerSocket(Integer.parseInt(args[0]));
			while(true) {
				echoSocket = s.accept();
				System.out.println("Connection from: " + echoSocket.getInetAddress());

				ObjectInputStream ois = new ObjectInputStream(echoSocket.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(echoSocket.getOutputStream());

				Thread thr = new WebThread((echoSocket), ois, oos);
				thr.start();
			}
//            while ((i = sin.read(b)) != -1) {
//                System.out.print(b);
//                byte[] temp = new byte[i+2];
//                temp[0] = (byte) '*';
//                for (int k=1; k<=i; k++)
//                    temp[k] = b[k-1];
//                temp[i+1] = (byte)'*';
            //sout.write(temp, 0, i+2);

//                sout.flush();
//            }
        }
        catch (Exception e) {
            System.err.println(e);
            return;
        }
    }
}


class WebThread extends Thread {
	private Socket sock;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;


	public WebThread(Socket s, ObjectInputStream in, ObjectOutputStream out) {
		this.sock = s;
		this.ois = in;
		this.oos = out;
	}

	public void run() {
		try {
            while(true)
            {
                String in;
                in = (String)ois.readObject();
                System.out.println("["+in+"]");
                //oos.writeObject(in);
                oos.writeObject("*-*"+in+"*-*");
                oos.flush();
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}