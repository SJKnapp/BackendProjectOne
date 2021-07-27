package com.qa.service;

import java.util.ArrayList;

import com.qa.data.ToDoList;

public interface ToDoListService {
	public ArrayList<ToDoList> getAllTask();

	public ToDoList getTask(int id);

	public ToDoList DeleteTask(int id);

	public ToDoList CreateTask(ToDoList toDoList);

	public ToDoList PutTask(int id, ToDoList toDoList);
}
