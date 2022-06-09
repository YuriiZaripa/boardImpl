package com.example.trelloimplementation.rest.responce;

import com.example.trelloimplementation.privatedb.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContainerResponseDto {

    private UUID containerId;

    private String containerName;

    private int containerOrder;

    private List<Task> tasks = new ArrayList<>();
}
