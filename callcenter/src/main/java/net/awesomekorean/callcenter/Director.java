package net.awesomekorean.callcenter;

public class Director implements Employee<String> {

  String name ;

  public Director(String name) {
    this.name = name;
  }

  @Override
  public EmployeeType getEmployeeType() {
    return EmployeeType.DIRECTOR;
  }

  @Override
  public String getName() {
    return name;
  }
}
