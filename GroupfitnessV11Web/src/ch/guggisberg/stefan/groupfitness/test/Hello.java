package ch.guggisberg.stefan.groupfitness.test;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import ch.guggisberg.stefan.groupfitness.exceptions.UserNotFoundException;
import ch.guggisberg.stefan.groupfitness.services.UserServiceRemote;

@ManagedBean
public class Hello {
	
	@EJB
	UserServiceRemote h;
	public String getWorld() throws UserNotFoundException {
		System.out.println("Hey!");
		return h.getUser(3L).getUserName();
	}
}
