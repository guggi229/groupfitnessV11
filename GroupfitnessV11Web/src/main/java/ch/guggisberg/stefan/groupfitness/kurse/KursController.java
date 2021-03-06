package ch.guggisberg.stefan.groupfitness.kurse;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import ch.guggisberg.stefan.groupfitness.base.BaseBean;
import ch.guggisberg.stefan.groupfitness.entities.Kurs;
import ch.guggisberg.stefan.groupfitness.exceptions.KursAlreadyExistsException;
import ch.guggisberg.stefan.groupfitness.exceptions.KursNotFoundException;
import ch.guggisberg.stefan.groupfitness.services.KursService;
import ch.guggisberg.stefan.groupfitness.utils.ImageUtil;
import ch.guggisberg.stefan.groupfitness.utils.PropertiesExporter;

@RequestScoped
@Named
public class KursController extends BaseBean implements Serializable {
	private static final long serialVersionUID = 8012191796192067840L;
	private final String PROPERTY_IMAGE_PATH_COURS = PropertiesExporter.getPropertyImagePathCours();
	private final int PROPERTY_IMAGE_SIZE_COURS=PropertiesExporter.getPropertyImageSizeCours();
	private static Logger log = Logger.getLogger(KursController.class);

	@EJB
	private KursService kursService;
	private Part file;
	private Kurs kurs = new Kurs(); // Inject
	
	
	/**
	 * 
	 * @return Liefert alle Kurs zurück, die nicht gelöscht wurden.
	 */
	public List<Kurs> getAllKurse() {
		return kursService.getAllKurs();
	}
	
		
	/**
	 * Fügt einen neuen Kurs hinzu und speichert, falls vorhanden, das Bild im Filesystem.
	 * @throws KursAlreadyExistsException
	 */
	public void addKurs() throws KursAlreadyExistsException  {
		try {
			kurs = kursService.create(kurs);								 // Nach dem persistieren wird das Avatar mit Kurs ID gespeichert
			if (file != null) {
				file.write(PROPERTY_IMAGE_PATH_COURS+ kurs.getId() + "." + getFileTyp());
				ImageUtil.imageResizerFile(new File (PROPERTY_IMAGE_PATH_COURS+ kurs.getId() + "." + getFileTyp()), PROPERTY_IMAGE_SIZE_COURS);
			}
			showGlobalMessage("info.UserDataSaved", null);
		}  catch (IOException e) {
			log.error("Es gab beim Speicher ein Problem", e);
			showGlobalErrorMessage("warn.error", null);
			e.printStackTrace();
		}
		finally {
			file= null;
			kurs=null;
		}

	}

	public String editKurs() throws KursNotFoundException {
		try {
			kursService.update(kurs);	
			showGlobalMessage("info.UserDataSaved", null);
			if (file != null) file.write(PROPERTY_IMAGE_PATH_COURS+ kurs.getId() + "." + getFileTyp());
			ImageUtil.imageResizerFile(new File (PROPERTY_IMAGE_PATH_COURS+ kurs.getId() + "." + getFileTyp()), 300);
		}  catch (IOException e) {
			log.error("Es gab beim Speicher ein Problem", e);
			showGlobalErrorMessage("warn.error", null);
			e.printStackTrace();
		}
		finally {
			file= null;
			kurs=null;
		}
		return "viewCoursTyp";
	}

	public Kurs update(Kurs kurs) throws KursNotFoundException {
		return kursService.update(kurs);
	}

	public void getKurs(Long id) throws KursNotFoundException {
		kurs= kursService.getKurs(id); 
	}

	public String modKurs (Kurs k) throws KursNotFoundException {
		kurs = kursService.getKurs(k.getId());
		return "groupfitnessAdmin/modCours";
	}
	public void remove (Kurs k) throws KursNotFoundException {

		kursService.remove(k.getId());
	}

	// Utils

	private String getFileTyp() {
		return file.getContentType().substring(file.getContentType().indexOf("/")+1);
	}

	// Getter / Setter

	public Kurs getKurs() {
		return kurs;
	}
	public void setKurs(Kurs kurs) {
		this.kurs = kurs;
	}
	public Part getFile() {
		return file;
	}
	public void setFile(Part file) {
		this.file = file;
	}
	public String getImgPath(String id) {
		File filejpg = new File(PROPERTY_IMAGE_PATH_COURS, id +".jpeg");
		if (filejpg.exists()) return (PROPERTY_IMAGE_PATH_COURS + id +".jpeg");
		return "/GroupfitnessV11Web/images/placeholder-image.png";
	}
}
