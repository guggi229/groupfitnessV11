package ch.guggisberg.stefan.groupfitness.kurseRun;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.log4j.Logger;

import ch.guggisberg.stefan.groupfitness.entities.CoursRun;
import ch.guggisberg.stefan.groupfitness.entities.Kurs;
import ch.guggisberg.stefan.groupfitness.entities.User;
import ch.guggisberg.stefan.groupfitness.exceptions.KursNotFoundException;
import ch.guggisberg.stefan.groupfitness.exceptions.UserNotFoundException;
import ch.guggisberg.stefan.groupfitness.services.KursRunService;
import ch.guggisberg.stefan.groupfitness.services.KursService;
import ch.guggisberg.stefan.groupfitness.services.UserService;

@SessionScoped
@Named
public class KursRunController implements Serializable {

	@EJB
	private KursRunService crServce;

	@EJB
	private KursService cService;

	@EJB
	private UserService uService;
	private static Logger log = Logger.getLogger(KursRunController.class);

	private CoursRun coursRun = new CoursRun();
	private Long userId;
	private User selectedUser;
	private int day;
	// Dating Stuff
	private LocalDate startDate;
	private LocalDate endDate;
	private List<LocalDate> localDates;

	private List<CoursRun> coursRuns= new ArrayList<>();
	private Kurs kurs = new Kurs();
	private User teacher = new User();

	public String newSerie(Kurs k) {
		kurs=k;
		return "groupfitnessAdmin/newCoursRun";
	}

	public String previewCoursRun() throws UserNotFoundException {
		System.out.println("******first line***************");
		selectedUser = uService.getUser(userId);
		localDates = new ArrayList<>();
		LocalDate tempDate = startDate;
		// Suche alle passende daten zwischen starDate und endDate die dem Tag day entsprechen.
		while (tempDate.isBefore(endDate)) {
			if (tempDate.getDayOfWeek().equals(DayOfWeek.of(day))) {
				try {
					CoursRun tempCours = coursRun.copy(coursRun);
					tempCours.setCurrentDate(tempDate);
					tempCours.setKurs(cService.getKurs(kurs.getId()));
					coursRuns.add(tempCours);
					//crServce.create(tempCours);
				} catch (CloneNotSupportedException e) {
					log.warn(e);
					// return to a sorry Page
				} catch (KursNotFoundException e) {
					log.warn(e);
				}
				localDates.add(tempDate);
				
			}
			tempDate=tempDate.plusDays(1L);
		}
		
		System.out.println("**********************");
		System.out.println("**********************");

		System.out.println("**********************");

		System.out.println(userId);
		try {
			teacher = uService.getUser(userId);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "previewCoursRun";

	}
	public String createCoursRuns() throws KursNotFoundException {
		for (CoursRun coursRun : coursRuns) {
		
			crServce.create(coursRun);
		}
		return "/GroupfitnessV11Web/success"; //????????
	}

	public CoursRun getCoursRun() {
		return coursRun;
	}
	public void setCoursRun(CoursRun coursRun) {
		this.coursRun = coursRun;
	}

	public Long getUserId() {
		System.out.println("******getter***************");
		return userId;
	}

	public void setUserId(Long id) {
		System.out.println("************setter *********" + id);
		this.userId = id;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
	public List<User> getPossibleUserForThisCours(){
		System.out.println("*********getpossible********" + kurs.getId());
		return cService.getPossibleUserForThisCours(kurs.getId());
	}

	public Kurs getKurs() {
		return kurs;
	}

	public void setKurs(Kurs kurs) {
		this.kurs = kurs;
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

	public List<CoursRun> getCoursRuns() {
		return coursRuns;
	}

	public void setCoursRuns(List<CoursRun> coursRuns) {
		this.coursRuns = coursRuns;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

}
