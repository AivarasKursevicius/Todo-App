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
public class TodoJpaResource {

  @Autowired private TodoHardcodedService todoService;

  @Autowired private TodoJpaRepository todoJpaRepository;

  @GetMapping("/jpa/users/{username}/todos")
  public List<Todo> getAllTodos(@PathVariable String username) {

    return todoJpaRepository.findByUsername(username);
  }

  @GetMapping("/jpa/users/{username}/todos/{id}")
  public Todo getTodo(@PathVariable String username, @PathVariable long id) {

    return todoJpaRepository.findById(id).get();
  }

  // DELETE /users/{username}/todos/{id}
  @DeleteMapping("/jpa/users/{username}/todos/{id}")
  public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {
    todoJpaRepository.deleteById(id);
    return ResponseEntity.notFound().build();
  }

  //  Edit a Todo
  //  PUT /users/{user_name}/todos/{todo_id}
  @PutMapping("/jpa/users/{username}/todos/{id}")
  public ResponseEntity<Todo> updateTodo(
      @PathVariable String username, @PathVariable long id, @RequestBody Todo todo) {
    todo.setUsername(username);
    todoJpaRepository.save(todo);
    return new ResponseEntity<Todo>(todo, HttpStatus.OK);
  }

  //  Create a new Todo
  //  POST /users/{user_name}/todos/
  @PostMapping("/jpa/users/{username}/todos")
  public ResponseEntity<Void> createTodo(@PathVariable String username, @RequestBody Todo todo) {

    todo.setUsername(username);

    Todo createdTodo = todoJpaRepository.save(todo);

    // Location
    // Get current resource url
    /// {id}
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdTodo.getId())
            .toUri();

    return ResponseEntity.created(uri).build();
  }
}
