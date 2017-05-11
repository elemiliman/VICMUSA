package org.vicmusa.church.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.vicmusa.church.dto.EventForm;
import org.vicmusa.church.entities.Event;
import org.vicmusa.church.entities.User;
import org.vicmusa.church.repositories.EventRepository;
import org.vicmusa.church.utilities.MyUtility;
import org.vicmusa.church.utilities.S3FileUploader;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class EventServiceImpl implements EventService {

	private static final Log log = LogFactory.getLog(EventServiceImpl.class);
	
	private EventRepository eventRepository;
	private S3FileUploader s3fileUploader;
	
	/**
	 * Constructor based injection
	 */
	@Autowired
	EventServiceImpl(EventRepository eventRepository, S3FileUploader s3fileUploader){
		this.s3fileUploader = s3fileUploader;
		this.eventRepository = eventRepository;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void creatEvent(EventForm eventForm, MultipartFile multipartFile) {
		
		User loggedIn = MyUtility.getSessionUser();
		
		String mediaURL = "";
		String bucketName = "vicmevents";
		Event event = new Event();
		
		SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM d, yyyy");
		Date fromDate = null;
		Date tomDate = null;
		
		if (loggedIn.isAdmin() || loggedIn.isEditor()) {
			event.setEventName(eventForm.getEventName());
			event.setEventDescription(eventForm.getEventDescription());
			event.setEventLocation(eventForm.getEventLocation());
			event.setEventStartDate(eventForm.getEventStartDate());
			event.setEventEndDate(eventForm.getEventEndDate());
			event.setEventTime(eventForm.getEventTime());
			
			try {
				fromDate = new SimpleDateFormat("MM-dd-yyyy").parse(eventForm.getEventStartDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			event.setEventStartDateFormatted(formatter.format(fromDate));
			

			try {
				tomDate =  new SimpleDateFormat("MM-dd-yyyy").parse(eventForm.getEventEndDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			event.setEventEndDateFormatted(formatter.format(tomDate));
			
			event.setOrganizerName(eventForm.getOrganizerName());
			event.setOrganizerPhone(eventForm.getOrganizerPhone());
			event.setOrganizerEmail(eventForm.getOrganizerEmail());
			event.setOrganizerWebsite(eventForm.getOrganizerWebsite());
			
			if (!eventForm.getEventName().isEmpty() && !multipartFile.isEmpty() && multipartFile.getSize() > 0) {
				log.info("File upload in progress....");
				mediaURL = s3fileUploader.uploadSingleFile(multipartFile, bucketName);
				log.info("File upload finished " + mediaURL);
				event.setEventImage(mediaURL);
			}
		}
		eventRepository.save(event);
	}

	@Override
	public Page<Event> viewEvents(Pageable pageable) {
		
		return eventRepository.findAll(pageable);
	}

	@Override
	public Event findOneEvent(long eventId) {

		return eventRepository.findOne(eventId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void updateEvent(long eventId, EventForm eventForm, MultipartFile multipartFile) {
		
		Event eventUpdate = eventRepository.findOne(eventId);
		User loggedIn = MyUtility.getSessionUser();
		
		String mediaURL = "";
		String bucketName = "vicmevents";
		
		SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM d, yyyy");
		Date fromDate = null;
		Date tomDate = null;
		
		if(eventUpdate != null && loggedIn.isAdmin() || loggedIn.isEditor()){
			eventUpdate.setEventName(eventForm.getEventName());
			eventUpdate.setEventDescription(eventForm.getEventDescription());
			eventUpdate.setEventLocation(eventForm.getEventLocation());
			eventUpdate.setEventStartDate(eventForm.getEventStartDate());
			eventUpdate.setEventEndDate(eventForm.getEventEndDate());
			eventUpdate.setEventTime(eventForm.getEventTime());
			
			try {
				fromDate = new SimpleDateFormat("MM-dd-yyyy").parse(eventForm.getEventStartDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			eventUpdate.setEventStartDateFormatted(formatter.format(fromDate));
			

			try {
				tomDate =  new SimpleDateFormat("MM-dd-yyyy").parse(eventForm.getEventEndDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			eventUpdate.setEventEndDateFormatted(formatter.format(tomDate));
			
			eventUpdate.setOrganizerName(eventForm.getOrganizerName());
			eventUpdate.setOrganizerPhone(eventForm.getOrganizerPhone());
			eventUpdate.setOrganizerEmail(eventForm.getOrganizerEmail());
			eventUpdate.setOrganizerWebsite(eventForm.getOrganizerWebsite());
			
			if (!eventForm.getEventName().isEmpty() && !multipartFile.isEmpty() && multipartFile.getSize() > 0) {
				log.info("File upload in progress....");
				mediaURL = s3fileUploader.uploadSingleFile(multipartFile, bucketName);
				log.info("File upload finished " + mediaURL);
				eventUpdate.setEventImage(mediaURL);
			}
		}
		eventRepository.save(eventUpdate);
	}

	@Override
	public Page<Event> findAllEvents(Pageable pageable) {
		
		return eventRepository.findAllByOrderByEventStartDateDesc(pageable);
		
	}

	@Override
	public List<Event> findLastEvents() {
	
		return eventRepository.findLast6ByOrderByEventStartDateDesc();
	}

	
}
