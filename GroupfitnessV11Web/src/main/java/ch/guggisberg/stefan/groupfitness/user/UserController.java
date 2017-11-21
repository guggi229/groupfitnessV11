package ch.guggisberg.stefan.groupfitness.user;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import ch.guggisberg.stefan.groupfitness.entities.Kurs;
import ch.guggisberg.stefan.groupfitness.entities.User;
import ch.guggisberg.stefan.groupfitness.exceptions.KursNotFoundException;
import ch.guggisberg.stefan.groupfitness.exceptions.UserAlreadyExistsException;
import ch.guggisberg.stefan.groupfitness.services.KursService;
import ch.guggisberg.stefan.groupfitness.services.UserService;
import ch.guggisberg.stefan.groupfitness.utils.ImageUtil;

@RequestScoped
@Named
public class UserController implements Serializable {
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
	private Set<Kurs> kannUnterrichten = new HashSet<>();	
	/**
	 * Fügt einen neuen User hinzu und speichert, falls vorhanden, das Avatar im Filesystem.
	 * @throws UserAlreadyExistsException
	 * @throws KursNotFoundException 
	 */
	public void addUser() throws UserAlreadyExistsException, KursNotFoundException {
/*
		Kurs myKurs1 = kursService.getKurs(123L);
		Kurs myKurs2 = kursService.getKurs(124L);

		Set<Kurs> kannUnterrichten2 = new HashSet<>();
		kannUnterrichten2.add(myKurs2);
		kannUnterrichten2.add(myKurs1);
		
		user.setKannUnterrichten(kannUnterrichten2);
*/
		user = userService.create(user, new Long[] {123L, 124L}); // Nach dem persistieren wird das Avatar mit User ID gespeichert

		System.out.println("**************************************");
		System.out.println(user.getId());
/*
		myKurs1.addUser(user);
		myKurs2.addUser(user);

		myKurs1 = kursService.update(myKurs1);
		myKurs2 = kursService.update(myKurs2);
	
		System.out.println("**************************************");
		System.out.println("**************************************");
		System.out.println("**************************************");
		System.out.println("**************************************");
		System.out.println("**************************************");
		System.out.println("**************************************");
		System.out.println("**************************************");
		System.out.println("**************************************");
*/
		
	//	try {
			

		
//		kursService.update(kurs)
//			if (file != null) {
//				file.write(avatarPath+ user.getId() + "." + getFileTyp());
//				ImageUtil.imageResizerFile(new File (avatarPath+ user.getId() + "." + getFileTyp()), 200);
//
//			}
//		} catch (Exception e) {
//			log.error("Es gab beim Speicher ein Problem", e);
//			e.printStackTrace();
//		} finally {
//			user = null;
//			file= null;
//		}	
	}

	public void setKurse(Long[] kurse) throws KursNotFoundException {
		for (Long id : kurse) {
			kannUnterrichten.add(kursService.getKurs(id));
		}
	}



	public Long[] getKurse() {
		return kurse;
	}

	private String getFileTyp() {
		return file.getContentType().substring(file.getContentType().indexOf("/")+1);
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
