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
  public void testRemoveNotesByIndex() {

    Notes notes = new Notes();
    assertEquals(0, notes.getTodos().size());

    Note note1 = new Note("Buy food from store.");
    notes.addTodo(note1);

    assertEquals(1, notes.getTodos().size());

    Note note2 = new Note("Take Kids to School.");
    notes.addTodo(note2);

    assertEquals(2, notes.getTodos().size());

    notes.removeTodo(1);

    assertEquals(1, notes.getTodos().size());

  }

  @Test
  public void testRemoveNotesByObject() {

    Notes notes = new Notes();
    assertEquals(0, notes.getTodos().size());

    Note note1 = new Note("Buy food from store.");
    notes.addTodo(note1);

    assertEquals(1, notes.getTodos().size());

    Note note2 = new Note("Take Kids to School.");
    notes.addTodo(note2);

    assertEquals(2, notes.getTodos().size());

    notes.getTodos().remove(note2);

    // Danny, try and implement the remove(Object o) method to fix this
    assertEquals(1, notes.getTodos().size());

  }

  @Test
  public void testAddAll() {

    AwesomeArrayList list1 = new AwesomeArrayList();
    assertEquals(0, list1.size());

    Note note1 = new Note("Buy food from store.");
    list1.add(note1);

    assertEquals(1, list1.size());

    Note note2 = new Note("Take Kids to School.");
    list1.add(note2);

    assertEquals(2, list1.size());

    AwesomeArrayList list2 = new AwesomeArrayList();
    assertEquals(0, list2.size());

    Note note3 = new Note("Study Korean");
    list2.add(note3);

    assertEquals(1, list2.size());

    list2.addAll(list1);

    // Danny, try to implement addAll(Collection c) to fix this
    assertEquals(3, list2.size());

  }




}
