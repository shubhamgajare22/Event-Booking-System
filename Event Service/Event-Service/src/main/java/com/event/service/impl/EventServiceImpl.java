package com.event.service.impl;

import com.event.dto.EventDto;
import com.event.entity.Event;
import com.event.exception.ResourceNotFoundException;
import com.event.repository.EventRepository;
import com.event.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EventDto createEvent(EventDto eventDto) {
        Event event = modelMapper.map(eventDto, Event.class);
        event.setEventId(UUID.randomUUID().toString());
        Event event1 = eventRepository.save(event);
        EventDto eventDto1 = modelMapper.map(event1, EventDto.class);
        return eventDto1;
    }

    @Override
    public List<EventDto> getAllEvents() {
        List<Event> event = eventRepository.findAll();
        return event.stream().map(events -> modelMapper.map(events, EventDto.class)).collect(Collectors.toList());
    }

    @Override
    public EventDto getSingleEvent(String eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new ResourceNotFoundException("Event Not Found"));
        return modelMapper.map(event, EventDto.class);
    }

    @Override
    public void deleteEvent(String eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new ResourceNotFoundException("Event Not Found With " + eventId));
        eventRepository.delete(event);
    }

    @Override
    public EventDto updateEvent(String eventId, EventDto eventDto) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new ResourceNotFoundException("Event Not Found with Given Id " + eventId));
        event.setEventPrice(eventDto.getEventPrice());
        event.setSeats(eventDto.getSeats());
        event.setAddress(eventDto.getAddress());
        Event updatedEvent = eventRepository.save(event);
        return modelMapper.map(updatedEvent, EventDto.class);
    }


}
