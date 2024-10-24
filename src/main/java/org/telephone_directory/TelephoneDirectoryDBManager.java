package org.telephone_directory;

import java.sql.*;

public class TelephoneDirectoryDBManager {
    private final static String url = "  ";
    private final static String user = "  ";
    private final static String password = "  ";
    private final static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void addContact(String firstName, String lastName, String number) throws SQLException {
      var statement = connection.createStatement();
        String add = String.format("INSERT INTO persons (first_name, last_name, number) " +
                "VALUES ('%s', '%s', '%s');", firstName, lastName, number);
        statement.executeUpdate(add);
    }

    public static void getContacts() throws SQLException {
        var statement = connection.createStatement();
        String getAll = "SELECT * FROM persons";
        ResultSet allData = statement.executeQuery(getAll);
        while (allData.next()) {
            System.out.print("\n" + allData.getLong("id") + ". "
                    + allData.getString("first_name") + " "
                    + allData.getString("last_name") + " "
                    + allData.getString("number"));
            System.out.println("\n ---------------------------");
        }
    }

    public static void searchByName(String name) throws SQLException {
        var statement = connection.createStatement();

        String result = "SELECT * FROM persons " +
                "WHERE first_name ilike ('%s%s');".formatted(name, "%");

        ResultSet allData = statement.executeQuery(result);
        while (allData.next()) {
            System.out.print("\n" + allData.getLong("id") + ". "
                    + allData.getString("first_name") + " "
                    + allData.getString("last_name") + " "
                    + allData.getString("number"));
            System.out.println("\n ---------------------------");
        }
    }

    public static void searchByNumber(String number) throws SQLException {
        var statement = connection.createStatement();

        String result = ("SELECT * FROM persons " +
                "WHERE number ILIKE ('%s%s%s');").formatted("%", number, "%");
        ResultSet allData = statement.executeQuery(result);
        while (allData.next()) {
            System.out.print("\n" + allData.getLong("id") + ". "
                    + allData.getString("first_name") + " "
                    + allData.getString("last_name") + " "
                    + allData.getString("number"));
            System.out.println("\n ---------------------------");
        }
    }

    public static void deleteByNumber(String number) throws SQLException {
        var statement = connection.createStatement();
        String delete = ("DELETE FROM persons " +
                "WHERE number = '%s';").formatted(number);
        statement.executeUpdate(delete);
        System.out.println("Контакт було видалено успішно.");
    }

    public static void deleteByName(String first_name, String last_name) throws SQLException {
        var statement = connection.createStatement();
        String delete = ("DELETE FROM persons " +
                "WHERE first_name = '%s' AND last_name = '%s';").formatted(first_name, last_name);
        statement.executeUpdate(delete);
        System.out.println("Контакт було видалено успішно.");

    }
}







