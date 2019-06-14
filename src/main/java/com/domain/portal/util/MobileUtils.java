package com.domain.portal.util;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

public class MobileUtils {

	public static final void convertPdfToImage(InputStream is, String docName, ServletOutputStream os) {
		try {
			PDDocument document = PDDocument.load(is);
			PDFRenderer render = new PDFRenderer(document);
			BufferedImage image = render.renderImageWithDPI(0, 300, ImageType.RGB);
			ImageIO.write(image, "png", os);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
