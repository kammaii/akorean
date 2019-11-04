package akorean.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {

  private String serverAddress;
  private String username;
  private String password;
  private String databaseName;

  public DatabaseManager(String serverAddress, String username, String password, String databaseName) {
    this.serverAddress = serverAddress;
    this.username = username;
    this.password = password;
    this.databaseName = databaseName;
  }

  <T> T execute(DatabaseCall<T> call) throws SQLException {

    String url = "jdbc:mysql://"+serverAddress+"/"+databaseName+"?user="+username+"&password="+password+"&serverTimezone=UTC";

    Properties props = new Properties();
    //props.setProperty("ssl", "true");

    try (Connection conn = DriverManager.getConnection(url, props)) {
      return call.withConnection(conn);
    }
  }
}
