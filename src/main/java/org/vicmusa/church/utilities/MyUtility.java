package org.vicmusa.church.utilities;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.vicmusa.church.dto.UserDetailsImpl;
import org.vicmusa.church.entities.User;

@Component
public class MyUtility {

	private static MessageSource messageSource;
	
	@Autowired
	public MyUtility(MessageSource messageSource) {
		MyUtility.messageSource = messageSource;
	}
	
	public static void flash(RedirectAttributes redirectAttributes, String kind, String messageKey){
		redirectAttributes.addFlashAttribute("flashKind", kind);
		redirectAttributes.addFlashAttribute("flashMessage", MyUtility.getMessage(messageKey));
	}

	public static String getMessage(String messageKey, Object...args) {
		return messageSource.getMessage(messageKey, args, Locale.getDefault());
	}
	
	private static String hostAndPort;
	
	@Value("${hostAndPort}")
	public void setHostAndPort(String hostAndPort) {
		MyUtility.hostAndPort = hostAndPort;
	}
	
	public static String hostUrl() {
		return (isDev() ? "http://" : "https://") + hostAndPort;
	}
	
	private static String activeProfiles;
	
	@Value("${spring.profiles.active}")
    public void setActiveProfiles(String activeProfiles) {
		MyUtility.activeProfiles = activeProfiles;
    }
	
	public static boolean isDev() {
    	return activeProfiles.equals("dev");
    }

	public static void validate(boolean valid, String msgContent, Object...args) {
		if(!valid){
			throw new RuntimeException(getMessage(msgContent, args));
		}
	}

	public static User getSessionUser() {
		UserDetailsImpl auth = getAuth();
		return auth == null ? null : auth.getUser();
	}

	public static UserDetailsImpl getAuth() {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	
	    if (auth != null) {
	      Object principal = auth.getPrincipal();
	      if (principal instanceof UserDetailsImpl) {
	        return (UserDetailsImpl) principal;
	      }
	    }
	    return null;	  
	}

}
