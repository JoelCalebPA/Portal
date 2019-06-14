package com.domain.portal.repository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.domain.portal.config.Config;
import com.domain.portal.model.DocumentType;
import com.openkm.sdk4j.OKMWebservices;
import com.openkm.sdk4j.OKMWebservicesFactory;
import com.openkm.sdk4j.bean.Document;
import com.openkm.sdk4j.bean.SqlQueryResultColumns;
import com.openkm.sdk4j.bean.SqlQueryResults;
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
//	private static final String METADATA_BOLETA = "okg:boleta";
//	private static final String METADATA_NAME_FIELD = "okp:boleta.razon.social";

	private String okmUrl;
//	private List<Document> docs;

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

//	private void loopDocs(String folder, OKMWebservices ws) throws AccessDeniedException, PathNotFoundException,
//			RepositoryException, DatabaseException, UnknowException, WebserviceException {
//		for (Folder f : ws.getFolderChildren(folder)) {
//			loopDocs(f.getPath(), ws);
//		}
//		for (Document d : ws.getDocumentChildren(folder)) {
//			docs.add(d);
//		}
//	}

	public List<DocumentType> getDocuments(String user)
			throws AccessDeniedException, PathNotFoundException, RepositoryException, DatabaseException,
			UnknowException, WebserviceException, ParseException, IOException, NoSuchGroupException {
//		docs = new ArrayList<Document>();
		OKMWebservices ws = getOKMWebservices();
		logger.info("getDocuments() { } for user: " + user);
//		loopDocs(configService.OPENKM_FOLDER_BOLETAS, ws);
		List<DocumentType> userDocs = new ArrayList<DocumentType>();
//		for (Document document : docs) {
//			if (ws.hasGroup(document.getUuid(), METADATA_BOLETA)) {
//				for (FormElement fe : ws.getPropertyGroupProperties(document.getUuid(), METADATA_BOLETA)) {
//					if (fe.getName().equals(METADATA_NAME_FIELD)) {
//						Input i = (Input) fe;
//						if (i.getValue().equals(user)) {
//							userDocs.add(document);
//						}
//					}
//				}
//			}
//		}

		String sql = "select nd.nbs_uuid, nbs_name, nd.ndc_mime_type from OKM_NODE_DOCUMENT nd inner join OKM_NODE_BASE nb on nd.nbs_uuid = nb.nbs_uuid inner join OKM_NODE_PROPERTY np on np.npg_node = nd.nbs_uuid where np.npg_value=\""
				+ user + "\" order by nbs_created desc";
		InputStream is = new ByteArrayInputStream(sql.getBytes("UTF-8"));
		SqlQueryResults result = ws.executeSqlQuery(is);

		for (SqlQueryResultColumns row : result.getResults()) {
			DocumentType doc = new DocumentType();
			doc.setUuid(row.getColumns().get(0));
			doc.setName(row.getColumns().get(1));
			doc.setMimeType(row.getColumns().get(2));
			userDocs.add(doc);
		}
		IOUtils.closeQuietly(is);
		
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
