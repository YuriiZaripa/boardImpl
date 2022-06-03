package com.example.trelloimplementation.privatedb.mapper;

import com.example.trelloimplementation.privatedb.vo.TaskVO;
import com.example.trelloimplementation.rest.request.TaskRequestDto;
import com.example.trelloimplementation.rest.responce.TaskResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskVO toTaskVO(TaskRequestDto taskRequestDto);

    TaskResponseDto toRaskResponseDto(TaskVO taskResponseVO);
}
