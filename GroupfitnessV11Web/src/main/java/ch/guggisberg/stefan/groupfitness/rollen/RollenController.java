package ch.guggisberg.stefan.groupfitness.rollen;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.log4j.Logger;

import ch.guggisberg.stefan.groupfitness.entities.Rollen;
import ch.guggisberg.stefan.groupfitness.services.RollenService;

@RequestScoped
@Named
public class RollenController implements Serializable {
	private static final long serialVersionUID = 4312906432401722173L;
	private static Logger log = Logger.getLogger(RollenController.class);

	@EJB 
	RollenService rollenService;
	
	public List<Rollen> getAllRollen() {
		return rollenService.getAllRollen();
	}

}
