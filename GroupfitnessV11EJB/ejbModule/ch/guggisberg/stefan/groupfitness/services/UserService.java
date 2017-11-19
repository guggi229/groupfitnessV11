package ch.guggisberg.stefan.groupfitness.services;



import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import ch.guggisberg.stefan.groupfitness.base.BaseBean;
import ch.guggisberg.stefan.groupfitness.entities.User;
import ch.guggisberg.stefan.groupfitness.exceptions.UserAlreadyExistsException;
import ch.guggisberg.stefan.groupfitness.exceptions.UserNotFoundException;


@Stateless
@LocalBean

public class UserService extends BaseBean {

	private static final long serialVersionUID = -987975636197353363L;
	private static Logger log = Logger.getLogger(UserService.class);
	@PersistenceContext
	private EntityManager em;

	
	public User create(User user) {
		try {
			user.setUserCreatedDate(LocalDateTime.now());
			user.setUserModifiedDate(LocalDateTime.now());
			em.persist(user);
			em.flush();
			showGlobalMessage("info.UserDataSaved", null);
		} catch(Exception e) { // TODO!
			showGlobalErrorMessage("warn.error", null);
			log.error(e);
		}


		return user;
	}
	public User getUserWithSkills(Long id) {
		// TODO ? --> Korrigieren
		
		EntityGraph<?> graph = em.getEntityGraph(User.GRAPH_WITH_USER_SKILLS);
		Map<String, Object> hints = new HashMap<String, Object>();
		hints.put("javax.persistence.fetchgraph", graph);
		return this.em.find(User.class, id, hints);
	}

	public User update(User user) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public void remove(Long id) throws UserNotFoundException {
		// TODO Auto-generated method stub

	}

	public User getUser(Long id) throws UserNotFoundException {
		return em.find(User.class, id);
	}

	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

