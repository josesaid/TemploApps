package com.showmeyourcode.projects.springmvc.domain.mapper;

import com.showmeyourcode.projects.springmvc.domain.model.CategoryDto;
import com.showmeyourcode.projects.springmvc.domain.model.EventDto;
import com.showmeyourcode.projects.springmvc.infrastructure.entity.CategoryEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryMapper {

    private final EventMapper eventMapper;

    public CategoryDto mapToDto(CategoryEntity c, boolean withEvents) {
        Set<EventDto> events = withEvents ? c.getEvents().stream()
                .map(e -> eventMapper.mapToDto(e, false))
                .collect(Collectors.toSet()) : Collections.emptySet();
        log.debug("Category ID: {} Number of associated events: {} Parameters: withEvents={}",
                c.getId(), events.size(), withEvents
        );
        return new CategoryDto(
                c.getId(),
                c.getName(),
                c.getDescription(),
                c.getImagePath(),
                events
        );
    }
}
