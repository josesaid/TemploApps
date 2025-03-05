package com.showmeyourcode.projects.springmvc.application;

import com.showmeyourcode.projects.springmvc.domain.mapper.CategoryMapper;
import com.showmeyourcode.projects.springmvc.domain.model.CategoryDto;
import com.showmeyourcode.projects.springmvc.infrastructure.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository repository;

    public List<CategoryDto> getAll(boolean withEvents) {
        log.info("Getting all categories (withEvents={})", withEvents);
        return repository.getAll().stream()
                .map(c -> categoryMapper.mapToDto(c, withEvents))
                .toList();
    }

    public List<CategoryDto> getByCount(int limit, boolean withEvents) {
        log.info("Getting all categories limited by '{}'", limit);
        return repository.getByCount(limit).stream()
                .map(c -> categoryMapper.mapToDto(c, withEvents))
                .toList();
    }
}
