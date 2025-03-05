package com.showmeyourcode.projects.springmvc.infrastructure.repository;

import com.showmeyourcode.projects.springmvc.infrastructure.entity.CategoryEntity;

import java.util.List;

public interface CategoryRepository {
    void delete(CategoryEntity entity);

    Long exists(String name);

    CategoryEntity get(Long identifier);

    List<CategoryEntity> getAll();

    List<CategoryEntity> getByCount(int number);

    Long save(CategoryEntity entity);
}
