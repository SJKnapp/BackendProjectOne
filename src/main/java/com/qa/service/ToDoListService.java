package com.qa.service;

import java.util.ArrayList;

import com.qa.data.ToDoList;

public interface ToDoListService {
	public ArrayList<ToDoList> getAllTask();

	public ToDoList getTask();

	public ToDoList DeleteTask();

	public ToDoList CreateTask();

	public ToDoList PutDuck();
}
