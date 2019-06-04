package com.domain.portal.dao;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.openkm.sdk4j.exception.PathNotFoundException;
import com.openkm.sdk4j.exception.RepositoryException;
import com.openkm.sdk4j.exception.UnknowException;
import com.openkm.sdk4j.exception.WebserviceException;

@Repository
public class OpenkmDaoImpl implements OpenkmDao {

	private static final Logger log = LoggerFactory.getLogger(OpenkmDaoImpl.class);

	@Autowired
	private Config configService;

	@Override
	public OKMWebservices getOKMWebServices() {
		OKMWebservices ws = null;
		log.info("Creating OKMWebservice instance");
		ws = OKMWebservicesFactory.newInstance(configService.OPENKM_URL, configService.OPENKM_USER,
				configService.OPENKM_PASSWORD);
		log.info("OKMWebservice instance created: " + ws.toString());
		return ws;
	}

	@Override
	public List<DocumentType> getDocuments(String user)
			throws DatabaseException, UnknowException, WebserviceException, UnsupportedEncodingException {
		OKMWebservices ws = getOKMWebServices();
		log.info("getDocuments() { } for user: " + user);
		List<DocumentType> userDocs = new ArrayList<DocumentType>();
		String sql = "select nd.nbs_uuid, nbs_name, nd.ndc_mime_type from OKM_NODE_DOCUMENT nd inner join OKM_NODE_BASE nb on nd.NBS_UUID = nb.NBS_UUID inner join OKM_NODE_PROPERTY np on np.NPG_NODE = nd.NBS_UUID where np.NPG_VALUE=\""
				+ user + "\" and nb.NBS_CONTEXT=\"okm_root\" order by nb.NBS_CREATED desc";
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

		log.info("Returning " + userDocs.size() + " docs for user: " + user);
		return userDocs;
	}

	@Override
	public Document getProperties(String node) throws RepositoryException, AccessDeniedException, PathNotFoundException,
			DatabaseException, UnknowException, WebserviceException {
		log.debug("getProperty({})", node);
		OKMWebservices ws = getOKMWebServices();
		return ws.getDocumentProperties(node);
	}

	@Override
	public InputStream getContent(String node) throws RepositoryException, IOException, PathNotFoundException,
			AccessDeniedException, DatabaseException, UnknowException, WebserviceException {
		log.debug("getContent({})", node);
		OKMWebservices ws = getOKMWebServices();
		return ws.getContent(node);
	}

}
