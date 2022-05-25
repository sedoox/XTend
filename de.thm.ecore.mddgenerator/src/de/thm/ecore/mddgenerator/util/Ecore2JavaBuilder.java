package de.thm.ecore.mddgenerator.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.LibraryLocation;

import de.thm.ecore.mddgenerator.generator.MDDGenerator;

public class Ecore2JavaBuilder extends IncrementalProjectBuilder {

	public static final String BUILDER_ID = "de.thm.ecore.mddgenerator.util.Ecore2JavaBuilder";
	IProject project;

	private IProgressMonitor progressMonitor;

	protected IProject[] build(int kind, Map<String, String> args,
			IProgressMonitor monitor) throws CoreException {

		System.out.println(BUILDER_ID
				+ " triggered AND CANCELED DUE TO TEST PHASE");

		progressMonitor = new NullProgressMonitor();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		// project = root.getProject(this.getProject().getName()+".Java");
		project = root.getProject(this.getProject().getName());

		if (project.exists()) {
			// IWorkspace workspace = ResourcesPlugin.getWorkspace();
			// workspace.addResourceChangeListener();
			System.out.println(getDelta(project));
			System.out.println(project.getName());
			System.out.println(getDelta(getProject()));
			System.out.println(getProject().getName());
			if (kind == AUTO_BUILD)
				visit(getDelta(getProject()));
		}

		if (!project.exists()) {

			// Create project
			project.create(progressMonitor);
			project.open(progressMonitor);

			// Add Java nature
			IProjectDescription description = project.getDescription();
			description.setNatureIds(new String[] { JavaCore.NATURE_ID });
			project.setDescription(description, null);

			IJavaProject javaProject = JavaCore.create(project);

			// Add java build path
			IFolder binFolder = project.getFolder("bin");
			binFolder.create(false, true, null);
			javaProject.setOutputLocation(binFolder.getFullPath(), null);

			// Classpath entries
			List<IClasspathEntry> entries = new ArrayList<IClasspathEntry>();
			IVMInstall vmInstall = JavaRuntime.getDefaultVMInstall();
			LibraryLocation[] locations = JavaRuntime
					.getLibraryLocations(vmInstall);
			for (LibraryLocation element : locations) {
				entries.add(JavaCore.newLibraryEntry(
						element.getSystemLibraryPath(), null, null));
			}

			// Add libs to project class path
			javaProject.setRawClasspath(
					entries.toArray(new IClasspathEntry[entries.size()]), null);

			// Add source code folder
			IFolder sourceFolder = project.getFolder("src-gen2");
			sourceFolder.create(false, true, null);

			// Classpath entries
			IPackageFragmentRoot FragmentRoot = javaProject
					.getPackageFragmentRoot(sourceFolder);
			IClasspathEntry[] oldEntries = javaProject.getRawClasspath();
			IClasspathEntry[] newEntries = new IClasspathEntry[oldEntries.length + 1];
			System.arraycopy(oldEntries, 0, newEntries, 0, oldEntries.length);
			newEntries[oldEntries.length] = JavaCore
					.newSourceEntry(FragmentRoot.getPath());
			javaProject.setRawClasspath(newEntries, null);

			/*
			 * // Generate a package IPackageFragment pack =
			 * javaProject.getPackageFragmentRoot
			 * (sourceFolder).createPackageFragment("packageName", false, null);
			 * 
			 * // Generate a Java class StringBuffer buffer = new
			 * StringBuffer(); buffer.append("package " + pack.getElementName()
			 * + ";\n"); buffer.append("\n"); buffer.append("Test");
			 * ICompilationUnit cu = pack.createCompilationUnit("Source.java",
			 * buffer.toString(), true, progressMonitor);
			 */
		}

		// get the project to build
		getProject();

		switch (kind) {

		case FULL_BUILD:
			System.out.println("FULL_BUILD");
			break;

		case INCREMENTAL_BUILD:
			System.out.println("INCREMENTAL_BUILD");
			break;

		case AUTO_BUILD:
			System.out.println("AUTO_BUILD");
			break;
		}

		return null;
	}

	public List<IResourceDelta> affecteChildren(IResourceDelta root) {

		List<IResourceDelta> ResourceDeltas = new ArrayList<IResourceDelta>();

		IResourceDelta[] resourceDelta = root.getAffectedChildren();

		for (int i = 0; i < resourceDelta.length; i++) {
			ResourceDeltas.add(resourceDelta[i]);
			ResourceDeltas.addAll(affecteChildren(resourceDelta[i]));
		}
		return ResourceDeltas;

	}

	public boolean visit(IResourceDelta delta) throws CoreException {

		List<IResourceDelta> ResourceDeltas = new ArrayList<IResourceDelta>();
		ResourceDeltas.add(delta);
		ResourceDeltas.addAll(0, affecteChildren(delta));

		Iterator<IResourceDelta> it = ResourceDeltas.iterator();

		while (it.hasNext()) {
			IResourceDelta resourceDelta = it.next();
			IResource resource = resourceDelta.getResource();
			int type = resource.getType();
			if (type == IResource.FILE) {
				switch (delta.getKind()) {
				case IResourceDelta.ADDED:
					break;
				case IResourceDelta.CHANGED:
					if (resource.getFileExtension().equals("ecore")) {
						ResourceSet resourceSet = new ResourceSetImpl();
						final URI uri = URI.createFileURI(resource
								.getRawLocation().toString());
						Resource res = resourceSet.createResource(uri);
						try {
							res.load(Collections.emptyMap());
						} catch (IOException e) {
							e.printStackTrace();
						}
						IFolder sourceFolder = project.getFolder("src-gen2");
						sourceFolder.delete(true, progressMonitor);
						sourceFolder.create(false, true, null);

						MDDGenerator ecore2javagen = new MDDGenerator();
						ecore2javagen.doGenerate(res, project, progressMonitor);
						// EClass e = new EClass();
						// e.getESuperTypes().get(0).getEAllAttributes().c

					}
					/*
					 * EList<EObject> model = ddlResource.getContents();
					 * Iterator<EObject> at = model.iterator(); while
					 * (at.hasNext()){ EObject eObject = at.next(); if (eObject
					 * instanceof EPackage){ IPackageFragment pack =
					 * javaProject.
					 * getPackageFragmentRoot(sourceFolder).createPackageFragment
					 * (((EPackage) eObject).getName(), false, null); } if
					 * (eObject instanceof EClass){
					 * 
					 * } }
					 */
					break;
				case IResourceDelta.REMOVED:
					break;
				}
			}
		}

		return true;
	}
}