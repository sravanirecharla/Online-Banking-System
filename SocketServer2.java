/******************************************************************************
*	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
*	Date: September, 2012													  *
*******************************************************************************/

import java.io.*;
import java.net.*;

public class SocketServer2
{
   public static void main(String[] args) throws IOException {
      Socket echoSocket;
      InputStream sin = null;
      OutputStream sout  = null;
      byte[] b = new byte[1024];
      int i;
      if (args.length != 1) {
	 System.err.println("Usage: java SocketServer port");
	 return;
      }
      try {
	 ServerSocket s = new ServerSocket(Integer.parseInt(args[0]));
	 echoSocket = s.accept();
	 System.out.println("Connection from: " + echoSocket.getInetAddress());
	 sin = echoSocket.getInputStream();
	 sout = echoSocket.getOutputStream();

	 while ((i = sin.read(b)) != -1) {
            byte[] temp = new byte[i+2];
            temp[0] = (byte) '*';
            for (int k=1; k<=i; k++)
               temp[k] = b[k-1];
            temp[i+1] = (byte)'*';
	    sout.write(temp, 0, i+2);
	    sout.flush();
	 }
      }
      catch (Exception e) {
	 System.err.println(e);
	 return;
      }
   }
}
