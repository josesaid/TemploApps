package com.showmeyourcode.projects.springmvc.presentation.rest;

import com.showmeyourcode.projects.springmvc.application.CategoryService;
import com.showmeyourcode.projects.springmvc.domain.model.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = CategoryController.CONTROLLER_PATH)
public class CategoryController {

    public static final String CONTROLLER_PATH = "/api/v1/categories";

    private final CategoryService categoryService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDto> getAllCategories(@RequestParam(required = false, name = "withEvents", defaultValue = "false") boolean withEvents) {
        return categoryService.getAll(withEvents);
    }
}
