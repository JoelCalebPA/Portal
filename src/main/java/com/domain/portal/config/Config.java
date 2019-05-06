package com.domain.portal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:application.properties")
public class Config {

	@Value(value = "${openkm.url}")
	public String OPENKM_URL;
	
	@Value(value = "${openkm.folder.boletas}")
	public String OPENKM_FOLDER_BOLETAS;
	
	@Value(value = "${openkm.manager.user}")
	public String OPENKM_USER;
	
	@Value(value = "${openkm.manager.password}")
	public String OPENKM_PASSWORD;
		
	@Value(value = "${openkm.preview.download.url}")
	public String OPENKM_PREVIEW_DOWNLOAD_URL;
	

	public String getPreviewKcenterToOpenKMUrl() {
		String baseUrl = OPENKM_URL;
		if (!OPENKM_URL.endsWith("/")) {
			baseUrl += "/";
		}
		return baseUrl + "Preview";
	}
	
}
