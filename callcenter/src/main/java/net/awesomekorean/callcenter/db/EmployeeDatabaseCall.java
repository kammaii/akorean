package net.awesomekorean.callcenter.db;

import net.awesomekorean.callcenter.db.DatabaseCall;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDatabaseCall implements DatabaseCall<List<String>> {

  @Override
  public List<String> withConnection(Connection connection) throws SQLException {

    String query = "SELECT * FROM callcenter.employee";
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(query);

    List employeeNames = new ArrayList<String>();

    while(resultSet.next()) {
      String employeeName = resultSet.getString("EMPLOYEE_NAME");
      System.out.println(employeeName);
      employeeNames.add(employeeName);
    }

    return employeeNames;

  }
}
