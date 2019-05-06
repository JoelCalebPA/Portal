package com.domain.portal.model;

import com.openkm.sdk4j.bean.Document;

public class DocumentType extends Document {

	private static final long serialVersionUID = 1L;

	private String owner;
	private String downloadUrl;
	private boolean seen;
	private String name;

	public DocumentType() {
		super();
	}

	public DocumentType(String owner, String downloadUrl, boolean seen, String name) {
		super();
		this.owner = owner;
		this.downloadUrl = downloadUrl;
		this.seen = seen;
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	public String getName() {
		name = getPath().substring(getPath().lastIndexOf("/")+1);
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
