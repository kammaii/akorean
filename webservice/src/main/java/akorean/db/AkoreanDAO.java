package akorean.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AkoreanDAO {

  DatabaseManager databaseManager;

  public AkoreanDAO(DatabaseManager databaseManager) {
    this.databaseManager = databaseManager;
  }


  public List<Map<String, String>> getUsers() {

    try {
      return databaseManager.execute(new DatabaseCall<List<Map<String, String>>>() {

        String sql = "SELECT * FROM USERS";

        @Override
        public List<Map<String, String>> withConnection(Connection connection) throws SQLException {

          System.out.println("Attempting find all users");
          System.out.println(sql);

          Statement statement = connection.createStatement();
          ResultSet rs = statement.executeQuery(sql);
          List<Map<String, String>> results = new ArrayList<>();

          while(rs.next()) {
            Map<String, String> result = userResultSetToMap(rs);
            results.add(result);
          }

          statement.close();
          return results;
        }
      });
    } catch (SQLException e) {
      System.out.println("ERROR: Unable to get Users because of SQL Exception");
      System.out.println(e);
      return null;
    }

  }

  public Map<String, String> getUserById(String userId) {

    try {
      return databaseManager.execute(new DatabaseCall<Map<String, String>>() {

        String sql = "SELECT * FROM USERS WHERE USER_ID = "+ userId;

        @Override
        public Map<String, String> withConnection(Connection connection) throws SQLException {

          System.out.println("Attempting find user with userId = '" + userId + "'");
          System.out.println(sql);

          Statement statement = connection.createStatement();
          ResultSet rs = statement.executeQuery(sql);
          List<Map<String, String>> results = new ArrayList<>();

          while(rs.next()) {
            Map<String, String> result = userResultSetToMap(rs);
            results.add(result);
          }

          statement.close();
          if(results.size() <= 0) {
            System.out.println("Unable to find user with userId= '" + userId + "'");
            return null;
          }
          if(results.size() > 1) {
            System.out.println("WARNING!!! Found multiple users with userId= '" + userId + "'");
          }

          return results.get(0);
        }
      });
    } catch (SQLException e) {
      System.out.println("ERROR: Unable to get Users because of SQL Exception");
      System.out.println(e);
      return null;
    }

  }

  public Map<String, String> insertUser(Map<String, String> newUser) {

    try {
      return databaseManager.execute(new DatabaseCall<Map<String, String>>() {

        String username = newUser.get("USERNAME");
        String email = newUser.get("EMAIL");
        String password = newUser.get("PASSWORD");

        String sql = "INSERT INTO USERS (USERNAME, EMAIL, PASSWORD) " +
                "values ('" + username + "', '" + email + "', '" + password + "')";

        @Override
        public Map<String, String> withConnection(Connection connection) throws SQLException {

          System.out.println("Attempting to insert new user");
          System.out.println(sql);

          Statement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

          int rows = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
          ResultSet rs = statement.getGeneratedKeys();
          rs.next();
          Integer userId = rs.getInt(1);

          newUser.put("USER_ID", userId.toString());
          statement.close();

          return newUser;
        }
      });
    } catch (SQLException e) {
      System.out.println("ERROR: Unable to insert user because of SQL Exception");
      System.out.println(e);
      return null;
    }
  }

  private Map<String, String> userResultSetToMap(ResultSet rs) throws SQLException {
    String username = rs.getString("USERNAME");
    String password = rs.getString("PASSWORD");
    String email = rs.getString("EMAIL");
    String userId = rs.getString("USER_ID");

    Map<String, String> result = new HashMap<>();
    result.put("USERNAME", username);
    result.put("PASSWORD", password);
    result.put("EMAIL", email);
    result.put("USER_ID", userId);
    System.out.println("Found User: " + username);
    return result;
  }
}

/**
 * This code can convert ResultSet to List<Map>
 */
/*
int columnCount = rs.getMetaData().getColumnCount();
            for(int i = 0; i<columnCount; i++) {
        String colName = rs.getMetaData().getColumnName(i);
        String value = rs.getString(colName);
        result.put(colname, value);
        }
        */
