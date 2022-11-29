import org.example.DatabaseHandler;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseHandlerTest {

    @Test
    public void isUserWithIdTest() throws SQLException, ClassNotFoundException {
         Long userId = (long) 1;
         DatabaseHandler databaseHandler = new DatabaseHandler();

         Boolean response = databaseHandler.isUserWithId(userId);
         Boolean expected = true;
         assertEquals(expected, response);
    }

    @Test
    public void getVehiclesAndInsurancesTest() throws SQLException, ClassNotFoundException {
        String userLogin = "Doe";

        DatabaseHandler databaseHandler = new DatabaseHandler();

        String response = databaseHandler.getVehiclesAndInsurances(userLogin);
        String expected = "ID = " + 1 + "LOGIN = " + "Doe" + "BRAND = "
                + "ford" + "MODEL = " + "mustang" + "\n";

        assertEquals(expected,response);

    }

    @Test
    public void getUserLoginTest() throws SQLException, ClassNotFoundException {
        Long userId = (long) 1;
        DatabaseHandler crud = new DatabaseHandler();

        String response = crud.getUserLogin(userId);
        String expected = "Doe";

        assertEquals(expected,response);
    }


}
