package com.example.trelloimplementation.privatedb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "destination")
    private String destination;

    @Column(name = "created")
    private LocalDate created;

    @Column(name = "task_order")
    private Long taskOrder;

    @ManyToOne
//    @JsonBackReference
    @JsonIgnoreProperties({"tasks"})
    Container container;

}
