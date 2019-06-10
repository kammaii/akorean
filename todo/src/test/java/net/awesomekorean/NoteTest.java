package net.awesomekorean;

import org.junit.Test;

import java.util.Iterator;

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

    Note note2 = new Note("Take Kids to School.");
    notes.addTodo(note2);

    assertTrue(notes.getTodos().size() ==  2);

    assertEquals(note2, notes.getTodos().get(1));
    assertEquals(note1, notes.getTodos().get(0));


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
    notes.addTodo(note2);

    Note note3 = new Note("Take Kids to School.");
    notes.addTodo(note3);

    assertEquals(4, notes.getTodos().size());

    notes.getTodos().remove(note2);

    // We had 4 notes, removed "Take Kids to School", why isn't this 1 ???
    assertEquals(2, notes.getTodos().size());

  }

  @Test
  public void equalsIsTricky() {

    String one = new String("Hello");
    String two = new String("Hello");
    String three = one;

    System.out.println(one == two);
    assertTrue(one != two);

    System.out.println(one == one);
    assertTrue(one == one);

    System.out.println(one == three);
    assertTrue(one == three);

    assertTrue(one.equals(two));


    Note note1 = new Note("This is a note");
    Note note2 = new Note("This is a note");

    assertTrue(note1.equals(note2));

    //assertEquals(one, two);
  }

  @Test
  public void iteratorFun() {

    AwesomeArrayList list1 = new AwesomeArrayList();
    assertEquals(0, list1.size());

    Note note1 = new Note("Buy food from store.");
    list1.add(note1);

    assertEquals(1, list1.size());

    Note note2 = new Note("Take Kids to School.");
    list1.add(note2);

    assertEquals(2, list1.size());

    /*
     * Have to implement "ListIterator" for this
    Iterator i = list1.listIterator();
    while(i.hasNext()) {
      assertNotNull(i.next());
    }*/

    for(int i = 0; i<list1.size(); i++) {
      list1.get(i);
    }

    // This uses "iterator"
    for(Object n : list1) {
      System.out.println(n.toString());
      assertNotNull(n);
    }

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
    list1.add(note2);

    assertEquals(3, list1.size());

    AwesomeArrayList list2 = new AwesomeArrayList();
    assertEquals(0, list2.size());

    Note note3 = new Note("Study Korean");
    list2.add(note3);

    assertEquals(1, list2.size());

    list2.addAll(list1);

    assertEquals(4, list2.size());

  }
}
