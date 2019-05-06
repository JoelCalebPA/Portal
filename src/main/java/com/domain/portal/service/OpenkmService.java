package com.domain.portal.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.portal.repository.OpenkmRepository;
import com.openkm.sdk4j.bean.Document;
import com.openkm.sdk4j.exception.AccessDeniedException;
import com.openkm.sdk4j.exception.DatabaseException;
import com.openkm.sdk4j.exception.NoSuchGroupException;
import com.openkm.sdk4j.exception.ParseException;
import com.openkm.sdk4j.exception.PathNotFoundException;
import com.openkm.sdk4j.exception.RepositoryException;
import com.openkm.sdk4j.exception.UnknowException;
import com.openkm.sdk4j.exception.WebserviceException;

@Service("openkmService")
public class OpenkmService {

	@Autowired
	private OpenkmRepository okmRepository;

	public OpenkmService() {
	}

	public List<Document> getDocuments(String user)
			throws AccessDeniedException, IOException, ParseException, PathNotFoundException, RepositoryException,
			DatabaseException, UnknowException, WebserviceException, NoSuchGroupException {
		return okmRepository.getDocuments(user);
	}

}
