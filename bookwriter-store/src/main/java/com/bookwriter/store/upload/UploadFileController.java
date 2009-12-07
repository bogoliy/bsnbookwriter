package com.bookwriter.store.upload;

import java.io.File;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import com.bookwriter.model.Genre;
import com.bookwriter.model.Story;
import com.bookwriter.persist.GenreManager;
import com.bookwriter.persist.StoryManager;
import com.bookwriter.store.tree.StoryTree;
import com.icesoft.faces.component.inputfile.FileInfo;
import com.icesoft.faces.component.inputfile.InputFile;
import com.icesoft.faces.webapp.xmlhttp.PersistentFacesState;
import com.icesoft.faces.webapp.xmlhttp.RenderingException;

public class UploadFileController {
	private FileInfo fileInfo;

	public FileInfo getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}

	private String fileLocation;
	private int percent;
	private PersistentFacesState state;
	private List<FileInfo> files = new ArrayList<FileInfo>();
	private String description;
	private String title;
	private String genres[] = {};
	private String author;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String[] getGenres() {
		return genres;
	}

	public void setGenres(String[] genres) {
		this.genres = genres;
	}

	public List<javax.faces.model.SelectItem> getAvailableGenres() {
		GenreManager genreManager = new GenreManager();
		List<javax.faces.model.SelectItem> selectedItems = new ArrayList<javax.faces.model.SelectItem>();
		for (Genre genre : genreManager.getGenres()) {
			SelectedGenre sGenre = new SelectedGenre(genre.toString());
			sGenre.setGenre(genre);
			sGenre.setValue(genre.getId() + "");
			sGenre.setLabel(genre.toString());
			selectedItems.add(sGenre);
		}
		return selectedItems;
	}

	private int genreId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<FileInfo> getFilesList() {
		/*
		 * String filesList = ""; for (File file : files) { filesList +=
		 * file.getName() + "\n"; } return filesList;
		 */
		return files;
	}

	public UploadFileController() {
		state = PersistentFacesState.getInstance();
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	// Required to bind the InputFile component to its OutputProgress component.
	public void progress(EventObject event) {
		InputFile inputFileComponent = (InputFile) event.getSource();
		percent = inputFileComponent.getFileInfo().getPercent();
		try {
			if (state != null) {
				state.render();
			}
		} catch (RenderingException ee) {
			System.out.println(ee.getMessage());
		}
	}

	/**
	 * <p>
	 * Action event method which is triggered when a user clicks on the upload
	 * file button. Uploaded files are added to a list so that user have the
	 * option to delete them programatically. Any errors that occurs during the
	 * file uploaded are added the messages output.
	 * </p>
	 * 
	 * @param event
	 *            jsf action event.
	 */
	public void uploadFileAction(ActionEvent event) {
		InputFile inputFile = (InputFile) event.getSource();
		fileInfo = inputFile.getFileInfo();

		if (fileInfo.getException() == null)
			synchronized (files) {
				files.add(fileInfo);
			}

	}

	/**
	 * Listens to client input from commandButtons in the UI map and sets the
	 * selected time zone.
	 * 
	 * @param event
	 *            ActionEvent.
	 */
	public void listen(ActionEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		Map requestParams = context.getExternalContext()
				.getRequestParameterMap();
		for (String g : genres) {
			Story story = new Story();
			story.setDescription(description);
			story.setFilename(getFileLocation());
			story.setGenreId(Integer.parseInt(g));
			story.setTitle(title);
			StoryManager storyManager = new StoryManager();
			System.out.println(g + "----");
			story.setUserId(0);
			story.setAuthor(author);
			storyManager.saveStory(story);
		}
		clear();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Object treeObject = facesContext.getApplication().createValueBinding(
				"#{tree}").getValue(facesContext);
		if (treeObject instanceof StoryTree) {
			StoryTree tree = (StoryTree) treeObject;
			tree.refresh();
		}
	}

	public String action() {
		return "success";
	}

	/**
	 * clear props after submit
	 */
	private void clear() {
		this.description = "";
		this.genres = null;
		this.title = "";
		this.fileInfo = null;
		this.fileLocation = "";
		this.files.clear();
	}

	// File sizes used to generate formatted label
	public static final long MEGABYTE_LENGTH_BYTES = 1048000l;
	public static final long KILOBYTE_LENGTH_BYTES = 1024l;

	/**
	 * Method to return the file size as a formatted string For example, 4000
	 * bytes would be returned as 4kb
	 * 
	 *@return formatted file size
	 */
	public String getSizeFormatted() {
		if (fileInfo != null) {
			long ourLength = fileInfo.getSize();

			// Generate formatted label, such as 4kb, instead of just a plain
			// number
			if (ourLength >= MEGABYTE_LENGTH_BYTES) {
				return ourLength / MEGABYTE_LENGTH_BYTES + " MB";
			} else if (ourLength >= KILOBYTE_LENGTH_BYTES) {
				return ourLength / KILOBYTE_LENGTH_BYTES + " KB";
			} else if (ourLength == 0) {
				return "0";
			} else if (ourLength < KILOBYTE_LENGTH_BYTES) {
				return ourLength + " B";
			}

			return Long.toString(ourLength);
		}
		return "";
	}
}
