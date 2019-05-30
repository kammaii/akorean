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
    assertEquals(0 , notes.getTodos().size());
    assertEquals(true, notes.getTodos().isEmpty());

    Note note1 = new Note("Buy food from store.");
    notes.addTodo(note1);

    assertEquals(1, notes.getTodos().size());

    // Danny - try to implement AwesomeArrayList.isEmpty so this test will pass
    assertEquals(false, notes.getTodos().isEmpty());

    /*
    notes.addTodo("Take Kids to School.");

    assertTrue(notes.getTodos().length ==  2);

    assertEquals("Take Kids to School.", notes.getTodos()[1].getTodo());
    assertEquals("Buy food from store.", notes.getTodos()[0].getTodo());
    */

  }


  @Test
  public void testRemoveNotes() {

    Notes notes = new Notes();
    assertEquals(0, notes.getTodos().size());

    Note note1 = new Note("Buy food from store.");
    notes.addTodo(note1);

    assertEquals(1, notes.getTodos().size());

    Note note2 = new Note("Take Kids to School.");
    notes.addTodo(note2);

    assertEquals(2, notes.getTodos().size());

    notes.removeTodo(note1);

    assertEquals(1, notes.getTodos().size());

  }

}
