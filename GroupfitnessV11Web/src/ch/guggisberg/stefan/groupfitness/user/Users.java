package ch.guggisberg.stefan.groupfitness.user;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import ch.guggisberg.stefan.groupfitness.entities.User;
import ch.guggisberg.stefan.groupfitness.exceptions.UserAlreadyExistsException;
import ch.guggisberg.stefan.groupfitness.services.UserServiceRemote;

@RequestScoped
@Named
public class Users implements Serializable {
	private static final long serialVersionUID = 2145918315776262944L;
	private final String avatarPath ="C:\\Users\\guggi229\\Documents\\avatar\\";
	@EJB
	private UserServiceRemote userService;
	private Part file;
	private User user = new User();
	
	public void addUser() throws UserAlreadyExistsException, IOException {
		user = userService.create(user); // Nach dem persistieren wird das Avatar mit User ID gespeichert
		if (file != null) file.write(avatarPath+ user.getId() + "." + getFileTyp());
		user = null;
		file= null;
	}
	
	private String getFileTyp() {
		return file.getContentType().substring(file.getContentType().indexOf("/")+1);
	}

	public UserServiceRemote getUserService() {
		return userService;
	}

	public void setUserService(UserServiceRemote userService) {
		this.userService = userService;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
