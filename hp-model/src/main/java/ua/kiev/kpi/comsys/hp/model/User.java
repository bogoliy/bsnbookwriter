package ua.kiev.kpi.comsys.hp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "USER")
public class User {
	@Id
	@GeneratedValue(generator = "tableGenerator")
	@GenericGenerator(name = "tableGenerator", strategy = "org.hibernate.id.enhanced.TableGenerator", parameters = { @Parameter(name = "segment_value", value = "user_seq") })
	@Column(name = "us_id")
	private Long userId;
	@Column(name = "us_name")
	private String name;
	@Column(name = "us_pass")
	private String password;
	@Column(name = "us_enabled")
	private int status;
	@Column(name = "us_login")
	private String login;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}