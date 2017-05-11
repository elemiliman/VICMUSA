package org.vicmusa.church.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.vicmusa.church.entities.User;

public class SignupForm {

	@NotNull
	@Size(min=1, max=User.EMAIL_MAX, message="{emailSizeError}")
	@Pattern(regexp=User.EMAIL_PATTERN , message="{emailPatternError}")
	private String email;
	
	@NotNull
	@Size(min=5, max=User.EMAIL_MAX, message="{userNameSizeError}")
	private String userName;
	
	@NotNull
	@Size(min=1, max=User.NAME_MAX, message="{nameSizeError}")
	private String firstName;
	
	@NotNull
	@Size(min=1, max=User.NAME_MAX, message="{nameSizeError}")
	private String lastName;
	
	@NotNull
	@Size(min=7, max=User.PASSWORD_MAX, message="{passwordSizeError}")
	private String password;
	
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
	@Override
	public String toString() {
		return "SignupForm [email=" + email + ",User Name=" + userName + ", First Name=" + firstName + ", password=" + password + "]";
	}
	
}
