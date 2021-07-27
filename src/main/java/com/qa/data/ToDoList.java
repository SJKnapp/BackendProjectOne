package com.qa.data;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ToDoList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalTime dueDate;
	private String name;
	private String description;
	private int priority;
	private int timeEstimateMinutes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getTimeEstimateMinutes() {
		return timeEstimateMinutes;
	}

	public void setTimeEstimateMinutes(int timeEstimateMinutes) {
		this.timeEstimateMinutes = timeEstimateMinutes;
	}

	public LocalTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalTime dueDate) {
		this.dueDate = dueDate;
	}
}
