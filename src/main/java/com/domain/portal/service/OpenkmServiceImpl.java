package com.domain.portal.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.portal.dao.OpenkmDao;
import com.domain.portal.model.DocumentType;
import com.openkm.sdk4j.bean.Document;
import com.openkm.sdk4j.exception.AccessDeniedException;
import com.openkm.sdk4j.exception.DatabaseException;
import com.openkm.sdk4j.exception.PathNotFoundException;
import com.openkm.sdk4j.exception.RepositoryException;
import com.openkm.sdk4j.exception.UnknowException;
import com.openkm.sdk4j.exception.WebserviceException;

@Service
public class OpenkmServiceImpl implements OpenkmService {

	@Autowired
	private OpenkmDao okmDao;

	@Override
	public List<DocumentType> getDocuments(String user)
			throws DatabaseException, UnknowException, WebserviceException, UnsupportedEncodingException {
		return okmDao.getDocuments(user);
	}

	@Override
	public Document getProperties(String node) throws RepositoryException, AccessDeniedException, PathNotFoundException,
			DatabaseException, UnknowException, WebserviceException {
		return okmDao.getProperties(node);
	}

	@Override
	public InputStream getContent(String node) throws RepositoryException, IOException, PathNotFoundException,
			AccessDeniedException, DatabaseException, UnknowException, WebserviceException {
		return okmDao.getContent(node);
	}

	@Override
	public InputStream imageConvert(InputStream is, String fileName, String params, String dstMimeType)
			throws IOException, WebserviceException, UnknowException {
		return okmDao.imageConvert(is, fileName, params, dstMimeType);
	}

}
