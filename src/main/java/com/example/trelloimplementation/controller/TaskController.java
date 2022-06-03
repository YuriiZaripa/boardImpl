package com.example.trelloimplementation.controller;

import com.example.trelloimplementation.privatedb.entity.Container;
import com.example.trelloimplementation.privatedb.entity.Task;
import com.example.trelloimplementation.privatedb.mapper.TaskMapper;
import com.example.trelloimplementation.privatedb.vo.TaskVO;
import com.example.trelloimplementation.rest.request.TaskRequestDto;
import com.example.trelloimplementation.rest.responce.TaskResponseDto;
import com.example.trelloimplementation.rest.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    } //remove method

    @PostMapping
    public TaskResponseDto addNewTask(@RequestBody TaskRequestDto taskRequestDto) {
        TaskVO taskVO = taskMapper.toTaskVO(taskRequestDto);
        TaskVO taskResponseVO = taskService.save(taskVO);
        return taskMapper.toRaskResponseDto(taskResponseVO);
    }

    @PutMapping
    public TaskResponseDto editTask(@RequestBody TaskRequestDto taskRequestDto) {
        TaskVO taskVO = taskMapper.toTaskVO(taskRequestDto);
        TaskVO taskResponseVO = taskService.editTask(taskVO);
        return taskMapper.toRaskResponseDto(taskResponseVO);
    }

    @PatchMapping
    public TaskResponseDto move(TaskRequestDto taskRequestDto) {
        try {
            TaskVO taskVO = taskMapper.toTaskVO(taskRequestDto);
            TaskVO taskResponseVO = taskService.move(taskVO);
            return taskMapper.toRaskResponseDto(taskResponseVO);
        } catch (NoSuchElementException e) {
            log.error("Container hasn't been renamed", e);
            throw new RuntimeException();
        }
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody TaskRequestDto taskRequestDto) {
        TaskVO taskVO = taskMapper.toTaskVO(taskRequestDto);
        taskService.delete(taskVO);
    }
}
