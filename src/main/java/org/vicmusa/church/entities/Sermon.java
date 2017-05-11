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
@Table(name="sermon")
public class Sermon {
	
	public static enum SermonState{
		DRAFT, PUBLISHED
	}
	
	@Id
	@GeneratedValue
	private long sermonId;
	
	@Column(nullable = false)
	private String studyDate;
	
	@Column(nullable = false)
	private String studyDateFormatted;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String scripture;
	
	@Lob 
	@Column(nullable = false)
	private String sermonText;
	
	@Column(nullable = true)
	private String media;
	
	private String sermonPublished;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@JoinTable(name="published_sermon",	
			joinColumns=@JoinColumn(name="sermon_id") 
	)
	private Set<SermonState> published = new HashSet<SermonState>();
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public long getSermonId() {
		return sermonId;
	}

	public void setSermonId(long sermonId) {
		this.sermonId = sermonId;
	}
	public String getStudyDate() {
		return studyDate;
	}
	
	public String getStudyDateFormatted() {
		return studyDateFormatted;
	}

	public void setStudyDateFormatted(String studyDateFormatted) {
		this.studyDateFormatted = studyDateFormatted;
	}

	public void setStudyDate(String studyDate) {
		this.studyDate = studyDate;
	}
	
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

	public String getSermonText() {
		return sermonText;
	}

	public void setSermonText(String sermonText) {
		this.sermonText = sermonText;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}
	
	public String getSermonPublished() {
		return sermonPublished;
	}

	public void setSermonPublished(String sermonPublished) {
		this.sermonPublished = sermonPublished;
	}

	public Set<SermonState> getSermonState() {
		return published;
	}

	public void setSermonState(Set<SermonState> published) {
		this.published = published;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isPublished(){
		return published.contains(SermonState.PUBLISHED);
	}
	
}
