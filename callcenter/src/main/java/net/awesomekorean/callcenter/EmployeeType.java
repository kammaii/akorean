package net.awesomekorean.callcenter;

public enum EmployeeType {

  MANAGER, DIRECTOR, RECEPTIONIST;

  // Challenge: convert this to a Enum (Enumerated)

  // immutable == final
  // mutable == you can change it

  // this is a "constant" value
  //final public static String MANAGER = "Manager";
  //final public static String DIRECTOR = "Director";
  //final public static String RECEPTIONIST = "Receptionist";


  @Override
  public String toString() {
    if(MANAGER == this) {
      return "Manager";
    } else if(DIRECTOR == this) {
      return "Director";
    } else {
      return "Receptionist";
    }
  }
}
