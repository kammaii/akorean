package net.awesomekorean;

public class Note {

  private String todo;
  private Boolean isComplete;

  public Note(String todo) {
    this.todo = todo;
  }

  public void setTodo(String todo) {
    this.todo = todo;
  }

  public String getTodo() {
    return todo;
  }

  public void setComplete(Boolean isComplete) {
    this.isComplete = isComplete;
  }

  public Boolean getComplete() {
    if(isComplete == null ) {
      return false;
    } else
    return isComplete;
  }

  @Override
  public String toString() {
    return todo;
  }

  @Override
  public boolean equals(Object o) {
    if(o == null) {
      return false;
    }

    if(o instanceof Note){
      Note otherNote = (Note)o;
      return (otherNote.getTodo().equals(this.getTodo()));
    } else {
      return false;
    }
  }

}
