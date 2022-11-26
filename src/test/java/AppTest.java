import org.checkerframework.checker.units.qual.C;
import org.example.Client;
import org.example.Crud;
import org.example.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    private static Client client;

    @BeforeAll
    static void populateDatabase() throws IOException {
        client = new Client();
        client.startConnection("127.0.0.1", 5432);

        PrintWriter out = client.getOut();

        String requestUser = "INSERT INTO users (id, nick, login, password) VALUES (1, 'John','Doe', 'password' );";
        out.write(requestUser);

        String requestVehicle = "INSERT INTO vehicles (id, login, brand, model, ) VALUES (1, 'Doe', 'ford', 'mustang' );";
        out.write(requestVehicle);

        String requestInsurance = "INSERT INTO insurances (id, vehicle_id, insurer, price) VALUES (1, 1,'Unknown', 100 );";
        out.write(requestInsurance);

    }

    @Test
    public void startConnectionTest() throws IOException {
        Client client = new Client();
        client.startConnection("127.0.0.1", 5432);
    }


    @Test
    public void giveUserIdTest() throws IOException, SQLException {

        Long userId = (long) 1;
        assertEquals("No user with id: " + userId.toString(), client.sendUserId((long) userId) );
    }

    @Test
    public void downloadInfoFromDatabaseTest() throws IOException, SQLException, ClassNotFoundException {
        client.startConnection("127.0.0.1", 5432);

        Long userId = (long) 1;
        Crud crud = new Crud();

        String result = crud.getVehiclesAndInsurances(userId);

        assertEquals("test", result);

    }

}
