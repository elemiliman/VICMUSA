package org.vicmusa.church.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="blog")
public class Blog {

	public static enum BlogState{
		DRAFT, PUBLISHED
	}
	
	@Id
	@GeneratedValue
	private long blogId;
	
	@Column(nullable = false)
	private String blogDate;
	
	@Column(nullable = false)
	private String blogDateFormatted;
	
	@Column(nullable = false)
	private String blogTitle;
	
	@Lob 
	@Column(nullable = false)
	private String blogText;
	
	@Lob 
	@Column(nullable = false)
	private String blogPlainText;
	
	@Column(nullable = true)
	private String media;
	
	private String blogPublished;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@JoinTable(name="published_blog",	
			joinColumns=@JoinColumn(name="blog_id") 
	)
	private Set<BlogState> published = new HashSet<BlogState>();
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public long getBlogId() {
		return blogId;
	}

	public void setBlogId(long blogId) {
		this.blogId = blogId;
	}
	
	public String getBlogDate() {
		return blogDate;
	}
	
	public String getBlogDateFormatted() {
		return blogDateFormatted;
	}

	public void setBlogDateFormatted(String blogDateFormatted) {
		this.blogDateFormatted = blogDateFormatted;
	}

	public void setBlogDate(String blogDate) {
		this.blogDate = blogDate;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
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

	public Set<BlogState> getBlogState() {
		return published;
	}

	public void setBlogState(Set<BlogState> published) {
		this.published = published;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getBlogPublished() {
		return blogPublished;
	}

	public void setBlogPublished(String blogPublished) {
		this.blogPublished = blogPublished;
	}

	public boolean isPublished(){
		return published.contains(BlogState.PUBLISHED);
	}

	
}
