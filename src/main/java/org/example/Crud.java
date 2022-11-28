package org.example;

import java.sql.*;
import java.time.LocalDateTime;

public class Crud {

    Connection c;

    public Connection connectToDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/dev",
                        "postgres", "postgres");
        System.out.println("Opened database successfully");
        return c;
    }


    public boolean isUserWithId(long userId) throws SQLException, ClassNotFoundException {

        Connection connection = connectToDatabase();
        Statement stmtIsUser = connection.createStatement();

        ResultSet resultSet = stmtIsUser.executeQuery( "SELECT * FROM users WHERE ID= " + userId + ";" );
        if (resultSet.next()) {
            System.out.println("There is a user with id: " + userId);
            return true;
        }
        System.out.println("There is no user with id: " + userId);
        return false;
    }

    public String getVehiclesAndInsurances(String userLogin) throws SQLException, ClassNotFoundException {
        Connection connection = connectToDatabase();
        Statement stmtVehicle = connection.createStatement();
        Statement stmtInsurance = connection.createStatement();

        String result = "";

        ResultSet resultSetVehicles = stmtVehicle.executeQuery( "SELECT * FROM vehicles WHERE login='" + userLogin + "';" );
        while ( resultSetVehicles.next() ) {
            long idVehicle = resultSetVehicles.getInt("id");
            String  login = resultSetVehicles.getString("login");
            String brand  = resultSetVehicles.getString("brand");
            String model  = resultSetVehicles.getString("model");
            String  additionalData = resultSetVehicles.getString("additional_data");
            // LocalDateTime insertTime = resultSetVehicles.getTime("insertTime");

            System.out.println( "ID = " + idVehicle );
            System.out.println( "LOGIN = " + login );
            System.out.println( "BRAND = " + brand );
            System.out.println( "MODEL = " + model );
            System.out.println( "ADDITIONAL DATA = " + additionalData );
            System.out.println();

            String resultVehicle = "ID = " + idVehicle + "LOGIN = " + login + "BRAND = "
                    + brand + "MODEL = " + model + "\n" ;
            result += resultVehicle;


            ResultSet resultSetInsurances = stmtInsurance.executeQuery("SELECT * FROM insurances WHERE ID=" + idVehicle + ";");
            while (resultSetInsurances.next()) {
                long idInsurance = resultSetInsurances.getInt("id");
                long idVehicleInsurance = resultSetInsurances.getInt("vehicle_id");
                String  insurer = resultSetInsurances.getString("insurer");
                float price  = resultSetInsurances.getFloat("price");
                String  additionalDataInsurance = resultSetInsurances.getString("additional_data");
                // LocalDateTime insertTime = resultSetInsurances.getTime("insertTime");

                System.out.println( "ID = " + idInsurance );
                System.out.println( "VEHICLE ID = " + idVehicleInsurance);
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


        return result;
    }

    public String getUserLogin(Long userId) throws SQLException, ClassNotFoundException {
        Connection connection = connectToDatabase();
        Statement stmtUser = connection.createStatement();

        ResultSet resultSetUser = stmtUser.executeQuery( "SELECT * FROM users WHERE ID= '" + userId + "';" );
        String userLogin ="";
        while ( resultSetUser.next() ) {
            userLogin = resultSetUser.getString("login");
        }
        return userLogin;
    }

}
