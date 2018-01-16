package ch.guggisberg.stefan.groupfitness.kurseRun;

import java.io.Serializable;
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

	private static Logger log = Logger.getLogger(ViewKursRunWithoutTeacher.class);

	// Dating Stuff

		
	public List<CoursRun> getCourseRunWithoutTeacherinThisMonth(){
		return crServce.getCourseRunWithoutTeacherinThisMonth(LocalDate.of(2018, Month.JANUARY, 1), LocalDate.of(2018, Month.JANUARY, 31));
	}


	

}
