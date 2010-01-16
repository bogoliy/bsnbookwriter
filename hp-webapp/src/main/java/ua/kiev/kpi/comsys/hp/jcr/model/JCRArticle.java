package ua.kiev.kpi.comsys.hp.jcr.model;

import java.util.Date;

public class JCRArticle {
	private String data;
	private Date lastModified;
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	
}
