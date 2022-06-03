package com.example.trelloimplementation.privatedb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name =  "container")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Container {

    @Id
    @GeneratedValue
    @Column(name = "container_id")
    private UUID containerId;

    @Column(name = "container_name")
    private String containerName;

    @Column(name = "column_order")
    private Long containerOrder;

    @OneToMany(mappedBy = "container", cascade = CascadeType.ALL)
//    @JsonManagedReference
    private List<Task> tasks = new ArrayList<>();
}
