package com.qa.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.data.ToDoList;
import com.qa.data.repo.ToDoListRepo;

@SpringBootTest
@ActiveProfiles("test")
public class ToDoListDbUnitTest {

	@Autowired
	private ToDoListServiceDb service;

	@MockBean
	private ToDoListRepo repo;

	@Test
	void testGetAllTasks() {
		List<ToDoList> expected = new ArrayList<ToDoList>();

		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		expected.add(new ToDoList(1, date, "task", "long task", 4, 100, false));
		expected.add(new ToDoList(2, date, "long task", "long task", 4, 100, false));

		Mockito.when(this.repo.findAll()).thenReturn(expected);

		assertThat(service.getAllTask()).isEqualTo(expected);
	}

	@Test
	void testGetTask() {
		int id = 1;
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		ToDoList expectedToDoList = new ToDoList(id, date, "task", "long task", 4, 100, false);

		Mockito.when(repo.findById(id)).thenReturn(Optional.of(expectedToDoList));

		assertThat(service.getTask(id)).isEqualTo(expectedToDoList);
	}

	@Test
	void testDeleteTask() {
		int id = 1;
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		ToDoList expectedToDoList = new ToDoList(id, date, "task", "long task", 4, 100, false);

		Mockito.when(repo.findById(id)).thenReturn(Optional.of(expectedToDoList));

		assertThat(service.getTask(id)).isEqualTo(expectedToDoList);
	}

	@Test
	void testCreateTask() {
		int id = 1;
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		ToDoList requestToDoList = new ToDoList(date, "task", "long task", 4, 100);
		ToDoList expectedToDoList = new ToDoList(id, date, "task", "long task", 4, 100, false);

		Mockito.when(repo.save(requestToDoList)).thenReturn(expectedToDoList);

		assertThat(service.CreateTask(requestToDoList)).isEqualTo(expectedToDoList);
	}

	@Test
	void testPutTask() {
		int id = 1;
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		ToDoList requestToDoList = new ToDoList(date, "task", "long task", 4, 100);
		ToDoList expectedToDoList = new ToDoList(id, date, "task", "long task", 4, 100, false);

		Mockito.when(repo.findById(id)).thenReturn(Optional.of(expectedToDoList));

		assertThat(service.PutTask(id, requestToDoList)).isEqualTo(expectedToDoList);
	}

	@Test
	void testIsDoneTrue() {
		int id = 1;
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		ToDoList expectedToDoList = new ToDoList(id, date, "task", "long task", 4, 100, true);

		Mockito.when(repo.findById(id)).thenReturn(Optional.of(expectedToDoList));

		assertThat(service.SetDone(id, true)).isEqualTo(expectedToDoList);
	}

	@Test
	void testIsDoneFalse() {
		int id = 1;
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		ToDoList expectedToDoList = new ToDoList(id, date, "task", "long task", 4, 100, false);

		Mockito.when(repo.findById(id)).thenReturn(Optional.of(expectedToDoList));

		assertThat(service.SetDone(id, false)).isEqualTo(expectedToDoList);
	}
}
