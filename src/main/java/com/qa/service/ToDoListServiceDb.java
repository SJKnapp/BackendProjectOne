package com.qa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.data.ToDoList;
import com.qa.data.repo.ToDoListRepo;

@Service
public class ToDoListServiceDb implements ToDoListService {

	ToDoListRepo repo;

	public ToDoListServiceDb(ToDoListRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public List<ToDoList> getAllTask() {
		return repo.findAll();
	}

	@Override
	public ToDoList getTask(int id) {
		return repo.findById(id).get();
	}

	@Override
	public ToDoList DeleteTask(int id) {

		ToDoList object = getTask(id);
		repo.deleteById(id);
		return object;
	}

	@Override
	public ToDoList CreateTask(ToDoList toDoList) {
		return repo.save(toDoList);
	}

	@Override
	public ToDoList PutTask(int id, ToDoList toDoList) {
		ToDoList object = getTask(id);
		object = toDoList;
		return getTask(id);
	}

}
