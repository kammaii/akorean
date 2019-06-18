package net.awesomekorean.callcenter;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class CallCenterTest {

  @Test
  public void simpleTest() {
    assertTrue(true);
  }

  @Test
  public void processIncomingCalls() {

    CallCenter callCenter = new CallCenter();
    callCenter.readCalls("./src/test/resources/calls.txt");

  }
}
