package com.example.trelloimplementation.rest.request;

import com.example.trelloimplementation.privatedb.entity.Container;
import com.example.trelloimplementation.rest.responce.ContainerResponseDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class TaskRequestDto {

    private UUID taskId;

    private String taskName;

    private String destination;

    private LocalDate created;

    private int taskOrder;

    private ContainerResponseDto container;

}
