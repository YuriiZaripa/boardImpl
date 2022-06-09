package com.example.trelloimplementation.rest.service;

import com.example.trelloimplementation.privatedb.entity.Container;
import com.example.trelloimplementation.privatedb.mapper.ContainerMapper;
import com.example.trelloimplementation.privatedb.repository.ContainerRepository;
import com.example.trelloimplementation.privatedb.vo.ContainerVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ContainerService {

    private final ContainerRepository containerRepository;
    private final ContainerMapper containerMapper;

    public ContainerVO save(ContainerVO containerVO) {
        Container container = containerMapper.toContainer(containerVO);
        Container saveContainer = containerRepository.save(container);
        return containerMapper.toContainerVO(saveContainer);
    }

    public List<ContainerVO> saveAll(List<ContainerVO> containerVO) {
        List<Container> container = containerMapper.toContainerList(containerVO);
        List<Container> saveContainer = containerRepository.saveAll(container);
        return containerMapper.toListVO(saveContainer);
    }// Technical method for debugging. Remove in official version.

    public List<ContainerVO> findAll() {
        List<Container> containers = containerRepository.findAllByOrderByContainerOrder();
        return containerMapper.toListVO(containers);
    }

    @Transactional
    public ContainerVO update(ContainerVO containerVO) {
        Container container = containerMapper.toContainer(containerVO);
        containerRepository.save(container);
        return containerMapper.toContainerVO(
                containerRepository
                        .findById(container.getContainerId())
                        .orElseThrow());
    }

    @Transactional
    public List<ContainerVO> updateOrder(ContainerVO containerVO) {
        Container currentOrderContainer = containerMapper.toContainer(containerVO);
        List<Container> containers = containerRepository.findAllByOrderByContainerOrder();
        Container oldOrderContainer = containerRepository.findById(currentOrderContainer.getContainerId())
                                                .orElseThrow();
        int fromOrder = oldOrderContainer.getContainerOrder();

        replaceOrder(containers, fromOrder, currentOrderContainer.getContainerOrder());
        containerRepository.saveAll(containers);

        return containerMapper.toListVO(containerRepository.findAllByOrderByContainerOrder());
    }

    @Transactional
    public void delete(ContainerVO containerVO) {
        Container containerToDelete = containerMapper.toContainer(containerVO);
        List<Container> containers = containerRepository.findAllByOrderByContainerOrder();
        Container currentContainer = containerRepository.findById(containerToDelete.getContainerId()).orElseThrow();
        int fromOrder = currentContainer.getContainerOrder();

        replaceOrder(containers, fromOrder, containers.size() - 1);
        containers.remove(currentContainer);
        containerRepository.delete(currentContainer);
        containerRepository.saveAll(containers);

    }

    public void deleteAll() {
        containerRepository.deleteAll();
    }

    private List<Container> removeOrder(List<Container> containers, int containerOrder) {
        Container container;

        for (int order = containerOrder + 1; order < containers.size(); order++) {
            container = containers.get(order);
            container.setContainerOrder(order - 1);
        }

        return containers;
    }

    private void replaceOrder(List<Container> containers, int from, int to) {
        if (from > to) replaceToDown(containers, from, to);
        else if (from < to) replaceToUp(containers, from, to);
    }

    private void replaceToDown(List<Container> containers, int from, int to) {
        Container container;

        for (int order = to; order <= from; order++) {
            container = containers.get(order);
            container.setContainerOrder(order + 1);
        }

        container = containers.get(from);
        container.setContainerOrder(to);
    }

    private void replaceToUp(List<Container> containers, int from, int to) {
        Container container;

        for (int order = from + 1; order <= to; order++) {
            container = containers.get(order);
            container.setContainerOrder(order - 1);
        }

        container = containers.get(from);
        container.setContainerOrder(to);
    }
}
