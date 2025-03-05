package com.showmeyourcode.projects.springmvc.domain.mapper;

import com.showmeyourcode.projects.springmvc.domain.model.AppUserDto;
import com.showmeyourcode.projects.springmvc.domain.model.EventDto;
import com.showmeyourcode.projects.springmvc.infrastructure.entity.EventEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class EventMapper {

    public EventDto mapToDto(EventEntity e, boolean withUsers) {
        List<AppUserDto> users = withUsers ? e.getUsers().stream()
                .map(u -> new AppUserDto(u.getId(), u.getNick()))
                .toList() : Collections.emptyList();
        log.debug("Event ID: {} Number of associated users: {} Parameters: withUsers={}",
                e.getId(), users.size(), withUsers
        );
        return new EventDto(
                e.getId(),
                e.getCreatedBy(),
                e.getLocation(),
                e.getImagePath(),
                e.getName(),
                e.getCreateDate(),
                e.getTargetDate(),
                e.getParticipants(),
                e.getDescription(),
                e.getCategory().getId(),
                users
        );
    }
}
