package org.vicmusa.church.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class OurTeamForm {

	@NotEmpty
	private String fullName;
	
	private String designation;
	
	private String faceBookProfile;
	
	private String twitterProfile;
	
	private String linkedInProfile;
	
	private String profilePicture;

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
