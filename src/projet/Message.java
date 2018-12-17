package projet;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.sql.*;
import com.google.gson.*;

public class Message {

	private String message;

	public Message(String message){
		this.message = message;
	}

}
