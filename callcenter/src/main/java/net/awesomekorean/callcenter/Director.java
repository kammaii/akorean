package net.awesomekorean.callcenter;

public class Director implements Employee, Person<String> {

  String name ;

  public Director(String name) {
    this.name = name;
  }

  @Override
  public String getEmployeeType() {
    return "Director";
  }

  @Override
  public String getName() {
    return null;
  }
}
