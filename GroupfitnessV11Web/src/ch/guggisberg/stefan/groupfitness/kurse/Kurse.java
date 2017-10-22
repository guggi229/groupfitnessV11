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

	private Kurs kurs = new Kurs(); // Inject
	
	public List<Kurs> getAllKurse() {
		return kursService.getAllKurs();
	}
	public String remove (Kurs k) throws KursNotFoundException {
		kursService.remove(k.getId());
		return "kurse";
	}
	public void addKurs() throws KursAlreadyExistsException {
		kursService.create(kurs);
	}
	public Kurs update(Kurs kurs) throws KursNotFoundException {
		return kursService.update(kurs);
	}
	
	// Getter / Setter

	public Kurs getKurs() {
		return kurs;
	}
	public void setKurs(Kurs kurs) {
		this.kurs = kurs;
	}

}
