package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.sql.SQLException;
import java.util.Scanner;

public class Client {
    private Socket clientSocket;
    public static PrintWriter out;
    private BufferedReader in;
    public PrintWriter getOut() { return out; }


    public void startConnection(String ip, int port) throws IOException, SQLException, ClassNotFoundException {

        InetAddress inetAddress = InetAddress.getByName(ip);
        SocketAddress socketAddress = new InetSocketAddress(inetAddress, port);

        clientSocket = new Socket(ip, port);
        // clientSocket = new Socket(socketAddress.toString(), port);

        // clientSocket.bind(socketAddress);
        // clientSocket.connect(socketAddress);

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public String sendUserId(Long userId) throws IOException, SQLException {
        out.println(userId);
        // out.print(userId);

        String response = in.readLine();
        return response;
    }

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Client client = new Client();
        client.startConnection("127.0.0.1", 6666);

        Scanner input = new Scanner(System.in);
        System.out.println("If you want to send a userId write it" );

        if (input.hasNextInt()) {
            Long userId = (long) input.nextInt();
            client.sendUserId(userId);
        }
    }
}
