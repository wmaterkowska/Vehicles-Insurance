package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.file.LinkOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Client {
    private Socket clientSocket;
    public static PrintWriter out;
    private BufferedReader in;

    private Connection c;

    public PrintWriter getOut() { return out; }


    public void startConnection(String ip, int port) throws IOException, SQLException, ClassNotFoundException {

        InetAddress inetAddress = InetAddress.getByName(ip);
        SocketAddress socketAddress = new InetSocketAddress(inetAddress, port);

        clientSocket = new Socket(ip, port);
        // clientSocket = new Socket(socketAddress.toString(), port);

        // clientSocket.bind(socketAddress);
        // clientSocket.connect(socketAddress);

        out = new PrintWriter(clientSocket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public String sendUserId(Long userId) throws IOException, SQLException {
        // out.print(userId);
        // Long response = (long) in.read();
        // return response;


        String request = "SELECT * FROM users WHERE ID= '" + userId + "';";
        out.write(request);
        out.flush();

        StringBuffer stringBuffer = new StringBuffer();
        while(true) {
            int x = in.read();
            if ( x == -1) {
                return "No user with id: " + userId.toString();
            }
            stringBuffer.append((char) x);
            String response = stringBuffer.toString();
            System.out.println(response);
            return response;
        }
    }

    public String getInfoFromDatabase(String userLogin) throws IOException {

        String requestVehicles = "SELECT * FROM vehicles WHERE login=" + userLogin + ";";
        out.write(requestVehicles);

        StringBuffer stringBuffer = new StringBuffer();
        while(true) {
            int x = in.read();
            if ( x == -1) {
                return "No vehicles for user " + userLogin;
            }
            stringBuffer.append((char) x);
            String response = stringBuffer.toString();
            System.out.println(response);
            return response;
        }
    }

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Client client = new Client();
        client.startConnection("localhost", 5432);
        client.sendUserId((long) 1);
    }

}
