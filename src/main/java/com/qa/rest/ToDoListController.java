package com.qa.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.data.ToDoList;
import com.qa.service.ToDoListService;

@RestController
@CrossOrigin
public class ToDoListController {

	private ToDoListService service;

	public ToDoListController(ToDoListService service) {
		super();
		this.service = service;
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<ToDoList>> getAll() {
		List<ToDoList> response = service.getAllTask();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<ToDoList> createTask(@RequestBody ToDoList toDoList) {
		return new ResponseEntity<>(service.CreateTask(toDoList), HttpStatus.OK);
	}

	@GetMapping("/task/{id}/get")
	public ResponseEntity<ToDoList> getTask(@PathVariable int id) {
		return new ResponseEntity<>(service.getTask(id), HttpStatus.OK);
	}

	@PutMapping("/task/{id}/update")
	public ResponseEntity<ToDoList> updateTask(@PathVariable int id, @RequestBody ToDoList toDoList) {
		return new ResponseEntity<>(service.PutTask(id, toDoList), HttpStatus.OK);
	}

	@DeleteMapping("/task/{id}/delete")
	public ResponseEntity<ToDoList> deleteTask(@PathVariable int id) {
		return new ResponseEntity<>(service.DeleteTask(id), HttpStatus.OK);
	}

	@PatchMapping("/task/{id}/status/{isDone}")
	public ResponseEntity<ToDoList> patchStatus(@PathVariable int id, @PathVariable boolean isDone) {
		return new ResponseEntity<>(service.SetDone(id, isDone), HttpStatus.OK);
	}
}
