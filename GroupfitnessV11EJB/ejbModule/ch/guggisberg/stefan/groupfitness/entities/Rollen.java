package ch.guggisberg.stefan.groupfitness.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="roles")
public class Rollen {

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
