package org.vicmusa.church.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class BlogForm {

	@NotEmpty
	private String blogTitle;
	
	@NotEmpty
	private String blogDate;
	
	@NotEmpty
	private String blogText;
	
	private String blogPlainText;
	
	private String media;

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogDate() {
		return blogDate;
	}

	public void setBlogDate(String blogDate) {
		this.blogDate = blogDate;
	}

	public String getBlogText() {
		return blogText;
	}

	public void setBlogText(String blogText) {
		this.blogText = blogText;
	}
	
	public String getBlogPlainText() {
		return blogPlainText;
	}

	public void setBlogPlainText(String blogPlainText) {
		this.blogPlainText = blogPlainText;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}
	
}
