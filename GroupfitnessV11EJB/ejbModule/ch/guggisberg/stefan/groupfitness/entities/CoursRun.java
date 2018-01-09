package ch.guggisberg.stefan.groupfitness.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javax.validation.constraints.Size;

@Entity
@Table(name="coursRun")

@NamedQueries({
	@NamedQuery(name= CoursRun.QUERY_FIND_ALL_COURS_RUN, 
			query ="SELECT c FROM CoursRun c")
})
public class CoursRun implements Serializable {

	private static final long serialVersionUID = 6799084951953976291L;
	// Hibernate
	public static final String QUERY_FIND_ALL_COURS_RUN = "QUERY_FIND_ALL_COURS_RUN";
	public static final String GRAPH_WITH_KURS= "GRAPH_WITH_KURS";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="startDate")
	private LocalDate startDate;

	@Column(name="endDate")
	private LocalDate endDate;

	@Column(name="duration")
	private int duration;

	@Column(name="time")
	private LocalTime startTime;

	@Column(name="maxPlaceCustomer")
	private int maxPlace;
	
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public int getMaxPlace() {
		return maxPlace;
	}

	public void setMaxPlace(int maxPlace) {
		this.maxPlace = maxPlace;
	}




}
