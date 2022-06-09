package com.example.trelloimplementation.rest.service;

import com.example.trelloimplementation.privatedb.entity.Container;
import com.example.trelloimplementation.privatedb.entity.Task;
import com.example.trelloimplementation.privatedb.repository.TaskRepository;
import com.example.trelloimplementation.privatedb.vo.ContainerVO;
import com.example.trelloimplementation.privatedb.vo.TaskVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
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
        ContainerVO containerVO = ContainerVO.builder()
                .containerId(UUID.randomUUID())
                .build();

        TaskVO taskVO = TaskVO.builder()
                .taskOrder(1L)
                .taskName("TaskName")
                .container(containerVO)
                .build();

        Container container = Container.builder()
                .containerId(containerVO.getContainerId())
                .build();

        Task task = Task.builder()
                .taskId(UUID.randomUUID())
                .taskName(taskVO.getTaskName())
                .taskOrder(taskVO.getTaskOrder())
                .taskName(taskVO.getTaskName())
                .container(container)
                .build();

        Mockito.when(taskRepository.save(Mockito.any())).thenReturn(task);
        TaskVO response = taskService.save(taskVO);

        assertNotNull(response.getTaskId());
        assertEquals(taskVO.getTaskName(), response.getTaskName());
        assertEquals(taskVO.getDestination(), response.getDestination());
        assertEquals(taskVO.getCreated(), response.getCreated());
        assertEquals(taskVO.getTaskOrder(), response.getTaskOrder());
        assertEquals(taskVO.getContainer().getContainerId(),
                response.getContainer().getContainerId());
    }

    @Test
    void move() {
        ContainerVO containerVO = ContainerVO.builder()
                .containerId(UUID.randomUUID())
                .build();

        TaskVO taskVO = TaskVO.builder()
                .taskOrder(1L)
                .taskName("TaskName")
                .container(containerVO)
                .build();

        Container container = Container.builder()
                .containerId(containerVO.getContainerId())
                .build();

        Task task = Task.builder()
                .taskId(UUID.randomUUID())
                .taskName(taskVO.getTaskName())
                .taskOrder(taskVO.getTaskOrder())
                .taskName(taskVO.getTaskName())
                .container(container)
                .build();

        Mockito.when(taskRepository.save(Mockito.any())).thenReturn(task);
        TaskVO response = taskService.save(taskVO);

        assertEquals(taskVO.getTaskId(), response.getTaskId());
        assertEquals(taskVO.getDestination(), response.getDestination());
        assertEquals(taskVO.getTaskName(), response.getTaskName());
        assertEquals(taskVO.getCreated(), response.getCreated());
        assertEquals(taskVO.getTaskOrder(), response.getTaskOrder());
        assertEquals(taskVO.getContainer().getContainerId(),
                response.getContainer().getContainerId());
    }

    @Test
    void editTask() {
        ContainerVO containerVO = ContainerVO.builder()
                .containerId(UUID.randomUUID())
                .build();

        TaskVO taskVO = TaskVO.builder()
                .taskOrder(1L)
                .taskName("TaskName")
                .container(containerVO)
                .build();

        Container container = Container.builder()
                .containerId(containerVO.getContainerId())
                .build();

        Task task = Task.builder()
                .taskId(UUID.randomUUID())
                .taskName(taskVO.getTaskName())
                .taskOrder(taskVO.getTaskOrder())
                .taskName(taskVO.getTaskName())
                .container(container)
                .build();

        Mockito.when(taskRepository.save(Mockito.any())).thenReturn(task);
        TaskVO response = taskService.save(taskVO);

        assertEquals(taskVO.getTaskId(), response.getTaskId());
        assertEquals(taskVO.getTaskName(), response.getTaskName());
        assertEquals(taskVO.getDestination(), response.getDestination());
        assertEquals(taskVO.getCreated(), response.getCreated());
        assertEquals(taskVO.getTaskOrder(), response.getTaskOrder());
        assertEquals(taskVO.getContainer().getContainerId(),
                response.getContainer().getContainerId());
    }
}