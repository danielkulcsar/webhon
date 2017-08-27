import java.io.*;
import java.net.*;
import java.util.*;

// Chatting Server
public class ChatServer {
	
	// start
	public void start(int port) {
		ServerSocket server; // server socket
		Socket socket;       // socket
		ChatServerThread thread;// thread
		
		try {
			server=new ServerSocket(port);
			System.err.println("Chatting Server Start :"+port);
			while(true) {
				try {
					// wait for connecting
					socket=server.accept();
					
					// chatting server thread start
					thread=new ChatServerThread(socket);
					thread.start();
				} catch (IOException e) {
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	// main
	public static void main(String[] args) {
		ChatServer server=new ChatServer();
		server.start(8080);
	}
}