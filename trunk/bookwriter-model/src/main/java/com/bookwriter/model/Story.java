package com.bookwriter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "STORY")
public class Story {
	@Id
	@GeneratedValue
	@Column(name = "st_id")
	private int id;
	@Column(name = "st_title")
	private String title;
	@Column(name = "st_descr")
	private String description;
	@Column(name = "st_text")
	private String text;
	@Column(name = "st_filename")
	private String filename;
	@Column(name = "ge_id")
	public int genreId;
	@Column(name = "us_id")
	public int userId;
	@Column(name = "st_author")
	public String author;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	@ManyToOne
	@JoinColumn(name = "us_id", updatable = false, insertable = false)
	private User user;

	public User getUser() {
		return user;
	}

	@ManyToOne
	@JoinColumn(name = "ge_id", updatable = false, insertable = false)
	private Genre genre;

	public Genre getGenre() {
		return genre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

}
