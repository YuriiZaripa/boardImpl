package com.example.trelloimplementation.rest.service;

import com.example.trelloimplementation.privatedb.entity.Container;
import com.example.trelloimplementation.privatedb.repository.ContainerRepository;
import com.example.trelloimplementation.privatedb.vo.ContainerVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ContainerServiceTest {

    @Autowired
    private ContainerService containerService;

    @MockBean
    private ContainerRepository containerRepository;

    @Test
    void save() {
        ContainerVO containerVO = ContainerVO.builder()
                .containerName("Container1")
                .containerOrder(1L)
                .build();
        Container container = Container.builder()
                .containerId(UUID.randomUUID())
                .containerName(containerVO.getContainerName())
                .containerOrder(containerVO.getContainerOrder())
                .build();

        Mockito.when(containerRepository.save(Mockito.any())).thenReturn(container);
        ContainerVO response = containerService.save(containerVO);

        assertNotNull(response.getContainerId());
        assertEquals(containerVO.getContainerName(), response.getContainerName());
        assertEquals(containerVO.getContainerOrder(), response.getContainerOrder());
    }

    @Test
    void rename() {
        ContainerVO containerVO = ContainerVO.builder()
                .containerId(UUID.randomUUID())
                .containerName("Container1")
                .containerOrder(1L)
                .build();
        Container container = Container.builder()
                .containerId(containerVO.getContainerId())
                .containerName(containerVO.getContainerName())
                .containerOrder(containerVO.getContainerOrder())
                .build();

        Mockito.when(containerRepository.save(Mockito.any())).thenReturn(container);
        ContainerVO response = containerService.save(containerVO);

        assertEquals(containerVO.getContainerId(), response.getContainerId());
        assertEquals(containerVO.getContainerName(), response.getContainerName());
        assertEquals(containerVO.getContainerOrder(), response.getContainerOrder());
    }

    @Test
    void move() {
        ContainerVO containerVO = ContainerVO.builder()
                .containerId(UUID.randomUUID())
                .containerName("Container1")
                .containerOrder(1L)
                .build();
        Container container = Container.builder()
                .containerId(containerVO.getContainerId())
                .containerName(containerVO.getContainerName())
                .containerOrder(containerVO.getContainerOrder())
                .build();

        Mockito.when(containerRepository.save(Mockito.any())).thenReturn(container);
        ContainerVO response = containerService.save(containerVO);

        assertEquals(containerVO.getContainerId(), response.getContainerId());
        assertEquals(containerVO.getContainerName(), response.getContainerName());
        assertEquals(containerVO.getContainerOrder(), response.getContainerOrder());
    }
}