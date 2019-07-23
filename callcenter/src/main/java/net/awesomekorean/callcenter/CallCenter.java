package net.awesomekorean.callcenter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CallCenter {

  private List<Employee> employees;
  private List<TelephoneCall> activeCalls;

  public CallCenter(String pathToEmployeesFile) {
    employees = new ArrayList<>();
    readEmployees(pathToEmployeesFile);

    activeCalls = new ArrayList<>();
  }


  public Employee findNextAvailable(String employeeType) {

    List<Employee> availableEmployees = new ArrayList<Employee>();

    // First find all receptionists from list of employees
    List<Employee> employees = new ArrayList<>();
    for(Employee employee : employees) {
      if(employee.getEmployeeType().equals(employeeType)) {
        employees.add(employee);
      }
    }

    // For each employee, see if they are currently in an active call
    for(Employee employee : employees) {
      boolean foundAvailableEmployee = false;

      for (TelephoneCall activeCall : activeCalls) {

        Employee receiver = activeCall.getReceiver();
        if (!receiver.equals(employee)) {
          foundAvailableEmployee = true;
        }
      }

      if (!foundAvailableEmployee) {
        availableEmployees.add(employee);
      }
    }

    if(availableEmployees.size() > 0) {
      return availableEmployees.get(0);
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
        System.out.println("Receptionist named " + ((Receptionist)receptionist).getName() + " received call from "+ callerName);
      } else {
        // we need to find next manager
        Employee manager = findNextAvailable(EmployeeType.MANAGER);
        if(manager != null) {
          activeCalls.add(new TelephoneCall(callerName, manager));
          System.out.println("Manager named " + ((Manager)manager).getName() + " received call from "+ callerName);
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
      }

      System.out.println("Receptionist named " + receptionist.getName() + " received call from "+ callerName);

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
