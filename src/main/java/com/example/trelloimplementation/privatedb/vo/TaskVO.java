package com.example.trelloimplementation.privatedb.vo;

import com.example.trelloimplementation.privatedb.entity.Container;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskVO {

    private UUID taskId;

    private String taskName;

    private String destination;

    private LocalDate created;

    private int taskOrder;

    private ContainerVO container;

}
