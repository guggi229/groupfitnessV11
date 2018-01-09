package ch.guggisberg.stefan.groupfitness.kurseRun;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ch.guggisberg.stefan.groupfitness.entities.CoursRun;
import ch.guggisberg.stefan.groupfitness.entities.Kurs;
import ch.guggisberg.stefan.groupfitness.services.KursRunService;

@RequestScoped
@Named
public class KursRunController {

	@EJB
	private KursRunService crServce;

	private CoursRun coursRun = new CoursRun();
	private Long id;
		
	public void createCoursRun() {
		System.out.println(coursRun.getDuration());
		System.out.println(coursRun.getStartDate());
		System.out.println(coursRun.getEndDate());
		System.out.println(coursRun.getKurs());
		System.out.println(coursRun.getStartTime());
		coursRun.setEndDate(null);
		coursRun.setStartDate(null);
		coursRun.setStartTime(null);
		System.out.println(id);
		Kurs kurs = crServce.getKurs(id);
		System.out.println(kurs.getKursNameDe());
		System.out.println("***************");
		System.out.println("***************");
		coursRun.setKurs(kurs);
		crServce.create(coursRun);
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
	
	
	

}
