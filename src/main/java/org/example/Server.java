package org.example;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.Scanner;

public class Server {


    public static void main(String args[]) throws Exception {

        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/dev",
                            "postgres", "postgres");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");


        try ( Socket socket = new Socket() ) {

            InputStream input = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(input);

            Long userId = (long) reader.read();

            Crud crud = new Crud();

            if ( crud.isUserWithId(c, userId) == true) {
                crud.getVehiclesAndInsurances(c, userId);
            }

        }
    }
}
