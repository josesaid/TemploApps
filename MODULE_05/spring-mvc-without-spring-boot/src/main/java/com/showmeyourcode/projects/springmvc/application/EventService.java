package com.showmeyourcode.projects.springmvc.application;

import com.showmeyourcode.projects.springmvc.domain.mapper.EventMapper;
import com.showmeyourcode.projects.springmvc.domain.model.EventDto;
import com.showmeyourcode.projects.springmvc.infrastructure.repository.EventRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class EventService {

    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    public List<EventDto> getAll(boolean withUsers) {
        log.info("Getting all events...");
        return eventRepository.findAll().stream()
                .map(e -> eventMapper.mapToDto(e, withUsers))
                .toList();
    }

    public List<EventDto> getAllAvailable(boolean withUsers) {
        log.info("Getting all available events...");
        return eventRepository.findAvailableEvents().stream()
                .map(e -> eventMapper.mapToDto(e, withUsers))
                .toList();
    }

    public Collection<EventDto> getByCount(int number) {
        return eventRepository.findByCount(number).stream()
                .map(e -> eventMapper.mapToDto(e, false))
                .toList();
    }

    public Collection<EventDto> getCreatedByUser(Long id) {
        return eventRepository.findCreatedByUser(id).stream()
                .map(e -> eventMapper.mapToDto(e, false))
                .toList();
    }

    public Collection<EventDto> getParticipatedByUser(Long id) {
        return eventRepository.findParticipatedByUser(id).stream()
                .map(e -> eventMapper.mapToDto(e, false))
                .toList();
    }
}
