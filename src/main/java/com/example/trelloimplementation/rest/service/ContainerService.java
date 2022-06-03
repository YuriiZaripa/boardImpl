package com.example.trelloimplementation.rest.service;

import com.example.trelloimplementation.privatedb.entity.Container;
import com.example.trelloimplementation.privatedb.mapper.ContainerMapper;
import com.example.trelloimplementation.privatedb.repository.ContainerRepository;
import com.example.trelloimplementation.privatedb.vo.ContainerVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    }

    public List<ContainerVO> getAll() {
        List<Container> containers = containerRepository.findAll();
        return containerMapper.toListVO(containers);
    }

    @Transactional
    public ContainerVO rename(ContainerVO containerVO) {
        Container container = containerMapper.toContainer(containerVO);
        containerRepository.rename(container.getContainerId(), container.getContainerName());

        return containerMapper.toContainerVO(
                containerRepository.findById(container.getContainerId())
                .orElseThrow());
    }

    @Transactional
    public List<ContainerVO> move(ContainerVO containerVO) {
        Container container = containerMapper.toContainer(containerVO);
        containerRepository.move(container.getContainerOrder());
        containerRepository.editOrder(container.getContainerId(), container.getContainerOrder());

        return containerMapper.toListVO(containerRepository.findAll());
    }

    @Transactional
    public void delete(ContainerVO containerVO) {
        try{
            Container container = containerMapper.toContainer(containerVO);
            Optional<Container> currantContainer = containerRepository.findById(container.getContainerId());
            containerRepository.removeOrder(currantContainer.orElseThrow().getContainerOrder());
            containerRepository.delete(currantContainer.get());
        } catch (NoSuchElementException e) {
            log.error("Container hasn't been renamed", e);
            throw new RuntimeException();
        }
    }

    public void deleteAll() {
        containerRepository.deleteAll();
    }

}
