package org.example;

import java.io.*;
import java.net.*;
import java.sql.SQLException;

public class Server {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws IOException, SQLException, ClassNotFoundException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();

        out = new PrintWriter(clientSocket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        Long userId = (long) in.read();
        Crud crud = new Crud();
        if (userId != null) {
            out.print(crud.isUserWithId(userId));
        } else {
            out.println("unrecognised request");
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Server server = new Server();
        // server.stop();
        server.start(8080);
    }


}

