package com.bookwriter.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


@Entity
@Table (name="BOOK")
public class Book {
	@Id
	@Column(name="bo_id")
	private int bookId;
	@Column(name = "bo_title")
	private String title;
	@Column(name = "bo_about")
	private String about;
	@Column(name = "bo_author")
	private String author;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@OneToMany(mappedBy = "bookId")
	//@OrderBy("datetime")
	private List<Part> parts;

	public List<Part> getParts() {
		return parts;
	}
	

}
