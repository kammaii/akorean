package net.awesomekorean;

public class Notes {

  private Note[] todos;

  public Notes() {
    todos = new Note[0];
  }

  public Note[] getTodos() {
    return this.todos;
  }

  public void addTodo(String newTodo) {

    // Add newTodo to array of todos
    // How many slots are available in todos?
    todos = new Note[1];
    todos[0] = new Note(newTodo);


  }
}
