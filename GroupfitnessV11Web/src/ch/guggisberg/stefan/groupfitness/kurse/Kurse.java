package ch.guggisberg.stefan.groupfitness.kurse;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.management.DescriptorKey;

import ch.guggisberg.stefan.groupfitness.entities.Kurs;
import ch.guggisberg.stefan.groupfitness.exceptions.UserNotFoundException;
import ch.guggisberg.stefan.groupfitness.services.KursServiceRemote;
import ch.guggisberg.stefan.groupfitness.services.UserServiceRemote;

@ManagedBean
public class Kurse {
	
	@EJB
	private KursServiceRemote kursService;
	

	public String getWorld() {
		
		List<Kurs> kurse= kursService.getAllKurs();
		for (Kurs kurs : kurse) {
			System.out.println(kurs.getKursName());
		}
		return "123";
		
	}
}
