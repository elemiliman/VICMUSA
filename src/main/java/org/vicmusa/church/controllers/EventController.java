package org.vicmusa.church.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.vicmusa.church.dto.EventForm;
import org.vicmusa.church.entities.Event;
import org.vicmusa.church.services.EventService;

@Controller
public class EventController {

	private EventService eventService;
	
	/**
	 * Constructor based injection.
	 */
	@Autowired
	EventController(EventService eventService){
		this.eventService = eventService;
	}
	
	/**
	 * Controller method for creating new events [GET METHOD]
	 */
	@RequestMapping(value = "/event/create", method = RequestMethod.GET)
	public String event(Model model){
		
		String pageTitle = "Create Event";
		
		model.addAttribute("PageTitle", pageTitle);
		
		model.addAttribute("eventForm", new EventForm());
		
		return "create-event";
	}
	
	@RequestMapping(value = "/event/create", method = RequestMethod.POST )
	public String event(@ModelAttribute("eventForm") @Valid EventForm eventForm, BindingResult result,
			@RequestParam("file")  MultipartFile multipartFile){
		
		if (result.hasErrors()){
			return "create-event";
		}else{
			eventService.creatEvent(eventForm, multipartFile);
		}
		
		return "create-event";
	}
	
	/**
	 * Controller method for displaying created events
	 */
	@RequestMapping(value = "/event/view", method = RequestMethod.GET)
	public String viewEvents(Pageable pageable, Model model, HttpServletRequest request){
		
		String pageTitle = "View Events";
		model.addAttribute("PageTitle", pageTitle);
		
		int startPage = 0;
	    int recordsPerPage = 20;
	    int fromIndex = 0;
		int toIndex = 0;
		
		Page<Event> createdEvents = eventService.viewEvents(pageable);
		
		if(request.getParameter("offset")  != null){
			startPage = Integer.parseInt(request.getParameter("offset"));
			fromIndex = Math.abs(startPage - 0);
			toIndex = (recordsPerPage + startPage);
		}else{
			fromIndex = Math.abs(startPage - 0);
			toIndex = (recordsPerPage + startPage);
		}
		
		if(toIndex > createdEvents.getContent().size()){
			toIndex = createdEvents.getContent().size();
			model.addAttribute("createdEvents", createdEvents.getContent().subList(fromIndex , toIndex));
		}else{
			model.addAttribute("createdEvents", createdEvents.getContent().subList(fromIndex , toIndex));
		}
		
		model.addAttribute("count", createdEvents.getTotalElements() - 10);
		
		model.addAttribute("offset", startPage);
		
		return "view-events";
	}
	

	/**
	 * Controller method for editing and event [GET METHOD]
	 */
	@RequestMapping(value = "/event/{eventId}/edit", method = RequestMethod.GET)
	public String editEvent(@PathVariable("eventId") long eventId, Model model){
		
		Event event = eventService.findOneEvent(eventId);
		
		EventForm eventForm  = new EventForm();
		
		if(event != null){
			eventForm.setEventName(event.getEventName());
			eventForm.setEventDescription(event.getEventDescription());
			eventForm.setEventLocation(event.getEventLocation());
			eventForm.setEventStartDate(event.getEventStartDate());
			eventForm.setEventEndDate(event.getEventEndDate());
			eventForm.setEventTime(event.getEventTime());
		}
		
		String pageTitle = "Edit Event";
		model.addAttribute("PageTitle", pageTitle);
		
		model.addAttribute("eventForm", event);
		
		return "create-event";
	}
	
	/**
	 * Controller method for editing and event [GET METHOD]
	 */
	@RequestMapping(value = "/event/{eventId}/edit", method = RequestMethod.POST)
	public String editEvent(@PathVariable("eventId") long eventId, @ModelAttribute("eventForm") @Valid EventForm eventForm,
			@RequestParam("file") MultipartFile multipartFile, BindingResult result){
		
		if (result.hasErrors()){
			return "create-event";
		}
		
		eventService.updateEvent(eventId, eventForm, multipartFile);
		
		return "create-event";
	}
	
	/**
	 * Controller method for public event page [GET METHOD]
	 */
	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public String allEvents(Pageable pageable, Model model, HttpServletRequest request){
		
		String pageTitle = "Events";
		model.addAttribute("PageTitle", pageTitle);
		
		int startPage = 0;
	    int recordsPerPage = 20;
	    int fromIndex = 0;
		int toIndex = 0;
		
		Page<Event> events = eventService.findAllEvents(pageable);
		
		if(request.getParameter("offset")  != null){
			startPage = Integer.parseInt(request.getParameter("offset"));
			fromIndex = Math.abs(startPage - 0);
			toIndex = (recordsPerPage + startPage);
		}else{
			fromIndex = Math.abs(startPage - 0);
			toIndex = (recordsPerPage + startPage);
		}
		
		if(toIndex > events.getContent().size()){
			toIndex = events.getContent().size();
			model.addAttribute("events", events.getContent().subList(fromIndex , toIndex));
		}else{
			model.addAttribute("events", events.getContent().subList(fromIndex , toIndex));
		}
		
		model.addAttribute("count", events.getTotalElements() - 10);
		
		model.addAttribute("offset", startPage);
		
		return "all-events";
	}
	
	/**
	 * Controller method for displaying a single event [GET METHOD]
	 */
	@RequestMapping(value = "/events/event/{eventId}", method = RequestMethod.GET)
	public String oneEvent(@PathVariable("eventId") long eventId, Model model){
		
		Event event = eventService.findOneEvent(eventId);
		
		String pageTitle = "Event";
		
		model.addAttribute("PageTitle", pageTitle);
	
		model.addAttribute("event", event);
		
		return "one-event";
	}
}
