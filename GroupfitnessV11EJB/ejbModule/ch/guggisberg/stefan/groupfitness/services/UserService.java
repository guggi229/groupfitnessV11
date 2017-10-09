package ch.guggisberg.stefan.groupfitness.services;



import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.guggisberg.stefan.groupfitness.entities.User;
import ch.guggisberg.stefan.groupfitness.exceptions.UserAlreadyExistsException;
import ch.guggisberg.stefan.groupfitness.exceptions.UserNotFoundException;


@Stateless
@Remote(UserServiceRemote.class)
public class UserService implements UserServiceRemote {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public User create(User user) throws UserAlreadyExistsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User user) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Long id) throws UserNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public User getUser(Long id) throws UserNotFoundException {
		return em.find(User.class, id);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

}

