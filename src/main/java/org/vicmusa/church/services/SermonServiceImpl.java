package org.vicmusa.church.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.vicmusa.church.dto.SermonForm;
import org.vicmusa.church.entities.Sermon;
import org.vicmusa.church.entities.Sermon.SermonState;
import org.vicmusa.church.entities.User;
import org.vicmusa.church.repositories.SermonRepository;
import org.vicmusa.church.repositories.UserRepository;
import org.vicmusa.church.utilities.MyUtility;
import org.vicmusa.church.utilities.S3FileUploader;


@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class SermonServiceImpl implements SermonService {
	
	private UserRepository userRepository;
	private SermonRepository sermonRepository;
	private S3FileUploader s3fileUploader;
	
	/**
	 * Constructor based injection
	 */
	@Autowired
	SermonServiceImpl(UserRepository userRepository, SermonRepository sermonRepository,
			S3FileUploader s3fileUploader){
		this.userRepository = userRepository;
		this.s3fileUploader = s3fileUploader;
		this.sermonRepository = sermonRepository;
	}

	/**
	 * Saves Sermons into the database and mark it as draft until it is published by the user.
	 * Also uploads the media type of the sermon e.g. picture audio video to Amazon s3
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void createSermon(SermonForm sermonForm, MultipartFile multipartFile) {
		
		User loggedIn = MyUtility.getSessionUser();
		User user = userRepository.findByUserName(loggedIn.getUserName());
		
		Sermon sermon = new Sermon();
		String mediaURL = null;
		SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM d, yyyy");
		Date fromFromat = null;
		String bucketName = "vicmsermons";
		
		if (loggedIn.isAdmin() || loggedIn.isEditor()){
			
			sermon.setTitle(sermonForm.getTitle());
			sermon.setScripture(sermonForm.getScripture());
			sermon.setStudyDate(sermonForm.getStudyDate());
			try {
				fromFromat = new SimpleDateFormat("MM-dd-yyyy").parse(sermonForm.getStudyDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			sermon.setStudyDateFormatted(formatter.format(fromFromat));
			
			sermon.setSermonText(sermonForm.getSermonText());
			
			if (!sermonForm.getTitle().isEmpty() && !multipartFile.isEmpty()  && multipartFile.getSize() > 0){
				mediaURL = s3fileUploader.uploadSingleFile(multipartFile, bucketName);
				sermon.setMedia(mediaURL);
			}
			
			sermon.getSermonState().add(SermonState.DRAFT);
			sermon.setSermonPublished(SermonState.DRAFT.toString());
			user.getSermon().add(sermon);
		}else{
			return;
		}
		
		sermonRepository.save(sermon);
		sermon.setUser(user);
		userRepository.save(user);
	}
	
	/**
	 * Searches the database for sermons created by a user that are published
	 */
	@Override
	public Page<Sermon> findPublishedSermons(Pageable pageable) {
		
		User loggedIn = MyUtility.getSessionUser();
		User user = userRepository.findByUserName(loggedIn.getUserName());
		
		return sermonRepository.findByUserAndPublishedOrderBySermonIdDesc(user, SermonState.PUBLISHED, pageable);
	}
	
	/**
	 * Returns the number of pageable pages
	 */
	@Override
	public Long count() {
		
		return sermonRepository.count();
	}
	
	/**
	 * Searches the database for drafted sermons created by a user. Sermons are saved as draft by default.
	 */
	@Override
	public Page<Sermon> findDraftSermons(Pageable pageable) {
	
		User loggedIn = MyUtility.getSessionUser();
		User user = userRepository.findByUserName(loggedIn.getUserName());
		
		return sermonRepository.findByUserAndPublishedOrderBySermonIdDesc(user, SermonState.DRAFT, pageable);
	}
	
	/**
	 * Searches the database for a single sermon
	 */
	@Override
	public Sermon findOneSermon(long sermonId) {
		
		Sermon sermon = sermonRepository.findOne(sermonId);
		
		return sermon;
	}
	
	/**
	 * Searches the database for sermon and updates it
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void updateSermon(long sermonId, SermonForm sermonForm, MultipartFile multipartFile) {
		
		User loggedIn = MyUtility.getSessionUser();
		Sermon sermonUpdate = sermonRepository.findOne(sermonId);
		SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM d, yyyy");
		Date fromFromat = null;
		
		if (loggedIn.isAdmin() || loggedIn.isEditor()){
	        
			if(sermonUpdate != null){
				sermonUpdate.setTitle(sermonForm.getTitle());
				sermonUpdate.setScripture(sermonForm.getScripture());
				sermonUpdate.setStudyDate(sermonForm.getStudyDate());
				
				try {
					fromFromat = new SimpleDateFormat("MM-dd-yyyy").parse(sermonForm.getStudyDate());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				sermonUpdate.setStudyDateFormatted(formatter.format(fromFromat));
				sermonUpdate.setSermonText(sermonForm.getSermonText());
			}
			
			String mediaURL = null;
			String bucketName = "vicmsermons";
			
			if (!sermonForm.getTitle().isEmpty() && !multipartFile.isEmpty() && multipartFile.getSize() > 0){
				mediaURL = s3fileUploader.uploadSingleFile(multipartFile, bucketName);
				sermonUpdate.setMedia(mediaURL);
			}
		}
		
		sermonRepository.save(sermonUpdate);
	}
	
	/**
	 * Publishes sermons that are save as drafts. Sermons are saved as draft by default.
	 */
	@Override
	public void publishSermon(long sermonId) {
		
		Sermon sermonUpdate = sermonRepository.findOne(sermonId);
		sermonUpdate.getSermonState().remove(SermonState.DRAFT);
		sermonUpdate.getSermonState().add(SermonState.PUBLISHED);
		sermonUpdate.setSermonPublished(SermonState.PUBLISHED.toString());
		
		sermonRepository.save(sermonUpdate);
		
	}
	
	/**
	 * Returns all published sermons saved in the database
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Sermon> findAllSermons(Pageable pageable) {
		
		return sermonRepository.findBySermonPublishedOrderByStudyDateDesc(pageable);
	}
	
	/**
	 * Returns all sermons saved in the database by users (publishers)
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Sermon> findSermonsByTeacher(String userName, Pageable pageable) {
		
		User user = userRepository.findByUserName(userName);
		
		return sermonRepository.findByUserAndPublishedOrderBySermonIdDesc(user, SermonState.PUBLISHED, pageable);
	}
}
