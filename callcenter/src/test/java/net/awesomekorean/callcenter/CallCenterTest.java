package net.awesomekorean.callcenter;

import org.junit.Test;

import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_16;
import static org.junit.Assert.*;

public class CallCenterTest {

  @Test
  public void simpleTest() {
    assertTrue(true);
  }

  @Test
  public void processIncomingCalls() {

    CallCenter callCenter = new CallCenter();

    // process calls
    callCenter.processCalls("./src/test/resources/calls.txt");

  }

  @Test
  public void createPeople() {


    Manager dave = new Manager("Dave");
    assertEquals("Dave", dave.getName());

    Manager danny = new Manager("Danny");
    String name = danny.getName();

    Receptionist kathy = new Receptionist(new PersonName("Kathy", "Paroulek"));
    assertEquals("Kathy Paroulek", kathy.getName().toString());

  }

  @Test
  public void koreanEquals() {
    String one = "경찰";
    String two = "경찰";
    assertTrue(one.equals(two));

    String wordQuiz1Answer = "경찰";
    String word = "경찰";

  }

  @Test
  public void employeeTypeTest() {
    assertEquals(EmployeeType.MANAGER, EmployeeType.MANAGER);
    String manager = "Manager";
    assertEquals(manager, EmployeeType.MANAGER.toString());
  }
}
