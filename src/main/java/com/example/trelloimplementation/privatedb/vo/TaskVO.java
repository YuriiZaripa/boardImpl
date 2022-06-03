package com.example.trelloimplementation.privatedb.vo;

import com.example.trelloimplementation.privatedb.entity.Container;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@Builder
public class TaskVO {

    private UUID taskId;

    private String taskName;

    private String destination;

    private LocalDate created;

    private Long taskOrder;

    ContainerVO container;

}
