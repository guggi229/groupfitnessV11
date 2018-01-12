package ch.guggisberg.stefan.groupfitness.entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="coursRun")

@NamedQueries({
	@NamedQuery(name= CoursRun.QUERY_FIND_ALL_COURS_RUN, 
			query ="SELECT c FROM CoursRun c"),
	@NamedQuery(name= CoursRun.QUERY_FIND_COURS_AT_THIS_DATE, 
	query ="SELECT c FROM CoursRun c WHERE c.runningDate=:" + CoursRun.PARAM_DATE)
})
public class CoursRun implements Serializable, Cloneable {

	private static final long serialVersionUID = 6799084951953976291L;
	// Hibernate
	public static final String QUERY_FIND_ALL_COURS_RUN = "QUERY_FIND_ALL_COURS_RUN";
	public static final String QUERY_FIND_COURS_AT_THIS_DATE = "QUERY_FIND_COURS_AT_THIS_DATE";
	public static final String PARAM_DATE="PARAM_DATE";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;


	@Column(name="runningDate")
	private LocalDate runningDate;

	@Column(name="duration")
	private int duration;

	@Column(name="time")
	private String startTime;

	@Column(name="maxPlaceCustomer")
	private int maxPlace;

	
	// Warum Eager? Ein KursRun ohne Kurstyp ist nutzlos!
	@ManyToOne(fetch=FetchType.EAGER)
	private Kurs kurs;


	// Getter / Setter

	public Kurs getKurs() {
		return kurs;
	}

	public void setKurs(Kurs kurs) {
		this.kurs = kurs;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getMaxPlace() {
		return maxPlace;
	}

	public void setMaxPlace(int maxPlace) {
		this.maxPlace = maxPlace;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public LocalDate getCurrentDate() {
		return runningDate;
	}

	public void setCurrentDate(LocalDate currentDate) {
		this.runningDate = currentDate;
	}

	public CoursRun copy(CoursRun origin) throws CloneNotSupportedException {
		return (CoursRun) origin.clone();
	}



}
