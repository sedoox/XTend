package de.thm.ecore.mddgenerator.util;

import java.util.Hashtable;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.TextEdit;

public class JavaFormatter {

	public static String option_comment_line_length = "org.eclipse.jdt.core.formatter.comment.line_length";
	public static String option_formatter_lineSplit = "org.eclipse.jdt.core.formatter.lineSplit";

	/**
	 * Formats the code.
	 * 
	 * @param code
	 * @return the formatted code, or {@code null} if the given source code
	 *         could not be formatted.
	 */
	public static String format(String code) {
		Hashtable<String, String> options = JavaCore.getOptions();
		options.put(option_comment_line_length, "120");
		options.put(option_formatter_lineSplit, "120");
		CodeFormatter codeFormatter = ToolFactory.createCodeFormatter(options);

		TextEdit textEdit = codeFormatter.format(
				CodeFormatter.K_COMPILATION_UNIT, code, 0, code.length(), 0,
				null);
		IDocument doc = new Document(code);
		try {
			textEdit.apply(doc);
			return doc.get();
		} catch (Exception e) {
			return null;
		}
	}

}
