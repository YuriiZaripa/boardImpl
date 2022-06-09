package com.example.trelloimplementation.privatedb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "task")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue
    @Column(name = "taskId")
    private UUID taskId;

//todo    @NonNull
    @Column(name = "task_name")
    private String taskName;

    @Column(name = "destination")
    private String destination;

//todo    @NonNull
    @Column(name = "created")
    private LocalDate created;

//todo    @NonNull
//todo    @UniqueElements
    @Column(name = "task_order")
    private int taskOrder;

    @ManyToOne
    @JsonIgnoreProperties({"tasks"})
    private Container container;

}
