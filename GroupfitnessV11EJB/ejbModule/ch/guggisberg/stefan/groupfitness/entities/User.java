package ch.guggisberg.stefan.groupfitness.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;

@Entity
@Table(name="user")
@RequestScoped
@NamedEntityGraphs({
	@NamedEntityGraph(
			name = User.GRAPH_WITH_USER_SKILLS,
			attributeNodes = {
					@NamedAttributeNode("kannUnterrichten")
			}
			)
})
@NamedQueries({
	@NamedQuery(name= User.QUERY_FIND_USER_BY_EMAIL, 
			query ="SELECT u FROM User u WHERE  u.userEmail=:"+User.PARAM_EMAIL)
})
public class User implements Serializable {

	private static final long serialVersionUID = -8974946876389769968L;

	// Hibernate
	public static final String GRAPH_WITH_USER_SKILLS= "GRAPH_WITH_USER_SKILLS";
	public static final String QUERY_FIND_USER_BY_EMAIL= "QUERY_FIND_USER_BY_EMAIL";
	public static final String PARAM_EMAIL= "PARAM_EMAIL";

	public static final String PATTERN="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
			+"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
			+"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Size(min=1,max=45)
	@Column(name="name")
	private String  userName;

	@Size(min=1,max=45)
	@Column(name="vorname")
	private String  userVorname;

	@Pattern(regexp=PATTERN,
			message="{warn.email.invalid}")
	@Column(name="email")
	private String  userEmail;

	@Size(min=8,max=45)
	@Column(name="password")
	private String  userPassword;

	@Column(name="lang")
	private String  userLang;


	@Column(name="createdDate")
	private LocalDateTime  userCreatedDate;

	@Column(name="modifiedDate")
	private LocalDateTime  userModifiedDate;

	@ManyToMany(cascade = { CascadeType.ALL })	// Standard LAZY! 
	@JoinTable( name = "UserRolle", joinColumns = { 
			@JoinColumn(name = "UserId") }, 
	inverseJoinColumns = { @JoinColumn(name = "RolleId") }
			)
	private Set<Rollen> userRolle = new HashSet<>();

	@ManyToMany(cascade = { CascadeType.ALL })	// Standard LAZY! 
	@JoinTable( name = "kannUnterrichten", joinColumns = { 
			@JoinColumn(name = "userid") }, 
	inverseJoinColumns = { @JoinColumn(name = "kursid") }
			)
	private List<Kurs> kannUnterrichten = new ArrayList<>();

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

	public List<Kurs> getKannUnterrichten() {
		return kannUnterrichten;
	}
	public void setKannUnterrichten(List<Kurs> kannUnterrichten) {
		this.kannUnterrichten = kannUnterrichten;
	}
	public Set<Rollen> getUserRolle() {
		return userRolle;
	}
	public void setUserRolle(Set<Rollen> userRolle) {
		this.userRolle = userRolle;
	}

}
