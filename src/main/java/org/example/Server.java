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

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        Crud crud = new Crud();
        crud.connectToDatabase();

        // Long userId = (long) in.read();
        String userIdString =   in.readLine();
        Long userId = Long.parseLong(userIdString);

        if (crud.isUserWithId(userId) == true) {
            String userLogin = crud.getUserLogin( userId );
            out.print(crud.getVehiclesAndInsurances(userLogin));
        } else {
            out.print("No such User");
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
        server.start(6666);
    }


}

