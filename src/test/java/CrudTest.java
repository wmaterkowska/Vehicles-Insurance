import org.checkerframework.checker.units.qual.C;
import org.example.Crud;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CrudTest {

    @Test
    public void isUserWithIdTest() throws SQLException, ClassNotFoundException {
         Long userId = (long) 1;
         Crud crud = new Crud();

         Boolean response = crud.isUserWithId(userId);
         Boolean expected = true;
         assertEquals(expected, response);
    }

    @Test
    public void getVehiclesAndInsurancesTest() throws SQLException, ClassNotFoundException {
        String userLogin = "Doe";

        Crud crud = new Crud();

        String response = crud.getVehiclesAndInsurances(userLogin);
        String expected = "ID = " + 1 + "LOGIN = " + "Doe" + "BRAND = "
                + "ford" + "MODEL = " + "mustang" + "\n";

        assertEquals(expected,response);

    }

    @Test
    public void getUserLoginTest() throws SQLException, ClassNotFoundException {
        Long userId = (long) 1;
        Crud crud = new Crud();

        String response = crud.getUserLogin(userId);
        String expected = "Doe";

        assertEquals(expected,response);
    }


}
