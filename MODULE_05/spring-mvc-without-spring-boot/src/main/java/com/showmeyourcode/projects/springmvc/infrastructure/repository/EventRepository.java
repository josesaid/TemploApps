package com.showmeyourcode.projects.springmvc.infrastructure.repository;

import com.showmeyourcode.projects.springmvc.infrastructure.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
    // JOIN FETCH vs JOIN
    // The FETCH keyword tells the entityManager to also fetch the associated entities which are joined eagerly.
    // The "JOIN FETCH" will have its effect if you have (fetch = FetchType.LAZY).
    // Ref: https://stackoverflow.com/questions/17431312/what-is-the-difference-between-join-and-join-fetch-when-using-jpa-and-hibernate
    @Query(value = "SELECT e FROM EventEntity e JOIN e.users u WHERE size(u) < e.participants")
    List<EventEntity> findAvailableEvents();

    // An example of using a native query.
    // A native query is a SQL statement that is specific to a particular database (e.g. MySQL),
    // which differs slightly from JPQL which is used by Spring Data JPA by default.
    // In most cases, you don't need to use native queries in a Spring application.
    @Query(value = "SELECT * FROM event LIMIT ?", nativeQuery = true)
    List<EventEntity> findByCount(int number);

    @Query(value = "SELECT e FROM EventEntity e WHERE e.createdBy=:userId")
    List<EventEntity> findCreatedByUser(@Param("userId") Long userId);

    @Query(value = "SELECT e FROM EventEntity e INNER JOIN e.users u WHERE u.id=:userId")
    List<EventEntity> findParticipatedByUser(@Param("userId") Long userId);
}
