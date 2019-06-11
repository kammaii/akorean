package net.awesomekorean;

import java.util.Iterator;

public class AwesomeArrayListIterator implements Iterator {

  private int index = 0;
  private AwesomeArrayList list;
  public AwesomeArrayListIterator(AwesomeArrayList list) {
    this.list = list;
  }

  @Override
  public boolean hasNext() {
    if(list == null) {
      return false;
    }

    if(index < (list.size()-1)) {
      return true;
    } else {
      return false;
    }

  }

  @Override
  public Object next() {

    if(list == null) {
      return null;
    }

    index++;
    return list.get(index);

  }
}
