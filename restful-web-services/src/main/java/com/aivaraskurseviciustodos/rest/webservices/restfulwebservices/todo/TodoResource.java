package com.aivaraskurseviciustodos.rest.webservices.restfulwebservices.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TodoResource {

  @Autowired private TodoHardcodedService todoService;

  @GetMapping("/users/{username}/todos")
  public List<Todo> getAllTodos(@PathVariable String username) {
    return todoService.findAll();
  }

  @GetMapping("/users/{username}/todos/{id}")
  public Todo getTodo(@PathVariable String username, @PathVariable long id) {
    return todoService.findById(id);
  }

  // DELETE /users/{username}/todos/{id}
  @DeleteMapping("/users/{username}/todos/{id}")
  public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {
    Todo todo = todoService.deleteById(id);
    if (todo != null) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }

  //  Edit a Todo
  //  PUT /users/{user_name}/todos/{todo_id}
  @PutMapping("/users/{username}/todos/{id}")
  public ResponseEntity<Todo> updateTodo(
      @PathVariable String username, @PathVariable long id, @RequestBody Todo todo) {
    Todo todoUpdate = todoService.save(todo);
    return new ResponseEntity<Todo>(todo, HttpStatus.OK);
  }

  //  Create a new Todo
  //  POST /users/{user_name}/todos/
  @PostMapping("/users/{username}/todos")
  public ResponseEntity<Void> updateTodo(@PathVariable String username, @RequestBody Todo todo) {

    Todo createdTodo = todoService.save(todo);

    // Location
    // Get current resource url
    /// {id}
    URI uri =ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(createdTodo.getId())
        .toUri();

    return ResponseEntity.created(uri).build();
  }
}
