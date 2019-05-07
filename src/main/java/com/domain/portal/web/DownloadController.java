package com.domain.portal.web;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.domain.portal.service.OpenkmService;
import com.domain.portal.util.PathUtils;
import com.domain.portal.util.WebUtils;
import com.openkm.sdk4j.bean.Document;
import com.openkm.sdk4j.exception.AccessDeniedException;
import com.openkm.sdk4j.exception.ConversionException;
import com.openkm.sdk4j.exception.DatabaseException;
import com.openkm.sdk4j.exception.PathNotFoundException;
import com.openkm.sdk4j.exception.RepositoryException;
import com.openkm.sdk4j.exception.UnknowException;
import com.openkm.sdk4j.exception.WebserviceException;

@Controller
@RequestMapping("/Download")
public class DownloadController {

	private static Logger log = LoggerFactory.getLogger(DownloadController.class);

	@Autowired
	private OpenkmService catalogService;

	@RequestMapping(method = RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response)
			throws IOException, RepositoryException, PathNotFoundException, AccessDeniedException, DatabaseException,
			UnknowException, WebserviceException {
		log.debug("download({}, {})", request, response);
		String node = request.getParameter("node");
		boolean inline = true;

		if (request.getParameter("attachment") != null && !request.getParameter("attachment").isEmpty()) {
			inline = false;
		}

		InputStream is = catalogService.getContent(node);
		Document doc = catalogService.getProperties(node);
		WebUtils.prepareSendFile(request, response, PathUtils.getName(doc.getPath()), doc.getMimeType(), inline);

		// Set length
		// response.setContentLength(is.available()); // Cause a bug, because at this
		// point InputStream still has not its real size.
		response.setContentLength(new Long(doc.getActualVersion().getSize()).intValue());

		ServletOutputStream sos = response.getOutputStream();
		IOUtils.copy(is, sos);
		sos.flush();
		sos.close();
	}

	@RequestMapping(value = "/downloadForPreview", method = RequestMethod.GET)
	public void downloadForPreview(HttpServletRequest request, HttpServletResponse response)
			throws IOException, RepositoryException, PathNotFoundException, AccessDeniedException, DatabaseException,
			UnknowException, WebserviceException, NotImplementedException, ConversionException {
		log.debug("downloadForPreview({}, {})", request, response);
		String node = request.getParameter("node");
		String jSessionId = WebUtils.getJSessionIdFromUrl(request.getRequestURI());

		if (jSessionId != null && jSessionId.equalsIgnoreCase(request.getSession().getId())) {
			boolean inline = true;
			if (request.getParameter("attachment") != null && !request.getParameter("attachment").isEmpty()) {
				inline = false;
			}

			Document doc = catalogService.getProperties(node);
			String fileName = PathUtils.getName(doc.getPath());

			InputStream is;
			is = catalogService.getContent(node);

			WebUtils.prepareSendFile(request, response, fileName, doc.getMimeType(), inline);

			response.setContentLength(new Long(doc.getActualVersion().getSize()).intValue());

			ServletOutputStream sos = response.getOutputStream();
			IOUtils.copy(is, sos);
			sos.flush();
			sos.close();
		}
	}
}