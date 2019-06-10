package net.awesomekorean;

import java.util.*;

public class AwesomeArrayList implements List {

  private int arraySize;
  private Object[] notes;

  public AwesomeArrayList() {
    arraySize = 0;
    notes = new Note[arraySize];
  }

  public int size() {
    return arraySize;
  }

  public boolean isEmpty() {
    return (notes == null || notes.length <= 0);
  }

  @Override
  public boolean contains(Object o) {
    return false;
  }

  @Override
  public Iterator iterator() {

    return new Iterator() {

      private int cursor = 0;

      @Override
      public boolean hasNext() {
        System.out.println("We're in hasNext");
        return cursor < notes.length;
      }

      @Override
      public Object next() {
        Object n = notes[cursor];
        cursor++;
        return n;
      }

    };
  }

  @Override
  public Object[] toArray() {
    return new Object[0];
  }

  @Override
  public boolean add(Object o) {
    Object[] oldNotes = notes;
    arraySize = arraySize + 1;
    notes = new Note[arraySize];

    int i = 0;
    for(Object note : oldNotes){
      notes[i] = note;
      i = i+1;
    }

    notes[i] = o;
    return true;
  }

  @Override
  public boolean remove(Object o) {
    int found = 0;
    //arraySize--;
    //Object[] newTodos = new Note[arraySize];

    for(int i=0; i<notes.length; i++) {
      Object next = notes[i];
      if(o == next) {
        found++;
      }
    }

    Object[] newTodos = null;
    if(found > 0) {
      arraySize = arraySize - found;
      newTodos = new Note[arraySize];
      int j=0;
      for(int i=0; i<notes.length; i++) {
        Object next = notes[i];
        if(o != next) {
          newTodos[j] = next;
          j++;
        }
      }
      notes = newTodos;
    }

    return found > 0;
  }

  @Override
  public boolean addAll(Collection c) {

    if(c == null || c.size() <= 0) {
      return false;
    }

    arraySize = arraySize + c.size();
    Object[] newTodos = new Note[arraySize];

    for(int i=0; i< notes.length; i++ ) {
      newTodos[i] = notes[i];
    }

    int j=notes.length;
    for(Object o : c) {
      newTodos[j] = o;
      j++;
    }

    return true;
  }

  @Override
  public boolean addAll(int index, Collection c) {
    return false;
  }

  @Override
  public void clear() {

  }

  @Override
  public Object get(int index) {
    if(notes != null && index >= 0 && index < notes.length) {
      return notes[index];
    } else return null;
  }

  @Override
  public Object set(int index, Object element) {
    return null;
  }

  @Override
  public void add(int index, Object element) {

  }

  @Override
  public Object remove(int index) {
    arraySize--;

    Object[] newTodos = new Note[arraySize];
    Object removed = null;

    int j=0;
    for(int i=0; i<notes.length; i++) {
      if(index != i) {
        newTodos[j] = notes[i];
        j++;
      } else {
        removed = notes[i];
      }
    }

    notes = newTodos;
    return removed;

  }

  @Override
  public int indexOf(Object o) {
    return 0;
  }

  @Override
  public int lastIndexOf(Object o) {
    return 0;
  }

  @Override
  public ListIterator listIterator() {
    return null;
  }

  @Override
  public ListIterator listIterator(int index) {
    return null;
  }

  @Override
  public List subList(int fromIndex, int toIndex) {
    return null;
  }

  @Override
  public boolean retainAll(Collection c) {
    return false;
  }

  @Override
  public boolean removeAll(Collection c) {
    return false;
  }

  @Override
  public boolean containsAll(Collection c) {
    return false;
  }

  @Override
  public Object[] toArray(Object[] a) {
    return new Object[0];
  }


}
