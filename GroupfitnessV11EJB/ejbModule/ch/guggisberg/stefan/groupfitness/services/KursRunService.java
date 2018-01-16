package ch.guggisberg.stefan.groupfitness.services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ch.guggisberg.stefan.groupfitness.entities.CoursRun;
import ch.guggisberg.stefan.groupfitness.entities.Kurs;

@Stateless
@LocalBean
@DeclareRoles({"GroupfitnessAdmin", "Customer", "Admin", "Teacher"})
public class KursRunService extends BaseCrud<CoursRun>{

	@RolesAllowed("GroupfitnessAdmin")
	public void create(CoursRun coursRun) {
		entityManager.persist(coursRun);
	}
	@RolesAllowed("GroupfitnessAdmin")
	public CoursRun update(CoursRun coursRun) {
		return entityManager.merge(coursRun);
	}
	@RolesAllowed("GroupfitnessAdmin")
	public void remove(CoursRun coursRun) {
		entityManager.remove(coursRun);
	}
	@PermitAll
	public CoursRun getCoursRun(Long id)  {
		return entityManager.find(CoursRun.class, id);
	}
	@SuppressWarnings("unchecked")
	@PermitAll
	public List<CoursRun> getAllCoursRun(){
		return entityManager.createNamedQuery(CoursRun.QUERY_FIND_ALL_COURS_RUN).getResultList();
	}
	@PermitAll
	public Kurs getKurs(Long id) {
		return entityManager.find(Kurs.class, id);
	}
	@PermitAll
	public List<CoursRun> getCoursRunListAtThisDate(LocalDate date){
		HashMap<String, Object> params = new HashMap<>();
		params.put(CoursRun.PARAM_DATE, date);
		return findListResultNamedQuery(CoursRun.QUERY_FIND_COURS_AT_THIS_DATE,params);
	}
	@PermitAll
	public List<CoursRun> getKursRunWithParticipantAmount(LocalDate startDate, LocalDate endDate, Long teacherID){
		HashMap<String, Object> params = new HashMap<>();
		params.put(CoursRun.PARAM_START_DATE, startDate);
		params.put(CoursRun.PARAM_END_DATE, endDate);
		params.put(CoursRun.PARAM_TEACHER_ID, teacherID);
		return findListResultNamedQuery(CoursRun.QUERY_FIND_ALL_PARTICIPANT_IN_THIS_MONTH,params);
	}	

}
