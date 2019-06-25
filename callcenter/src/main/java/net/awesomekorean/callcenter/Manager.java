package net.awesomekorean.callcenter;

public class Manager implements Employee, Person<String> {

  private String name;

  public Manager(String name) {
    this.name = name;
  }

  @Override
  public String getEmployeeType() {
    //??EmployeeType employeeType = new EmployeeType();
    return EmployeeType.MANAGER;
  }

  @Override
  public String getName() {
    return name;
  }


}
