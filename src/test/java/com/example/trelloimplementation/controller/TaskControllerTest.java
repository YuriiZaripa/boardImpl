package com.example.trelloimplementation.controller;

import com.example.trelloimplementation.rest.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TaskControllerTest {

    @Autowired
    private TaskService taskService;

    @Test
    void addNewTask() {

    }

    @Test
    void editTask() {
    }

    @Test
    void move() {
    }

    @Test
    void delete() {
    }
}