package org.vicmusa.church.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.vicmusa.church.entities.User;

public class UserEditAccountForm {

	@NotNull
	@Size(min=7, max=User.PASSWORD_MAX, message="{passwordSizeError}")
	private String oldPassword = "";
	
	@NotNull
	@Size(min=7, max=User.PASSWORD_MAX, message="{passwordSizeError}")
	private String password = "";
	
	@NotNull
	@Size(min=7, max=User.PASSWORD_MAX, message="{passwordSizeError}")
	private String retypePassword = "";
	
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRetypePassword() {
		return retypePassword;
	}

	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}		
}
