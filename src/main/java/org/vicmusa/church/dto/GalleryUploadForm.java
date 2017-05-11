package org.vicmusa.church.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;
import org.vicmusa.church.entities.User;

public class GalleryUploadForm implements Serializable {

	private static final long serialVersionUID = 410670L;
	
	@NotNull
    @Size(min=1, max=User.IMG_DESCRIPTION, message="{imageDescriptionSizeError}")
    private String imageDescription;
 
    private List<MultipartFile> images;

	public String getImageDescription() {
		return imageDescription;
	}

	public void setImageDescription(String imageDescription) {
		this.imageDescription = imageDescription;
	}

	public List<MultipartFile> getImages() {
		return images;
	}

	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}
    
    
	
}
