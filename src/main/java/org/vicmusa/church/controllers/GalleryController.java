package org.vicmusa.church.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.vicmusa.church.dto.GalleryUploadForm;
import org.vicmusa.church.services.GalleryService;

@Controller
public class GalleryController {

	private GalleryService galleryService;

	/**
	 * Constructor based injection.
	 */
	@Autowired
	GalleryController(GalleryService galleryService){
		this.galleryService = galleryService;
	}
	
	@RequestMapping(value = "/upload-gallery")
	public String galleryUpload(Model model){
		
		String pageTitle = "Gallery Upload";
		model.addAttribute("PageTitle", pageTitle);
		
		model.addAttribute("galleryUploadForm", new GalleryUploadForm());
		
		return "gallery-upload";
	}
	
	@RequestMapping(value = "/upload-gallery", method = RequestMethod.POST)
	public String s3GalleryUpload(@ModelAttribute("galleryUploadForm") @Valid GalleryUploadForm galleryUploadForm,
			BindingResult result, RedirectAttributes redirectAttributes){
		
		if (result.hasErrors()){
			return "gallery-upload";
		}
		
		galleryService.uploadGallery(galleryUploadForm);
		
		return "gallery-upload";
	}
	
	
	@RequestMapping(value = "/gallery")
	public String photoGallery(Pageable pageable, Model model, HttpServletRequest request){
		
		 int startPage = 0;
		 int recordsPerPage = 15;
		 int fromIndex = 0;
		 int toIndex = 0;
		
		 String pageTitle = "Gallery";
		 model.addAttribute("PageTitle", pageTitle);
		
		 List<String> gallery = galleryService.getPhotoGallery();
		
		 Page<String> page = new PageImpl<>(gallery, pageable,
		 gallery.size());
		
		 if(request.getParameter("offset") != null){
		 startPage = Integer.parseInt(request.getParameter("offset"));
		 fromIndex = Math.abs(startPage - 0);
		 toIndex = (recordsPerPage + startPage);
		 }else{
		 fromIndex = Math.abs(startPage - 0);
		 toIndex = (recordsPerPage + startPage);
		 }
		
		 if(toIndex > gallery.size()){
		 toIndex = gallery.size();
		 model.addAttribute("gallery", page.getContent().subList(fromIndex ,
		 toIndex));
		 }else{
		 model.addAttribute("gallery", page.getContent().subList(fromIndex ,
		 toIndex));
		 }
		
		 model.addAttribute("count", page.getTotalElements() - 10);
		
		 model.addAttribute("offset", startPage);
			
		return "photo-gallery";	
	}
	
	
	
}
