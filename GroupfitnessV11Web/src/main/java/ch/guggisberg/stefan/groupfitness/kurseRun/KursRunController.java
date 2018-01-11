package ch.guggisberg.stefan.groupfitness.kurseRun;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.log4j.Logger;

import ch.guggisberg.stefan.groupfitness.entities.CoursRun;
import ch.guggisberg.stefan.groupfitness.entities.Kurs;
import ch.guggisberg.stefan.groupfitness.entities.User;
import ch.guggisberg.stefan.groupfitness.exceptions.KursNotFoundException;
import ch.guggisberg.stefan.groupfitness.services.KursRunService;
import ch.guggisberg.stefan.groupfitness.services.KursService;

@RequestScoped
@Named
public class KursRunController {

	@EJB
	private KursRunService crServce;

	@EJB
	private KursService cService;

	private static Logger log = Logger.getLogger(KursRunController.class);

	private CoursRun coursRun = new CoursRun();
	private Long id;
	private int day;
	// Dating Stuff
	private LocalDate startDate;
	private LocalDate endDate;
	private List<LocalDate> localDates;
	private List<CoursRun> coursRuns= new ArrayList<>();
	private Kurs kurs = new Kurs();

	public String newSerie(Kurs k) {
		kurs=k;
		return "groupfitnessAdmin/newCoursRun";
	}

	public String previewCoursRun() {
		
		localDates = new ArrayList<>();
		LocalDate tempDate = startDate;
		while (tempDate.isBefore(endDate)) {
			if (tempDate.getDayOfWeek().equals(DayOfWeek.of(day))) {
				try {
					CoursRun tempCours = coursRun.copy(coursRun);
					tempCours.setCurrentDate(tempDate);
					tempCours.setKurs(cService.getKurs(kurs.getId()));
					coursRuns.add(tempCours);
					crServce.create(tempCours);
				} catch (CloneNotSupportedException e) {
					log.warn(e);
					// return to a sorry Page
				} catch (KursNotFoundException e) {
				log.warn(e);
				}
				System.out.println(tempDate.toString());
				localDates.add(tempDate);
			}
			tempDate=tempDate.plusDays(1L);
		}


		//		System.out.println(coursRun.getStartTime());
		////		coursRun.setEndDate(null);
		////		coursRun.setStartDate(null);
		//		coursRun.setStartTime(null);
		//		System.out.println(id);
		//		Kurs kurs = crServce.getKurs(id);
		//		System.out.println(kurs.getKursNameDe());
		//		System.out.println("***************");
		//		System.out.println("***************");
		//
		//		System.out.println(DayOfWeek.MONDAY);
		//		DayOfWeek dayOfWeek = DayOfWeek.of(day);
		//		System.out.println("Day of weekend is " + dayOfWeek);
		//		
		//		LocalDate tempDate = coursRun.getStartDate();
		//		

		//		
		//		//coursRun.setKurs(kurs);
		//		//crServce.create(coursRun);
		System.out.println("previewCoursRun durchlaufen");
		return "previewCoursRun";

	}

	public CoursRun getCoursRun() {
		return coursRun;
	}
	public void setCoursRun(CoursRun coursRun) {
		this.coursRun = coursRun;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
	public List<User> getPossibleUserForThisCours(){
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




}
