package projet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.PrintWriter;
import com.google.gson.*;
import java.sql.*;

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
			String message = in.readLine();
			System.out.println("Message du client: " +message);



			Message message1 = new Message(message);

			Gson gson = new Gson();
			String json = gson.toJson(message1);

			Gson gson1 = new Gson();
			Message message2 = gson.fromJson(json, Message.class);

			try {

				//connection to database
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/pds_dette?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "root", "");
				System.out.println("CONNEXION DB");

				// create a Statement from the connection
				Statement statement = myConn.createStatement();

				// insert the data
				statement.executeUpdate("INSERT INTO message (contenu_message) VALUES ('"+ message +"')");



				//create statement
				Statement myStmt = myConn.createStatement();

				//execute sql query
				ResultSet myRs = myStmt.executeQuery("select * from message");

				//results set
				while (myRs.next()) {
					System.out.println(myRs.getString("contenu_message"));
				}
			}
			catch (Exception exc) {
				exc.printStackTrace();
			}           
			socketduserveur.close();
			socketserver.close();

		}catch (IOException e) {

			e.printStackTrace();
		}



	}


}

