package socket_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.PrintWriter;

public class Serveur {
	
	public static void main(String[] args) {
		
		ServerSocket socketserver  ;
		Socket socketduserveur ;
		BufferedReader in;
		PrintWriter out;
		
		try {
		
			socketserver = new ServerSocket(2018);
			System.out.println("Le serveur est à l'écoute du port "+socketserver.getLocalPort());
			socketduserveur = socketserver.accept(); 
		        System.out.println("Un client s'est connecté");
			out = new PrintWriter(socketduserveur.getOutputStream());
		        out.println("Bonjour, vous êtes connecté !");
		        out.flush();
		        
		        in = new BufferedReader (new InputStreamReader (socketduserveur.getInputStream()));
		        String message_distant = in.readLine();
		        System.out.println(message_distant);
		                
		        socketduserveur.close();
		        socketserver.close();
		        
		}catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}

