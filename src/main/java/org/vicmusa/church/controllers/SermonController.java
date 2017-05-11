package org.vicmusa.church.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.vicmusa.church.dto.SermonForm;
import org.vicmusa.church.entities.Sermon;
import org.vicmusa.church.services.SermonService;

@Controller
public class SermonController {
	
	private SermonService sermonService;
	
	/**
	 * Constructor based injection.
	 */
	@Autowired
	SermonController(SermonService sermonService) {
		this.sermonService = sermonService;
	}
	
	
	/**
	 * Controller method for creating new sermon [GET METHOD]
	 */
	@RequestMapping(value = "/sermon/create", method = RequestMethod.GET)
	public String sermon(Model model){
		
		String pageTitle = "Create Sermon";
		model.addAttribute("PageTitle", pageTitle);
		
		model.addAttribute("sermonForm", new SermonForm());
		
		return "create-sermon";
	}
	
	/**
	 * Controller method for creating new sermon [POST METHOD]
	 */
	@RequestMapping(value = "/sermon/create", method = RequestMethod.POST )
	public String sermon(@ModelAttribute("sermonForm") @Valid SermonForm sermonForm, BindingResult result, 
			@RequestParam("file") MultipartFile file){
		
		if (result.hasErrors()){
			return "create-sermon";
		}else{
			sermonService.createSermon(sermonForm, file);
		}
		
		return "create-sermon";
	}
	
	/**
	 * Controller method for displaying published sermons
	 */
	@RequestMapping(value = "/sermon/published", method = RequestMethod.GET)
	public String published(Pageable pageable, Model model, HttpServletRequest request){
		
		String pageTitle = "Published Sermons";
		model.addAttribute("PageTitle", pageTitle);
		
		int startPage = 0;
	    int recordsPerPage = 20;
	    int fromIndex = 0;
		int toIndex = 0;
		
		Page<Sermon> publishedSermon = sermonService.findPublishedSermons(pageable);
		
		if(request.getParameter("offset")  != null){
			startPage = Integer.parseInt(request.getParameter("offset"));
			fromIndex = Math.abs(startPage - 0);
			toIndex = (recordsPerPage + startPage);
		}else{
			fromIndex = Math.abs(startPage - 0);
			toIndex = (recordsPerPage + startPage);
		}
		
		if(toIndex > publishedSermon.getContent().size()){
			toIndex = publishedSermon.getContent().size();
			model.addAttribute("publishedSermon", publishedSermon.getContent().subList(fromIndex , toIndex));
		}else{
			model.addAttribute("publishedSermon", publishedSermon.getContent().subList(fromIndex , toIndex));
		}
		
		model.addAttribute("count", publishedSermon.getTotalElements() - 10);
		
		model.addAttribute("offset", startPage);
		
		return "published-sermon";
	}
	
	/**
	 * Controller method for displaying drafted sermons
	 */
	@RequestMapping(value = "/sermon/drafts", method = RequestMethod.GET)
	public String draft(Pageable pageable, Model model, HttpServletRequest request){
		
		String pageTitle = "Drafted Sermons";
		model.addAttribute("PageTitle", pageTitle);
		
		int startPage = 0;
	    int recordsPerPage = 20;
	    int fromIndex = 0;
		int toIndex = 0;
		
		Page<Sermon> draftSermon = sermonService.findDraftSermons(pageable);
		
		if(request.getParameter("offset")  != null){
			startPage = Integer.parseInt(request.getParameter("offset"));
			fromIndex = Math.abs(startPage - 0);
			toIndex = (recordsPerPage + startPage);
		}else{
			fromIndex = Math.abs(startPage - 0);
			toIndex = (recordsPerPage + startPage);
		}
		
		if(toIndex > draftSermon.getContent().size()){
			toIndex = draftSermon.getContent().size();
			model.addAttribute("draftSermon", draftSermon.getContent().subList(fromIndex , toIndex));
		}else{
			model.addAttribute("draftSermon", draftSermon.getContent().subList(fromIndex , toIndex));
		}
		
		model.addAttribute("count", draftSermon.getTotalElements() - 10);
		
		model.addAttribute("offset", startPage);
		
		return "drafted-sermon";
	}
	
	/**
	 * Controller method for getting sermon that is to be edited [GET METHOD]
	 */
	@RequestMapping(value = "/sermon/{sermonId}/edit", method = RequestMethod.GET)
	public String editSermon(@PathVariable("sermonId") long sermonId, Model model){
		
		Sermon sermon = sermonService.findOneSermon(sermonId);
		SermonForm sermonForm = new SermonForm();
		
		if(sermon != null){
			sermonForm.setTitle(sermon.getTitle());
			sermonForm.setStudyDate(sermonForm.getStudyDate());
			sermonForm.setScripture(sermon.getScripture());
			sermonForm.setSermonText(sermon.getSermonText());
		}
		String pageTitle = "Edit Sermon";
		model.addAttribute("PageTitle", pageTitle);
		
		model.addAttribute("sermonForm", sermon);
		
		return "create-sermon";
	}
	
	/**
	 * Controller method for editing sermon [POST METHOD]
	 */
	@RequestMapping(value = "/sermon/{sermonId}/edit", method = RequestMethod.POST)
	public String editSermon(@PathVariable("sermonId") long sermonId, @ModelAttribute("sermonForm") @Valid SermonForm sermonForm, 
			@RequestParam("file") MultipartFile file, BindingResult result){
		
		if (result.hasErrors()){
			return "create-sermon";
		}
		
		sermonService.updateSermon(sermonId, sermonForm, file);
		
		return "create-sermon";
	}
	
	/**
	 * Controller method for publishing sermon
	 */
	@RequestMapping(value = "/sermon/{sermonId}/publish")
	public String publish(@PathVariable("sermonId") long sermonId){
		
		sermonService.publishSermon(sermonId);
		
		return "drafted-sermon";
	}
	
	/**
	 * Controller method for displaying all sermons
	 */
	@RequestMapping(value = "/sermons", method = RequestMethod.GET)
	public String sermons(Pageable pageable, Model model, HttpServletRequest request){
		
		String pageTitle = "Sermons";
		model.addAttribute("PageTitle", pageTitle);
		
		int startPage = 0;
	    int recordsPerPage = 20;
	    int fromIndex = 0;
		int toIndex = 0;
		
		Page<Sermon> sermons = sermonService.findAllSermons(pageable);
		
		if(request.getParameter("offset")  != null){
			startPage = Integer.parseInt(request.getParameter("offset"));
			fromIndex = Math.abs(startPage - 0);
			toIndex = (recordsPerPage + startPage);
		}else{
			fromIndex = Math.abs(startPage - 0);
			toIndex = (recordsPerPage + startPage);
		}
		
		if(toIndex > sermons.getContent().size()){
			toIndex = sermons.getContent().size();
			model.addAttribute("sermons", sermons.getContent().subList(fromIndex , toIndex));
		}else{
			model.addAttribute("sermons", sermons.getContent().subList(fromIndex , toIndex));
		}
		
		model.addAttribute("count", sermons.getTotalElements() - 10);
		
		model.addAttribute("offset", startPage);
		
		return "all-sermons";
	}
	
	/**
	 * Controller method for getting a single sermon [GET METHOD]
	 */
	@RequestMapping(value = "/sermons/sermon/{sermonId}", method = RequestMethod.GET)
	public String sermon(@PathVariable("sermonId") long sermonId, Model model){
		
		Sermon sermon = sermonService.findOneSermon(sermonId);
		
		String pageTitle = "Sermons";
		String teacheProfileImage = sermon.getUser().getUserAddress().getProfilePicture();
		
		model.addAttribute("PageTitle", pageTitle);
		
		model.addAttribute("teacheProfileImage", teacheProfileImage);
		
		model.addAttribute("sermon", sermon);
		
		return "one-sermon";
	}
	
	/**
	 * Controller method for getting sermons by teacher [GET METHOD]
	 */
	@RequestMapping(value = "/sermons/teacher/{userName}", method = RequestMethod.GET)
	public String teacher(@PathVariable("userName") String userName, Pageable pageable, Model model, HttpServletRequest request){
		
		int startPage = 0;
	    int recordsPerPage = 20;
	    int fromIndex = 0;
		int toIndex = 0;
		
		Page<Sermon> teacher = sermonService.findSermonsByTeacher(userName, pageable);
		
		if(request.getParameter("offset")  != null){
			startPage = Integer.parseInt(request.getParameter("offset"));
			fromIndex = Math.abs(startPage - 0);
			toIndex = (recordsPerPage + startPage);
		}else{
			fromIndex = Math.abs(startPage - 0);
			toIndex = (recordsPerPage + startPage);
		}
		
		if(toIndex > teacher.getContent().size()){
			toIndex = teacher.getContent().size();
			model.addAttribute("teacher", teacher.getContent().subList(fromIndex , toIndex));
		}else{
			model.addAttribute("teacher", teacher.getContent().subList(fromIndex , toIndex));
		}
		
		model.addAttribute("count", teacher.getTotalElements() - 10);
		
		model.addAttribute("offset", startPage);
		
		return "teacher-sermons";
	}
	
}
