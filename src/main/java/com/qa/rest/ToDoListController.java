package com.qa.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

}
