
import client.Client;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

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

        String expected =  "ID = " + 1 + "LOGIN = " + "Doe" + "BRAND = "
                + "ford" + "MODEL = " + "mustang" + "\n";

        assertEquals(expected, response);
    }


}
