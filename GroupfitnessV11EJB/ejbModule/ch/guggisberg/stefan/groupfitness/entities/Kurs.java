package ch.guggisberg.stefan.groupfitness.entities;


import java.io.Serializable;
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
import ch.guggisberg.stefan.groupfitness.entities.User;

@Entity
@Table(name="kurs")
@NamedQueries({
	@NamedQuery(name= Kurs.QUERY_FIND_ALL, 
			query ="SELECT k FROM Kurs k WHERE  k.deleted=false")
})
public class Kurs implements Serializable {
	
	private static final long serialVersionUID = 5942147005085804609L;
	
	// Hibernate
	public static final String QUERY_FIND_ALL = "QUERY_FIND_ALL";
	
	public Kurs() {
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="nameDe")
	@Size( max=45)
	private String kursNameDe;
	
	@Column(name="nameFr")
	@Size( max=45)
	private String kursNameFr;
	
	@Size(max=200)
	@Column(name="descriptionDe")
	private String kursDescriptionDe;

	@Size(max=200)
	@Column(name="descriptionFr")
	private String kursDescriptionFr;
	
	@Column(name="deleted" ,columnDefinition="tinyint(1)")
	private boolean deleted;
	
	@ManyToMany(mappedBy = "kannUnterrichten")
    private Set<User> users = new HashSet<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKursDescriptionDe() {
		return kursDescriptionDe;
	}

	public void setKursDescriptionDe(String kursDescriptionDe) {
		this.kursDescriptionDe = kursDescriptionDe;
	}

	public String getKursDescriptionFr() {
		return kursDescriptionFr;
	}

	public void setKursDescriptionFr(String kursDescriptionFr) {
		this.kursDescriptionFr = kursDescriptionFr;
	}

	public String getKursNameDe() {
		return kursNameDe;
	}

	public void setKursNameDe(String kursNameDe) {
		this.kursNameDe = kursNameDe;
	}

	public String getKursNameFr() {
		return kursNameFr;
	}

	public void setKursNameFr(String kursNameFr) {
		this.kursNameFr = kursNameFr;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
		
}
