package org.vicmusa.church.controllers;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.vicmusa.church.dto.ForgotPasswordForm;
import org.vicmusa.church.dto.ResetPasswordForm;
import org.vicmusa.church.dto.SignupForm;
import org.vicmusa.church.entities.Blog;
import org.vicmusa.church.entities.Event;
import org.vicmusa.church.services.BlogService;
import org.vicmusa.church.services.EventService;
import org.vicmusa.church.services.GalleryService;
import org.vicmusa.church.services.UserService;
import org.vicmusa.church.utilities.MyUtility;
import org.vicmusa.church.validators.ForgotPasswordFormValidator;
import org.vicmusa.church.validators.ResetPasswordFormValidator;
import org.vicmusa.church.validators.SignupFormValidator;

@Controller
public class RootController {
	
	private UserService userService;
	private BlogService blogService;
	private EventService eventService;
	private GalleryService galleryService;
	private SignupFormValidator signupFormValidator;
	private ForgotPasswordFormValidator forgotPasswordFormValidator;
	private ResetPasswordFormValidator resetPasswordFormValidator;
	
	/**
	 * Constructor based injection
	 */
	@Autowired
	RootController(UserService userService, GalleryService galleryService, BlogService blogService, EventService eventService, 
			SignupFormValidator signupFormValidator, ForgotPasswordFormValidator forgotPasswordFormValidator,
			ResetPasswordFormValidator resetPasswordFormValidator) {
		this.userService = userService;
		this.blogService = blogService;
		this.eventService = eventService;
		this.galleryService = galleryService;
		this.signupFormValidator = signupFormValidator;
		this.forgotPasswordFormValidator = forgotPasswordFormValidator;
		this.resetPasswordFormValidator = resetPasswordFormValidator;
	}
	
	/**
	 * Sign up form validation binder
	 */
	@InitBinder("signupForm")
	protected void initSignupBinder(WebDataBinder binder){
		binder.setValidator(signupFormValidator);
	}
	
	/**
	 * Forgot password form validation binder
	 */
	@InitBinder("forgotPasswordForm")
	protected void initForgotPasswordBinder(WebDataBinder binder){
		binder.setValidator(forgotPasswordFormValidator);
	}
	
	/**
	 * Reset password form validation binder
	 */
	@InitBinder("resetPasswordForm")
	protected void initResetPasswordBinder(WebDataBinder binder){
		binder.setValidator(resetPasswordFormValidator);
	}
	
	/**
	 * Home Controller
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, Pageable pageable) {
		
		List<String> fromGallery = galleryService.getPhotoGallery().subList(0, 6);
		
		List<Event> events = eventService.findLastEvents();
		
		List<Blog> blogs = blogService.findLastBlogs();
		
		model.addAttribute("fromGallery", fromGallery);
		
		model.addAttribute("events", events);
		
		model.addAttribute("blogs", blogs);
		
		String pageTitle = "Home";
		model.addAttribute("PageTitle", pageTitle);
		
		return "home";
	}
	
	/**
	 * Logout Controller
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) throws ServletException{
		request.logout();
		return "redirect:/";
	}
	
	/**
	 * Forgot Password for existing user
	 */
	@RequestMapping("/logout-forgotpassword")
	public String logoutForgotPassword(HttpServletRequest request) throws ServletException{
		request.logout();
		return "redirect:/forgot-password";
	}
	
	/**
	 * Sign up new user GET METHOD
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) throws MessagingException {
		
		String pageTitle = "Register";
		model.addAttribute("PageTitle", pageTitle);
		model.addAttribute(new SignupForm());

		return "register";
	}
	
	/**
	 * Sign up new user POST METHOD
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("signupForm") @Valid SignupForm signupForm, 
			BindingResult result, RedirectAttributes redirectAttributes){
		
		if(result.hasErrors()){
			return "register";
		}
		
		userService.signup(signupForm);
		
		MyUtility.flash(redirectAttributes, "success", "signupSuccess");
		
		return "redirect:/";
	}
	
	/**
	 * Forgot password GET Method
	 */
	@RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
	public String forgotPassword(Model model) {
		
		String pageTitle = "Forgot Password";
		model.addAttribute("PageTitle", pageTitle);
		
		model.addAttribute(new ForgotPasswordForm());
		
		return "forgot-password";
	}
	
	/**
	 * Forgot password POST METHOD
	 */
	@RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
	public String forgotPassword(
			@ModelAttribute("forgotPasswordForm") @Valid ForgotPasswordForm forgotPasswordForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors())
			return "forgot-password";

		userService.forgotPassword(forgotPasswordForm);
		MyUtility.flash(redirectAttributes, "info", "checkMailResetPassword");

		return "redirect:/";
	}
	
	 /**
     * Reset password no GET METHOD. Because we are not receiving any input from the form
     */
    @RequestMapping(value = "/reset-password/{forgotPasswordCode}")
    public String resetPassword(@PathVariable("forgotPasswordCode") String forgotPasswordCode, Model model) {
    	
    	String pageTitle = "Reset Password";
		model.addAttribute("PageTitle", pageTitle);
		
     	model.addAttribute(new ResetPasswordForm());
    	
     	return "reset-password";
    }
    
    /**
     * Reset password POST METHOD
     */
    @RequestMapping(value = "/reset-password/{forgotPasswordCode}", method = RequestMethod.POST)
	public String resetPassword(
			@PathVariable("forgotPasswordCode") String forgotPasswordCode, @ModelAttribute("resetPasswordForm")
				@Valid ResetPasswordForm resetPasswordForm, BindingResult result, 
				RedirectAttributes redirectAttributes) {

		userService.resetPassword(forgotPasswordCode, resetPasswordForm, result);
		
		if (result.hasErrors())
			return "reset-password";

		MyUtility.flash(redirectAttributes, "success", "passwordChanged");

		return "redirect:/login";
	}
}
