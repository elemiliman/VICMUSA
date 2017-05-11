package org.vicmusa.church.services;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.multipart.MultipartFile;
import org.vicmusa.church.dto.BlogForm;
import org.vicmusa.church.entities.Blog;
import org.vicmusa.church.entities.Blog.BlogState;
import org.vicmusa.church.entities.User;
import org.vicmusa.church.repositories.BlogRepository;
import org.vicmusa.church.repositories.UserRepository;
import org.vicmusa.church.utilities.MyUtility;
import org.vicmusa.church.utilities.S3FileUploader;


@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class BlogServiceImpl implements BlogService {
	
	private static final Log log = LogFactory.getLog(BlogServiceImpl.class);
	
	private UserRepository userRepository;
	private BlogRepository blogRepository;
	private S3FileUploader s3fileUploader;
	
	/**
	 * Constructor based injection
	 */
	@Autowired
	BlogServiceImpl(UserRepository userRepository, BlogRepository blogRepository, S3FileUploader s3fileUploader){
		this.userRepository = userRepository;
		this.blogRepository = blogRepository;
		this.s3fileUploader = s3fileUploader;
	}

	/**
	 * Saves Blog into the database and mark it as draft until it is published by the user.
	 * Also uploads the image associated to the blog into Amazon s3 storage
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void createBlog(BlogForm blogForm,  MultipartFile multipartFile, HttpServletRequest request) {
		
		User loggedIn = MyUtility.getSessionUser();
		User user = userRepository.findByUserName(loggedIn.getUserName());
		Blog blog = new Blog();
		String mediaURL = "";
		Date date = new Date();
		String bucketName = "vicmblogs";
		String strOut = "";
		
		if(!request.getParameter("blogtext").isEmpty()){
			String plainText = request.getParameter("blogtext");
			if(plainText.length() > 300){
				strOut = plainText.substring(0,249) + "...";
			}
		}
		
		if (loggedIn.isAdmin() || loggedIn.isEditor() || loggedIn.isContributor()) {

			blog.setBlogTitle(blogForm.getBlogTitle());
			blog.setBlogDate(blogForm.getBlogDate());
			blog.setBlogDateFormatted(DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(date));
			blog.setBlogText(blogForm.getBlogText());
			
			blog.setBlogPlainText(strOut);
			
			if (!blogForm.getBlogTitle().isEmpty() && !multipartFile.isEmpty() && multipartFile.getSize() > 0) {
				
				log.info("File upload in progress....");
				mediaURL = s3fileUploader.uploadSingleFile(multipartFile, bucketName);
				log.info("File upload finished " + mediaURL);
				blog.setMedia(mediaURL);
			}
			
			blog.getBlogState().add(BlogState.DRAFT);
			blog.setBlogPublished(BlogState.DRAFT.toString());
			user.getBlog().add(blog);
		} else {
			return;
		}
		
		blogRepository.save(blog);
		blog.setUser(user);
		userRepository.save(user);
	}
	
	/**
	 * Searches the database for blogs created by a user that are published
	 */
	@Override
	public Page<Blog> findPublishedBlog(Pageable pageable) {
		
		User loggedIn = MyUtility.getSessionUser();
		User user = userRepository.findByUserName(loggedIn.getUserName());
		
		return blogRepository.findByUserAndPublishedOrderByBlogIdDesc(user, BlogState.PUBLISHED, pageable);
	}
	
	/**
	 * Returns the number of pageable pages
	 */
	@Override
	public Long count() {
		
		return blogRepository.count();
	}
	
	/**
	 * Searches the database for drafted blog created by a user. Blog are saved as draft by default.
	 */
	@Override
	public Page<Blog> findDraftBlog(Pageable pageable) {
	
		User loggedIn = MyUtility.getSessionUser();
		User user = userRepository.findByUserName(loggedIn.getUserName());
		
		return blogRepository.findByUserAndPublishedOrderByBlogIdDesc(user, BlogState.DRAFT, pageable);
	}
	
	/**
	 * Searches the database for a single blog
	 */
	@Override
	public Blog findOneBlog(long blogId) {
		
		Blog blog = blogRepository.findOne(blogId);
		
		return blog;
	}
	
	/**
	 * Searches the database for blog and updates it.
	 *  Also uploads the blog image if not uploaded previously 
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void updateBlog(long blogId, BlogForm blogForm, MultipartFile multipartFile, HttpServletRequest request) {
		
		User loggedIn = MyUtility.getSessionUser();
		Blog blogUpdate = blogRepository.findOne(blogId);
		String mediaURL = null;
		Date date = new Date();
		String bucketName = "vicmblogs";
		
		if(blogUpdate != null && loggedIn.isAdmin() || loggedIn.isEditor() || loggedIn.isContributor()){
			blogUpdate.setBlogTitle(blogForm.getBlogTitle());
			blogUpdate.setBlogDate(blogForm.getBlogDate());
			blogUpdate.setBlogDateFormatted(DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(date));
			blogUpdate.setBlogText(blogForm.getBlogText());
			
			if(!request.getParameter("blogtext").isEmpty()){
				String plainText = request.getParameter("blogtext");
				String strOut = "";
				if(plainText.length() > 300){
					strOut = plainText.substring(0,249) + "...";
				}
				blogUpdate.setBlogPlainText(strOut);
			}
			
			if (!blogForm.getBlogTitle().isEmpty() && !multipartFile.isEmpty() && multipartFile.getSize() > 0) {
				mediaURL = s3fileUploader.uploadSingleFile(multipartFile, bucketName);
				blogUpdate.setMedia(mediaURL);
			}
		}
		
		blogRepository.save(blogUpdate);
	}
	

	/**
	 * Publishes blog that are saved as drafts. Blogs are saved as draft by default.
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void publishBlog(long blogId) {
		
		Blog blogUpdate = blogRepository.findOne(blogId);
		blogUpdate.getBlogState().remove(BlogState.DRAFT);
		blogUpdate.getBlogState().add(BlogState.PUBLISHED);
		blogUpdate.setBlogPublished(BlogState.PUBLISHED.toString());
		
		blogRepository.save(blogUpdate);
	}

	/**
	 * Returns all published blogs saved in the database and Order by date posted
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Blog> findAllBlogs(Pageable pageable) {
		
		return blogRepository.findByBlogPublishedOrderByBlogDateDesc(pageable);
	}

	@Override
	public List<Blog> findLastBlogs() {
		
		return blogRepository.findLast6ByBlogPublishedOrderByBlogDateDesc();
	}

	@Override
	public Page<Blog> findBlogsByAuthor(String userName, Pageable pageable) {
		
		User user = userRepository.findByUserName(userName);
		
		return blogRepository.findByUserAndPublishedOrderByBlogIdDesc(user, BlogState.PUBLISHED, pageable);
	}

	
}
