package net.awesomekorean.callcenter;

import net.awesomekorean.callcenter.db.DatabaseCall;
import net.awesomekorean.callcenter.db.DatabaseManager;
import net.awesomekorean.callcenter.db.EmployeeDatabaseCall;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.List;

import static org.junit.Assert.*;

public class DbTest {

  private String username = "dparoulek";
  private String password = "";
  private String databaseName = "akorean";

  @Test
  public void sanityTest() {
    assertTrue(true);
  }

  public Integer createNewEmployee(Integer id, String name, Integer type, Connection conn) {
    String insert = "INSERT INTO dave.EMPLOYEE VALUES ('" + name + "', " + id + ", " + type + ");";

    Statement insertStatement = null;
    try {
      insertStatement = conn.createStatement();
      Integer resultCount = insertStatement.executeUpdate(insert);
      return resultCount;
    } catch (SQLException e) {
      return -1;
    }

  }

  @Test
  public void connectionTest() throws SQLException {

    DatabaseManager databaseManager = new DatabaseManager(username, password, databaseName);

    EmployeeDatabaseCall call = new EmployeeDatabaseCall();

    List<String> employeeNames = databaseManager.execute(call);

    assertTrue(employeeNames.size() == 2);

  }

  public String readTestResourceFile(String fileName) throws IOException, URISyntaxException {

    // Read SQL File
    File file = new File("src/test/resources/" + fileName);

    assertNotNull(file);
    assertTrue(file.exists());
    System.out.println(file.getCanonicalPath());

    BufferedReader in = new BufferedReader(new FileReader(file));

    String result = null;
    StringBuffer sb = new StringBuffer();
    while ((result = in.readLine()) != null) {
      sb.append(result);
      sb.append("\n");
    }

    in.close();

    return sb.toString();
  }

  @Test
  public void testReadResourceFile() {

    String fileContents = null;
    try {
      fileContents = readTestResourceFile("create_db.sql");
    } catch (IOException e) {
      e.printStackTrace();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    System.out.println(fileContents);
    assertNotNull(fileContents);

  }

  public Integer executeSqlFile(String sqlFileName) {
    try {

      String sql = readTestResourceFile(sqlFileName);

      DatabaseManager databaseManager = new DatabaseManager(username, password, databaseName);

      Integer results = databaseManager.execute(new DatabaseCall<Integer>() {
        @Override
        public Integer withConnection(Connection connection) throws SQLException {
          Statement statement = connection.createStatement();
          int results = statement.executeUpdate(sql);

          System.out.println(results);

          statement.close();
          return results;
        }
      });

      return results;

    } catch (IOException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Test
  public void recreateDatabase() {

    // recreate sequences and tables
    executeSqlFile("create_db.sql");
    executeSqlFile("data.sql");

  }

}
