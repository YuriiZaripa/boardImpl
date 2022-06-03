package com.example.trelloimplementation.rest.mapper;

import com.example.trelloimplementation.privatedb.entity.Container;
import com.example.trelloimplementation.privatedb.vo.ContainerVO;
import com.example.trelloimplementation.rest.request.ContainerRequestDto;
import com.example.trelloimplementation.rest.responce.ContainerResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContainerRestMapper {

    ContainerVO toContainerVO(ContainerRequestDto containerRequestDto);

    ContainerResponseDto toContainerResponseDto(ContainerVO containerVO);

    List<ContainerResponseDto> toListDto(List<ContainerVO> containersVO);

    List<ContainerVO> toListVO(List<ContainerRequestDto> containerRequestDtos);

}
