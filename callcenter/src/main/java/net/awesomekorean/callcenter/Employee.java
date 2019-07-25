package net.awesomekorean.callcenter;

public interface Employee<T> {

  public EmployeeType getEmployeeType();

  public T getName();

}
