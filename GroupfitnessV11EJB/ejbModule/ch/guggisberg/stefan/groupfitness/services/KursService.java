package ch.guggisberg.stefan.groupfitness.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityGraph;

import ch.guggisberg.stefan.groupfitness.entities.Kurs;
import ch.guggisberg.stefan.groupfitness.entities.User;
import ch.guggisberg.stefan.groupfitness.exceptions.KursAlreadyExistsException;
import ch.guggisberg.stefan.groupfitness.exceptions.KursNotFoundException;

@Stateless
@LocalBean
@DeclareRoles({"GroupfitnessAdmin", "Customer", "Admin"})
public class KursService extends  BaseCrud<Kurs> {

	private static final long serialVersionUID = 319735437119932640L;

	@RolesAllowed("GroupfitnessAdmin")
	public Kurs create(Kurs kurs) throws KursAlreadyExistsException {
		kurs.setDeleted(false);
		entityManager.persist(kurs);

		return kurs;
	}

	@RolesAllowed("GroupfitnessAdmin")
	public Kurs update(Kurs kurs) {
		return entityManager.merge(kurs);
	}

	@RolesAllowed("GroupfitnessAdmin")
	public void remove(Long id) throws KursNotFoundException {
		Kurs kurs = getKurs(id);
		kurs.setDeleted(true);
		update(kurs);
	}

	@PermitAll
	public Kurs getKurs(Long id) throws KursNotFoundException {
		return entityManager.find(Kurs.class, id);
	}

	@SuppressWarnings("unchecked")
	@PermitAll
	public List<Kurs> getAllKurs() {
		return  entityManager.createNamedQuery(Kurs.QUERY_FIND_ALL).getResultList();
	}
/**
 * Sucht alle passende Teacher für diesen Kurs
 * @param id
 * @return Liste mit Teachern
 */
	@PermitAll
	public List<User> getPossibleUserForThisCours(Long id){
		EntityGraph<?> graph = entityManager.getEntityGraph(Kurs.GRAPH_WITH_USERS);
		Map<String, Object> hints = new HashMap<String, Object>();
		hints.put("javax.persistence.fetchgraph", graph);
		Kurs kurs = this.entityManager.find(Kurs.class, id, hints);	
		Set<User> users = kurs.getUsers();
		return new ArrayList<>(users);
	}

}
