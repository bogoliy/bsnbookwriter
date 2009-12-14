package com.bookwriter.model;
/**
 * Genre bean
 * @author Alex Bogoley
 */
import javax.persistence.*;
@Entity
@Table(name = "GENRE")
public class Genre {
	@Id
	@GeneratedValue
	@Column(name = "ge_id")
	private int id;
	@Column(name = "ge_title")
	private String title;
	@Column(name = "ge_descr")
	private String desription;
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
	public String getDesription() {
		return desription;
	}
	public void setDesription(String desription) {
		this.desription = desription;
	}
	
	@Override
	public String toString() {
		return title;
	}

}
