package util;

import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {

        try (Connection connection = DBConnection.getConnection()) {

            System.out.println("Connected Successfully");

        } catch (Exception e) {

            System.out.println("Connection Failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
