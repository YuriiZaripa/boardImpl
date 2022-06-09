package com.example.trelloimplementation.privatedb.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
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

//todo    @NonNull
    @Column(name = "container_name")
    private String containerName;

//todo    @NonNull
//todo    @UniqueElements
    @Column(name = "column_order")
    private int containerOrder;

    @OneToMany(mappedBy = "container", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();
}
