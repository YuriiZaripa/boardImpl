package com.example.trelloimplementation.rest.service;

import com.example.trelloimplementation.privatedb.entity.Task;
import com.example.trelloimplementation.privatedb.repository.TaskRepository;
import com.example.trelloimplementation.privatedb.vo.TaskVO;
import com.example.trelloimplementation.rest.mapper.TaskRestMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    public final TaskRepository taskRepository;
    public final TaskRestMapper taskRestMapper;

    public List<Task> saveAll(List<Task> tasks) {
        return taskRepository.saveAll(tasks);
    }// todo Technical method for debugging. Remove in official version.

    public TaskVO save(TaskVO taskVO) {
        Task task = taskRestMapper.toTask(taskVO);
        Task taskResponse = taskRepository.save(task);
        return taskRestMapper.toTaskVO(taskResponse);
    }

    public List getAll() {
        return taskRepository.findAll();
    } // todo Technical method for debugging. Remove in official version.

    @Transactional
    public TaskVO updateOrder(TaskVO taskVO) {
        Task currentOrderTask = taskRestMapper.toTask(taskVO);
        Task oldOrderTask = taskRepository.findById(currentOrderTask.getTaskId()).orElseThrow();
        List<Task> resultList;
        if (currentOrderTask.getContainer().getContainerId().equals(oldOrderTask.getContainer().getContainerId())) {
            resultList = replaceInContainer(currentOrderTask, oldOrderTask);
        } else {
            resultList = replaceBetweenContainers(currentOrderTask, oldOrderTask);
        }

        taskRepository.saveAll(resultList);
        Task resultTask = taskRepository.findById(currentOrderTask.getTaskId()).orElseThrow();
        return taskRestMapper.toTaskVO(resultTask);
    }

    public TaskVO updateTask(TaskVO taskVO) {
        Task task = taskRestMapper.toTask(taskVO);
        Task taskResponse = taskRepository.save(task);
        return taskRestMapper.toTaskVO(taskResponse);
    }

    @Transactional
    public void delete(TaskVO taskVO) {
        Task task = taskRestMapper.toTask(taskVO);
        taskRepository.removeOrder(task.getTaskOrder(), task.getContainer().getContainerId());
        taskRepository.delete(task);
    }

    private List<Task> replaceBetweenContainers(Task currentOrderTask, Task oldOrderTask) {
        ArrayList<Task> oldContainer = taskRepository.findAllByContainerOrderByTaskOrder(oldOrderTask.getContainer());
        ArrayList<Task> currentContainer = taskRepository.findAllByContainerOrderByTaskOrder(currentOrderTask.getContainer());
        ArrayList<Task> resultList = new ArrayList<>(oldContainer.size() + currentContainer.size());//todo

        if (currentContainer.size() < currentOrderTask.getTaskOrder()) currentOrderTask.setTaskOrder(currentContainer.size());
        replaceToDown(currentContainer, currentContainer.size(), currentOrderTask.getTaskOrder());
        replaceToUp(oldContainer, oldOrderTask.getTaskOrder(), oldContainer.size()-1);
        resultList.addAll(oldContainer);
        resultList.addAll(currentContainer);
        oldOrderTask.setTaskOrder(currentOrderTask.getTaskOrder());
        oldOrderTask.setContainer(currentOrderTask.getContainer());

        return resultList;
    }

    private List<Task> replaceInContainer(Task currentOrderTask, Task olsOrderTask) {
        List<Task> result = taskRepository.findAllByContainerOrderByTaskOrder(olsOrderTask.getContainer());

        if (currentOrderTask.getTaskOrder() > result.size()) currentOrderTask.setTaskOrder(result.size());
        if (currentOrderTask.getTaskOrder() > olsOrderTask.getTaskOrder())
            replaceToUp(result, olsOrderTask.getTaskOrder(), currentOrderTask.getTaskOrder());
        else
            replaceToDown(result, olsOrderTask.getTaskOrder(), currentOrderTask.getTaskOrder());
        olsOrderTask.setTaskOrder(currentOrderTask.getTaskOrder());

        return result;
    }

    private void replaceToUp(List<Task> container, int from, int to) {
        Task task;

        for (int order = from +1; order <= to; order++) {
            task = container.get(order);
            task.setTaskOrder(order - 1);
        }
    }

    private void replaceToDown(List<Task> container, int from, int to) {
        Task task;

        for (int order = to; order < from; order++) {
            task = container.get(order);
            task.setTaskOrder(order + 1);
        }
    }
}
