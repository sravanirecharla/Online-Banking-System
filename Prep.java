import java.util.*;
import java.io.*;
import java.net.*;

class Multithread extends Thread {
	private  Socket socket;
	ObjectInputStream sin = null;
	ObjectOutputStream sout = null;
	public Multithread(Socket socket) {
		this.socket = socket;
	}
	@Override
	public void run() {
		try
		  {
		     System.out.println("Client Connected:" +socket);
		     sin = new ObjectInputStream(socket.getInputStream());
		     sout = new ObjectOutputStream(socket.getOutputStream());
		     while(true) {
				  String clientmsg = (String)sin.readObject();
				  System.out.println(clientmsg);
				  if(clientmsg.equals("exit")) {
					  break;
			      }
			      String servermsg = "*"+clientmsg+"*";
			      sout.writeObject(servermsg);
			  }
		  } catch (IOException e) {
			System.out.println("Oops: " + e.getMessage());
		} catch(Exception e) {
			System.err.println(e);
			return;
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
public class Prep {
	 public static void main(String[] args) throws IOException {
		 if(args.length != 1) {
			 System.err.println("Usage: Java socketServer port:");
			 return;
		 }
		 try {
			 Prep  s = new Prep(Integer.parseInt(args[0]));
			 while (true) {
			 new Multithread(s.accept()).start();
		 }
		 } catch (IOException e) {
			 System.out.println("Server exception"+ e.getMessage());
		 }
	 }
 }

