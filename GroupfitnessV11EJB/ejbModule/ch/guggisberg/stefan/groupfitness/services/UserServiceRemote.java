package ch.guggisberg.stefan.groupfitness.services;

import java.util.List;

import ch.guggisberg.stefan.groupfitness.entities.User;
import ch.guggisberg.stefan.groupfitness.exceptions.UserAlreadyExistsException;
import ch.guggisberg.stefan.groupfitness.exceptions.UserNotFoundException;

public interface UserServiceRemote {

	public User create(User user)throws UserAlreadyExistsException;
	public User update(User user) throws UserNotFoundException;
	public void remove(Long id) throws UserNotFoundException;
	public User getUser(Long id) throws UserNotFoundException;
	public User getUserWithSkills(Long id) throws UserNotFoundException;
	public List<User> getAllUser();
	public void sayHello();
	
}

