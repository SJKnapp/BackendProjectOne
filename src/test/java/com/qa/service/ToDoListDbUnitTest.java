package com.qa.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.qa.data.ToDoList;
import com.qa.data.repo.ToDoListRepo;

@SpringBootTest
@ActiveProfiles("test")
public class ToDoListDbUnitTest {

	@Autowired
	private ToDoListServiceDb service;

	@Autowired
	private ToDoListRepo repo;

	@Test
	void testGetTask() {
		int id = 1;
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		ToDoList expectedToDoList = new ToDoList(id, date, "task", "long task", 4, 100);

		Mockito.when(repo.getById(id)).thenReturn(expectedToDoList);

		assertThat(service.getTask(id)).isEqualTo(expectedToDoList);
	}

	@Test
	void testDeleteTask() {
		int id = 1;

		assertThat(service.getTask(id)).isEqualTo(null);
	}

	@Test
	void testCreateTask() {
		int id = 1;
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		ToDoList requestToDoList = new ToDoList(date, "task", "long task", 4, 100);
		ToDoList expectedToDoList = new ToDoList(id, date, "task", "long task", 4, 100);

		Mockito.when(repo.save(requestToDoList)).thenReturn(expectedToDoList);

		assertThat(service.CreateTask(requestToDoList)).isEqualTo(expectedToDoList);
	}

	@Test
	void testPutTask() {
		int id = 1;
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		ToDoList requestToDoList = new ToDoList(date, "task", "long task", 4, 100);
		ToDoList expectedToDoList = new ToDoList(id, date, "task", "long task", 4, 100);

		Mockito.when(repo.getById(id)).thenReturn(expectedToDoList);

		assertThat(service.PutTask(id, requestToDoList)).isEqualTo(expectedToDoList);
	}
}
