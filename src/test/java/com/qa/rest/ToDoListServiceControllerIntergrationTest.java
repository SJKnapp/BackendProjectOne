package com.qa.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
		toDoLists.add(new ToDoList(1, Date.valueOf("2021-07-12"), "one", "a short task", 1, 100, false));
		toDoLists.add(new ToDoList(2, Date.valueOf("2021-08-11"), "two", "a long task", 5, 1000, true));
		toDoLists.add(new ToDoList(3, Date.valueOf("2021-09-11"), "three", "a medium task", 10, 1999, false));
		ResultMatcher body = content().json(this.mapper.writeValueAsString(toDoLists));

		this.mockMvc.perform(get("/getAll")).andExpect(status().isOk()).andExpect(body);
	}

	@Test
	void testCreateTask() throws Exception {
		ToDoList request = new ToDoList(Date.valueOf("2021-07-12"), "one", "a short task", 1, 100);
		ToDoList expected = new ToDoList(4, Date.valueOf("2021-07-12"), "one", "a short task", 1, 100, false);

		ResultMatcher body = content().json(this.mapper.writeValueAsString(expected));

		this.mockMvc.perform(post("/create").contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(request))).andExpect(status().isOk()).andExpect(body);
	}

	@Test
	void testGetTask() throws Exception {
		ToDoList toDoList = new ToDoList(1, Date.valueOf("2021-07-12"), "one", "a short task", 1, 100, false);
		ResultMatcher response = content().json(this.mapper.writeValueAsString(toDoList));

		this.mockMvc.perform(get("/task/1/get")).andExpect(status().isOk()).andExpect(response);
	}

	@Test
	void testUpdateTask() throws Exception {
		ToDoList request = new ToDoList(Date.valueOf("2022-6-05"), "three", "a very long task", 15, 1000);
		ToDoList toDoList = new ToDoList(1, Date.valueOf("2022-6-05"), "three", "a very long task", 15, 1000, false);
		ResultMatcher response = content().json(this.mapper.writeValueAsString(toDoList));

		this.mockMvc
				.perform(put("/task/1/update").contentType(MediaType.APPLICATION_JSON)
						.content(this.mapper.writeValueAsString(request)))
				.andExpect(status().isOk()).andExpect(response);
	}

	@Test
	void testDeleteTask() throws Exception {
		ToDoList toDoLists = new ToDoList(1, Date.valueOf("2021-07-12"), "one", "a short task", 1, 100, false);
		ResultMatcher response = content().json(this.mapper.writeValueAsString(toDoLists));

		this.mockMvc.perform(delete("/task/1/delete")).andExpect(status().isOk()).andExpect(response);
	}

	@Test
	void testPatchStatusTrue() throws Exception {
		ToDoList toDoLists = new ToDoList(1, Date.valueOf("2021-07-12"), "one", "a short task", 1, 100, true);
		ResultMatcher response = content().json(this.mapper.writeValueAsString(toDoLists));

		this.mockMvc.perform(patch("/task/1/status/true")).andExpect(status().isOk()).andExpect(response);
	}

	@Test
	void testPatchStatusFalse() throws Exception {
		ToDoList toDoLists = new ToDoList(2, Date.valueOf("2021-08-11"), "two", "a long task", 5, 1000, false);
		ResultMatcher response = content().json(this.mapper.writeValueAsString(toDoLists));

		this.mockMvc.perform(patch("/task/2/status/false")).andExpect(status().isOk()).andExpect(response);
	}
}
