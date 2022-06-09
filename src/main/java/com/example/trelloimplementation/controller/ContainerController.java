package com.example.trelloimplementation.controller;


import com.example.trelloimplementation.privatedb.entity.Container;
import com.example.trelloimplementation.privatedb.vo.ContainerVO;
import com.example.trelloimplementation.rest.mapper.ContainerRestMapper;
import com.example.trelloimplementation.rest.request.ContainerRequestDto;
import com.example.trelloimplementation.rest.responce.ContainerResponseDto;
import com.example.trelloimplementation.rest.service.ContainerService;
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

@RestController
@RequestMapping("/container")
@AllArgsConstructor
@Slf4j
public class ContainerController {

    private final ContainerService containerService;
    private final ContainerRestMapper containerRestMapper;

    @GetMapping
    public List<ContainerResponseDto> findAllContainers() {
        List<ContainerVO> containersVO = containerService.findAll();
        return containerRestMapper.toListDto(containersVO);
    }

    @PostMapping
    public ContainerResponseDto createContainer(@RequestBody ContainerRequestDto containerRequestDto) {
        ContainerVO containerVO = containerRestMapper.toContainerVO(containerRequestDto);
        ContainerVO containerResponseVO = containerService.save(containerVO);
        return containerRestMapper.toContainerResponseDto(containerResponseVO);
    }

    @PostMapping("/import")
    public List<ContainerResponseDto> addAll(@RequestBody List<ContainerRequestDto> containerRequestDto) {
        List<ContainerVO> containerVO = containerRestMapper.toListVO(containerRequestDto);
        List<ContainerVO> containerResponseVO = containerService.saveAll(containerVO);
        return containerRestMapper.toListDto(containerResponseVO);
    }// Technical method for debugging. Remove in official version.

    @PutMapping("/{containerID}")
    public ContainerResponseDto update(@RequestBody ContainerRequestDto containerRequestDto) {
        try {
            ContainerVO containerVO = containerRestMapper.toContainerVO(containerRequestDto);
            ContainerVO containerResponseVO = containerService.update(containerVO);
            return containerRestMapper.toContainerResponseDto(containerResponseVO);
        } catch (NoSuchElementException e) {
            log.error("Container hasn't been renamed", e);
            throw new RuntimeException();
        }
    }

    @PatchMapping
    public List<ContainerResponseDto> updateOrder(@RequestBody ContainerRequestDto containerRequestDto) {
        ContainerVO containerVO = containerRestMapper.toContainerVO(containerRequestDto);
        List<ContainerVO> containerResponseVO = containerService.updateOrder(containerVO);

        return containerRestMapper.toListDto(containerResponseVO);
    }

    @DeleteMapping
    public void deleteContainer(@RequestBody ContainerRequestDto containerRequestDto) {
        ContainerVO containerVO = containerRestMapper.toContainerVO(containerRequestDto);
        containerService.delete(containerVO);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        containerService.deleteAll();
    }

}
