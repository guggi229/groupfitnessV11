package ch.guggisberg.stefan.groupfitness.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
	@NamedQuery(name= CoursRun.QUERY_FIND_COURSRUN_WITHOUT_TEACHER_FOR_THIS_TEACHER, 
	query ="SELECT cr FROM CoursRun cr WHERE cr.user IS NULL"),
	@NamedQuery(name= CoursRun.QUERY_FIND_ALL_COURS_WITHOUT_TEACHER, 
	query ="SELECT c FROM CoursRun c WHERE c.user IS NULL AND c.runningDate>=:" + CoursRun.PARAM_START_DATE + " AND c.runningDate<=:" + CoursRun.PARAM_END_DATE +" order by c.runningDate asc,c.startTime asc"),
	@NamedQuery(name= CoursRun.QUERY_FIND_COURS_AT_THIS_DATE, 
	query ="SELECT c FROM CoursRun c WHERE c.runningDate=:" + CoursRun.PARAM_DATE + " order by c.startTime asc"),
	@NamedQuery(name= CoursRun.QUERY_FIND_ALL_PARTICIPANT_IN_THIS_MONTH, 
	query ="SELECT c FROM CoursRun c WHERE c.runningDate>=:" + CoursRun.PARAM_START_DATE + " AND c.runningDate<=:" + CoursRun.PARAM_END_DATE + " AND c.user.id=:"+ CoursRun.PARAM_TEACHER_ID +" order by c.startTime asc")
	
})

public class CoursRun implements Serializable, Cloneable {

	private static final long serialVersionUID = 6799084951953976291L;
	// Hibernate
	public static final String QUERY_FIND_ALL_COURS_RUN = "QUERY_FIND_ALL_COURS_RUN";
	public static final String QUERY_FIND_ALL_PARTICIPANT_IN_THIS_MONTH= "QUERY_FIND_ALL_Participant_IN_THIS_MONTH";
	public static final String QUERY_FIND_ALL_COURS_WITHOUT_TEACHER = "QUERY_FIND_ALL_COURS_WITHOUT_TEACHER";
	public static final String QUERY_FIND_COURS_AT_THIS_DATE = "QUERY_FIND_COURS_AT_THIS_DATE";
	public static final String QUERY_FIND_COURSRUN_WITHOUT_TEACHER_FOR_THIS_TEACHER = "QUERY_FIND_COURSRUN_WITHOUT_TEACHER";
	
	public static final String PARAM_DATE="PARAM_DATE";
	public static final String PARAM_START_DATE="PARAM_START_DATE";
	public static final String PARAM_END_DATE="PARAM_END_DATE";
	public static final String PARAM_TEACHER_ID="PARAM_TECHER_ID";

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

	// Warum Eager? Ein KursRun ohne User ist nutzlos!
	@ManyToOne(fetch=FetchType.EAGER)
	private User user;

	@Column(name="participantAmount")
	private int participantAmount;
	
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
	public String getFormattedCurrentDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		return runningDate.format(formatter);
	}
	public void setCurrentDate(LocalDate currentDate) {
		this.runningDate = currentDate;
	}

	public CoursRun copy(CoursRun origin) throws CloneNotSupportedException {
		return (CoursRun) origin.clone();
	}

	public LocalDate getRunningDate() {
		return runningDate;
	}

	public void setRunningDate(LocalDate runningDate) {
		this.runningDate = runningDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getParticipantAmount() {
		return participantAmount;
	}

	public void setParticipantAmount(int participantAmount) {
		this.participantAmount = participantAmount;
	}



}
