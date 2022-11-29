package server;

import org.example.DatabaseHandler;

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

        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.connectToDatabase();

        Long userId = (long) in.read();
        String userIdString =   in.readLine();
        // Long userId = Long.parseLong(userIdString);

        if (databaseHandler.isUserWithId(userId) == true) {
            String userLogin = databaseHandler.getUserLogin( userId );
            out.print(databaseHandler.getVehiclesAndInsurances(userLogin));
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
        server.start(6666);
    }


}

