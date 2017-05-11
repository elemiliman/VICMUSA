package org.vicmusa.church.entities;

import javax.persistence.Embeddable;

@Embeddable
public class UserNotification {

	private boolean blogUpdates;
	
	private boolean sermonUpdates;
	
	private boolean eventUpdates;
	
	private boolean weeklyUpdates;

	public boolean isBlogUpdates() {
		return blogUpdates;
	}

	public void setBlogUpdates(boolean blogUpdates) {
		this.blogUpdates = blogUpdates;
	}

	public boolean isSermonUpdates() {
		return sermonUpdates;
	}

	public void setSermonUpdates(boolean sermonUpdates) {
		this.sermonUpdates = sermonUpdates;
	}

	public boolean isEventUpdates() {
		return eventUpdates;
	}

	public void setEventUpdates(boolean eventUpdates) {
		this.eventUpdates = eventUpdates;
	}

	public boolean isWeeklyUpdates() {
		return weeklyUpdates;
	}

	public void setWeeklyUpdates(boolean weeklyUpdates) {
		this.weeklyUpdates = weeklyUpdates;
	}
	
}
