package net.awesomekorean.callcenter;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CallCenter {

  private List<Employee> employees;
  private List<TelephoneCall> activeCalls;

  public CallCenter(String pathToEmployeesFile) {
    employees = new ArrayList<>();
    readEmployees(pathToEmployeesFile);

    activeCalls = new ArrayList<>();
  }


  public Employee findNextAvailable(EmployeeType employeeType) {

    List<Employee> result = new ArrayList<>();

    // First find all receptionists from list of employees
    List<Employee> candidates = new ArrayList<>();
    for(Employee employee : employees) {
      if(employee.getEmployeeType().equals(employeeType)) {
        candidates.add(employee);
      }
    }

    // For each candidate, see if they are currently in an active call
    for(Employee employee : candidates) {
      boolean isAvailable = true;

      for (TelephoneCall activeCall : activeCalls) {
        Employee receiver = activeCall.getReceiver();
        if (receiver.equals(employee)) {
          isAvailable = false;
        }
      }

      if (isAvailable) {
        result.add(employee);
      }

    }

    if(result.size() > 0) {
      return result.get(0);
    } else {
      return null;
    }
  }

  public void dispatchCall(String callerName) {

    System.out.println("New call from:  " + callerName);

    if(activeCalls.size() > 0) {

      Employee receptionist = findNextAvailable(EmployeeType.RECEPTIONIST);
      if(receptionist != null) {
        activeCalls.add(new TelephoneCall(callerName, receptionist));
        System.out.println("Receptionist named " + receptionist.getName() + " received call from "+ callerName);
      } else {
        // we need to find next manager
        Employee manager = findNextAvailable(EmployeeType.MANAGER);
        if(manager != null) {
          activeCalls.add(new TelephoneCall(callerName, manager));
          System.out.println("Manager named " + manager.getName() + " received call from "+ callerName);
        } else {
          // we need to find next manager
          Employee director = findNextAvailable(EmployeeType.DIRECTOR);
          if(director != null) {
            activeCalls.add(new TelephoneCall(callerName, director));
            System.out.println("Director named " + director.getName() + " received call from "+ callerName);
          }
        }
      }



    } else {
      // this is the first call, so just dispatch to receptionist
      // find receptionist inside employees
      Receptionist receptionist = null;

      // Option 1 (Explicit Type Cast)
      /*for(Object object : employees) {
        Employee employee = (Employee)object;
        if(employee.getEmployeeType().equals(EmployeeType.RECEPTIONIST)) {
          receptionist = employee;
        }
      }*/

      // Option 2 - Use Generics
      for(Employee employee : employees) {
        if(employee.getEmployeeType().equals(EmployeeType.RECEPTIONIST) && receptionist == null) {

          // Hi Danny, here's another good example of type casting. We know that "employees" is a list of Employee
          // objects. And since this specific Employee is type "RECEPTIONIST", then we know this is a RECEPTIONIST,
          // so we can type cast it.
          //
          // Be very, very careful when using type casting! It is usually a sign that
          // your program can be designed better! This case it's ok, because this is just a learning exercise!

          receptionist = (Receptionist) employee;
        }
      }

      if(receptionist != null) {
        TelephoneCall telephoneCall = new TelephoneCall(callerName, receptionist);
        activeCalls.add(telephoneCall);
        System.out.println("Receptionist named " + receptionist.getName() + " received call from "+ callerName);
      } else {
        throw new IllegalStateException("Can't find any receptionists??!!!");
      }

    }

  }

  public void processCalls(String pathToFile) {

    if(pathToFile == null) {
      System.out.println("Oops, null file");
      return;
    }

    File callFile = new File(pathToFile);

    System.out.println("========================== ");
    System.out.println("Attempting to Process Calls from file: " + pathToFile);
    System.out.println();

    try {
      BufferedReader fileReader = new BufferedReader(new FileReader(callFile));
      String line = fileReader.readLine();
      while(line != null) {
        //System.out.println(line);

        // handle this caller name
        String callerName = line;
        dispatchCall(callerName);

        // get the next caller name
        line = fileReader.readLine();

      }

    } catch (FileNotFoundException e) {
      System.out.println("Oops, can't find file: " + pathToFile);
    } catch (IOException e) {
      System.out.println("Oops, io exception when trying to read file: "+ pathToFile);
    }

    System.out.println();
    System.out.println("Finished Processing calls");
    System.out.println("========================== ");

  }

  private void readEmployees(String pathToFile) {

    String url = "jdbc:postgresql://localhost/akorean";
    Properties props = new Properties();
    props.setProperty("user", "CHANGEME");
    props.setProperty("password", "");


    try {
      Connection conn = DriverManager.getConnection(url, props);
      String query = "SELECT EMPLOYEE_NAME, EMPLOYEE_TYPE FROM callcenter.employee";

      Statement statement = conn.createStatement();
      ResultSet resultSet = statement.executeQuery(query);

      while(resultSet.next()) {

        String employeeName = resultSet.getString("EMPLOYEE_NAME");
        System.out.println(employeeName);

        String employeeType = resultSet.getString("EMPLOYEE_TYPE");
        System.out.println(employeeType);

        Employee employee = null;
        if (employeeType.toUpperCase().equals(EmployeeType.RECEPTIONIST.toString().toUpperCase())) {
          PersonName personName = new PersonName(employeeName, "");
          employee = new Receptionist(personName);
        } else if (employeeType.toUpperCase().equals(EmployeeType.MANAGER.toString().toUpperCase())) {
          employee = new Manager(employeeName);
        } else if (employeeType.toUpperCase().equals(EmployeeType.DIRECTOR.toString().toUpperCase())) {
          employee = new Director(employeeName);
        } else {
          throw new IllegalStateException("Found employee type that is not defined");
        }

        employees.add(employee);

      }

      resultSet.close();
      statement.close();
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  private void readEmployeesFromCSVFile(String pathToFile) {

    if(pathToFile == null) {
      System.out.println("Oops, null file");
      return;
    }

    File callFile = new File(pathToFile);
    try {
      BufferedReader fileReader = new BufferedReader(new FileReader(callFile));
      String line = fileReader.readLine();
      while(line != null) {
        //System.out.println(line);
        line = fileReader.readLine();

        if(line != null) {
          String name = line.substring(0, line.indexOf(","));
          String employeeType = line.substring(line.indexOf(",") + 1);

          Employee employee = null;
          if (employeeType.equals(EmployeeType.RECEPTIONIST.toString())) {
            PersonName personName = new PersonName(name, "");
            employee = new Receptionist(personName);
          } else if (employeeType.equals(EmployeeType.MANAGER.toString())) {
            employee = new Manager(name);
          } else if (employeeType.equals(EmployeeType.DIRECTOR.toString())) {
            employee = new Director(name);
          }

          employees.add(employee);
        }

      }


    } catch (FileNotFoundException e) {
      System.out.println("Oops, can't find file: " + pathToFile);
    } catch (IOException e) {
      System.out.println("Oops, io exception when trying to read file: "+ pathToFile);
    }

  }


}
