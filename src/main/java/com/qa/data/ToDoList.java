package com.qa.data;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ToDoList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date dueDate;
	private String name;
	private String description;
	private int priority;
	private int timeEstimateMinutes;
	private boolean isDone = false;

	@Override
	public int hashCode() {
		return Objects.hash(description, dueDate, id, name, priority, timeEstimateMinutes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToDoList other = (ToDoList) obj;
		return Objects.equals(description, other.description) && Objects.equals(dueDate, other.dueDate)
				&& id == other.id && Objects.equals(name, other.name) && priority == other.priority
				&& timeEstimateMinutes == other.timeEstimateMinutes;
	}

	public ToDoList() {
		super();
	}

	public ToDoList(Date dueDate, String name, String description, int priority, int timeEstimateMinutes) {
		super();
		this.dueDate = dueDate;
		this.name = name;
		this.description = description;
		this.priority = priority;
		this.timeEstimateMinutes = timeEstimateMinutes;
	}

	public ToDoList(int id, Date dueDate, String name, String description, int priority, int timeEstimateMinutes,
			boolean isDone) {
		super();
		this.id = id;
		this.dueDate = dueDate;
		this.name = name;
		this.description = description;
		this.priority = priority;
		this.timeEstimateMinutes = timeEstimateMinutes;
		this.isDone = isDone;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

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

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
}
