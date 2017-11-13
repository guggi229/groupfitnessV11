package ch.guggisberg.stefan.groupfitness.services;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ch.guggisberg.stefan.groupfitness.base.BaseBean;
import ch.guggisberg.stefan.groupfitness.entities.Kurs;
import ch.guggisberg.stefan.groupfitness.exceptions.KursAlreadyExistsException;
import ch.guggisberg.stefan.groupfitness.exceptions.KursNotFoundException;

@Stateless
@Remote(KursServiceRemote.class)
@DeclareRoles({"GroupfitnessAdmin", "Customer", "Admin"})
public class KursService extends BaseBean implements KursServiceRemote{

	private static final long serialVersionUID = 319735437119932640L;
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@RolesAllowed("GroupfitnessAdmin")
	public Kurs create(Kurs kurs) throws KursAlreadyExistsException {
		showGlobalMessage("info.UserDataSaved", "saveOK");
		kurs.setDeleted(false);
		em.persist(kurs);
		em.flush();
		return kurs;
	}

	@Override
	@RolesAllowed("GroupfitnessAdmin")
	public Kurs update(Kurs kurs) throws KursNotFoundException {
		return em.merge(kurs);
	}

	@Override
	@RolesAllowed("GroupfitnessAdmin")
	public void remove(Long id) throws KursNotFoundException {
		Kurs kurs = getKurs(id);
		kurs.setDeleted(true);
		update(kurs);
	}

	@Override
	@PermitAll
	public Kurs getKurs(Long id) throws KursNotFoundException {
		return em.find(Kurs.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	@PermitAll
	public List<Kurs> getAllKurs() {
		return  em.createNamedQuery(Kurs.QUERY_FIND_ALL).getResultList();
	}
	
}
