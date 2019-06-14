package com.domain.portal.web;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.domain.portal.service.OpenkmService;
import com.domain.portal.util.PathUtils;
import com.domain.portal.util.WebUtils;
import com.openkm.sdk4j.bean.Document;
import com.openkm.sdk4j.exception.AccessDeniedException;
import com.openkm.sdk4j.exception.DatabaseException;
import com.openkm.sdk4j.exception.PathNotFoundException;
import com.openkm.sdk4j.exception.RepositoryException;
import com.openkm.sdk4j.exception.UnknowException;
import com.openkm.sdk4j.exception.WebserviceException;

@RequestMapping("/mobile")
public class MobileController {

	private static Logger log = LoggerFactory.getLogger(MobileController.class);

	@Autowired
	private OpenkmService okmService;

	@GetMapping("/Download")
	public void download(HttpServletRequest request, HttpServletResponse response)
			throws RepositoryException, AccessDeniedException, PathNotFoundException, DatabaseException,
			UnknowException, WebserviceException, IOException {
		log.debug("mobileDownload({}, {})", request, response);
		String node = request.getParameter("node");
		boolean inline = true;

		InputStream is = okmService.getContent(node);
		Document doc = okmService.getProperties(node);
		WebUtils.prepareSendFile(request, response, PathUtils.getName(doc.getPath()), doc.getMimeType(), inline);
		response.setContentLength(new Long(doc.getActualVersion().getSize()).intValue());

		ServletOutputStream sos = response.getOutputStream();
		IOUtils.copy(is, sos);
		sos.flush();
		sos.close();
	}

}
