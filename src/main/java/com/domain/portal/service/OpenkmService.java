package com.domain.portal.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.domain.portal.model.DocumentType;
import com.openkm.sdk4j.bean.Document;
import com.openkm.sdk4j.exception.AccessDeniedException;
import com.openkm.sdk4j.exception.DatabaseException;
import com.openkm.sdk4j.exception.PathNotFoundException;
import com.openkm.sdk4j.exception.RepositoryException;
import com.openkm.sdk4j.exception.UnknowException;
import com.openkm.sdk4j.exception.WebserviceException;

public interface OpenkmService {

	List<DocumentType> getDocuments(String user)
			throws DatabaseException, UnknowException, WebserviceException, UnsupportedEncodingException;

	Document getProperties(String node) throws RepositoryException, AccessDeniedException, PathNotFoundException,
			DatabaseException, UnknowException, WebserviceException;

	InputStream getContent(String node) throws RepositoryException, IOException, PathNotFoundException,
			AccessDeniedException, DatabaseException, UnknowException, WebserviceException;

	InputStream imageConvert(InputStream is, String fileName, String params, String dstMimeType)
			throws IOException, WebserviceException, UnknowException;

}
