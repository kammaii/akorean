package net.awesomekorean.callcenter.db;

import net.awesomekorean.callcenter.db.DatabaseCall;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDatabaseCall implements DatabaseCall<List<Map>> {

  @Override
  public List<Map> withConnection(Connection connection) throws SQLException {

    String query = "SELECT * FROM callcenter.employee";
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(query);

    // [{ "EmployeeName" : "Dave",
    // "EmployeeType": "Manager" },
    // { "EmployeeName": "Danny",
    // "EmployeeType": "Employee"}]

    List results = new ArrayList<Map<String, String>>();

    while(resultSet.next()) {
      Map result = new HashMap<String, String>();
      String employeeName = resultSet.getString("EMPLOYEE_NAME");
      result.put("EmployeeName", employeeName);
      String employeeType = resultSet.getString("EMPLOYEE_TYPE");
      result.put("EmployeeType", employeeType);

      results.add(result);
    }

    resultSet.close();
    statement.close();

    return results;

  }
}
