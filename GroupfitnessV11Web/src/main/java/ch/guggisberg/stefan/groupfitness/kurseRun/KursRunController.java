package ch.guggisberg.stefan.groupfitness.kurseRun;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.log4j.Logger;

import ch.guggisberg.stefan.groupfitness.entities.CoursRun;
import ch.guggisberg.stefan.groupfitness.entities.Kurs;
import ch.guggisberg.stefan.groupfitness.entities.User;
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
	private List<LocalDate> localDate;
	private Kurs kurs = new Kurs();
	
	public String newSerie(Kurs k) {
		kurs=k;
		return "groupfitnessAdmin/newCoursRun";
	}
	
	public String previewCoursRun() {
//		System.out.println(coursRun.getDuration());
//		System.out.println(coursRun.getStartDate());
//		System.out.println(coursRun.getEndDate());
//		System.out.println(coursRun.getKurs());
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
//		while (tempDate.isBefore(coursRun.getEndDate())) {
//			if (tempDate.getDayOfWeek().equals(DayOfWeek.of(day))) {
//				System.out.println(tempDate.toString());
//				
//			}
//			tempDate=tempDate.plusDays(1L);
//		}
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
	
	
	

}
