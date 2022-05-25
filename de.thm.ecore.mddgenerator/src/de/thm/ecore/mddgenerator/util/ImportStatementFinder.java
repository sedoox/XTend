package de.thm.ecore.mddgenerator.util;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Finds package statements within a String that is supposed to be Java code.
 * 
 * @author Marco Richter
 *
 */
public class ImportStatementFinder {

	/**
	 * This regex finds (almost all) package instructions within the code. For
	 * instance it will find 'myapp.util' in 'myapp.util.Utility'.
	 */
	public static final String PACKAGE_REGEX = "(?<=\\W)([a-z]+[\\w]+[.]{1})+[A-Z]+[\\w]+"; // [a-z_0-9]{1}

	public static void main(String[] args) {
		new ImportStatementFinder().test();
	}

	public String getWithImports(String source) {
		try {
			// the finished source
			String finishedSource = "";
			// source after the first { -> represents the class
			String afterClassDefinition = source.substring(source.indexOf("{"));

			// Set for collecting import statements; we don't need duplicates
			HashSet<String> imports = new HashSet<>();

			// find the package regex
			Queue<XY> queueFoundRegex = new LinkedList<ImportStatementFinder.XY>();

			Pattern p = Pattern.compile(PACKAGE_REGEX);
			Matcher m = p.matcher(afterClassDefinition);
			// add each found position
			while (m.find()) {
				XY _xy = new XY();
				_xy.x = m.start();
				_xy.y = m.end();
				queueFoundRegex.add(_xy);
			}

			// create a new source file without package declaration inside the
			// code
			StringBuffer newSource = new StringBuffer();
			// determines whether the current char is within a String
			boolean withinString = false;
			int index = 0;
			char c = 0;
			XY xy = queueFoundRegex.poll();
			// consume each char...
			while (index < afterClassDefinition.length()) {
				c = afterClassDefinition.charAt(index);
				if (c == '\"') {
					withinString = !withinString;
				}
				if (xy != null) {
					if (index == xy.x) {
						if (withinString == false) {
							// cut the package declaration
							String packageDec = afterClassDefinition.substring(
									xy.x, xy.y);
							imports.add(packageDec);
							int indexOfDot = packageDec.lastIndexOf(".") + 1;
							packageDec = packageDec.substring(indexOfDot);

							// jump to new position
							index += indexOfDot;
							c = afterClassDefinition.charAt(index);
						}
						xy = queueFoundRegex.poll();
					}
				}
				newSource.append(c);
				index++;

			}

			String importStatements = "";
			for (String importString : imports) {
				importStatements += "\nimport " + importString + ";";
			}
			// find the package declaration
			int firstSemi = -1;
			if (source.contains("package ")) {
				firstSemi = source.indexOf(";");
			} else {
				throw new Exception("Missing 'package' statement");
			}
			String packageDeclartion = source.substring(0, firstSemi + 1);
			// add the rest after the package declaration
			finishedSource += packageDeclartion + importStatements
					+ source.substring(firstSemi + 1, source.indexOf("{"))
					+ newSource;

			return finishedSource;
		} catch (Exception e) {
			e.printStackTrace();
			// if exception occurs, return the given source String
			return source;
		}
	}

	private void test() {
		String test = "package blabla; public class MyClass { "
				+ "baseQueryString += \", IN(e.veranstaltungPersons) VeranstaltungPersons\";"
				+ "myapp.util.A;"
				+ "java.util.ArrayList asd = new ArrayList();\n"
				+ "setAttributesAndSingleReferences(entity, personDTO, log); "
				+ "queryString += \" WHERE veranstaltungPersons.Veranstaltung.db_Id = :idVeranstaltungPersons_Veranstaltung\";"
				+ "throw new de.thm.Exception(\"bla\")"
				+ "String log = \"\";"
				+ "\njava.util.List<de.thm.ressourcenplaner.jpa.Veranstaltung> veranstaltungPersons = personDTO.getVeranstaltungPersons();if (veranstaltungPersons != null) { createVeranstaltungPersonsReference(entity, veranstaltungPersons);	}"
				+ "java.util.HashMap map = new java.util.HashMap();\n"
				+ "java.security.SecurityManager s; "
				+ "catch (javax.persistence.Rollbackexception e) {e.printstracktace()} } \n"
				+ "public static void doit(myPackage.util.MyClass c) { \n"
				+ "Status s = c.getStatus();"
				+ "s.doIt();"
				+ "java.util.List<de.thm.ressourcenplaner.VeranstaltungsPerson>> bla;"
				+ "java.lang.Math.pow(2,5)"
				+ "anotherpackage.message.Message = s.getError().getMessage();\n"
				+ "Status.Doit;\n"
				+ "Status.ERROR;\n"
				+ "String testStr = \"de.thm.mnd.Utility u\";"
				+ "}"
				+ "public static IStatus save(de.thm.ressourcenplaner.dto.PersonDTO personDTO, Blabla bla) {}";

		String test2 = "package blabla; public class MyClass {"
				+ "\"de.thm.ressourcenplaner.util.TableUtil\";"
				+ "Util u = de.thm.ressourcenplaner.Util;"
				+ "String s = \"java.util.ArrayList\";" + "java.util.ArrayList";

		String test3 = "package de.thm.util";
		test3 += "\npublic class Hallo {";
		test3 += "public void doit(java.util.ArrayList list) { }";
		System.out.println(test);

		System.out.println("-------------------");
		// for (String s : split) {
		// System.out.println(s);
		// }

		System.out.println(getWithImports(test));
		System.out.println();
		// System.out.println(isWithinQuote(test, test.length(), false, 0));
	}

	public void test2() {
		String t = "package ecore.util;"
				+ "\npublic class Test<V> {"
				+ "\nde.thm.util.Util u=null;"
				+ "\nString s = \"java.util.regex.Matcher;\""
				+ "\nString t = \"mnd.de.thm.Util;\""
				+ "\nString t2 = \"Hallo Welt\" + \" 123 \";"
				+ "\njava.util.ArrayList<String> x = new java.util.ArrayList<String>();"
				+ "\njava.util.HashMap<String, V> h = new java.util.HashMap<>();"
				+ "\nArrayList al = new ArrayList<>();"
				+ "\nPerson p = new Person(\"Marco\");" + "\n}";

		System.out.println(getWithImports(t));

	}

	private class XY {

		public int x;
		public int y;

	}

}
