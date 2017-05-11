package org.vicmusa.church.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.vicmusa.church.utilities.MyUtility;

/**
 * User entity class for storing user info in the database
 */
@Entity
@Table(name="usr", indexes={
	@Index(columnList = "email", unique = true),
	@Index(columnList = "userName", unique = true),
	@Index(columnList = "forgotPasswordCode", unique = true)
})
public class User {
	
	public static final int EMAIL_MAX = 255;
	public static final int IMG_DESCRIPTION = 255;
	public static final int NAME_MAX = 50;
	public static final int ZIP_CODE_MAX = 10;
	public static final int RANDOM_CODE_LENGTH = 16;
	public static final int PASSWORD_MAX = 30;
	public static final String EMAIL_PATTERN = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
	
	public static enum Role{
		UNVERIFIED, BLOCKED, CONTRIBUTOR, EDITOR, ADMIN
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	
	@Column(nullable = false, length = NAME_MAX)
	private String firstName;

	@Column(nullable = false, length = NAME_MAX)
	private String lastName;
	
	@Column(nullable = false, length = EMAIL_MAX)
	private String email;
	
	@Column(nullable = false, length = NAME_MAX)
	private String userName;
	
	@Column(nullable = false) // No length because it will be encrypted
	private String password;
	
	@Column(length = RANDOM_CODE_LENGTH)
	private String verificationCode;
	
	@Column(length = RANDOM_CODE_LENGTH)
	private String forgotPasswordCode;
	
	@OneToOne(fetch = FetchType.EAGER)
	private UserAddress userAddress;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Collection<Sermon> sermon = new ArrayList<Sermon>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Collection<Blog> blog = new ArrayList<Blog>();

	@ElementCollection(fetch = FetchType.EAGER)
	@JoinTable(	joinColumns=@JoinColumn(name="user_id") )
	private Set<Role> roles = new HashSet<Role>();
	
	@ElementCollection(fetch = FetchType.EAGER)
	@JoinTable(name="user_notification", 
			joinColumns=@JoinColumn(name="user_id")
	)
	private Collection<UserNotification> userNotification = new ArrayList<UserNotification>();
	
	public Collection<UserNotification> getUserNotification() {
		return userNotification;
	}

	public void setUserNotification(Collection<UserNotification> userNotification) {
		this.userNotification = userNotification;
	}
	
	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}

	public Collection<Sermon> getSermon() {
		return sermon;
	}

	public void setSermon(Collection<Sermon> sermon) {
		this.sermon = sermon;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public String getForgotPasswordCode() {
		return forgotPasswordCode;
	}

	public void setForgotPasswordCode(String forgotPasswordCode) {
		this.forgotPasswordCode = forgotPasswordCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
	public Collection<Blog> getBlog() {
		return blog;
	}

	public void setBlog(Collection<Blog> blog) {
		this.blog = blog;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return roles.contains(Role.ADMIN);
	}
	
	public boolean isEditor() {
		return roles.contains(Role.EDITOR);
	}
	
	public boolean isContributor() {
		return roles.contains(Role.CONTRIBUTOR);
	}
	
	public boolean isEditable() {
		User loggedIn = MyUtility.getSessionUser();
		if (loggedIn == null){
			return false;
		}else{
			return loggedIn.isAdmin() ||   // ADMIN or
					loggedIn.getUserName().equals(userName); // self can edit
		}
	}
}
