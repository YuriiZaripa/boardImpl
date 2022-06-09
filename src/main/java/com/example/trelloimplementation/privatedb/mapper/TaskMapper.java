package com.example.trelloimplementation.privatedb.mapper;

import com.example.trelloimplementation.privatedb.entity.Task;
import com.example.trelloimplementation.privatedb.vo.TaskVO;
import com.example.trelloimplementation.rest.request.TaskRequestDto;
import com.example.trelloimplementation.rest.responce.TaskResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskVO toTaskVO(TaskRequestDto taskRequestDto);

    TaskResponseDto toТaskResponseDto(TaskVO taskResponseVO);
}
