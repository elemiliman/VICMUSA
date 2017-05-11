package org.vicmusa.church.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.vicmusa.church.dto.OurTeamForm;
import org.vicmusa.church.entities.OurTeam;
import org.vicmusa.church.services.OurTeamService;

@Controller
public class AboutUsController {

	private OurTeamService ourTeamService;
	
	/**
	 * Constructor based injection.
	 */
	@Autowired
	AboutUsController(OurTeamService ourTeamService){
		this.ourTeamService = ourTeamService;
	}
	
	/**
	 * About Us Controller
	 */
	@RequestMapping(value = "/about-us", method = RequestMethod.GET)
	public String aboutUs(Model model, Pageable pageable) {
		
		List<OurTeam> ourTeam = ourTeamService.getTeamList();
		
		model.addAttribute("ourTeam", ourTeam);
		
		String pageTitle = "About Us";
		
		model.addAttribute("PageTitle", pageTitle);
		
		return "about-us";
	}
	
	/**
	 * Controller method for creating new event [GET METHOD]
	 */
	@RequestMapping(value = "/team/create", method = RequestMethod.GET)
	public String ourTeam(Model model){
		
		String pageTitle = "Create Team";
		
		model.addAttribute("PageTitle", pageTitle);
		
		model.addAttribute("ourTeamForm", new OurTeamForm());
		
		return "create-team";
	}
	
	@RequestMapping(value = "/team/create", method = RequestMethod.POST)
	public String ourTeam(@ModelAttribute("ourTeamForm") @Valid OurTeamForm ourTeamForm, BindingResult result, 
							@RequestParam("file")  MultipartFile multipartFile){
		
		if (result.hasErrors()){
			return "create-team";
		}else{
			ourTeamService.createTeam(ourTeamForm, multipartFile);
		}
		
		return "create-team";
	}
	
	/**
	 * Controller method for viewing all events [GET METHOD]
	 */
	@RequestMapping(value = "/team/view", method = RequestMethod.GET)
	public String viewTeam(Model model){
		
		String pageTitle = "View Team";
		
		model.addAttribute("PageTitle", pageTitle);
		
		List<OurTeam> ourTeam = ourTeamService.getTeamList();
		
		model.addAttribute("ourTeam", ourTeam);
		
		return "view-team";
	}
	
	/**
	 * Controller method for editing a team member [GET METHOD]
	 */
	@RequestMapping(value = "/ourteam/view/{ourTeamId}/edit", method = RequestMethod.GET)
	public String editTeam(@PathVariable("ourTeamId") long ourTeamId, Model model){
		
		OurTeam member = ourTeamService.findOneTeamMember(ourTeamId);
		
		OurTeamForm ourTeamForm  = new OurTeamForm();
		
		if(member != null){
			ourTeamForm.setFullName(member.getFullName());
			ourTeamForm.setDesignation(member.getDesignation());
			ourTeamForm.setFaceBookProfile(member.getFaceBookProfile());
			ourTeamForm.setTwitterProfile(member.getTwitterProfile());
			ourTeamForm.setLinkedInProfile(member.getLinkedInProfile());
		}
		
		String pageTitle = "Edit Team";
		model.addAttribute("PageTitle", pageTitle);
		
		model.addAttribute("ourTeamForm", member);
		
		return "create-team";
	}
	
	
	@RequestMapping(value = "/ourteam/view/{ourTeamId}/edit", method = RequestMethod.POST)
	public String editTeam(@PathVariable("ourTeamId") long ourTeamId, @ModelAttribute("ourTeamForm") @Valid OurTeamForm ourTeamForm,
			@RequestParam("file") MultipartFile multipartFile, BindingResult result){
		
		if (result.hasErrors()){
			return "create-team";
		}
		
		ourTeamService.updateTeamMember(ourTeamId, ourTeamForm, multipartFile);
		
		return "create-team";
	}
}
