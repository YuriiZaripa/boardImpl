package com.example.trelloimplementation.privatedb.repository;

import com.example.trelloimplementation.privatedb.entity.Container;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ContainerRepository extends JpaRepository<Container, UUID> {

    @Modifying
    @Query("UPDATE Container c SET c.containerName=:newName WHERE c.containerId=:id")
    void rename(@Param("id")UUID containerId, @Param("newName") String newName);

    @Modifying
    @Query("UPDATE Container c SET c.containerOrder=:order WHERE c.containerId=:id")
    void editOrder(@Param("id")UUID containerId, @Param("order")Long order);

    @Modifying
    @Query("UPDATE Container c SET c.containerOrder=c.containerOrder+1 WHERE c.containerOrder>=:order")
    void move(@Param("order") Long order);

    @Modifying
    @Query("UPDATE Container c SET c.containerOrder=c.containerOrder-1 WHERE c.containerOrder>:order")
    void removeOrder(@Param("order") Long order );

}
