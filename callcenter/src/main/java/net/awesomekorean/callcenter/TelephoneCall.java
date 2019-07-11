package net.awesomekorean.callcenter;

public class TelephoneCall {

  String callerName;
  Employee receiver;

  public TelephoneCall(String callerName, Employee receiver) {
    this.callerName = callerName;
    this.receiver = receiver;
  }

  public String getCallerName() {
    return callerName;
  }

  public Employee getReceiver() {
    return receiver;
  }
}
