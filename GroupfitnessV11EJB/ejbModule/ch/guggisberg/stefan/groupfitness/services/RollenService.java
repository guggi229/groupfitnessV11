package ch.guggisberg.stefan.groupfitness.services;

import java.io.Serializable;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ch.guggisberg.stefan.groupfitness.entities.Rollen;

@Stateless
@LocalBean
@DeclareRoles({"GroupfitnessAdmin", "Customer", "Admin"})
public class RollenService extends  BaseCrud<Rollen> implements Serializable {

	private static final long serialVersionUID = -2278864342781915360L;

	@PermitAll
	public Rollen getRollen(Long id)  {
		return entityManager.find(Rollen.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@PermitAll
	public List<Rollen> getAllRollen() {
		return entityManager.createNamedQuery(Rollen.QUERY_FIND_ALL_ROLES).getResultList();
	}
}
