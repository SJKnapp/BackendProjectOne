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
	void testCreateTask() {
		int id = 1;
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		ToDoList requestToDoList = new ToDoList(date, "task", "long task", 4, 100);
		ToDoList expectedToDoList = new ToDoList(id, date, "task", "long task", 4, 100);

		Mockito.when(service.CreateTask(requestToDoList)).thenReturn(expectedToDoList);

		assertThat(service.CreateTask(requestToDoList)).isEqualTo(expectedToDoList);
	}

}
