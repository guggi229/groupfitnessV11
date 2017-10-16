package ch.guggisberg.stefan.groupfitness.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ch.guggisberg.stefan.groupfitness.entities.Kurs;
import ch.guggisberg.stefan.groupfitness.exceptions.KursAlreadyExistsException;
import ch.guggisberg.stefan.groupfitness.exceptions.KursNotFoundException;

@Stateless
@Remote(KursServiceRemote.class)
public class KursService implements KursServiceRemote{

	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Kurs kurs) throws KursAlreadyExistsException {
		em.persist(kurs);
	}

	@Override
	public Kurs update(Kurs kurs) throws KursNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Long id) throws KursNotFoundException {
		em.remove(getKurs(id));		
	}

	@Override
	public Kurs getKurs(Long id) throws KursNotFoundException {
		return em.find(Kurs.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Kurs> getAllKurs() {
		return  em.createNamedQuery(Kurs.QUERY_FIND_ALL).getResultList();
	}

}
