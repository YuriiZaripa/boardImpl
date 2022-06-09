package com.example.trelloimplementation.privatedb.repository;

import com.example.trelloimplementation.privatedb.entity.Container;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ContainerRepository extends JpaRepository<Container, UUID> {

    List<Container> findAllByOrderByContainerOrder();

}
