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
import org.vicmusa.church.dto.BlogForm;
import org.vicmusa.church.entities.Blog;
import org.vicmusa.church.services.BlogService;

@Controller
public class BlogController {
	
	private BlogService blogService;
	
	/**
	 * Constructor based injection.
	 */
	@Autowired
	BlogController(BlogService blogService) {
		this.blogService = blogService;
	}
	
	/**
	 * Controller method for creating new blog [GET METHOD]
	 */
	@RequestMapping(value = "/blog/create", method = RequestMethod.GET)
	public String blog(Model model){
		
		String pageTitle = "Create Blog";
		
		model.addAttribute("PageTitle", pageTitle);
		
		model.addAttribute("blogForm", new BlogForm());
		
		return "create-blog";
	}
	
	/**
	 * Controller method for creating new blog [POST METHOD]
	 */
	@RequestMapping(value = "/blog/create", method = RequestMethod.POST )
	public String blog(@ModelAttribute("blogForm") @Valid BlogForm blogForm, BindingResult result, 
			@RequestParam("file")  MultipartFile multipartFile, HttpServletRequest request){
		
		if (result.hasErrors()){
			return "create-blog";
		}else{
			blogService.createBlog(blogForm, multipartFile, request);
		}
		
		return "create-blog";
	}
	
	/**
	 * Controller method for displaying published blogs
	 */
	@RequestMapping(value = "/blog/published", method = RequestMethod.GET)
	public String published(Pageable pageable, Model model, HttpServletRequest request){
		
		String pageTitle = "Published Blogs";
		model.addAttribute("PageTitle", pageTitle);
		
		int startPage = 0;
	    int recordsPerPage = 20;
	    int fromIndex = 0;
		int toIndex = 0;
		
		Page<Blog> publishedBlog = blogService.findPublishedBlog(pageable);
		
		if(request.getParameter("offset")  != null){
			startPage = Integer.parseInt(request.getParameter("offset"));
			fromIndex = Math.abs(startPage - 0);
			toIndex = (recordsPerPage + startPage);
		}else{
			fromIndex = Math.abs(startPage - 0);
			toIndex = (recordsPerPage + startPage);
		}
		
		if(toIndex > publishedBlog.getContent().size()){
			toIndex = publishedBlog.getContent().size();
			model.addAttribute("publishedBlog", publishedBlog.getContent().subList(fromIndex , toIndex));
		}else{
			model.addAttribute("publishedBlog", publishedBlog.getContent().subList(fromIndex , toIndex));
		}
		
		model.addAttribute("count", publishedBlog.getTotalElements() - 10);
		
		model.addAttribute("offset", startPage);
		
		return "published-blog";
	}
	
	/**
	 * Controller method for displaying drafted blogs
	 */
	@RequestMapping(value = "/blog/drafts", method = RequestMethod.GET)
	public String draft(Pageable pageable, Model model, HttpServletRequest request){
		
		String pageTitle = "Drafted Blogs";
		model.addAttribute("PageTitle", pageTitle);
		
		int startPage = 0;
	    int recordsPerPage = 20;
	    int fromIndex = 0;
		int toIndex = 0;
		
		Page<Blog> draftBlog = blogService.findDraftBlog(pageable);
		
		if(request.getParameter("offset")  != null){
			startPage = Integer.parseInt(request.getParameter("offset"));
			fromIndex = Math.abs(startPage - 0);
			toIndex = (recordsPerPage + startPage);
		}else{
			fromIndex = Math.abs(startPage - 0);
			toIndex = (recordsPerPage + startPage);
		}
		
		if(toIndex > draftBlog.getContent().size()){
			toIndex = draftBlog.getContent().size();
			model.addAttribute("draftBlog", draftBlog.getContent().subList(fromIndex , toIndex));
		}else{
			model.addAttribute("draftBlog", draftBlog.getContent().subList(fromIndex , toIndex));
		}
		
		model.addAttribute("count", draftBlog.getTotalElements() - 10);
		
		model.addAttribute("offset", startPage);
		
		return "drafted-blog";
	}
	
	/**
	 * Controller method for getting a blog that is to be edited [GET METHOD]
	 */
	@RequestMapping(value = "/blog/{blogId}/edit", method = RequestMethod.GET)
	public String editBlog(@PathVariable("blogId") long blogId, Model model){
		
		Blog blog = blogService.findOneBlog(blogId);
		BlogForm blogFrom = new BlogForm();
		
		if(blog != null){
			blogFrom.setBlogTitle(blog.getBlogTitle());
			blogFrom.setBlogDate(blog.getBlogDate());
			blogFrom.setBlogText(blog.getBlogText());
		}
		String pageTitle = "Edit Blog";
		model.addAttribute("PageTitle", pageTitle);
		
		model.addAttribute("blogForm", blog);
		
		return "create-blog";
	}
	
	/**
	 * Controller method for editing blog [POST METHOD]
	 */
	@RequestMapping(value = "/blog/{blogId}/edit", method = RequestMethod.POST)
	public String editBlog(@PathVariable("blogId") long blogId, @ModelAttribute("blogForm") @Valid BlogForm blogForm, 
			@RequestParam("file") MultipartFile multipartFile, BindingResult result, HttpServletRequest request){
		
		if (result.hasErrors()){
			return "create-blog";
		}
		
		blogService.updateBlog(blogId, blogForm, multipartFile, request);
		
		return "create-blog";
	}
	
	/**
	 * Controller method for publishing blog
	 */
	@RequestMapping(value = "/blog/{blogId}/publish")
	public String publish(@PathVariable("blogId") long blogId){
		
		blogService.publishBlog(blogId);
		
		return "drafted-blog";
	}
	
	/**
	 * Controller method for displaying all blogs
	 */
	@RequestMapping(value = "/blogs", method = RequestMethod.GET)
	public String allBlogs(Pageable pageable, Model model, HttpServletRequest request){
		
		String pageTitle = "Blog";
		model.addAttribute("PageTitle", pageTitle);
		
		int startPage = 0;
	    int recordsPerPage = 20;
	    int fromIndex = 0;
		int toIndex = 0;
		
		Page<Blog> blogs = blogService.findAllBlogs(pageable);
		
		if(request.getParameter("offset")  != null){
			startPage = Integer.parseInt(request.getParameter("offset"));
			fromIndex = Math.abs(startPage - 0);
			toIndex = (recordsPerPage + startPage);
		}else{
			fromIndex = Math.abs(startPage - 0);
			toIndex = (recordsPerPage + startPage);
		}
		
		if(toIndex > blogs.getContent().size()){
			toIndex = blogs.getContent().size();
			model.addAttribute("blogs", blogs.getContent().subList(fromIndex , toIndex));
		}else{
			model.addAttribute("blogs", blogs.getContent().subList(fromIndex , toIndex));
		}
		
		model.addAttribute("count", blogs.getTotalElements() - 10);
		
		model.addAttribute("offset", startPage);
		
		return "all-blogs";
	}
	
	/**
	 * Controller method for getting a single blog [GET METHOD]
	 */
	@RequestMapping(value = "/blogs/blog/{blogId}", method = RequestMethod.GET)
	public String blog(@PathVariable("blogId") long blogId, Model model){
		
		Blog blog = blogService.findOneBlog(blogId);
		
		String pageTitle = "Blog";
		
		model.addAttribute("PageTitle", pageTitle);
	
		model.addAttribute("blog", blog);
		
		return "one-blog";
	}
	
	/**
	 * Controller method for getting blog post by contributor [GET METHOD]
	 */
	@RequestMapping(value = "/blogs/author/{userName}", method = RequestMethod.GET)
	public String author(@PathVariable("userName") String userName, Pageable pageable, Model model, HttpServletRequest request){
		
		String pageTitle = "Author";
		model.addAttribute("PageTitle", pageTitle);
		
		int startPage = 0;
	    int recordsPerPage = 20;
	    int fromIndex = 0;
		int toIndex = 0;
		
		Page<Blog> author = blogService.findBlogsByAuthor(userName, pageable);
		
		if(request.getParameter("offset")  != null){
			startPage = Integer.parseInt(request.getParameter("offset"));
			fromIndex = Math.abs(startPage - 0);
			toIndex = (recordsPerPage + startPage);
		}else{
			fromIndex = Math.abs(startPage - 0);
			toIndex = (recordsPerPage + startPage);
		}
		
		if(toIndex > author.getContent().size()){
			toIndex = author.getContent().size();
			model.addAttribute("author", author.getContent().subList(fromIndex , toIndex));
		}else{
			model.addAttribute("author", author.getContent().subList(fromIndex , toIndex));
		}
		
		model.addAttribute("count", author.getTotalElements() - 10);
		
		model.addAttribute("offset", startPage);
		
		return "blog-author";
	}
	
}
