package akorean.db;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.junit.Assert.*;

public class DatabaseManagerTest {

  private String serverAddress = "3.1.131.127";
  private String username = "akorean";
  private String password = "gabman84";
  private String databaseName = "akorean";

  @Test
  public void recreateDb() throws IOException, SQLException {
    DatabaseManager databaseManager = new DatabaseManager(serverAddress, username, password, databaseName);
    executeSqlFile(databaseManager, "create_db.sql");
    executeSqlFile(databaseManager, "seed_data.sql");
  }
/*
  @Test
  public void getUsersTest() {
    DatabaseManager databaseManager = new DatabaseManager(serverAddress, username, password, databaseName);
    AkoreanDAO akoreanDAO = new AkoreanDAO(databaseManager);
    List<Map<String, String>> users = akoreanDAO.getUsers();
    assertNotNull(users);
    assertTrue(users.size() > 0);
  }

  @Test
  public void insertUser() {
    DatabaseManager databaseManager = new DatabaseManager(serverAddress, username, password, databaseName);
    AkoreanDAO akoreanDAO = new AkoreanDAO(databaseManager);
    Map<String, String > newUser = new HashMap<>();
    newUser.put("USERNAME", "dave");
    newUser.put("EMAIL", "dave@gmail.com");

    Map<String, String> user = akoreanDAO.insertUser(newUser);
    assertNotNull(user);
    String userId = user.get("USER_ID");
    assertNotNull(userId);
    System.out.println(userId);
  }
*/
  private void executeSqlFile(DatabaseManager databaseManager, String fileName) throws IOException, SQLException {

    // Read SQL File
    File file = new File("src/test/resources/" + fileName);

    assertNotNull(file);
    assertTrue(file.exists());
    System.out.println(file.getCanonicalPath());

    BufferedReader in = new BufferedReader(new FileReader(file));

    Scanner s = new Scanner(in);
    s.useDelimiter("(;(\r)?\n)|(--\n)");

    while (s.hasNext()) {
      String line = s.next();
      if (line.trim().length() > 0) {
        System.out.println();
        executeSql(databaseManager, line);
      }
    }
  }

  private void executeSql(DatabaseManager databaseManager, String sql) throws SQLException {

    databaseManager.execute((DatabaseCall<Void>) connection -> {

      System.out.println("Attempting to run sql");
      System.out.println(sql);

      Statement statement = connection.createStatement();
      Boolean results1 = statement.execute(sql);

      System.out.println(results1);

      statement.close();
      return null;
    });
  }

}
