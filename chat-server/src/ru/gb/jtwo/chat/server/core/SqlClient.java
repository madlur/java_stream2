package ru.gb.jtwo.chat.server.core;

import java.sql.*;

public class SqlClient {

    private static Connection connection;
    private static Statement statement;

    synchronized static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:chat-server/chat-db.sqlite");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    synchronized static String getNickname(String login, String password) {
        String query = String.format("select nickname from users where login='%s' and password='%s'", login, password);
        try (ResultSet set = statement.executeQuery(query)) {
            if (set.next())
                return set.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public synchronized static void changeNickName(String query) {

        try {
            statement.executeUpdate(query);

        } catch (SQLException throwables) {
            System.out.println("Change nickname exception");
            throwables.printStackTrace();
        }
    }

    synchronized static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    synchronized static void addClientToDB(String login, String password) {

            try {
                connection.setAutoCommit(false);
                statement.executeUpdate("INSERT INTO users (login, password, nickname) VALUES ('" + login +"', '"+ password + "', '"+ login + "')");
                connection.commit();

            } catch (SQLException e) {
                System.out.println("SQL Error in addClient method");
            }
        }

    }
