package com.example.trelloimplementation.privatedb.vo;

import com.example.trelloimplementation.privatedb.entity.Task;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContainerVO {

    private UUID containerId;

    private String containerName;

    private int containerOrder;

    private List<Task> tasks = new ArrayList<>();

}
