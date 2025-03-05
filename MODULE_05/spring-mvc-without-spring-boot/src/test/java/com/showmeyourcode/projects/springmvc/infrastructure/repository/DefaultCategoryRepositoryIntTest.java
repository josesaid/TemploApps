package com.showmeyourcode.projects.springmvc.infrastructure.repository;

import com.showmeyourcode.projects.springmvc.ITBase;
import com.showmeyourcode.projects.springmvc.infrastructure.entity.CategoryEntity;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.LazyInitializationException;
import org.hibernate.collection.spi.PersistentSet;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
class DefaultCategoryRepositoryIntTest extends ITBase {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Transactional
    void shouldDeleteCategory() {
        var entityToSave = new CategoryEntity(null, Instant.now().toString(), "Description", Collections.emptySet(), null);

        var newEntityId = categoryRepository.save(entityToSave);
        assertThat(categoryRepository.get(newEntityId), is(notNullValue()));

        entityToSave.setId(newEntityId);

        categoryRepository.delete(entityToSave);
        assertThat(categoryRepository.get(newEntityId), is(nullValue()));
    }

    @Test
    void shouldGetAllCategories() {
        assertThat(categoryRepository.getAll().size(), greaterThan(0));
    }

    @Test
    @Transactional
    void shouldGetCategoriesByCount() {
        saveRandomEntity();
        saveRandomEntity();
        saveRandomEntity();
        saveRandomEntity();

        assertThat(categoryRepository.getByCount(3), hasSize(3));
    }

    @Test
    @Transactional
    void shouldNotSaveCategoriesWithTheSameName() {
        var entityToSave1 = new CategoryEntity(null, "Category1", "Description", Collections.emptySet(), null);
        var entityToSave2 = new CategoryEntity(null, "Category1", "Description", Collections.emptySet(), null);
        categoryRepository.save(entityToSave1);

        assertThrows(ConstraintViolationException.class, () -> {
            categoryRepository.save(entityToSave2);
        });
    }

    @Test
    void shouldNotThrowErrorWhenDeletingNotExistingCategory() {
        var entityToDelete = new CategoryEntity(23456785432L, Instant.now().toString(), "Description", Collections.emptySet(), null);
        assertDoesNotThrow(() -> {
            categoryRepository.delete(entityToDelete);
        });
    }

    @Test
    @Transactional
    void shouldSaveCategoryWithoutEvents() {
        var entityToSave = new CategoryEntity(null, "Sample", "Description", Collections.emptySet(), null);

        var newEntityId = categoryRepository.save(entityToSave);

        var savedEntity = categoryRepository.get(newEntityId);
        assertThat(savedEntity.getName(), is(entityToSave.getName()));
        assertThat(savedEntity.getDescription(), is(entityToSave.getDescription()));
        var exception = assertThrows(LazyInitializationException.class, () -> {
            savedEntity.getEvents().size();
        });
        log.error("An expected exception: ", exception);
    }

    @Test
    void shouldVerifyEntityDoesNotExist() {
        assertThat(categoryRepository.exists("It does not exist"), is(nullValue()));
    }

    @Test
    @Transactional
    void shouldVerifyEntityExists() {
        var name = "Sample " + Instant.now();
        var entityToSave = new CategoryEntity(null, name, "Description", Collections.emptySet(), null);

        var newEntityId = categoryRepository.save(entityToSave);

        assertThat(categoryRepository.exists(name), is(newEntityId));
    }

    private void saveRandomEntity() {
        var entityToSave = new CategoryEntity(null, Instant.now().toString(), "Description", Collections.emptySet(), null);
        categoryRepository.save(entityToSave);
    }
}
