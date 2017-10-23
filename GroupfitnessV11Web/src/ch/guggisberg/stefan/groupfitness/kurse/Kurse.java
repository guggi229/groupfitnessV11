package ch.guggisberg.stefan.groupfitness.kurse;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import javax.management.DescriptorKey;
import javax.servlet.http.Part;

import ch.guggisberg.stefan.groupfitness.entities.Kurs;
import ch.guggisberg.stefan.groupfitness.exceptions.KursAlreadyExistsException;
import ch.guggisberg.stefan.groupfitness.exceptions.KursNotFoundException;
import ch.guggisberg.stefan.groupfitness.exceptions.UserNotFoundException;
import ch.guggisberg.stefan.groupfitness.services.KursService;
import ch.guggisberg.stefan.groupfitness.services.KursServiceRemote;
import ch.guggisberg.stefan.groupfitness.services.UserServiceRemote;

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
		kurs = kursService.create(kurs);
		System.out.println("Kurs ID :" + kurs.getId());
		getFileTyp();
		file.write("C:\\Users\\guggi229\\Documents\\"+ kurs.getId() + "." + getFileTyp());
		kurs=null;
		
	}
	public Kurs update(Kurs kurs) throws KursNotFoundException {
		return kursService.update(kurs);
	}
	
	private String getFileTyp() {
		return file.getContentType().substring(file.getContentType().indexOf("/")+1);
	}
	
//	private static String getFilename(Part part) {
//        for (String cd : part.getHeader("content-disposition").split(";")) {
//  
//        	if (cd.trim().startsWith("filename")) {
//                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
//                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
//            }
//        }
//        return null;
//    }
	
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
