package ch.guggisberg.stefan.groupfitness.entities;


import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="kurs")
@NamedQueries({
	@NamedQuery(name= Kurs.QUERY_FIND_ALL, 
			query ="SELECT k FROM Kurs k")
})
public class Kurs implements Serializable {
	
	private static final long serialVersionUID = 5942147005085804609L;

	public static final String QUERY_FIND_ALL = "QUERY_FIND_ALL";
	
	public Kurs() {
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="classId")
	private Long id;
	
	@Column(name="classNameDe")
	@Size(min=1, max=45)
	private String kursNameDe;
	
	@Column(name="classNameFr")
	@Size(min=1, max=45)
	private String kursNameFr;
	
	@Size(max=200)
	@Column(name="classDescriptionDe")
	private String kursDescriptionDe;

	@Size(max=200)
	@Column(name="classDescriptionFr")
	private String kursDescriptionFr;
	
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
	
	
	
}
