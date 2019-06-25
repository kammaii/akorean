package net.awesomekorean.callcenter;

public class PersonName {

  String firstName;
  String lastName;

  public PersonName(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  @Override
  public String toString() {
    return firstName;
  }

}
