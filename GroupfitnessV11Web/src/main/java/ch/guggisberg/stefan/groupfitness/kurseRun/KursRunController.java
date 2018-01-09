package ch.guggisberg.stefan.groupfitness.kurseRun;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ch.guggisberg.stefan.groupfitness.entities.CoursRun;
import ch.guggisberg.stefan.groupfitness.services.KursRunService;

@RequestScoped
@Named
public class KursRunController {

	@EJB
	private KursRunService crServce;
	private CoursRun coursRun = new CoursRun();
	
	
	
	
	public void createCoursRun() {
		System.out.println(coursRun.getDuration());
		System.out.println(coursRun.getStartDate());
		System.out.println(coursRun.getEndDate());
		System.out.println(coursRun.getKurs());
		System.out.println("***************");
		System.out.println("***************");
	}
	
	public CoursRun getCoursRun() {
		return coursRun;
	}
	public void setCoursRun(CoursRun coursRun) {
		this.coursRun = coursRun;
	}
	
	
	

}
