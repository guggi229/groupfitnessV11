package ch.guggisberg.stefan.groupfitness.services;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import ch.guggisberg.stefan.groupfitness.entities.Kurs;
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



}
