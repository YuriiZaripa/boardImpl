package com.example.trelloimplementation.rest.mapper;

import com.example.trelloimplementation.privatedb.entity.Task;
import com.example.trelloimplementation.privatedb.vo.TaskVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskRestMapper {

    Task toTask(TaskVO taskVO);

    TaskVO toTaskVO(Task task);

}
