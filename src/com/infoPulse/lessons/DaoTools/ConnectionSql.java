package com.infoPulse.lessons.DaoTools;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class ConnectionSql {

    private static String login = "maestro";
    private static String password = "maestro";
    private static String databaseURL = "jdbc:mysql://localhost/metroless7?" + "user=" + login + "&password=" + password;
    private static ConnectionSource connectionSource;

    static {
        try {
            connectionSource = new JdbcConnectionSource(databaseURL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionSource  getConnection() {
            return connectionSource;
    }

}
