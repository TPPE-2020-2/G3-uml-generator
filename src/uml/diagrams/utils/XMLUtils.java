package uml.diagrams.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class XMLUtils {

	public static void generateXML(String source, String pathName) 
			throws 
			ParserConfigurationException, 
			UnsupportedEncodingException, 
			SAXException, 
			IOException, 
			TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		
		DOMSource domSource; 


		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
		DocumentBuilder builder;
		
	    builder = factory.newDocumentBuilder();  
	    Document document = builder.parse(new ByteArrayInputStream(source.getBytes("UTF-8")));
	    domSource = new DOMSource(document);
	    
	    FileWriter writer = new FileWriter(new File(pathName));
	    StreamResult result = new StreamResult(writer);
	    
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

	    transformer.transform(domSource, result);
	}
}
