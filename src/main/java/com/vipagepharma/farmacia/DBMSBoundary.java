package com.vipagepharma.farmacia;

import java.sql.*;

public class DBMSBoundary {
    public static void start()
    {
        Connection connection = null;
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://vipagesite.duckdns.org:3306/vipagepharma_farmacia",
                    "pi", "BubJbhvbj373838&#@!");

            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(
                    "select * from farmaco");
            int code;
            String title;
            while (resultSet.next()) {
                code = resultSet.getInt("id_f");
                title = resultSet.getString("nome").trim();
                System.out.println("Code : " + code
                        + " Title : " + title);
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
    } // function ends
} // class ends
