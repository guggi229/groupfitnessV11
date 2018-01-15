package ch.guggisberg.stefan.groupfitness.user;

import java.io.File;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.mail.MessagingException;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import ch.guggisberg.stefan.groupfitness.base.BaseBean;
import ch.guggisberg.stefan.groupfitness.entities.Kurs;
import ch.guggisberg.stefan.groupfitness.entities.User;
import ch.guggisberg.stefan.groupfitness.exceptions.KursNotFoundException;
import ch.guggisberg.stefan.groupfitness.exceptions.UserAlreadyExistsException;
import ch.guggisberg.stefan.groupfitness.services.UserService;
import ch.guggisberg.stefan.groupfitness.utils.EmailManager;
import ch.guggisberg.stefan.groupfitness.utils.ImageUtil;
import ch.guggisberg.stefan.groupfitness.utils.PropertiesExporter;

@RequestScoped
@Named
public class UserController extends BaseBean implements Serializable {
	private static final long serialVersionUID = 2145918315776262944L;
	private final String PROPERTY_IMAGE_PATH_COURS = PropertiesExporter.getPropertyImagePathAvatar();
	private final String PROPERTY_IMAGE_PATH_EMPTY_AVATAR = PropertiesExporter.getPropertyImagePathEmptyAvatar();
	private final int PROPERTY_IMAGE_SIZE_AVATAR=PropertiesExporter.getPropertyImageSizeAvatar();

	private static Logger log = Logger.getLogger(UserController.class);

	@EJB
	private UserService userService;

	@EJB
	private EmailManager emailManager;

	
	private Part file;
	private User user = new User();
	private Long[] kurse;
	private Long[] rollen;
	/**
	 * Fügt einen neuen User hinzu und speichert, falls vorhanden, das Avatar im Filesystem.
	 * @throws UserAlreadyExistsException
	 * @throws KursNotFoundException 
	 */
	public void addUser()  {

		//Speichern des users
		try {
			user = userService.create(user, kurse, rollen); // Nach dem persistieren wird das Avatar mit User ID gespeichert
			// Versenden der Welcome Mail
			emailManager.sendEmail(user.getUserEmail(), getText("email.welcomemail.user.subject"), 
					"Ersatz Text", MessageFormat.format(getText("email.welcomemail.user.content"),
							user.getUserVorname(),user.getUserEmail(),user.getUserPassword()));
			showGlobalMessage("info.UserDataSaved", null);
			// Bild speichern
			if (file != null) {
				file.write(PROPERTY_IMAGE_PATH_COURS+ user.getId() + "." + getFileTyp());
				ImageUtil.imageResizerFile(new File (PROPERTY_IMAGE_PATH_COURS+ user.getId() + "." + getFileTyp()), PROPERTY_IMAGE_SIZE_AVATAR);
			}
		} 
		catch (KursNotFoundException e) {
			showGlobalErrorMessage("warn.error", null);
			log.error("Es gab beim Speicher ein Problem", e); // Könnte /Sollte man differenzieren.
		} 
		catch (MessagingException e) {
			showGlobalErrorMessage("warn.error", null);
			log.error("Es gab beim Versenden der Email ein Problem", e);
		}
		catch (Exception e) {
			showGlobalErrorMessage("warn.error", null);
			log.error("Es gab beim Speicher ein Problem", e);
			e.printStackTrace();
		} 
		finally {
			user = null;
			file= null;
			kurse=null;
			rollen=null;
		}	
	}


	public Long[] getKurse() {
		return kurse;
	}

	public void setKurse(Long[] kurse) {
		this.kurse = kurse;
	}

	public Long[] getRollen() {
		return rollen;
	}

	public void setRollen(Long[] rollen) {
		this.rollen = rollen;
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
	 * Sucht ein Avatar mit Name id. Sollte es nicht vorhanden sein, wird der Pfad des Standard Avatars zurück geliefert.
	 * @param id
	 * @return
	 */
	public String getImgPath(String id) {
		File filejpg = new File(PROPERTY_IMAGE_PATH_COURS, id +".jpeg");
		if (filejpg.exists()) return (PROPERTY_IMAGE_PATH_COURS + id +".jpeg");
		return PROPERTY_IMAGE_PATH_EMPTY_AVATAR;

	}
	/**
	 * Geht in den File Content und liest den Typ. 
	 * @return File Typ
	 */
	private String getFileTyp() {
		return file.getContentType().substring(file.getContentType().indexOf("/")+1);
	}

	public String getPROPERTY_IMAGE_PATH_EMPTY_AVATAR() {
		return PROPERTY_IMAGE_PATH_EMPTY_AVATAR;
	}

	


}
