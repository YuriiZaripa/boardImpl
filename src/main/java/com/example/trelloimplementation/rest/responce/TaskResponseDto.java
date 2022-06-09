package com.example.trelloimplementation.rest.responce;

import com.example.trelloimplementation.privatedb.entity.Container;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;


@Data
@RequiredArgsConstructor
public class TaskResponseDto {

    private UUID taskId;

    private String taskName;

    private String destination;

    private LocalDate created;

    private int taskOrder;

    private ContainerResponseDto container;

}
