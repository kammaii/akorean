package akorean.db;

import akorean.webservice.User;

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


  // 모든 유저 불러오기
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

          while(rs.next()) {  // 결과를 순차적으로 보내기 위해 ResultSet 을 사용함. 결과가 아주 많으면 다운될 수 있으니까.
            Map<String, String> result = resultSetToMap(rs);
            results.add(result);
          }

          statement.close();
          return results;
        }
      });
    } catch (SQLException e) {
      System.out.println("ERROR: Unable to get User because of SQL Exception");
      System.out.println(e);
      return null;
    }
  }

  // 아이디로 유저 불러오기
  public List<Map<String, String>> getUserById(String userId) {

    try {
      return databaseManager.execute(new DatabaseCall<List<Map<String, String>>>() {

        String sql = "SELECT * FROM USERS WHERE ID = "+ userId;

        @Override
        public List<Map<String, String>> withConnection(Connection connection) throws SQLException {

          System.out.println("Attempting find user with user Id = '" + userId + "'");
          System.out.println(sql);

          Statement statement = connection.createStatement();
          ResultSet rs = statement.executeQuery(sql);
          List<Map<String, String>> results = new ArrayList<>(1);

          while(rs.next()) {
            Map<String, String> result = resultSetToMap(rs);
            results.add(result);
          }

          statement.close();
          if(results.size() <= 0) {
            System.out.println("Unable to find user with user Id= '" + userId + "'");
            return null;
          }
          if(results.size() > 1) {
            System.out.println("WARNING!!! Found multiple users with user Id= '" + userId + "'");
          }

          return results;
        }
      });
    } catch (SQLException e) {
      System.out.println("ERROR: Unable to get users with user Id because of SQL Exception");
      System.out.println(e);
      return null;
    }
  }

  // 이름으로 유저 불러오기
  public List<Map<String, String>> getUserByName(String userName) {

    try {
      return databaseManager.execute(new DatabaseCall<List<Map<String, String>>>() {

        String sql = "SELECT * FROM USERS WHERE NAME='" + userName + "'";

        @Override
        public List<Map<String, String>> withConnection(Connection connection) throws SQLException {

          System.out.println("Attempting find user with user name = '" + userName + "'");
          System.out.println(sql);

          Statement statement = connection.createStatement();
          ResultSet rs = statement.executeQuery(sql);
          List<Map<String, String>> results = new ArrayList<>(1); // List 의 capacity 를 1 로 제한

          while(rs.next()) {
            Map<String, String> result = resultSetToMap(rs);
            results.add(result);
          }

          statement.close();
          if(results.size() <= 0) {
            System.out.println("Unable to find user with username = '" + userName + "'");
            return null;
          }
          if(results.size() > 1) {
            System.out.println("WARNING!!! Found multiple users with username = '" + userName + "'");
          }
          return results;
        }
      });
    } catch (SQLException e) {
      System.out.println("ERROR: Unable to get User because of SQL Exception");
      System.out.println(e);
      return null;
    }
  }

  // 이메일로 유저 불러오기
  public List<Map<String, String>> getUserByEmail(String userEmail) {

    try {
      return databaseManager.execute(new DatabaseCall<List<Map<String, String>>>() {

        String sql = "SELECT * FROM USERS WHERE EMAIL='" + userEmail + "'";

        @Override
        public List<Map<String, String>> withConnection(Connection connection) throws SQLException {

          System.out.println("Attempting find user with user name = '" + userEmail + "'");
          System.out.println(sql);

          Statement statement = connection.createStatement();
          ResultSet rs = statement.executeQuery(sql);
          List<Map<String, String>> results = new ArrayList<>(1); // List 의 capacity 를 1 로 제한

          while(rs.next()) {
            Map<String, String> result = resultSetToMap(rs);
            results.add(result);
          }

          statement.close();
          if(results.size() <= 0) {
            System.out.println("Unable to find user with user email = '" + userEmail + "'");
            return null;
          }
          if(results.size() > 1) {
            System.out.println("WARNING!!! Found multiple users with user email = '" + userEmail + "'");
          }
          return results;
        }
      });
    } catch (SQLException e) {
      System.out.println("ERROR: Unable to get User because of SQL Exception");
      System.out.println(e);
      return null;
    }
  }


  // 유저 등록하기
  public Map<String, String> insertUser(Map<String, String> newUser) {

    try {
      return databaseManager.execute(new DatabaseCall<Map<String, String>>() {

        String userName = newUser.get("NAME");
        String userEmail = newUser.get("EMAIL");
        String userPassword = newUser.get("PASSWORD");
        String dateSignUp = newUser.get("DATE_SIGNUP");
        String dateSignIn = newUser.get("DATE_SIGNIN");


        String sql = "INSERT INTO USERS (NAME, EMAIL, PASSWORD, DATE_SIGNUP, DATE_SIGNIN) " +
                "values ('" + userName + "', '" + userEmail + "', '" + userPassword + "', " + dateSignUp + ", " + dateSignIn + ")";

        @Override
        public Map<String, String> withConnection(Connection connection) throws SQLException {

          System.out.println("Attempting to insert new user");
          System.out.println(sql);

          Statement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

          int rows = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
          ResultSet rs = statement.getGeneratedKeys();
          rs.next();
          Integer userId = rs.getInt(1);

          newUser.put("ID", userId.toString());
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


  public User insertUser(User newUser) {

    try {
      return databaseManager.execute(new DatabaseCall<User>() {

        String userName = newUser.getName();
        String userEmail = newUser.getEmail();
        String userPassword = newUser.getPassword();
        String dateSignUp = newUser.getDateSignUp();
        String dateSignIn = newUser.getDateSignIn();


        String sql = "INSERT INTO USERS (NAME, EMAIL, PASSWORD, DATE_SIGNUP, DATE_SIGNIN) " +
                "values ('" + userName + "', '" + userEmail + "', '" + userPassword + "', " + dateSignUp + ", " + dateSignIn + ")";

        @Override
        public User withConnection(Connection connection) throws SQLException {

          System.out.println("Attempting to insert new user");
          System.out.println(sql);

          Statement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

          int rows = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
          ResultSet rs = statement.getGeneratedKeys();
          rs.next();
          //Integer userId = rs.getInt(1);

          //newUser.put("ID", userId.toString());
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


  // ResultSet 을 Map 으로 변경하기
  private Map<String, String> resultSetToMap(ResultSet rs) throws SQLException {
    String userId = rs.getString("ID");
    String userName = rs.getString("NAME");
    String userPassword = rs.getString("PASSWORD");
    String userEmail = rs.getString("EMAIL");
    String userSignUp = rs.getString("DATE_SIGNUP");
    String userSignIn = rs.getString("DATE_SIGNIN");

    Map<String, String> result = new HashMap<>();
    result.put("ID", userId);
    result.put("NAME", userName);
    result.put("PASSWORD", userPassword);
    result.put("EMAIL", userEmail);
    result.put("DATE_SIGNUP", userSignUp);
    result.put("DATE_SIGNIN", userSignIn);

    System.out.println("Found User: " + userName);
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
