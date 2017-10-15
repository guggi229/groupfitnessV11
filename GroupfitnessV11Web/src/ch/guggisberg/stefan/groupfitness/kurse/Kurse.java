package ch.guggisberg.stefan.groupfitness.kurse;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.management.DescriptorKey;

import ch.guggisberg.stefan.groupfitness.entities.Kurs;
import ch.guggisberg.stefan.groupfitness.exceptions.KursNotFoundException;
import ch.guggisberg.stefan.groupfitness.exceptions.UserNotFoundException;
import ch.guggisberg.stefan.groupfitness.services.KursServiceRemote;
import ch.guggisberg.stefan.groupfitness.services.UserServiceRemote;

@ManagedBean
public class Kurse {

	@EJB
	private KursServiceRemote kursService;


	public List<Kurs> getAllKurse() {
		return kursService.getAllKurs();
	}
	public String remove (Kurs k) throws KursNotFoundException {
		kursService.remove(k.getId());
		return "kurse";
	}
}
