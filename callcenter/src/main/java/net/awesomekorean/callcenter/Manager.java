package net.awesomekorean.callcenter;

public class Manager implements Employee<String> {

  private String name;

  public Manager(String name) {
    this.name = name;
  }

  @Override
  public EmployeeType getEmployeeType() {
    //??EmployeeType employeeType = new EmployeeType();
    return EmployeeType.MANAGER;
  }

  @Override
  public String getName() {
    return name;
  }


}
