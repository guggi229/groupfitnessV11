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
import ch.guggisberg.stefan.groupfitness.services.KursService;
import ch.guggisberg.stefan.groupfitness.services.UserService;

@SessionScoped
@Named
public class KursRunController implements Serializable {

	private static final long serialVersionUID = -5838233856742419611L;

	@EJB
	private KursRunService crServce;

	@EJB
	private KursService cService;

	@EJB
	private UserService uService;
	private static Logger log = Logger.getLogger(KursRunController.class);

	// Dating Stuff
	private LocalDate startDate = LocalDate.now();
		
	public List<CoursRun> getCoursRunListAtThisDate(){
		return crServce.getCoursRunListAtThisDate((startDate));
	}
	public String reload() {
		return "/viewCoursRun";
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public String getDay() {
		return startDate.getDayOfWeek().toString();
	}

}
