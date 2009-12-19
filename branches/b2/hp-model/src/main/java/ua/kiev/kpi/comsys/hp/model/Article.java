package ua.kiev.kpi.comsys.hp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
@Entity
@Table (name="ARTICLE")
public class Article {
	@Id
	@GeneratedValue(generator = "tableGenerator")
    @GenericGenerator(name = "tableGenerator", strategy = "org.hibernate.id.enhanced.TableGenerator",
        parameters = {@Parameter(name = "segment_value", value = "article_seq")})
	@Column(name="ar_id")
	private Long articleId;
	@Column(name="us_id")
	private Long userId;
	@Column(name="ar_title")
	private String title;
	@Column(name="ar_text")
	private String text;
	@Column(name="ar_type")
	private String type;
	@ManyToOne
	@JoinColumn(name="us_id", updatable = false, insertable = false)
	private User user;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getArticleId() {
		return articleId;
	}
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public User getUser() {
		return user;
	}
	

}
