package org.vicmusa.church.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.vicmusa.church.entities.Blog;
import org.vicmusa.church.entities.Blog.BlogState;
import org.vicmusa.church.entities.User;

public interface BlogRepository extends JpaRepository<Blog, Long> {

	Page<Blog> findByUserAndPublishedOrderByBlogIdDesc(User user, BlogState state, Pageable pageable);
	
	@Query("SELECT b FROM Blog b WHERE b.blogPublished = 'PUBLISHED' ORDER BY blogDateFormatted DESC")
	Page<Blog> findByBlogPublishedOrderByBlogDateDesc(Pageable pageable);

	@Query("SELECT b FROM Blog b WHERE b.blogPublished = 'PUBLISHED' ORDER BY blogDateFormatted DESC")
	List<Blog> findLast6ByBlogPublishedOrderByBlogDateDesc();

}
