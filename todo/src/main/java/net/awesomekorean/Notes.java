package net.awesomekorean;

public class Notes {

  private Note[] todos;
  private int noteCount = 0;

  public Notes() {
    todos = new Note[0];
  }

  public Note[] getTodos() {
    return this.todos;
  }

  public void addTodo(String newTodo) {

    Note[] oldTodos = todos;

    noteCount++;
    todos = new Note[noteCount];

    // copy old todos into new todos
    for(int i=0; i<oldTodos.length; i++) {
      todos[i] = oldTodos[i];
    }

    todos[noteCount-1] = new Note(newTodo);

  }

  public void removeTodo(int arrayIndex) {

    // create array that is one index less than todos
    noteCount--;
    Note[] newTodos = new Note[noteCount];

    // todos should contain all of the original todos except for arrayIndex
    int j = 0;
    for(int i=0; i<todos.length; i++) {
      if(arrayIndex != i) {
        newTodos[j] = todos[i];
        j++;
      }
    }

    todos = newTodos;

  }
}
