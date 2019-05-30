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

    assertEquals("Take Kids to School.", notes.getTodos()[1].getTodo());
    assertEquals("Buy food from store.", notes.getTodos()[0].getTodo());

  }


  @Test
  public void testRemoveNotes() {

    Notes notes = new Notes();
    assertTrue(notes.getTodos().length ==  0);

    notes.addTodo("Buy food from store.");

    assertTrue(notes.getTodos().length ==  1);

    notes.addTodo("Take Kids to School.");

    assertTrue(notes.getTodos().length ==  2);

    assertEquals("Buy food from store.", notes.getTodos()[0].getTodo());
    assertEquals("Take Kids to School.", notes.getTodos()[1].getTodo());

    notes.removeTodo(0);

    assertTrue(notes.getTodos().length == 1);

    assertEquals("Take Kids to School.", notes.getTodos()[0].getTodo());

  }

}
