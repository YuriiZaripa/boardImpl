package com.example.trelloimplementation.controller;


import com.example.trelloimplementation.privatedb.vo.ContainerVO;
import com.example.trelloimplementation.rest.mapper.ContainerRestMapper;
import com.example.trelloimplementation.rest.request.ContainerRequestDto;
import com.example.trelloimplementation.rest.responce.ContainerResponseDto;
import com.example.trelloimplementation.rest.service.ContainerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
;
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
    public List<ContainerResponseDto> getAllContainers() {
        List<ContainerVO> containersVO = containerService.getAll();
        return containerRestMapper.toListDto(containersVO);
    }

    @PostMapping
    public ContainerResponseDto createNewContainer(@RequestBody ContainerRequestDto containerRequestDto) {
        ContainerVO containerVO = containerRestMapper.toContainerVO(containerRequestDto);
        ContainerVO containerResponseVO = containerService.save(containerVO);
        return containerRestMapper.toContainerResponseDto(containerResponseVO);
    }

    @PostMapping("/import")
    public List<ContainerResponseDto> addAll(@RequestBody List<ContainerRequestDto> containerRequestDto) {
        List<ContainerVO> containerVO = containerRestMapper.toListVO(containerRequestDto);
        List<ContainerVO> containerResponseVO = containerService.saveAll(containerVO);
        return containerRestMapper.toListDto(containerResponseVO);
    }

    @PutMapping("/edit")
    public ContainerResponseDto rename(@RequestBody ContainerRequestDto containerRequestDto) {
        try {
            ContainerVO containerVO = containerRestMapper.toContainerVO(containerRequestDto);
            ContainerVO containerResponseVO = containerService.rename(containerVO);
            return containerRestMapper.toContainerResponseDto(containerResponseVO);
        } catch (NoSuchElementException e) {
            log.error("Container hasn't been renamed", e);
            throw new RuntimeException();
        }
    }

    @PatchMapping("/edit")
    public List<ContainerResponseDto> move(@RequestBody ContainerRequestDto containerRequestDto) {
        ContainerVO containerVO = containerRestMapper.toContainerVO(containerRequestDto);
        List<ContainerVO> containerResponseVO = containerService.move(containerVO);

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
