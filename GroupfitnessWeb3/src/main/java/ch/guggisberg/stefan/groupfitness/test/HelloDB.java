package ch.guggisberg.stefan.groupfitness.test;

import javax.ejb.EJB;

import ch.guggisberg.stefan.groupfitness.exceptions.UserNotFoundException;
import ch.guggisberg.stefan.groupfitness.services.UserServiceRemote;

public class HelloDB {

	@EJB
	private UserServiceRemote us;
	
	public void hello() throws UserNotFoundException {
		System.out.println(us.getUser(3L));
	}
}
