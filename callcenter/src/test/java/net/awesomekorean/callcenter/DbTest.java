package net.awesomekorean.callcenter;

import org.junit.Test;

import java.sql.*;
import java.util.Properties;

import static org.junit.Assert.*;

public class DbTest {

  @Test
  public void sanityTest() {
    assertTrue(true);
  }

  /*
  postgres=# create database mydb;
  postgres=# create user myuser with encrypted password 'mypass';
  postgres=# grant all privileges on database mydb to myuser;
  */


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
  public void connectionTest() {

    String url = "jdbc:postgresql://localhost/mydb";

    Properties props = new Properties();
    props.setProperty("user", "CHANGEME");
    props.setProperty("password", "");
    //props.setProperty("ssl", "true");


    try {
      Connection conn = DriverManager.getConnection(url, props);
      assertNotNull(conn);


      String query = "SELECT * FROM dave.employee";

      Statement statement = conn.createStatement();
      ResultSet resultSet = statement.executeQuery(query);

      while(resultSet.next()) {
        String employeeName = resultSet.getString("EMPLOYEE_NAME");
        System.out.println(employeeName);
      }


      Integer result = createNewEmployee(10, "Kathy", 0, conn);
      assertEquals(new Integer(1), result);

      statement.close();
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
