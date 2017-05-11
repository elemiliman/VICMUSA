package org.vicmusa.church.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class SermonForm {

	@NotEmpty
	private String title;
	
	@NotEmpty
	private String scripture;
	
	@NotEmpty
	private String studyDate;
	
	@NotEmpty
	private String sermonText;
	
	private String media;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getScripture() {
		return scripture;
	}

	public void setScripture(String scripture) {
		this.scripture = scripture;
	}

	public String getStudyDate() {
		return studyDate;
	}

	public void setStudyDate(String studyDate) {
		this.studyDate = studyDate;
	}
	
	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getSermonText() {
		return sermonText;
	}

	public void setSermonText(String sermonText) {
		this.sermonText = sermonText;
	}
}
