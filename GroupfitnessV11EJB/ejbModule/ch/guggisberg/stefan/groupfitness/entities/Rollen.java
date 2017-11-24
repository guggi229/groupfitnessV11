package ch.guggisberg.stefan.groupfitness.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="roles")

@NamedQueries({
	@NamedQuery(name= Rollen.QUERY_FIND_ALL_ROLES, 
			query ="SELECT r FROM Rollen r")
})
public class Rollen {
	// Hibernate
	public static final String QUERY_FIND_ALL_ROLES = "QUERY_FIND_ALL_ROLES";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rolesId")
	private Long id;

	@Column(name="role")
	@Size(max=45)
	private String roleName;

	//	 @ManyToOne(fetch=FetchType.EAGER) // Braucht keinen Speicher
	//	 @JoinColumn(name = "Userid")
	//	 private User user;

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

	//	public User getUser() {
	//		return user;
	//	}
	//
	//	public void setUser(User user) {
	//		this.user = user;
	//	}


}
