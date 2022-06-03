package com.example.trelloimplementation.privatedb.repository;

import com.example.trelloimplementation.privatedb.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Modifying
    @Query("UPDATE Task t SET t.taskOrder=t.taskOrder-1 " +
            "WHERE t.container.containerId=:containerId AND t.taskOrder>:exOrder")
    void moveExContainerTasks(@Param("containerId") UUID containerId, @Param("exOrder") Long taskOrder);

    @Modifying
    @Query("UPDATE Task t SET t.taskOrder=t.taskOrder+1 " +
            "WHERE t.container.containerId=:containerId AND t.taskOrder>:exOrder")
    void moveCurrentContainerTask(@Param("containerId") UUID containerId, @Param("exOrder") Long taskOrder);

    @Modifying
    @Query("UPDATE Task t SET t.taskOrder=t.taskOrder-1 WHERE t.container.containerId =:containerId AND t.taskOrder >:taskOrder")
    void removeOrder(@Param("taskOrder") Long taskOrder, @Param("containerId") UUID containerId);

}
