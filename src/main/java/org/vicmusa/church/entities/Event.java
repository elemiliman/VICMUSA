package org.vicmusa.church.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Event {

	@Id
	@GeneratedValue
	private long eventId;
	
	@Column(nullable = false)
	private String eventName;
	
	@Lob
	@Column(nullable = false)
	private String eventDescription;
	
	@Column(nullable = false)
	private String eventLocation;
	
	@Column(nullable = false)
	private String eventStartDate;
	
	@Column(nullable = false)
	private String eventStartDateFormatted;
	
	@Column(nullable = true)
	private String eventEndDate;
	
	@Column(nullable = true)
	private String eventEndDateFormatted;
	
	@Column(nullable = false)
	private String eventTime;
	
	@Column(nullable = true)
	private String organizerName;
	
	@Column(nullable = true)
	private String organizerPhone;
	
	@Column(nullable = true)
	private String organizerEmail;
	
	@Column(nullable = true)
	private String organizerWebsite;
	
	@Column(nullable = true)
	private String eventImage;
	
	public long getEventId() {
		return eventId;
	}
	
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	
	public String getEventName() {
		return eventName;
	}
	
	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
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
	
	public String getEventStartDateFormatted() {
		return eventStartDateFormatted;
	}

	public void setEventStartDateFormatted(String eventStartDateFormatted) {
		this.eventStartDateFormatted = eventStartDateFormatted;
	}

	public String getEventEndDate() {
		return eventEndDate;
	}
	
	public void setEventEndDate(String eventEndDate) {
		this.eventEndDate = eventEndDate;
	}
	
	public String getEventEndDateFormatted() {
		return eventEndDateFormatted;
	}

	public void setEventEndDateFormatted(String eventEndDateFormatted) {
		this.eventEndDateFormatted = eventEndDateFormatted;
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
