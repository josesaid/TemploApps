package com.showmeyourcode.projects.springmvc.infrastructure.repository;

import com.showmeyourcode.projects.springmvc.infrastructure.entity.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<AppUserEntity, Long> {
    @Query("SELECT u FROM AppUserEntity u WHERE u.nick = ?1")
    AppUserEntity getByLogin(String name);
}
