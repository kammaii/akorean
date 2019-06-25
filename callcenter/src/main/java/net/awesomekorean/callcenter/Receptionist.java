package net.awesomekorean.callcenter;

public class Receptionist implements Employee, Person<PersonName> {

  PersonName name;

  public Receptionist(PersonName name ) {
    this.name = name;
  }

  @Override
  public String getEmployeeType() {
    return EmployeeType.RECEPTIONIST;
  }

  @Override
  public PersonName getName() {
    return name;
  }

}
