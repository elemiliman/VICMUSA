package org.vicmusa.church.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;

@Configuration
public class AWSConfiguration {

	@Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;
	
	@Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

	@Bean
	public BasicAWSCredentials basicAWSCredentials() {
		return new BasicAWSCredentials(accessKey, secretKey);
	}
	
	@SuppressWarnings("deprecation")
	@Bean
	public AmazonS3Client amazonS3Client(BasicAWSCredentials awsCredentials) {
		
		AmazonS3Client amazonS3Client = new AmazonS3Client(awsCredentials);

		return amazonS3Client;
	}
}
