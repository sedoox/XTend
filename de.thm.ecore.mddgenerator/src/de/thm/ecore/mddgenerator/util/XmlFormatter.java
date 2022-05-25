package de.thm.ecore.mddgenerator.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Pretty-formats xml, supplied as a string.
 * <p/>
 * eg. <code>
 * String formattedXml = new XmlFormatter().format("<tag><nested>hello</nested></tag>");
 * </code>
 */
public class XmlFormatter {

	/**
	 * Formats the given XML-String.
	 * 
	 * @param xml
	 * @return the formatted XML-String or the origin String, if an exception
	 *         occurred.
	 */
	public static String format(String xml) {
		try {
			Source xmlInput = new StreamSource(new StringReader(xml));
			StringWriter stringWriter = new StringWriter();
			StreamResult xmlOutput = new StreamResult(stringWriter);
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			transformerFactory.setAttribute("indent-number", 2);
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(xmlInput, xmlOutput);
			return xmlOutput.getWriter().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return xml;
		}
	}

}