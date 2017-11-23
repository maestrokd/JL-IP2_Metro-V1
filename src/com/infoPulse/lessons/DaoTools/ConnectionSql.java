package com.infoPulse.lessons.DaoTools;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class ConnectionSql {

    private static ConnectionSql instance = null;

    private String login = "maestro";
    private String password = "maestro";
    private String databaseURL = "jdbc:mysql://localhost/metroless7?" + "user=" + login + "&password=" + password;

    private static ConnectionSource connectionSource;

    private ConnectionSql() {
        try {
            connectionSource = new JdbcConnectionSource(databaseURL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionSql getInstance() {
        if (instance == null) {
            instance = new ConnectionSql();
        }
        return instance;
    }

    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }

}
