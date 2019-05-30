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

  public void removeTodo(Note note){

    // Danny, try to implement AwesomeArrayList.remove()
    // Here's a hint: try to use similar solution to the code below inside AwesomeArrayList.remove()


    /*
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
    */

  }
}
