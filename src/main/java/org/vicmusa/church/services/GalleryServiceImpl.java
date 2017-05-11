package org.vicmusa.church.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vicmusa.church.dto.GalleryUploadForm;
import org.vicmusa.church.utilities.S3FileUploader;

@Service
public class GalleryServiceImpl implements GalleryService {

	private S3FileUploader s3fileUploader;
	
	/**
	 * Constructor based injection
	 */
	@Autowired
	GalleryServiceImpl(S3FileUploader s3fileUploader){
		this.s3fileUploader = s3fileUploader;
	}
	
	@Override
	public void uploadGallery(GalleryUploadForm galleryUploadForm) {

		s3fileUploader.uploadMultipleFiles(galleryUploadForm);
	
	}

	@Override
	public List<String> getPhotoGallery() {
		
		 return s3fileUploader.getUploadedGallery();
	}
	

}
