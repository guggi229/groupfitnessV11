package ch.guggisberg.stefan.groupfitness.user;

import java.io.File;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import ch.guggisberg.stefan.groupfitness.base.BaseBean;
import ch.guggisberg.stefan.groupfitness.entities.User;
import ch.guggisberg.stefan.groupfitness.exceptions.KursNotFoundException;
import ch.guggisberg.stefan.groupfitness.exceptions.UserAlreadyExistsException;
import ch.guggisberg.stefan.groupfitness.services.KursService;
import ch.guggisberg.stefan.groupfitness.services.UserService;
import ch.guggisberg.stefan.groupfitness.utils.ImageUtil;

@RequestScoped
@Named
public class UserController extends BaseBean implements Serializable {
	private static final long serialVersionUID = 2145918315776262944L;
	private final String avatarPath ="D:\\Documents\\employee\\";
	private static Logger log = Logger.getLogger(UserController.class);

	@EJB
	private UserService userService;
	@EJB
	private KursService kursService;

	private Part file;
	private User user = new User();
	private Long[] kurse;
	/**
	 * Fügt einen neuen User hinzu und speichert, falls vorhanden, das Avatar im Filesystem.
	 * @throws UserAlreadyExistsException
	 * @throws KursNotFoundException 
	 */
	public void addUser()  {
		
		//Speichern des users
		try {
			user = userService.create(user, kurse); // Nach dem persistieren wird das Avatar mit User ID gespeichert
			showGlobalMessage("info.UserDataSaved", null);
		} 
		catch (KursNotFoundException e) {
			showGlobalErrorMessage("warn.error", null);
			log.error("Es gab beim Speicher ein Problem", e); // Könnte /Sollte man differenzieren.
		}
		catch (Exception e) {
			showGlobalErrorMessage("warn.error", null);
			log.error("Es gab beim Speicher ein Problem", e);
		}
		
		// Speichern des Avatars:
		try {
			if (file != null) {
				file.write(avatarPath+ user.getId() + "." + getFileTyp());
				ImageUtil.imageResizerFile(new File (avatarPath+ user.getId() + "." + getFileTyp()), 200);
			}
		} catch (Exception e) {
			showGlobalErrorMessage("warn.error", null);
			log.error("Es gab beim Speicher ein Problem", e);
			e.printStackTrace();
		} finally {
			user = null;
			file= null;
			kurse=null;
		}	
	}


	public Long[] getKurse() {
		return kurse;
	}


	public void setKurse(Long[] kurse) {
		this.kurse = kurse;
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
	/**
	 * Sucht ein Avatar mit name id. Sollte es nicht vorhanden sein, wird der Pfad des Standard Avatars zurück geliefert.
	 * @param id
	 * @return
	 */
	public String getImgPath(String id) {
		File filejpg = new File(avatarPath, id +".jpeg");
		if (filejpg.exists()) return (avatarPath + id +".jpeg");
		return "/GroupfitnessV11Web/images/avatarneutral.png";

	}
	/**
	 * Geht in den File Content und liest den Typ. 
	 * @return File Typ
	 */
	private String getFileTyp() {
		return file.getContentType().substring(file.getContentType().indexOf("/")+1);
	}

}
