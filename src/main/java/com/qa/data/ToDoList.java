package com.qa.data;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ToDoList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "fullName", unique = true)
	private String name;
	private int age;

	public ToDoList() {
		// TODO Auto-generated constructor stub
	}

	public ToDoList(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, id, name);
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
		return age == other.age && id == other.id && Objects.equals(name, other.name);
	}

	public ToDoList(int id, String name, int age) {
		super();
		this.setId(id);
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
