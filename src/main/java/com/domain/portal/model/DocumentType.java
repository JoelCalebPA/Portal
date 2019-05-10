package com.domain.portal.model;

import com.openkm.sdk4j.bean.Document;

public class DocumentType extends Document {

	private static final long serialVersionUID = 1L;

	private String downloadUrl;
	private String name;

	public DocumentType() {
		super();
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
