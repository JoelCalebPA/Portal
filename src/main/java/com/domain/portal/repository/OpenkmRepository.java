package com.domain.portal.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.domain.portal.config.Config;
import com.openkm.sdk4j.OKMWebservices;
import com.openkm.sdk4j.OKMWebservicesFactory;
import com.openkm.sdk4j.bean.Configuration;
import com.openkm.sdk4j.bean.Document;
import com.openkm.sdk4j.bean.Folder;
import com.openkm.sdk4j.bean.form.FormElement;
import com.openkm.sdk4j.bean.form.Input;
import com.openkm.sdk4j.exception.AccessDeniedException;
import com.openkm.sdk4j.exception.DatabaseException;
import com.openkm.sdk4j.exception.NoSuchGroupException;
import com.openkm.sdk4j.exception.ParseException;
import com.openkm.sdk4j.exception.PathNotFoundException;
import com.openkm.sdk4j.exception.RepositoryException;
import com.openkm.sdk4j.exception.UnknowException;
import com.openkm.sdk4j.exception.WebserviceException;

@Repository("openkmRepository")
public class OpenkmRepository {

	private static final Logger logger = LoggerFactory.getLogger(OpenkmRepository.class);
	private static final String METADATA_BOLETA = "okg:boleta";
	private static final String METADATA_NAME_FIELD = "okp:boleta.razon.social";

	private String okmUrl;
	private List<Document> docs;

	@Autowired
	private Config configService;

	@Autowired
	public OpenkmRepository(@Value("${openkm.url}") String okmUrl) {
		this.okmUrl = okmUrl;
		logger.info("Creating @Repository OpenkmRepository with url: " + this.okmUrl);
	}

	public OKMWebservices getOKMWebservices() {
		OKMWebservices ws = null;
		logger.info("Creating OKMWebservice instance");
		ws = OKMWebservicesFactory.newInstance(okmUrl, configService.OPENKM_USER, configService.OPENKM_PASSWORD);
		logger.info("OKMWebservice instance created: " + ws.toString());
		return ws;
	}

	private void loopDocs(String folder, OKMWebservices ws) throws AccessDeniedException, PathNotFoundException,
			RepositoryException, DatabaseException, UnknowException, WebserviceException {
		for (Folder f : ws.getFolderChildren(folder)) {
			loopDocs(f.getPath(), ws);
		}
		for (Document d : ws.getDocumentChildren(folder)) {
			docs.add(d);
		}
	}

	public List<Document> getDocuments(String user)
			throws AccessDeniedException, PathNotFoundException, RepositoryException, DatabaseException,
			UnknowException, WebserviceException, ParseException, IOException, NoSuchGroupException {
		docs = new ArrayList<Document>();
		OKMWebservices ws = getOKMWebservices();
		logger.info("getDocuments() { } for user: " + user);
		logger.debug("Looping through docs");
		loopDocs(configService.OPENKM_FOLDER_BOLETAS, ws);
		List<Document> userDocs = new ArrayList<Document>();
		for (Document document : docs) {
			if (ws.hasGroup(document.getUuid(), METADATA_BOLETA)) {
				logger.debug("Metadata " + METADATA_BOLETA + " found");
				for (FormElement fe : ws.getPropertyGroupProperties(document.getUuid(), METADATA_BOLETA)) {
					if (fe.getName().equals(METADATA_NAME_FIELD)) {
						Input i = (Input) fe;
						logger.debug("Metadata property " + METADATA_NAME_FIELD + " value = " + i.getValue());
						if (i.getValue().equals(user)) {
							userDocs.add(document);
						}
					}
				}
			}
		}
		logger.info("Returning " + userDocs.size() + " docs for user: " + user);
		return userDocs;
	}

	public Document getProperties(String node) throws RepositoryException, AccessDeniedException, PathNotFoundException,
			DatabaseException, UnknowException, WebserviceException {
		logger.debug("getProperty({})", node);
		OKMWebservices ws = getOKMWebservices();
		return ws.getDocumentProperties(node);
	}

	public InputStream getContent(String node) throws RepositoryException, IOException, PathNotFoundException,
			AccessDeniedException, DatabaseException, UnknowException, WebserviceException {
		logger.debug("getContent({})", node);
		OKMWebservices ws = getOKMWebservices();
		return ws.getContent(node);
	}

}
