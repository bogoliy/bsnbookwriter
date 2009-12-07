package com.bookwriter.store.upload;

import com.bookwriter.model.Genre;

public class SelectedGenre extends javax.faces.model.SelectItem{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Genre genre = null;

	public SelectedGenre(String string) {
		super(string);
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
}
