package org.vicmusa.church.services;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.vicmusa.church.dto.OurTeamForm;
import org.vicmusa.church.entities.OurTeam;
import org.vicmusa.church.entities.User;
import org.vicmusa.church.repositories.OurTeamRepository;
import org.vicmusa.church.utilities.MyUtility;
import org.vicmusa.church.utilities.S3FileUploader;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class OurTeamServiceImpl implements OurTeamService{

	private static final Log log = LogFactory.getLog(OurTeamServiceImpl.class);
			
	private OurTeamRepository ourTeamRepository;
	private S3FileUploader s3fileUploader;
	
	/**
	 * Constructor based injection
	 */
	@Autowired
	OurTeamServiceImpl(OurTeamRepository ourTeamRepository, S3FileUploader s3fileUploader){
		this.ourTeamRepository = ourTeamRepository;
		this.s3fileUploader = s3fileUploader;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void createTeam(OurTeamForm ourTeamForm, MultipartFile multipartFile) {
		
		User loggedIn = MyUtility.getSessionUser();
		OurTeam ourTeam = new OurTeam();
		String mediaURL = "";
		String bucketName = "vicmutil";
		
		if (loggedIn.isAdmin() || loggedIn.isEditor()) {
			
			ourTeam.setFullName(ourTeamForm.getFullName());
			ourTeam.setDesignation(ourTeamForm.getDesignation());
			ourTeam.setFaceBookProfile(ourTeamForm.getFaceBookProfile());
			ourTeam.setTwitterProfile(ourTeamForm.getTwitterProfile());
			ourTeam.setLinkedInProfile(ourTeamForm.getLinkedInProfile());
			
			if (!ourTeamForm.getFullName().isEmpty() && !multipartFile.isEmpty() && multipartFile.getSize() > 0) {
				
				log.info("File upload in progress....");
				mediaURL = s3fileUploader.uploadSingleFile(multipartFile, bucketName);
				log.info("File upload finished " + mediaURL);
				ourTeam.setProfilePicture(mediaURL);
			}
		}
		
		ourTeamRepository.save(ourTeam);
	}

	@Override
	public List<OurTeam> getTeamList() {
		
		return ourTeamRepository.findFirst10ByOrderByOurTeamId();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void updateTeamMember(long ourTeamId, OurTeamForm ourTeamForm, MultipartFile multipartFile) {
		
		User loggedIn = MyUtility.getSessionUser();
		OurTeam ourTeamUpdate = ourTeamRepository.findOne(ourTeamId);
		String mediaURL = "";
		String bucketName = "vicmutil";
		
		if (ourTeamUpdate != null && loggedIn.isAdmin() || loggedIn.isEditor()) {
			
			ourTeamUpdate.setFullName(ourTeamForm.getFullName());
			ourTeamUpdate.setDesignation(ourTeamForm.getDesignation());
			ourTeamUpdate.setFaceBookProfile(ourTeamForm.getFaceBookProfile());
			ourTeamUpdate.setTwitterProfile(ourTeamForm.getTwitterProfile());
			ourTeamUpdate.setLinkedInProfile(ourTeamForm.getLinkedInProfile());
			
			if (!ourTeamForm.getFullName().isEmpty() && !multipartFile.isEmpty() && multipartFile.getSize() > 0) {
				
				log.info("File upload in progress....");
				mediaURL = s3fileUploader.uploadSingleFile(multipartFile, bucketName);
				log.info("File upload finished " + mediaURL);
				ourTeamUpdate.setProfilePicture(mediaURL);
			}
		}
		
		ourTeamRepository.save(ourTeamUpdate);
		
	}

	@Override
	public OurTeam findOneTeamMember(long ourTeamId) {
		
		return ourTeamRepository.findOne(ourTeamId);
	}

}
