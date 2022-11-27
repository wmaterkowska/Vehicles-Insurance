import org.checkerframework.checker.units.qual.C;
import org.example.Client;
import org.example.Crud;
import org.example.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    private static Client client;

    @Test
    public void startConnectionTest() throws IOException, SQLException, ClassNotFoundException {
        Client client = new Client();
        client.startConnection("127.0.0.1", 6666);
    }


    @Test
    public void sendUserIdTest() throws IOException, SQLException, ClassNotFoundException {
        Client client = new Client();
        client.startConnection("127.0.0.1", 6666);

        Long userId = (long) 1;
        String response = client.sendUserId(userId);

        //Crud crud = new Crud();
        //String response = crud.getVehiclesAndInsurances(crud.getUserLogin(userId));

        String expected =  "ID = " + 1 + "LOGIN = " + "Doe" + "BRAND = "
                + "ford" + "MODEL = " + "mustang" + "\n";

        assertEquals(expected, response);
    }


}
