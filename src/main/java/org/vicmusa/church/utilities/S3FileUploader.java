package org.vicmusa.church.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.vicmusa.church.dto.GalleryUploadForm;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;

@Component
public class S3FileUploader {

	private AmazonS3Client amazonS3Client;
	
	@Autowired
	S3FileUploader( AmazonS3Client amazonS3Client){
		this.amazonS3Client = amazonS3Client;
	}
	
	public String uploadSingleFile(MultipartFile multipartFile, String bucketName) {
		
		String mediaURL = null;
		
		if (!multipartFile.isEmpty() && multipartFile.getSize() > 0) {
				
				try {
				
					ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 60, TimeUnit.SECONDS,
							new LinkedBlockingQueue<Runnable>(50));
					
					@SuppressWarnings("deprecation")
					TransferManager tx = new TransferManager(amazonS3Client, executor);
					
					InputStream inputStream = multipartFile.getInputStream();
	
					long lengthOfFileUpload = multipartFile.getSize();
	
					String fileName = multipartFile.getOriginalFilename();
	
					ObjectMetadata objectMetadata = new ObjectMetadata();
	
					objectMetadata.setContentLength(lengthOfFileUpload);
	
					PutObjectRequest request = new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata)
							.withCannedAcl(CannedAccessControlList.PublicRead);
	
					request.getMetadata().addUserMetadata("selector", "one");
	
					Upload upload = tx.upload(request);

					upload.waitForCompletion();
					
					if(upload.isDone() == true){
					
						S3Object s3Object = amazonS3Client.getObject(new GetObjectRequest(bucketName,  fileName));
					
						mediaURL = s3Object.getObjectContent().getHttpRequest().getURI().toString();
					}
				}catch (AmazonClientException | InterruptedException | IOException e) {
					e.printStackTrace();
				}

		} // End of File Upload
			
		return mediaURL;
	}
	
	public void uploadMultipleFiles(GalleryUploadForm galleryUploadForm) {

		String bucketName = "vicmgallery";
		
		List<MultipartFile> multipartFile = new ArrayList<MultipartFile>();
		
		multipartFile = galleryUploadForm.getImages();

		if (!multipartFile.isEmpty() && multipartFile.size() > 0) {
			
			for (MultipartFile file : multipartFile) {
				
				try {
					InputStream inputStream =  file.getInputStream();
					
					ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 60, TimeUnit.SECONDS,
				            new LinkedBlockingQueue<Runnable>(50));
					
					@SuppressWarnings("deprecation")
					TransferManager tx = new TransferManager(amazonS3Client, executor);
					
					long lengthOfFileUpload = file.getSize();
					
					String fileName = file.getOriginalFilename();
					
					ObjectMetadata objectMetadata = new ObjectMetadata();
					
					objectMetadata.setContentLength(lengthOfFileUpload);
					
					PutObjectRequest request = new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead);
					
					Upload upload = tx.upload(request);
				
					upload.waitForCompletion();
				} catch (AmazonClientException | InterruptedException | IOException e) {
					e.printStackTrace();
				}
				
			}// End of File Upload Loop

		} // End of File Upload
		
	}
	
	public List<String> getUploadedGallery() {
		String bucketName = "vicmgallery";
		String imageURL = null;
		List<String> pageURL = new ArrayList<String>();
		List<S3ObjectSummary> keyList = null;
		
		if(amazonS3Client.doesBucketExist(bucketName) == true){
			
			ObjectListing current = amazonS3Client.listObjects(bucketName);
			
			keyList = current.getObjectSummaries();
			current = amazonS3Client.listNextBatchOfObjects(current);

			while (current.isTruncated()){
				keyList.addAll(current.getObjectSummaries());
				current = amazonS3Client.listNextBatchOfObjects(current);
			}
			
			keyList.addAll(current.getObjectSummaries());

			for(S3ObjectSummary objects: keyList){
				
				imageURL = "https://" + bucketName + ".s3.amazonaws.com/" + objects.getKey();
				
				pageURL.add(imageURL);
			}
		}
		
		return pageURL;
	}
	

}
