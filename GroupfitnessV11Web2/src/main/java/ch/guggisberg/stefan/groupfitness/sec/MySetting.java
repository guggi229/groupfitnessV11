package ch.guggisberg.stefan.groupfitness.sec;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

import ch.guggisberg.stefan.groupfitness.services.UserServiceRemote;

@ManagedBean

public class MySetting {
	
	@EJB
	UserServiceRemote usr;

	public void sayHello() {
		usr.sayHello();
	}
}
