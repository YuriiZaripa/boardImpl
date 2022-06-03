package com.example.trelloimplementation.rest.service;

import com.example.trelloimplementation.privatedb.entity.Task;
import com.example.trelloimplementation.privatedb.repository.TaskRepository;
import com.example.trelloimplementation.privatedb.vo.ContainerVO;
import com.example.trelloimplementation.privatedb.vo.TaskVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @MockBean
    private TaskRepository taskRepository;

    @Test
    void save() {
        //given
        ContainerVO containerVO = ContainerVO.builder()
                .containerId(UUID.randomUUID())
                .build();

        TaskVO taskVO = TaskVO.builder()
                .taskOrder(1L)
                .taskName("TaskName")
                .container(containerVO)
                .build();

        Task task = Task.builder()
                .taskId(UUID.randomUUID())
                .taskName(taskVO.getTaskName())
                .taskOrder(taskVO.getTaskOrder())
                .taskName(taskVO.getTaskName())
                .build();

        Mockito.when(taskRepository.save(Mockito.any())).thenReturn(task);

        //when
        TaskVO response = taskService.save(taskVO);

        //then
        assertEquals(taskVO.getTaskName(), response.getTaskName());
    }

    @Test
    void getAll() {
    }

    @Test
    void move() {
    }

    @Test
    void editTask() {
    }

    @Test
    void delete() {
    }
}