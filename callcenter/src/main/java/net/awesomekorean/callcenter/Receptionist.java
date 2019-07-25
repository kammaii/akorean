package net.awesomekorean.callcenter;

public class Receptionist implements Employee<PersonName> {

  private PersonName name;

  public Receptionist(PersonName name ) {
    this.name = name;
  }

  @Override
  public EmployeeType getEmployeeType() {
    return EmployeeType.RECEPTIONIST;
  }

  @Override
  public PersonName getName() {
    return name;
  }

}
