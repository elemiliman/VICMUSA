package org.vicmusa.church.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Credentials {

	@Value("${accessKey}")
    private String accessKey;
	
	@Value("${secreteAccessKey}")
    private String secreteAccessKey;

	public String getAccessKey() {
		return accessKey;
	}

	public String getSecreteAccessKey() {
		return secreteAccessKey;
	}
}
