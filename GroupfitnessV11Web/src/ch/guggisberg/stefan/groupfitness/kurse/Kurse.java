package ch.guggisberg.stefan.groupfitness.kurse;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import javax.management.DescriptorKey;

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

	private String kursName;
	private String kursDescDe;
	private String kursDescFr;
	

	private Kurs kurs = new Kurs();

	
	public List<Kurs> getAllKurse() {
		return kursService.getAllKurs();
	}
	public String remove (Kurs k) throws KursNotFoundException {
		kursService.remove(k.getId());
		return "kurse";
	}
	public KursServiceRemote getKursService() {
		return kursService;
	}
	public void setKursService(KursServiceRemote kursService) {
		this.kursService = kursService;
	}

	public void addKurs() throws KursAlreadyExistsException {
		kursService.create(kurs);
	}
	public String getKursName() {
		return kursName;
	}
	public void setKursName(String kursName) {
		this.kursName = kursName;
	}
	public String getKursDescDe() {
		return kursDescDe;
	}
	public void setKursDescDe(String kursDescDe) {
		this.kursDescDe = kursDescDe;
	}
	public String getKursDescFr() {
		return kursDescFr;
	}
	public void setKursDescFr(String kursDescFr) {
		this.kursDescFr = kursDescFr;
	}
	public Kurs getKurs() {
		return kurs;
	}
	public void setKurs(Kurs kurs) {
		this.kurs = kurs;
	}


	
	
	
	

}
