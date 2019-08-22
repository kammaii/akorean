package net.awesomekorean.callcenter.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {

/**
 * Here's how to create a new database and database user
 *  postgres=# create database mydb;
 *  postgres=# create user myuser with encrypted password 'mypass';
 *  postgres=# grant all privileges on database mydb to myuser;
 */

  private String username;
  private String password;
  private String databaseName;

  public DatabaseManager(String username, String password, String databaseName) {
    this.username = username;
    this.password = password;
    this.databaseName = databaseName;
  }

  public <T> T execute(DatabaseCall<T> call) throws SQLException {

    String url = "jdbc:postgresql://localhost/" + databaseName;

    Properties props = new Properties();
    props.setProperty("user", username);
    props.setProperty("password", password);
    //props.setProperty("ssl", "true");

    // Options
    // Do we want to use the same connection for all queries?
    // Do we want to open and close a connection for every query?
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(url, props);
      return call.withConnection(conn);
    } finally {
      if(conn != null) {
        conn.close();
      }
    }

  }

}
