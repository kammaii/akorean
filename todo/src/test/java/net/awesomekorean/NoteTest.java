package net.awesomekorean;

import org.junit.Test;

import static org.junit.Assert.*;

public class NoteTest {

  @Test
  public void haveSomeNotes() {

    Note buyFood = new Note("Buy food from store.");

    assertNotNull(buyFood);
    assertFalse(buyFood.getComplete());

    Note kidsToSchool = new Note("Take Kids to School.");
    assertFalse(kidsToSchool.getComplete());
    assertNotNull(kidsToSchool.getTodo());

  }

  @Test
  public void testListOfNotes() {

    Notes notes = new Notes();
    assertTrue(notes.getTodos().length ==  0);

    notes.addTodo("Buy food from store.");

    assertTrue(notes.getTodos().length ==  1);

    notes.addTodo("Take Kids to School.");

    assertTrue(notes.getTodos().length ==  2);

  }

}
