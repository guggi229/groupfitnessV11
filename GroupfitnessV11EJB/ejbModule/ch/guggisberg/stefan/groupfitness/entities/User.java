package ch.guggisberg.stefan.groupfitness.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	@Column(name="UserVorname")
	private String  userVorname;
	
	@Column(name="UserCreatedDate")
	private LocalDateTime  userCreatedDate;
	
	@Column(name="UserModifiedDate")
	private LocalDateTime  userModifiedDate;

	@Column(name="UserPassword")
	private String  userPassword;
	
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Rollen> roles;
	
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
	public String getUserVorname() {
		return userVorname;
	}
	public void setUserVorname(String userVorname) {
		this.userVorname = userVorname;
	}
	public Set<Rollen> getRoles() {
		return roles;
	}
	public void setRoles(Set<Rollen> roles) {
		this.roles = roles;
	}
	public LocalDateTime getUserCreatedDate() {
		return userCreatedDate;
	}
	public void setUserCreatedDate(LocalDateTime userCreatedDate) {
		this.userCreatedDate = userCreatedDate;
	}
	public LocalDateTime getUserModifiedDate() {
		return userModifiedDate;
	}
	public void setUserModifiedDate(LocalDateTime userModifiedDate) {
		this.userModifiedDate = userModifiedDate;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}




}
