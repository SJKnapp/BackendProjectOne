package com.qa.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.data.ToDoList;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:ToDoList-schema.sql",
		"classpath:ToDoList-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class ToDoListServiceControllerIntergrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testGetAll() throws Exception {

		List<ToDoList> toDoLists = new ArrayList<>();
		toDoLists.add(new ToDoList(1, Date.valueOf("2021-07-12"), "one", "a short task", 1, 100));
		toDoLists.add(new ToDoList(2, Date.valueOf("2021-08-11"), "two", "a long task", 5, 1000));
		ResultMatcher body = content().json(this.mapper.writeValueAsString(toDoLists));

		this.mockMvc.perform(get("/getAll")).andExpect(status().isOk()).andExpect(body);
	}

	@Test
	void testCreateTask() throws Exception {
		ToDoList request = new ToDoList(Date.valueOf("2021-07-12"), "one", "a short task", 1, 100);
		ToDoList expected = new ToDoList(3, Date.valueOf("2021-07-12"), "one", "a short task", 1, 100);

		ResultMatcher body = content().json(this.mapper.writeValueAsString(expected));

		this.mockMvc.perform(post("/create").contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(request))).andExpect(status().isOk()).andExpect(body);
	}

	@Test
	void testGetTask() throws Exception {
		ToDoList toDoLists = new ToDoList(1, Date.valueOf("2021-07-12"), "one", "a short task", 1, 100);
		ResultMatcher response = content().json(this.mapper.writeValueAsString(toDoLists));

		this.mockMvc.perform(get("/task/1/get")).andExpect(status().isOk()).andExpect(response);
	}

	@Test
	void testUpdateTask() throws Exception {

	}

	@Test
	void testDeleteTask() throws Exception {
		ToDoList toDoLists = new ToDoList(1, Date.valueOf("2021-07-12"), "one", "a short task", 1, 100);
		ResultMatcher response = content().json(this.mapper.writeValueAsString(toDoLists));

		this.mockMvc.perform(delete("/task/1/delete")).andExpect(status().isOk()).andExpect(response);
	}
}
