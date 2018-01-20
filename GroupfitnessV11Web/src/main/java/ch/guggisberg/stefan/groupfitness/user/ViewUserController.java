package ch.guggisberg.stefan.groupfitness.user;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import ch.guggisberg.stefan.groupfitness.base.BaseBean;
import ch.guggisberg.stefan.groupfitness.entities.CoursRun;
import ch.guggisberg.stefan.groupfitness.entities.Kurs;
import ch.guggisberg.stefan.groupfitness.entities.User;
import ch.guggisberg.stefan.groupfitness.services.KursRunService;
import ch.guggisberg.stefan.groupfitness.services.UserService;
import ch.guggisberg.stefan.groupfitness.utils.DateManager;

import java.io.Serializable;

@SessionScoped
@Named
public class ViewUserController extends BaseBean implements Serializable {

	private static final long serialVersionUID = 987697781060951911L;

	@EJB
	private UserService userService;
	
	@EJB
	private KursRunService crService;
	
	private User user = new User();
	private List<Kurs> userSkills;
	private List<CoursRun> coursRuns;
	private List<CoursRun> withoutTeacher;
	private DateManager dm;
	private int month;
		
	@PostConstruct
	public void init() {
		
		// Load from DB
		user = userService.getUserWithSkills((FacesContext.getCurrentInstance().getExternalContext().getRemoteUser())); // Get Logged User
		userSkills=user.getKannUnterrichten();
		dm = new DateManager(LocalDate.now());
		withoutTeacher = crService.getAllPossibleCoursRunWithoutTeacher(user.getId());
		coursRuns = crService.getKursRunWithParticipantAmount(LocalDate.of(dm.getYear(), dm.getMonth(), dm.getFirstDay()), LocalDate.of(dm.getYear(), dm.getMonth(), dm.getLastDayOfMonth()), user.getId());
		month=dm.getMonth().getValue();
	}
	
	// Actions
	// =======
	public String reload() {
		coursRuns = crService.getKursRunWithParticipantAmount(LocalDate.of(dm.getYear(), dm.getMonth(), dm.getFirstDay()), LocalDate.of(dm.getYear(), dm.getMonth(), dm.getLastDayOfMonth()), user.getId());
		return "/viewUser";
	}
	
	// Getter / Setter
	//================
	public List<Kurs> getUserSkills(){
		return userSkills;
	}
	
	public List<CoursRun> getKursRunWithParticipantAmount(){
		return coursRuns;
	}
	
	public void saveParticipal(CoursRun c) {
		crService.update(c);
		showGlobalMessage("info.UserDataSaved", null);
	}
	
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
		dm.setMonth(month);
	}

	public List<CoursRun> getWithoutTeacher() {
		return withoutTeacher;
	}

	public void setWithoutTeacher(List<CoursRun> withoutTeacher) {
		this.withoutTeacher = withoutTeacher;
	}
	
}
