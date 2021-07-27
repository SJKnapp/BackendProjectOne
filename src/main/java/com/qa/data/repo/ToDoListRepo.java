package com.qa.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.data.ToDoList;

@Repository
public interface ToDoListRepo extends JpaRepository<ToDoList, Integer> {

}
