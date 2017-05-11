package org.vicmusa.church.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import org.vicmusa.church.dto.EventForm;
import org.vicmusa.church.entities.Event;

public interface EventService {

	public abstract void creatEvent(EventForm eventForm, MultipartFile multipartFile);

	public abstract Page<Event> viewEvents(Pageable pageable);

	public abstract Event findOneEvent(long eventId);

	public abstract void updateEvent(long eventId, EventForm eventForm, MultipartFile multipartFile);

	public abstract Page<Event> findAllEvents(Pageable pageable);

	public abstract List<Event> findLastEvents();


}
