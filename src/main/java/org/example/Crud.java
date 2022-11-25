package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class Crud {

    public boolean isUserWithId(Connection connection, long userId) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery( "SELECT * FROM users WHERE ID=" + userId + ";" );
        if (resultSet != null) {
            return true;
        }
        return false;
    }

    public void getVehiclesAndInsurances(Connection connection, long userId) throws SQLException {
        Statement stmtVehicle = connection.createStatement();
        ResultSet resultSetVehicles = stmtVehicle.executeQuery( "SELECT * FROM vehicles WHERE ID=" + userId + ";" );


        while ( resultSetVehicles.next() ) {
            long idVehicle = resultSetVehicles.getInt("id");
            String  login = resultSetVehicles.getString("login");
            String brand  = resultSetVehicles.getString("brand");
            String model  = resultSetVehicles.getString("model");
            String  additionalData = resultSetVehicles.getString("additionalData");
            // LocalDateTime insertTime = resultSetVehicles.getTime("insertTime");

            System.out.println( "ID = " + idVehicle );
            System.out.println( "LOGIN = " + login );
            System.out.println( "BRAND = " + brand );
            System.out.println( "MODEL = " + model );
            System.out.println( "ADDITIONAL DATA = " + additionalData );
            System.out.println();

            Statement stmtInsurance = connection.createStatement();
            ResultSet resultSetInsurances = stmtInsurance.executeQuery("SELECT * FROM insurances WHERE ID=" + idVehicle + ";");
            while (resultSetInsurances.next()) {
                long idInsurance = resultSetInsurances.getInt("id");
                String  insurer = resultSetInsurances.getString("insurer");
                float price  = resultSetInsurances.getFloat("price");
                String  additionalDataInsurance = resultSetInsurances.getString("additionalData");
                // LocalDateTime insertTime = resultSetInsurances.getTime("insertTime");

                System.out.println( "ID = " + idInsurance );
                System.out.println( "INSURER = " + insurer );
                System.out.println( "PRICE = " + price );
                System.out.println( "ADDITIONAL DATA = " + additionalDataInsurance );
                System.out.println();
            }
            resultSetInsurances.close();
            stmtInsurance.close();
        }
        resultSetVehicles.close();
        stmtVehicle.close();

    }


}
