package com.qa.service;

import java.util.List;

import com.qa.data.ToDoList;

public interface ToDoListService {
	public List<ToDoList> getAllTask();

	public ToDoList getTask(int id);

	public ToDoList DeleteTask(int id);

	public ToDoList CreateTask(ToDoList toDoList);

	public ToDoList PutTask(int id, ToDoList toDoList);
}
