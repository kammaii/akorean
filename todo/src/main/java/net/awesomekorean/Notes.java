package net.awesomekorean;

public class Notes {

  private AwesomeArrayList todos;
  private int noteCount = 0;

  public Notes() {
    todos = new AwesomeArrayList();
  }

  public AwesomeArrayList getTodos() {
    return this.todos;
  }

  public void addTodo(Note  newTodo) {
    todos.add(newTodo);
  }

  public void removeTodo(int i){

    this.todos.remove(i);

  }
}
