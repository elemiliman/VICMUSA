package org.vicmusa.church.services;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import org.vicmusa.church.dto.BlogForm;
import org.vicmusa.church.entities.Blog;

public interface BlogService {

	public abstract void createBlog(BlogForm blogForm, MultipartFile multipartFile, HttpServletRequest request);

	public abstract Page<Blog> findPublishedBlog(Pageable pageable);

	public abstract Long count();

	public abstract Page<Blog> findDraftBlog(Pageable pageable);
	
	public abstract Blog findOneBlog(long id);

	public abstract void updateBlog(long blogId, BlogForm blogForm, MultipartFile multipartFile, HttpServletRequest request);

	public abstract void publishBlog(long blogId);

	public abstract Page<Blog> findAllBlogs(Pageable pageable);

	public abstract List<Blog> findLastBlogs();

	public abstract Page<Blog> findBlogsByAuthor(String userName, Pageable pageable);


}
