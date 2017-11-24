package ch.guggisberg.stefan.groupfitness.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="rolle")

@NamedQueries({
	@NamedQuery(name= Rollen.QUERY_FIND_ALL_ROLES, 
			query ="SELECT r FROM Rollen r")
})
public class Rollen {
	// Hibernate
	public static final String QUERY_FIND_ALL_ROLES = "QUERY_FIND_ALL_ROLES";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="rolle")
	@Size(max=45)
	private String roleName;

	@ManyToMany(mappedBy="userRolle")
	private Set<User> users = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}




}
