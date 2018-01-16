package ch.guggisberg.stefan.groupfitness.kurseRun;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.log4j.Logger;

import ch.guggisberg.stefan.groupfitness.entities.CoursRun;
import ch.guggisberg.stefan.groupfitness.services.KursRunService;
import ch.guggisberg.stefan.groupfitness.utils.DateManager;

@SessionScoped
@Named
public class ViewKursRunWithoutTeacher implements Serializable {

	private static final long serialVersionUID = -5838233856742419611L;

	@EJB
	private KursRunService crServce;
	private DateManager dm;

	private static Logger log = Logger.getLogger(ViewKursRunWithoutTeacher.class);

	// Dating Stuff
	public ViewKursRunWithoutTeacher() {
		dm = new DateManager(LocalDate.now());
	}

	public List<CoursRun> getCourseRunWithoutTeacherinThisMonth(){
		return crServce.getCourseRunWithoutTeacherinThisMonth(LocalDate.of(dm.getYear(), dm.getMonth(), dm.getFirstDay()), LocalDate.of(dm.getYear(), dm.getMonth(), dm.getLastDayOfMonth()));
	}




}
