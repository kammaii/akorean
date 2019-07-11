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
    String name = "";
    if(firstName != null && firstName.length() > 0) {
      name += firstName;
    }

    if(lastName != null && lastName.length() > 0) {
      if(name.length() > 0) {
        name += " ";
      }
      name += lastName;
    }

    return name;
  }

}
