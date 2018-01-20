package com.anonymous;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.resource.XMLResource;

import com.lowagie.text.DocumentException;

public class PDFMaker {
	public static void makePDF(String Html,int id) {
		Document document = XMLResource.load(new ByteArrayInputStream(Html.getBytes()))
				.getDocument();

		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocument(document, null);

		renderer.layout();

		String fileNameWithPath = "..\\..\\media" +id+ ".pdf";
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fileNameWithPath);
			renderer.createPDF(fos);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("File 1: '" + fileNameWithPath + "' created.");
	}
}
