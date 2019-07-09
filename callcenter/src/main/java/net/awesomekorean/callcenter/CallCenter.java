package net.awesomekorean.callcenter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CallCenter {

  private List employees;

  public CallCenter() {
    employees = new ArrayList();
  }

  public void readCalls(String pathToFile) {

    if(pathToFile == null) {
      System.out.println("Oops, null file");
      return;
    }

    File callFile = new File(pathToFile);
    try {
      BufferedReader fileReader = new BufferedReader(new FileReader(callFile));
      String line = fileReader.readLine();
      while(line != null) {
        System.out.println(line);
        line = fileReader.readLine();
        // handle call
        // call "dispatchCall(...)"
      }


    } catch (FileNotFoundException e) {
      System.out.println("Oops, can't find file: " + pathToFile);
    } catch (IOException e) {
      System.out.println("Oops, io exception when trying to read file: "+ pathToFile);
    }

  }

  public void readEmployees(String pathToFile) {

    if(pathToFile == null) {
      System.out.println("Oops, null file");
      return;
    }

    File callFile = new File(pathToFile);
    try {
      BufferedReader fileReader = new BufferedReader(new FileReader(callFile));
      String line = fileReader.readLine();
      while(line != null) {
        System.out.println(line);
        line = fileReader.readLine();

        if(line != null) {
          String name = line.substring(0, line.indexOf(","));
          String employeeType = line.substring(line.indexOf(",") + 1);

          Employee employee = null;
          if (employeeType.equals(EmployeeType.RECEPTIONIST)) {
            PersonName personName = new PersonName(name, "");
            employee = new Receptionist(personName);
          } else if (employeeType.equals(EmployeeType.MANAGER)) {
            employee = new Manager(name);
          } else if (employeeType.equals(EmployeeType.DIRECTOR)) {
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
