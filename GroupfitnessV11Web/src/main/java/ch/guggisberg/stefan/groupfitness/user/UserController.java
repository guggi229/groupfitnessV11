package ch.guggisberg.stefan.groupfitness.user;

import java.io.File;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import ch.guggisberg.stefan.groupfitness.entities.User;
import ch.guggisberg.stefan.groupfitness.exceptions.UserAlreadyExistsException;
import ch.guggisberg.stefan.groupfitness.services.UserServiceRemote;
import ch.guggisberg.stefan.groupfitness.utils.ImageUtil;

@RequestScoped
@Named
public class UserController implements Serializable {
	private static final long serialVersionUID = 2145918315776262944L;
	private final String avatarPath ="D:\\Documents\\employee\\";
	private static Logger log = Logger.getLogger(UserController.class);
	
	@EJB
	private UserServiceRemote userService;
	private Part file;
	private User user = new User();
	
	/**
	 * Fügt einen neuen User hinzu und speichert, falls vorhanden, das Avatar im Filesystem.
	 * @throws UserAlreadyExistsException
	 */
	public void addUser() throws UserAlreadyExistsException {
		try {
			user = userService.create(user); // Nach dem persistieren wird das Avatar mit User ID gespeichert
			if (file != null) {
				file.write(avatarPath+ user.getId() + "." + getFileTyp());
				ImageUtil.imageResizerFile(new File (avatarPath+ user.getId() + "." + getFileTyp()), 200);

			}
		} catch (Exception e) {
			log.error("Es gab beim Speicher ein Problem", e);
			e.printStackTrace();
		} finally {
			user = null;
			file= null;
		}
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
	public String getImgPath(String id) {
		File filejpg = new File(avatarPath, id +".jpeg");
		if (filejpg.exists()) return (avatarPath + id +".jpeg");
		return "/GroupfitnessV11Web/images/avatarneutral.png";

	}
	
}
