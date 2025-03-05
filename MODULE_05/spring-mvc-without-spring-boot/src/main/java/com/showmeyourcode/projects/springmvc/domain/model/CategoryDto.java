package com.showmeyourcode.projects.springmvc.domain.model;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long categoryId;
    @Size(max = 255, min = 3, message = "{newEvent.name.size}")
    private String name;
    @Size(max = 255, min = 3, message = "{newEvent.name.size}")
    private String description;
    private String imagePath;
    private Set<EventDto> events;
}
