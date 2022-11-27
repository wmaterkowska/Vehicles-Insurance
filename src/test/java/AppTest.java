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

    static void populateDatabase() throws IOException, SQLException, ClassNotFoundException {
        client = new Client();
        client.startConnection("127.0.0.1", 5432);


        PrintWriter out = client.getOut();

        String requestUser = "INSERT INTO users (id, nick, login, password) VALUES (2, 'John','Doe', 'password' );";
        client.out.write(requestUser);
        client.out.flush();

        String requestVehicle = "INSERT INTO vehicles (id, login, brand, model, ) VALUES (2, 'Doe', 'ford', 'mustang' );";
        out.write(requestVehicle);
        out.flush();

        String requestInsurance = "INSERT INTO insurances (id, vehicle_id, insurer, price) VALUES (2, 1,'Unknown', 100 );";
        out.write(requestInsurance);
        out.flush();

    }

    @Test
    public void startConnectionTest() throws IOException, SQLException, ClassNotFoundException {
        Client client = new Client();
        client.startConnection("127.0.0.1", 5432);
    }


    @Test
    public void sendUserIdTest() throws IOException, SQLException, ClassNotFoundException {
        populateDatabase();
        Long userId = (long) 1;
        assertEquals("No user with id: " + userId.toString(), client.sendUserId((long) userId) );
    }

    @Test
    public void getInfoFromDatabaseTest() throws IOException, SQLException, ClassNotFoundException {

        populateDatabase();
        String userLogin = "Doe";
        //Crud crud = new Crud();
        //String result = crud.getVehiclesAndInsurances(userLogin);

        String result = client.getInfoFromDatabase(userLogin);
        String expected = "ID = " + 1 + "LOGIN = " + "Doe" + "BRAND = "
                + "ford" + "MODEL = " + "mustang";

        assertEquals(expected, result);

    }

}
