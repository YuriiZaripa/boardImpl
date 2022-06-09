package com.example.trelloimplementation.controller;

import com.example.trelloimplementation.privatedb.entity.Task;
import com.example.trelloimplementation.privatedb.mapper.TaskMapper;
import com.example.trelloimplementation.privatedb.vo.TaskVO;
import com.example.trelloimplementation.rest.request.TaskRequestDto;
import com.example.trelloimplementation.rest.responce.TaskResponseDto;
import com.example.trelloimplementation.rest.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping
    public List getAllTasks() {
        return taskService.getAll();
    } // Technical method for debugging. Remove in official version.

    @PostMapping
    public TaskResponseDto createTask(@RequestBody TaskRequestDto taskRequestDto) {
        TaskVO taskVO = taskMapper.toTaskVO(taskRequestDto);
        TaskVO taskResponseVO = taskService.save(taskVO);
        return taskMapper.toТaskResponseDto(taskResponseVO);
    }

    @PostMapping("/import")
    public List<Task> addAll(@RequestBody List<Task> tasks) {
        int i = 5;
       return taskService.saveAll(tasks);
    }// Technical method for debugging. Remove in official version.

    @PutMapping("/{containerId}/{taskId}")
    public TaskResponseDto updateTask(@RequestBody TaskRequestDto taskRequestDto) {
        TaskVO taskVO = taskMapper.toTaskVO(taskRequestDto);
        TaskVO taskResponseVO = taskService.updateTask(taskVO);
        return taskMapper.toТaskResponseDto(taskResponseVO);
    }

    @PatchMapping
    public TaskResponseDto move(@RequestBody TaskRequestDto taskRequestDto) {
        try {
            TaskVO taskVO = taskMapper.toTaskVO(taskRequestDto);
            TaskVO taskResponseVO = taskService.updateOrder(taskVO);
            return taskMapper.toТaskResponseDto(taskResponseVO);
        } catch (NoSuchElementException e) {
            log.error("Container hasn't been renamed", e);
            throw new RuntimeException();
        }
    }

    @DeleteMapping
    public void delete(@RequestBody TaskRequestDto taskRequestDto) {
        TaskVO taskVO = taskMapper.toTaskVO(taskRequestDto);
        taskService.delete(taskVO);
    }
}
