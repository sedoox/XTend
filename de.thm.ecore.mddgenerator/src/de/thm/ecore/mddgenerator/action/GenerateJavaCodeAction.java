package de.thm.ecore.mddgenerator.action;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import de.thm.ecore.mddgenerator.generator.MDDGenerator;

/**
 * 
 * @author Marco Richter
 * 
 */
public class GenerateJavaCodeAction implements IObjectActionDelegate {

	private IStructuredSelection selection;

	public GenerateJavaCodeAction() {
	}

	@Override
	public void run(IAction action) {
		if (selection == null) {
			return;
		}
		// get the selected ecore file
		IFile fileEcore = (IFile) selection.getFirstElement();

		IProject project = fileEcore.getProject();

		ResourceSet resourceSet = new ResourceSetImpl();
		// load the ecore file
		URI uriEcoreFile = URI.createFileURI(fileEcore.getRawLocation()
				.toString());
		Resource resEcoreFile = resourceSet.createResource(uriEcoreFile);
		try {
			resEcoreFile.load(Collections.emptyMap());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// start generation
		System.out.println("Generating Java code now.");
		GenerateJob job = new GenerateJob("Generating Java code", resEcoreFile,
				project);
		job.setUser(true);
		job.schedule();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			this.selection = (IStructuredSelection) selection;
		}
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	private class GenerateJob extends Job implements IJobChangeListener {

		long jobBegin;
		Resource resEcoreFile;
		IProject project;

		public GenerateJob(String name, Resource resEcoreFile, IProject project) {
			super(name);
			this.resEcoreFile = resEcoreFile;
			this.project = project;
			addJobChangeListener(this);
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {
			// start the generation
			jobBegin = System.currentTimeMillis();
			MDDGenerator generator = new MDDGenerator();
			generator.doGenerate(resEcoreFile, project, monitor);
			return Status.OK_STATUS;
		}

		@Override
		public void aboutToRun(IJobChangeEvent event) {
		}

		@Override
		public void awake(IJobChangeEvent event) {
		}

		@Override
		public void done(IJobChangeEvent event) {
			long jobEnd = System.currentTimeMillis();
			if (event.getResult().isOK()) {
				System.out.println("Generating Java code has finished (took "
						+ (jobEnd - jobBegin) + " ms).");
			} else if (event.getResult().matches(Status.CANCEL)) {
				System.out
						.println("Generating Java code has been cancelled (took "
								+ (jobEnd - jobBegin) + " ms).");
			}
		}

		@Override
		public void running(IJobChangeEvent event) {
		}

		@Override
		public void scheduled(IJobChangeEvent event) {
		}

		@Override
		public void sleeping(IJobChangeEvent event) {
		}

	}

}
