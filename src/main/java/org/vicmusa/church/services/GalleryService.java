package org.vicmusa.church.services;

import java.util.List;

import org.vicmusa.church.dto.GalleryUploadForm;

public interface GalleryService {

	public abstract void uploadGallery(GalleryUploadForm galleryUploadForm);

	public abstract List<String> getPhotoGallery();

	
}
