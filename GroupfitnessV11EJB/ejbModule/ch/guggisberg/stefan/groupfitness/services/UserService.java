package ch.guggisberg.stefan.groupfitness.services;



import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityGraph;
import org.apache.log4j.Logger;

import ch.guggisberg.stefan.groupfitness.entities.Kurs;
import ch.guggisberg.stefan.groupfitness.entities.User;
import ch.guggisberg.stefan.groupfitness.exceptions.KursNotFoundException;
import ch.guggisberg.stefan.groupfitness.exceptions.UserNotFoundException;


@Stateless
@LocalBean

public class UserService extends BaseCrud<User> {

	private static final long serialVersionUID = -987975636197353363L;
	private static Logger log = Logger.getLogger(UserService.class);

	@EJB
	private KursService kursService;

	public User create(User user) {
		try {
			user.setUserCreatedDate(LocalDateTime.now());
			user.setUserModifiedDate(LocalDateTime.now());
			persist(user);
		} catch(Exception e) { 
			log.error(e);
		}
		return user;
	}

	public User create(User user, Long[] kursIds) throws KursNotFoundException {
		for (Long id : kursIds) {
			Kurs kurs = kursService.getKurs(id);
			user.getKannUnterrichten().add(kurs);
		}
		user.setUserCreatedDate(LocalDateTime.now());
		user.setUserModifiedDate(LocalDateTime.now());
		return persist(user);

	}

	public User getUserWithSkills(Long id) {
		// TODO ? --> Korrigieren

		EntityGraph<?> graph = entityManager.getEntityGraph(User.GRAPH_WITH_USER_SKILLS);
		Map<String, Object> hints = new HashMap<String, Object>();
		hints.put("javax.persistence.fetchgraph", graph);
		return this.entityManager.find(User.class, id, hints);
	}

	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public void remove(Long id) throws UserNotFoundException {
		// TODO Auto-generated method stub

	}

	public User getUser(Long id) throws UserNotFoundException {
		return entityManager.find(User.class, id);
	}

	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}


}

