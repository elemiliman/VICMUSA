package org.vicmusa.church.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import org.vicmusa.church.dto.SermonForm;
import org.vicmusa.church.entities.Sermon;

public interface SermonService {

	public abstract void createSermon(SermonForm sermonForm, MultipartFile file);

	public abstract Page<Sermon> findPublishedSermons(Pageable pageable);

	public abstract Long count();

	public abstract Page<Sermon> findDraftSermons(Pageable pageable);
	
	public abstract Sermon findOneSermon(long id);

	public abstract void updateSermon(long sermonId, SermonForm sermonForm, MultipartFile file);

	public abstract void publishSermon(long sermonId);

	public abstract Page<Sermon> findAllSermons(Pageable pageable);

	public abstract Page<Sermon> findSermonsByTeacher(String userName, Pageable pageable);

}
