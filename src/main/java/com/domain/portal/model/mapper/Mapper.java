package com.domain.portal.model.mapper;

import java.util.ArrayList;
import java.util.List;

import com.domain.portal.model.DocumentType;
import com.domain.portal.util.PathUtils;
import com.openkm.sdk4j.bean.Document;

public class Mapper {

	public static DocumentType docToDocType(Document doc, String downloadUrl) {
		DocumentType dt = new DocumentType();
		dt.setPath(doc.getPath());
		dt.setActualVersion(doc.getActualVersion());
		dt.setCreated(doc.getCreated());
		dt.setUuid(doc.getUuid());
		dt.setCategories(doc.getCategories());
		dt.setMimeType(doc.getMimeType());
		dt.setLastModified(doc.getLastModified());
		dt.setName(PathUtils.getNameWithoutExtension(dt.getActualVersion().getName()));
		dt.setDownloadUrl(downloadUrl);
		return dt;
	}

	public static List<DocumentType> docToListDocType(List<Document> docs) {
		List<DocumentType> dts = new ArrayList<DocumentType>();
		for (Document doc : docs) {
			dts.add(docToDocType(doc, ""));
		}
		return dts;
	}

}
