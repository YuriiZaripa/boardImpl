package com.example.trelloimplementation.rest.service;

import com.example.trelloimplementation.privatedb.entity.Task;
import com.example.trelloimplementation.privatedb.repository.TaskRepository;
import com.example.trelloimplementation.privatedb.vo.TaskVO;
import com.example.trelloimplementation.rest.mapper.TaskRestMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskService {

    public final TaskRepository taskRepository;
    public final TaskRestMapper taskRestMapper;

    public TaskVO save(TaskVO taskVO) {
        Task task = taskRestMapper.toTask(taskVO);
        Task taskResponse = taskRepository.save(task);
        return taskRestMapper.toTaskVO(taskResponse);
    }

    public List getAll() {
        return taskRepository.findAll();
    }

    @Transactional
    public TaskVO move(TaskVO taskVO) {
        Task exTask = taskRepository.findById(taskVO.getTaskId()).orElseThrow();
        UUID exContainerID = exTask.getContainer().getContainerId();
        Task currentTask = taskRestMapper.toTask(taskVO);
        UUID currantContainerId = currentTask.getContainer().getContainerId();

        if (!exContainerID.equals(currantContainerId)) {
            taskRepository.moveExContainerTasks(exContainerID, exTask.getTaskOrder());
        }
        taskRepository.moveCurrentContainerTask(currantContainerId, currentTask.getTaskOrder());

        Task taskResponse = taskRepository.save(currentTask);//test it
        return taskRestMapper.toTaskVO(taskResponse);
    }

    public TaskVO editTask(TaskVO taskVO) {
        Task task = taskRestMapper.toTask(taskVO);
        Task taskResponse = taskRepository.save(task);//test it
        return taskRestMapper.toTaskVO(taskResponse);
    }

    @Transactional
    public void delete(TaskVO taskVO) {
        Task task = taskRestMapper.toTask(taskVO);
        taskRepository.removeOrder(task.getTaskOrder(), task.getContainer().getContainerId());
        taskRepository.delete(task);
    }
}
