package ch.guggisberg.stefan.groupfitness.kurseRun;

import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.log4j.Logger;

import ch.guggisberg.stefan.groupfitness.entities.CoursRun;
import ch.guggisberg.stefan.groupfitness.services.KursRunService;

@SessionScoped
@Named
public class ViewKursRunWithoutTeacher implements Serializable {

	private static final long serialVersionUID = -5838233856742419611L;

	@EJB
	private KursRunService crServce;
	private LocalDate date;
	private int startDate;
	private int endDate;
	private int year;
	
	private static Logger log = Logger.getLogger(ViewKursRunWithoutTeacher.class);

	// Dating Stuff
	public ViewKursRunWithoutTeacher() {
		 date = LocalDate.now();
		 startDate=1;
		 endDate =date.lengthOfMonth();
		 year =date.getYear();
	}
		
	public List<CoursRun> getCourseRunWithoutTeacherinThisMonth(){
		return crServce.getCourseRunWithoutTeacherinThisMonth(LocalDate.of(year, date.getMonth(), startDate), LocalDate.of(year, date.getMonth(), endDate));
	}


	

}
