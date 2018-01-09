package ch.guggisberg.stefan.groupfitness.services;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ch.guggisberg.stefan.groupfitness.entities.CoursRun;

@Stateless
@LocalBean
@DeclareRoles({"GroupfitnessAdmin", "Customer", "Admin"})
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

}
