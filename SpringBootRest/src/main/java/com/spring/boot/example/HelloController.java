package com.spring.boot.example;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
public class HelloController {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@RequestMapping("/")
	String hello() {
		return "Hello World!";
	}

	// @GET
	// @RequestMapping("/get")
	// @Produces("application/pdf")
	// public HttpEntity<byte[]> getFile() {
	//
	// ClassLoader classLoader = getClass().getClassLoader();
	// File file = new File(classLoader.getResource("testPDF.pdf").getFile());
	//
	// ResponseBuilder response = Response.ok((Object) file);
	// response.header("Content-Disposition",
	// "attachment; filename=new-android-book.pdf");
	// return response.build();
	//
	// }

	@GET
	@Path("getReceipt")
	@RequestMapping("/get")
	@Produces({ "application/pdf" })
	public HttpEntity<byte[]> getReceipt() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		String path = classLoader.getResource("testPDF.pdf").getPath();
		byte[] array = Files.readAllBytes(new File(path).toPath());

		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"samplePDF.pdf\"");
		header.setContentLength(array.length);

		return new HttpEntity<byte[]>(array, header);
	}

	@GET
	@RequestMapping("/get1")
	@Produces({ "application/pdf" })
	public HttpEntity<byte[]> getReceipt1() throws DocumentException, FileNotFoundException {
		byte[] array = writeUsingIText();

		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"samplePDF.pdf\"");
		header.setContentLength(array.length);

		return new HttpEntity<byte[]>(array, header);
	}

	private byte[] writeUsingIText() throws DocumentException, FileNotFoundException {
		// String FILE_NAME = "itext.pdf";
		Document document = new Document();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, stream);
		// open
		document.open();

		Paragraph p = new Paragraph();
		p.add("This is my paragraph 1");
		p.setAlignment(Element.ALIGN_CENTER);

		document.add(p);

		Paragraph p2 = new Paragraph();
		p2.add("This is my paragraph 2"); // no alignment

		document.add(p2);

		Font f = new Font();
		f.setStyle(Font.BOLD);
		f.setSize(8);

		document.add(new Paragraph("This is my paragraph 3", f));

		// close
		document.close();

		System.out.println("Done");

		// ByteArrayOutputStream stream = new ByteArrayOutputStream();
		// PdfWriter.getInstance(document, stream);
		return stream.toByteArray();
	}
}
