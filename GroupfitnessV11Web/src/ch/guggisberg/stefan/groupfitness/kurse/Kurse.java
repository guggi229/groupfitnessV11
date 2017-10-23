package ch.guggisberg.stefan.groupfitness.kurse;


import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import ch.guggisberg.stefan.groupfitness.entities.Kurs;
import ch.guggisberg.stefan.groupfitness.exceptions.KursAlreadyExistsException;
import ch.guggisberg.stefan.groupfitness.exceptions.KursNotFoundException;
import ch.guggisberg.stefan.groupfitness.services.KursServiceRemote;

@RequestScoped
@Named
public class Kurse implements Serializable {
	private static final long serialVersionUID = 8012191796192067840L;

	@EJB
	private KursServiceRemote kursService;
	private Part file;
	private Kurs kurs = new Kurs(); // Inject
	
	public List<Kurs> getAllKurse() {
		return kursService.getAllKurs();
	}
	public String remove (Kurs k) throws KursNotFoundException {
		kursService.remove(k.getId());
		return "kurse";
		
	}
	public void addKurs() throws KursAlreadyExistsException, IOException {
		kurs = kursService.create(kurs); // Nach dem persistieren wird das Avatar mit Kurs ID gespeichert
		if (file != null) file.write("C:\\Users\\guggi229\\Documents\\cours\\"+ kurs.getId() + "." + getFileTyp());
		file= null;
		kurs=null;
		
	}
	public Kurs update(Kurs kurs) throws KursNotFoundException {
		return kursService.update(kurs);
	}
	
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
	

}
