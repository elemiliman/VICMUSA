package org.vicmusa.church.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class EventForm {

	
	@NotEmpty
	private String eventName;
	
	@NotEmpty
	private String eventDescription;
	
	@NotEmpty
	private String eventLocation;
	
	@NotEmpty
	private String eventStartDate;
	
	private String eventEndDate;
	
	@NotEmpty
	private String eventTime;
	
	private String organizerName;
	
	private String organizerPhone;
	
	private String organizerEmail;
	
	private String organizerWebsite;
	
	private String eventImage;
	
	public String getEventName() {
		return eventName;
	}
	
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getEventLocation() {
		return eventLocation;
	}
	
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
	
	public String getEventStartDate() {
		return eventStartDate;
	}
	
	public void setEventStartDate(String eventStartDate) {
		this.eventStartDate = eventStartDate;
	}
	
	public String getEventEndDate() {
		return eventEndDate;
	}
	
	public void setEventEndDate(String eventEndDate) {
		this.eventEndDate = eventEndDate;
	}
	
	public String getEventTime() {
		return eventTime;
	}
	
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	
	public String getOrganizerName() {
		return organizerName;
	}

	public void setOrganizerName(String organizerName) {
		this.organizerName = organizerName;
	}

	public String getOrganizerPhone() {
		return organizerPhone;
	}

	public void setOrganizerPhone(String organizerPhone) {
		this.organizerPhone = organizerPhone;
	}

	public String getOrganizerEmail() {
		return organizerEmail;
	}

	public void setOrganizerEmail(String organizerEmail) {
		this.organizerEmail = organizerEmail;
	}

	public String getOrganizerWebsite() {
		return organizerWebsite;
	}

	public void setOrganizerWebsite(String organizerWebsite) {
		this.organizerWebsite = organizerWebsite;
	}

	public String getEventImage() {
		return eventImage;
	}

	public void setEventImage(String eventImage) {
		this.eventImage = eventImage;
	}
}
