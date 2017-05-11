package org.vicmusa.church.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OurTeam {

	@Id
	@GeneratedValue
	private long ourTeamId;
	
	@Column(nullable = false)
	private String fullName;
	
	@Column(nullable = true)
	private String designation;
	
	@Column(nullable = true)
	private String faceBookProfile;
	
	@Column(nullable = true)
	private String twitterProfile;
	
	@Column(nullable = true)
	private String linkedInProfile;
	
	@Column(nullable = true)
	private String profilePicture;
	
	public long getOurTeamId() {
		return ourTeamId;
	}
	
	public void setOurTeamId(long ourTeamId) {
		this.ourTeamId = ourTeamId;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getDesignation() {
		return designation;
	}
	
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public String getFaceBookProfile() {
		return faceBookProfile;
	}
	
	public void setFaceBookProfile(String faceBookProfile) {
		this.faceBookProfile = faceBookProfile;
	}
	
	public String getTwitterProfile() {
		return twitterProfile;
	}
	
	public void setTwitterProfile(String twitterProfile) {
		this.twitterProfile = twitterProfile;
	}
	
	public String getLinkedInProfile() {
		return linkedInProfile;
	}
	
	public void setLinkedInProfile(String linkedInProfile) {
		this.linkedInProfile = linkedInProfile;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	
}
