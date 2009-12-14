package com.bookwriter.model;

/**
 * Part bean.
 * @author Alex Bogoley
 *
 */
import java.text.StringCharacterIterator;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "PART")
public class Part {
	@Id
	@GeneratedValue
	@Column(name = "pa_id")
	private int partId;
	@Column(name = "bo_id")
	private int bookId;
	@Column(name = "pa_datetime")
	private Date datetime;
	@Column(name = "pa_text")
	private String text;
	@Column(name = "us_id")
	private int userId;
	@Column(name = "pa_keywords")
	public String keywords;
	
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}


	public int getPartId() {
		return partId;
	}

	public void setPartId(int partId) {
		this.partId = partId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@ManyToOne
	@JoinColumn(name = "bo_id", updatable = false, insertable = false)
	private Book book;

	public Book getBook() {
		return book;
	}

	@ManyToOne
	@JoinColumn(name = "us_id", updatable = false, insertable = false)
	private User user;

	public User getUser() {
		return user;
	}
	
	public String getShorttext() {
		return text.substring(0, 1000) + "...";
	}
}
