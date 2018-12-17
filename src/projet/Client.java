package projet;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;



public class Client {

	public static void main(String[] args) {


		Socket socket;
		BufferedReader in;
		PrintWriter out;

		try {

			socket = new Socket(InetAddress.getLocalHost(),2018);	
			System.out.println("Demande de connexion");

			in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
			String messagedistant = in.readLine();
			System.out.println(messagedistant);

			
				
			out = new PrintWriter(socket.getOutputStream());
			String message;
			Scanner sc = new Scanner(System.in);
			System.out.println("Entrez un message :");
			message = sc.nextLine();
			out.println(message);
			out.flush();
			


			socket.close();

		}catch (UnknownHostException e) {

			e.printStackTrace();
		}catch (IOException e) {

			e.printStackTrace();
		}
	}

}

