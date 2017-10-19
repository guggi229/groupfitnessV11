package ch.guggisberg.stefan.groupfitness.entities;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
@RequestScoped
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Userid")
	private Long id;

	@Column(name="username")
	private String  userName;
	
	@Column(name="UserEmail")
	private String  userEmail;
	
	@Column(name="UserLang")
	private String  userLang;
	

	
	// Konstruktor für Hibernate
	public User() {

	}
	public User( String name) {
		this.userName =name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserLang() {
		return userLang;
	}
	public void setUserLang(String userLang) {
		this.userLang = userLang;
	}


}
